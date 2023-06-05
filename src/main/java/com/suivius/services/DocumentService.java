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
import java.util.Date;

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
            documents.setType(ProjectDocumentType.valueOf(type));
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
}
