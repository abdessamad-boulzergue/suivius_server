package com.suivius.services;

import com.suivius.models.Document;
import com.suivius.models.Project;
import com.suivius.models.ProjectDocumentType;
import com.suivius.models.ProjectDocuments;
import com.suivius.repo.DocumentRepository;
import com.suivius.repo.ProjectDocumentsRepository;
import com.suivius.resource.IResource;
import com.suivius.resource.Resource;
import com.suivius.resource.ResourceStorageService;
import com.suivius.rest.dto.NewDocumentDto;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.suivius.helpers.Constants.IMAGE_JPEG;

@Service
public class DocumentService {

    private DocumentRepository documentRepository;
    private ProjectDocumentsRepository projectDocumentsRepository;

    ResourceStorageService resourceLoader;

    public  DocumentService(DocumentRepository documentRepository,
                            ProjectDocumentsRepository projectDocumentsRepository, ResourceStorageService resourceLoader){
        this.documentRepository =documentRepository;
        this.projectDocumentsRepository =projectDocumentsRepository;
        this.resourceLoader =resourceLoader;
    }

    public void save(){
    }
    private void writeResourceToFile(String fileName, String content)  {
        IResource document = new Resource(fileName,fileName, content.getBytes().length, new Date());
        resourceLoader.saveResource(document, content.getBytes());
    }

    public void addDocument(Project project, NewDocumentDto documentDto) {

        if(project !=null){

            Document doc = new Document();
            doc.setType(documentDto.type);
            doc.setTitle("new ".concat(documentDto.type));
            documentRepository.save(doc);
            writeResourceToFile(doc.getId().toString(),documentDto.content);

            ProjectDocuments documents = new ProjectDocuments();
            documents.setProject(project);
            documents.setDocument(doc);
            projectDocumentsRepository.save(documents);
        }

    }

    public void addDocument(Project project, MultipartFile file, String type) {
        if(project !=null){
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Document doc = new Document();
            doc.setType(file.getContentType());
            doc.setTitle(fileName);
            documentRepository.save(doc);
            try {
                writeResourceToFile(fileName,file.getResource().getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            ProjectDocuments documents = new ProjectDocuments();
            documents.setProject(project);
            documents.setDocument(doc);
            documents.setType(getType(type));
            projectDocumentsRepository.save(documents);
        }
    }
    private ProjectDocumentType getType(String type){
        try {
            return ProjectDocumentType.valueOf(type);
        }catch (Exception ex){
            return ProjectDocumentType.UNKOWN;
        }

    }
    private void writeResourceToFile(String fileName, InputStream inputStream) throws IOException {
        byte[] bytes = inputStream.readAllBytes();
        IResource document = new Resource(fileName,fileName, bytes.length, new Date());
        resourceLoader.saveResource(document, bytes);
    }
    public List<ProjectDocuments> getProjectDocuments(Long projectId){
       return projectDocumentsRepository.findByProjectId(projectId);
    }
    public List<Document> getDocumentsByProjectId(Long projectId){
        List<ProjectDocuments> projectDocs=  projectDocumentsRepository.findByProjectId(projectId);
        List<Document> documents = new ArrayList<>();
        if(projectDocs!=null ){
            documents=  projectDocs.stream().map(doc->doc.getDocument())
                    .collect(Collectors.toList());
        }
        return documents;
    }

    private byte[] readResourceFromFile(String fileName ) throws IOException {
        return resourceLoader.loadContent(fileName);
    }
    public byte[] getContent(String fileName)  {
        try {
            return readResourceFromFile(fileName);
        } catch (IOException e) {
            return new byte[]{};
        }
    }

    public boolean isImage(Document document) {
        return document!=null && document.getType()!=null && IMAGE_JPEG.equals(document.getType());
    }

    public String encodeContent(Document document) {
        byte[] docBytes = getContent(document.getTitle());
        if(docBytes!=null &&  isImage(document) ) {
            return  "data:image/jpeg;base64,"+ Base64.getEncoder().encodeToString(docBytes);
        }else if(docBytes!=null){
            return  ""+ Base64.getEncoder().encodeToString(docBytes);
        }else{
            return "";
        }
    }
}
