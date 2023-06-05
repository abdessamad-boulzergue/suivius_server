package com.suivius.rest.controllers;

import com.suivius.rest.dto.NewDocumentDto;
import com.suivius.services.DocumentService;
import com.suivius.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("suivius/api/v1/documents")
public class DocumentsController {

    @Autowired
    ProjectService projectService;

    @PostMapping("/project/{projectId}")
    public void addDocuments(@PathVariable Long projectId, @RequestBody NewDocumentDto documentDto){
        projectService.addDocument(projectId,documentDto);
    }
    @PostMapping("/project/{projectId}/files/{type}")
    public ResponseEntity<String> uploadFiles(@PathVariable Long projectId,@PathVariable String type,@RequestPart("files") MultipartFile[] files) {
        for (MultipartFile file : files) {
            projectService.addDocument(projectId,file,type);

        }

        return ResponseEntity.ok("Files uploaded successfully");
    }
}
