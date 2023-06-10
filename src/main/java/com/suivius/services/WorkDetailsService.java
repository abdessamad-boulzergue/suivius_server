package com.suivius.services;

import com.suivius.exceptions.ResourceNotFoundException;
import com.suivius.models.Project;
import com.suivius.models.ProjectWorkDetails;
import com.suivius.models.WorkInfo;
import com.suivius.repo.WorkDetailsRepository;
import com.suivius.repo.WorkInfoRepository;
import com.suivius.rest.dto.ProjectWorkDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkDetailsService {

    @Autowired
    WorkDetailsRepository workDetailsRepository;

    @Autowired
    WorkInfoRepository workInfoRepository;

    public void save(Project project,ProjectWorkDetailsDto dto){
        WorkInfo workInfo = workInfoRepository.findById(dto.workInfoId)
                .orElseThrow(()-> new ResourceNotFoundException("workInfo not found with id "+dto.workInfoId));
        ProjectWorkDetails projectWorkDetails = workDetailsRepository.findByInfoIdAndProjectId(workInfo.getId(),project.getId());
        if(projectWorkDetails==null) {
             projectWorkDetails = new ProjectWorkDetails();
            projectWorkDetails.setProject(project);
            projectWorkDetails.setInfo(workInfo);
        }
        projectWorkDetails.setValue(dto.value);
        workDetailsRepository.save(projectWorkDetails);
    }

    public void save(Project project,List<ProjectWorkDetailsDto> workDetails) {
        if(workDetails!=null && workDetails.size()>0){
            workDetails.forEach(workDt->{
                save(project,workDt);
            });
        }
    }
}
