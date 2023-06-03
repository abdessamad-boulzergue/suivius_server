package com.suivius.services;

import com.suivius.models.Unit;
import com.suivius.repo.UnitRepository;
import org.springframework.stereotype.Service;

@Service
public class UnitService {

    UnitRepository unitRepository;

    public UnitService(UnitRepository unitRepository){
        this.unitRepository =unitRepository;
    }

    public Unit save(Unit unit){
        return unitRepository.save(unit);
    }


    public Unit getUnite(String unit){
        return unitRepository.findByTitle(unit);
    }
}
