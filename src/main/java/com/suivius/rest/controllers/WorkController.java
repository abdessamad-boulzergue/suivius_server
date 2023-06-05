package com.suivius.rest.controllers;

import com.suivius.models.WorkInfo;
import com.suivius.services.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("suivius/api/v1/work")
public class WorkController {

    @Autowired
    WorkService workService;

    @GetMapping("info")
    public List<WorkInfo> getWorkInfo(){
        return  workService.getWorkInfo();
    }
}
