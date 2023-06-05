package com.suivius.rest.controllers;

import com.suivius.rest.dto.ReferenceDto;
import com.suivius.services.CableTypeService;
import com.suivius.services.ConnectionTypeService;
import com.suivius.services.EquipmentTypeService;
import com.suivius.services.SiteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("suivius/api/v1/references")
public class ReferenceController {
    @Autowired
    ConnectionTypeService connectionTypeService;
    @Autowired
    SiteTypeService siteTypeService;
    @Autowired
    EquipmentTypeService equipmentTypeService;
    @Autowired
    CableTypeService cableTypeService;


    @GetMapping
    public ReferenceDto getReferences(){
        ReferenceDto dto = new ReferenceDto();

        dto.cableTypes = cableTypeService.getAll();
        dto.siteTypes = siteTypeService.getAll();
        dto.equipmentTypes = equipmentTypeService.getAll();
        dto.connectionTypes = connectionTypeService.getAll();

        return  dto;
    }

}
