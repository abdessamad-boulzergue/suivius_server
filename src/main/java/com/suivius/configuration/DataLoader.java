package com.suivius.configuration;

import com.suivius.models.Article;
import com.suivius.models.Unit;
import com.suivius.models.Units;
import com.suivius.rest.dto.ArticleDto;
import com.suivius.services.ArticleService;
import com.suivius.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UnitService unitService;

    @Autowired
    ArticleService articleService;

    @Override
    public void run(String... args) throws Exception {

        Unit unit = unitService.getUnite(Units.Kg.name());
        if(unit==null){
            unit=new Unit();
            unit.setTitle(Units.Kg.name());
             unitService.save(unit);
        }
        String articleTitle="Article 1";
        ArticleDto articleDto=articleService.getByTitle(articleTitle);
        if(articleDto==null){
            Article article = new Article();
            article.setUnit(unit);
            article.setTitle(articleTitle);
            articleService.save(article);
        }

    }
}

