package com.suivius.services;

import com.suivius.models.Article;
import com.suivius.models.Project;
import com.suivius.repo.ArticleRepository;
import com.suivius.rest.dto.ArticleDto;
import com.suivius.rest.dto.ProjectDto;
import com.suivius.rest.mappers.ArticleMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    ArticleRepository articleRepository;
    ArticleMapper articleMapper;

    public ArticleService(  ArticleRepository articleRepository, ArticleMapper articleMapper){
        this.articleRepository =articleRepository;
        this.articleMapper=articleMapper;
    }

    public List<ArticleDto> getAll(){
        List<Article> articles = articleRepository.findAll();
        return articles.stream().map(art-> articleMapper.toDTO(art))
                .collect(Collectors.toList());
    }

    public ArticleDto getByTitle(String title){
       Article article = articleRepository.findByTitle(title);
        return  articleMapper.toDTO(article);
    }
    public Article save(Article article){
        return  articleRepository.save(article);
    }

    public Article getById(Long articleId) {
        Optional<Article> article = articleRepository.findById(articleId);
        return  article.isPresent()? article.get() : null;
    }
}
