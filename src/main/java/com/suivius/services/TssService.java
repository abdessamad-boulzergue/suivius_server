package com.suivius.services;

import com.suivius.exceptions.ResourceNotFoundException;
import com.suivius.models.*;
import com.suivius.repo.*;
import com.suivius.rest.dto.PostTssDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TssService {

    private final TssRepository tssRepository;
    private final CableTypeRepository cableTypeRepository;
    private final SiteTypeRepository siteTypeRepository;
    private final ConnectionTypeRepository connectionTypeRepository;
    private final EquipmentTypeRepository equipmentTypeRepository;

    public  TssService( CableTypeRepository cableTypeRepository,TssRepository tssRepository,
                        SiteTypeRepository siteTypeRepository ,ConnectionTypeRepository connectionTypeRepository,
                        EquipmentTypeRepository equipmentTypeRepository){
        this.cableTypeRepository =cableTypeRepository;
        this.tssRepository=tssRepository;
        this.siteTypeRepository=siteTypeRepository;
        this.connectionTypeRepository =connectionTypeRepository;
        this.equipmentTypeRepository =equipmentTypeRepository;

    }
    public Tss save(PostTssDto tssDto) {

        Tss tss = new Tss();
        CableType optCable = cableTypeRepository.findById(tssDto.cableTypeId)
                .orElseThrow(()->new ResourceNotFoundException("CableType not found , id = "+tssDto.cableTypeId));
        SiteType optSite = siteTypeRepository.findById(tssDto.siteTypeId)
                .orElseThrow(()->new ResourceNotFoundException("siteType not found , id = "+tssDto.siteTypeId));

        ConnectionType optConnection = connectionTypeRepository.findById(tssDto.connectionTypeId)
                .orElseThrow(()->new ResourceNotFoundException("connectionType not found , id = "+tssDto.connectionTypeId));

        EquipmentType optEquip = equipmentTypeRepository.findById(tssDto.equipmentTypeId)
                .orElseThrow(()->new ResourceNotFoundException("equipmentType not found , id = "+tssDto.equipmentTypeId));


        tss.setCableType(optCable);
        tss.setSiteType(optSite);
        tss.setConnectionType(optConnection);
        tss.setEquipmentType(optEquip);

        return tssRepository.save(tss);

    }
}
