package com.suivius.services;


import com.suivius.models.CableType;
import com.suivius.repo.CableTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CableTypeService {

    CableTypeRepository cableTypeRepository;

    public  CableTypeService(CableTypeRepository cableTypeRepository){
        this.cableTypeRepository =cableTypeRepository;
    }

    public List<CableType> getAll(){
        return cableTypeRepository.findAll();
    }

}
