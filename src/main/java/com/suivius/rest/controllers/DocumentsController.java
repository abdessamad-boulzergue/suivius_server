package com.suivius.rest.controllers;

import com.suivius.models.Document;
import com.suivius.rest.dto.DocumentContentDto;
import com.suivius.rest.dto.DocumentContentForUserDto;
import com.suivius.rest.dto.NewDocumentDto;
import com.suivius.services.DocumentService;
import com.suivius.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("suivius/api/v1/documents")
public class DocumentsController {

    @Autowired
    ProjectService projectService;

    @Autowired
    DocumentService documentService;


    @PostMapping("/project/{projectId}")
    public void addDocuments(@PathVariable Long projectId, @RequestBody NewDocumentDto documentDto){
        projectService.addDocument(projectId,documentDto);
    }

    @GetMapping("/project/{projectId}")
    public List<Document> getDocuments(@PathVariable Long projectId){
        return documentService.getDocumentsByProjectId(projectId);
    }
    @GetMapping("/project/{projectId}/content")
    public List<DocumentContentDto> getDocumentsContent(@PathVariable Long projectId){
        return projectService.getDocumentsContent(projectId);
    }

    @GetMapping("/projects/user/{userId}/content")
    public List<DocumentContentForUserDto> getDocumentsContentForUser(@PathVariable Long userId){
        return projectService.getDocumentsContentForUser(userId);
    }

    @PostMapping("/project/{projectId}/files/{type}")
    public ResponseEntity<String> uploadFiles(@PathVariable Long projectId,@PathVariable String type,@RequestPart("files") MultipartFile[] files) {
        for (MultipartFile file : files) {
            projectService.addDocument(projectId,file,type);
        }

        return ResponseEntity.ok("Files uploaded successfully");
    }
}
