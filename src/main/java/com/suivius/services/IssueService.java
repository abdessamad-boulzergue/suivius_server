package com.suivius.services;

import com.suivius.models.Project;
import com.suivius.models.StepStatus;
import com.suivius.repo.Issue;
import com.suivius.repo.IssueRepository;
import com.suivius.rest.dto.IssueDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {
    private final IssueRepository issueRepository;


    public  IssueService(IssueRepository issueRepository){
        this.issueRepository = issueRepository;
    }

    public List<Issue> getProjectIssues(Long projectId){
        return  getProjectIssues(projectId,false);
    }
    public List<Issue> getProjectIssues(Long projectId, boolean closed){
        return  this.issueRepository.findByClosedAndProjectId(closed,projectId);
    }

    public Issue addIssue(Project project, StepStatus status, IssueDto issueDto) {
        Issue issue = new Issue();
        issue.setClosed(false);
        issue.setDescription(issueDto.description);
        issue.setProject(project);
        issue.setStepStatus(status);
        return this.issueRepository.save(issue);
    }
    public Issue addIssue(Project project, StepStatus status, String description) {
        Issue issue = new Issue();
        issue.setClosed(false);
        issue.setDescription(description);
        issue.setProject(project);
        issue.setStepStatus(status);
        return this.issueRepository.save(issue);
    }

    public void closeIssue(Issue issue) {
        if(issue!=null){
            issue.setClosed(true);
            this.issueRepository.save(issue);
        }
    }
}
