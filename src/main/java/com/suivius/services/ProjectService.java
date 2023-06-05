package com.suivius.services;

import com.suivius.models.Article;
import com.suivius.models.BOQ;
import com.suivius.models.Tss;
import com.suivius.repo.BOQRepository;
import com.suivius.rest.dto.NewDocumentDto;
import com.suivius.rest.dto.PostBoqDto;
import com.suivius.rest.dto.PostTssDto;
import com.suivius.rest.mappers.ProjectMapper;
import com.suivius.models.Project;
import com.suivius.repo.ProjectRepository;
import com.suivius.rest.dto.ProjectDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final TssService tssService;
    private final DocumentService documentService;
    private final WorkDetailsService workDetailsService;
    private final ArticleService articleService;
    private final BOQRepository boqRepository;
    private final StepStatusService stepStatusService;


    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper,
                          TssService tssService,DocumentService documentService,
                          WorkDetailsService workDetailsService,BOQRepository boqRepository,
                          ArticleService articleService,StepStatusService stepStatusService) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.tssService =tssService;
        this.documentService=documentService;
        this.workDetailsService =workDetailsService;
        this.boqRepository = boqRepository;
        this.articleService=articleService;
        this.stepStatusService =stepStatusService;
    }

    public List<ProjectDto> getAll(){
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(p-> projectMapper.toDTO(p))
                .collect(Collectors.toList());
    }

    public List<ProjectDto> getUserProjects(Long userId) {
        List<Project> projects = projectRepository.findAffectedToUser(userId);
        return projects.stream().map(p-> projectMapper.toDTO(p))
                .collect(Collectors.toList());
    }

    public Project get(Long projectId) {
        Optional<Project> optProject = projectRepository.findById(projectId);
        if(optProject.isPresent())
            return  optProject.get() ;
        else
            return null;
    }


    public void preValidateTss(Long project_id, PostTssDto tssDto) {
        Project project = get(project_id);
        Tss tss = tssService.save(tssDto);
        project.setTss(tss);
        project.setStepStatus(stepStatusService.getWaitingValidationTSS());
        this.workDetailsService.save(project,tssDto.workDetails);
        projectRepository.save(project);

    }
    public void validateTss(Long project_id) {
        Project project = get(project_id);

        project.setStepStatus(stepStatusService.getWaitingValidationAPD());
        projectRepository.save(project);

    }
    public void setTss(Long project_id, PostTssDto tssDto) {
        Project project = get(project_id);
        Tss tss = tssService.save(tssDto);
        project.setTss(tss);
        project.setStepStatus(stepStatusService.getPreValidationTSS());
        this.workDetailsService.save(project,tssDto.workDetails);
        projectRepository.save(project);

    }

    public void addDocument(Long projectId, NewDocumentDto documentDto) {
        Project project = this.get(projectId);
        documentService.addDocument(project,documentDto);
    }

    public void addDocument(Long projectId, MultipartFile file,String type) {
        Project project = this.get(projectId);
        documentService.addDocument(project,file, type);
    }

    public void setBoq(Long project_id, PostBoqDto boqDto) {
        Project project = get(project_id);
        boqDto.details.forEach(boq -> {
            BOQ entityBoq =boqRepository.findByArticleIdAndProjectId(boq.articleId,project_id);
            if(entityBoq!=null){
                entityBoq.setQuantity(boq.quantity);
                this.boqRepository.save(entityBoq);
            }else{
                entityBoq = (entityBoq==null) ? new BOQ() : entityBoq;
                Article article =articleService.getById(boq.articleId);
                entityBoq.setArticle(article);
                entityBoq.setProject(project);
                this.boqRepository.save(entityBoq);
            }

        });
    }
}
