package com.suivius.rest.controllers;

import com.suivius.rest.dto.NewDocumentDto;
import com.suivius.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("suivius/api/v1/documents")
public class DocumentsController {

    @Autowired
    DocumentService documentService;

    @PostMapping("/project/{projectId}")
    public void addDocuments(@PathVariable Long projectId, @RequestBody NewDocumentDto documentDto){
        documentService.addDocument(projectId,documentDto);
    }
}
