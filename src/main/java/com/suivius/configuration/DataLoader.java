package com.suivius.configuration;

import com.suivius.models.*;
import com.suivius.repo.StepRepository;
import com.suivius.rest.dto.ArticleDto;
import com.suivius.services.ArticleService;
import com.suivius.services.ProjectService;
import com.suivius.services.StepStatusService;
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

    @Autowired
    ProjectService projectService;

    @Autowired
    StepStatusService stepStatusService;

    @Autowired
    StepRepository stepRepository;

    @Override
    public void run(String... args) throws Exception {

    }
}

