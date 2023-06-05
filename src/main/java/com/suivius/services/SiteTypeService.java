package com.suivius.services;


import com.suivius.models.SiteType;
import com.suivius.repo.SiteTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteTypeService {

    SiteTypeRepository siteTypeRepository;

    public SiteTypeService(SiteTypeRepository siteTypeRepository){
        this.siteTypeRepository =siteTypeRepository;
    }

    public List<SiteType> getAll(){
        return siteTypeRepository.findAll();
    }

}
