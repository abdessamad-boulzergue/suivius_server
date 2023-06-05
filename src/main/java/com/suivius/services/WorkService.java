package com.suivius.services;

import com.suivius.models.WorkInfo;
import com.suivius.repo.WorkInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkService {

    @Autowired
    WorkInfoRepository workInfoRepository;

    public List<WorkInfo> getWorkInfo(){
        return workInfoRepository.findAll();
    }

}
