package com.suivius.services;

import com.suivius.models.Document;
import com.suivius.models.Project;
import com.suivius.models.ProjectDocuments;
import com.suivius.repo.DocumentRepository;
import com.suivius.repo.ProjectDocumentsRepository;
import com.suivius.resource.IResource;
import com.suivius.resource.Resource;
import com.suivius.resource.ResourceStorageService;
import com.suivius.rest.dto.NewDocumentDto;
import com.suivius.rest.dto.ProjectDto;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DocumentService {

    private DocumentRepository documentRepository;
    private ProjectDocumentsRepository projectDocumentsRepository;
    private ProjectService projectService;

    ResourceStorageService resourceLoader;

    public  DocumentService(DocumentRepository documentRepository,  ProjectService projectService,
                            ProjectDocumentsRepository projectDocumentsRepository, ResourceStorageService resourceLoader){
        this.documentRepository =documentRepository;
        this.projectService =projectService;
        this.projectDocumentsRepository =projectDocumentsRepository;
        this.resourceLoader =resourceLoader;
    }

    public void save(){
    }
    private void writeResourceToFile(String fileName, String content)  {
        IResource document = new Resource(fileName,fileName, content.getBytes().length, new Date());
        resourceLoader.saveResource(document, content.getBytes());
    }

    public void addDocument(Long projectId, NewDocumentDto documentDto) {

        Project project = this.projectService.get(projectId);
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
}
