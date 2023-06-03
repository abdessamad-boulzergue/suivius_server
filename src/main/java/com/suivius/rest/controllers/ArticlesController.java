package com.suivius.rest.controllers;

import com.suivius.rest.dto.ArticleDto;
import com.suivius.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("suivius/api/v1/articles")
public class ArticlesController {

    @Autowired
    ArticleService articleService;

    @GetMapping
    public List<ArticleDto> getAll(){
        return articleService.getAll();
    }

}
