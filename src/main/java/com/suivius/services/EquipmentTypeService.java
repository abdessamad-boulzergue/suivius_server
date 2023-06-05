package com.suivius.services;


import com.suivius.models.EquipmentType;
import com.suivius.repo.EquipmentTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentTypeService {

    EquipmentTypeRepository equipmentTypeRepository;

    public EquipmentTypeService(EquipmentTypeRepository equipmentTypeRepository){
        this.equipmentTypeRepository =equipmentTypeRepository;
    }

    public List<EquipmentType> getAll(){
        return equipmentTypeRepository.findAll();
    }

}
