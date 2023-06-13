package com.suivius.services;

import com.suivius.models.*;
import com.suivius.repo.*;
import com.suivius.rest.dto.*;
import com.suivius.rest.mappers.ProjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
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
    private final IssueService issueService;
    private final StaffService staffService;
    private final WorkToolService workToolService;
    private final ProjectAuthorizationRepository authorizationRepository;
    private final HistoryService historyService;

    private final LocalisationRepository localisationRepository;

    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper,
                          TssService tssService,DocumentService documentService,
                          WorkDetailsService workDetailsService,BOQRepository boqRepository,
                          ArticleService articleService,StepStatusService stepStatusService,
                          IssueService issueService,ProjectAuthorizationRepository authorizationRepository,
                          StaffService staffService,WorkToolService workToolService,HistoryService historyService,
                          LocalisationRepository localisationRepository) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.tssService =tssService;
        this.documentService=documentService;
        this.workDetailsService =workDetailsService;
        this.boqRepository = boqRepository;
        this.articleService=articleService;
        this.stepStatusService =stepStatusService;
        this.issueService = issueService;
        this.authorizationRepository =authorizationRepository;
        this.staffService =staffService;
        this.workToolService =workToolService;
        this.historyService = historyService;
        this.localisationRepository = localisationRepository;
    }

    public List<ProjectDto> getAll(){
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(p-> projectMapper.toDTO(p))
                .collect(Collectors.toList());
    }

    public List<ProjectDto> getUserProjects(Long userId) {
        List<Project> projects = projectRepository.findAffectedToUser(userId);
        return projects.stream().map(p-> {

                    ProjectDto dto = projectMapper.toDTO(p);
                    dto.setSquad(staffService.getSquad(p.getId()));
                    dto.setToolsUsage(workToolService.getToolsUsage(p.getId()));
                    return  dto;

                })
                .collect(Collectors.toList());
    }

    public Project get(Long projectId) {
        Optional<Project> optProject = projectRepository.findById(projectId);
        if(optProject.isPresent())
            return  optProject.get() ;
        else
            return null;
    }


    public Project preValidateTss(Long project_id, PostTssDto tssDto) {
        Project project = get(project_id);
        Tss tss = tssService.save(tssDto);
        project.setTss(tss);
        project.setStepStatus(stepStatusService.getWaitingValidationTSS());
        this.workDetailsService.save(project,tssDto.workDetails);
        projectRepository.save(project);

        return project;
    }
    public Project validateTss(Long project_id) {
        Project project = get(project_id);
        project.setStepStatus(stepStatusService.getWaitingAPD());
        projectRepository.save(project);
        return project;
    }
    public Project preValidateAPD(Long project_id) {
        Project project = get(project_id);
        project.setStepStatus(stepStatusService.getWaitingValidationAPD());
        projectRepository.save(project);
        return project;
    }
    public Project validateAPD(Long project_id) {
        Project project = get(project_id);
        project.setStepStatus(stepStatusService.getAuthorizationWaiting());
        project.setStep(project.getStepStatus().getStep());
        projectRepository.save(project);
        return project;
    }

    public Project rejectTss(Long project_id, String motif) {
        Project project = get(project_id);
        StepStatus nextStatus  =stepStatusService.getWaitingMAJTSS();
        project.setStepStatus(nextStatus);
        issueService.addIssue(project,nextStatus,motif);
        projectRepository.save(project);
        return project;
    }
    public Project rejectAPD(Long project_id, String motif) {
        Project project = get(project_id);
        StepStatus nextStatus  =stepStatusService.getWaitingMAJAPD();
        project.setStepStatus(nextStatus);
        issueService.addIssue(project,nextStatus,motif);
        projectRepository.save(project);
        return project;
    }

    public Project setTss(Long project_id, PostTssDto tssDto) {
        Project project = get(project_id);
        Tss tss = tssService.save(tssDto);
        project.setTss(tss);
        project.setStepStatus(stepStatusService.getPreValidationTSS());
        this.workDetailsService.save(project,tssDto.workDetails);
        projectRepository.save(project);
        return project;
    }

    public void addDocument(Long projectId, NewDocumentDto documentDto) {
        Project project = this.get(projectId);
        documentService.addDocument(project,documentDto);
    }

    public void addDocument(Long projectId, MultipartFile file,String type) {
        Project project = this.get(projectId);
        documentService.addDocument(project,file, type);
    }

    public Project setBoq(Long project_id, PostBoqDto boqDto) {
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
        project.setStepStatus(stepStatusService.getPreValidationAPD());
        return project;
    }


    public List<ProjectDocuments> getProjectDocuments(Long projectId) {
        return documentService.getProjectDocuments(projectId);
    }

    public List<DocumentContentForUserDto> getDocumentsContentForUser(Long userId) {
        List<Project> projects = projectRepository.findAffectedToUser(userId);
        return projects.stream().map(proj->{
            DocumentContentForUserDto userProjectDocs = new DocumentContentForUserDto();
            List<ProjectDocuments> docProj = documentService.getProjectDocuments(proj.getId());
            userProjectDocs.projectId=proj.getId();
            userProjectDocs.documentsContent = docProj.stream().map(doc -> {
                DocumentContentDto contentDto = new DocumentContentDto();
                 contentDto.content =documentService.encodeContent(doc.getDocument());
                contentDto.title = doc.getDocument().getTitle();
                if(doc.getType()!=null)
                contentDto.type = doc.getType().name();
                return contentDto;
            }).collect(Collectors.toList());

            return userProjectDocs;
        }).collect(Collectors.toList());


    }
    public List<DocumentContentDto> getDocumentsContent(Long projectId) {

        return getProjectDocuments(projectId).stream().map(docProj -> {
            DocumentContentDto contentDto = new DocumentContentDto();
            byte[] docBytes = documentService.getContent(docProj.getDocument().getTitle());
            contentDto.content = Base64.getEncoder().encodeToString(docBytes);
            contentDto.title = docProj.getDocument().getTitle();
            contentDto.type = docProj.getType().name();
            return contentDto;
        }).collect(Collectors.toList());

    }
    public void endIssue(Long projectId){
        List<Issue> issues = this.issueService.getProjectIssues(projectId, false);
        if(issues!=null){
            issues.forEach(issue -> {
                this.issueService.closeIssue(issue);
            });
        }
    }
    public Issue addIssue(Long projectId, IssueDto issueDto) {
        Optional<Project> optProject = this.projectRepository.findById(projectId);
        Optional<StepStatus> optStepStatus = this.stepStatusService.findById(issueDto.stepStatusId);
        if(optProject.isPresent() && optStepStatus.isPresent()){
            return this.issueService.addIssue(optProject.get(),optStepStatus.get(),issueDto);
        }

        return null;
    }

    public void saveProject(Project project) {
        projectRepository.save(project);
    }

    public Project startStudy(Long project_id, PostStartStudyDto startStudyDto) {
        Project project = get(project_id);
        project.setStepStatus(stepStatusService.getStartStudy());
        projectRepository.save(project);

        this.historyService.addHistory(project,stepStatusService.getNotStarted(),project.getStepStatus(),startStudyDto.deviceId,null);
        return project;
    }

    public Project startTss(Long project_id,  PostStartTssDto startTssDto) {
        Project project = get(project_id);
        project.setStepStatus(stepStatusService.getTSSEdition());
        projectRepository.save(project);

        Localisation localisation = new Localisation();
        localisation.setLat(startTssDto.latitude);
        localisation.setLng(startTssDto.longitude);
        localisationRepository.save(localisation);

        this.historyService.addHistory(project,stepStatusService.getStartStudy(),project.getStepStatus(),startTssDto.deviceId,localisation);

        return project;
    }

    public Authorization addAuthorization(Long project_id, AuthorizationDto authorizationDto) {
        Project project = get(project_id);
        if(project!=null){

            Authorization authorization = project.getAuthorization();
            if(authorization==null) authorization = new Authorization();
            authorization.setDateCommission(authorizationDto.dateCommission);
            authorization.setDateDecision(authorizationDto.dateDecision);
            authorization.setDateDemand(authorizationDto.dateDemand);
            authorization.setDatePayment(authorizationDto.datePayment);
            authorization.setDateSign(authorizationDto.dateSign);
            this.authorizationRepository.save(authorization);
            project.setAuthorization(authorization);
            projectRepository.save(project);

            return  authorization;

        }
        return null;
    }
}
