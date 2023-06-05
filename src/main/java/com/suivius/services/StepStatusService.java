package com.suivius.services;

import com.suivius.exceptions.ResourceNotFoundException;
import com.suivius.helpers.Constants;
import com.suivius.models.StepStatus;
import com.suivius.repo.StepStatusRepository;
import org.springframework.stereotype.Service;

@Service
public class StepStatusService {

    private final StepStatusRepository stepStatusRepository;

    public StepStatusService(StepStatusRepository stepStatusRepository){
        this.stepStatusRepository =stepStatusRepository;

    }
    public StepStatus getPreValidationTSS(){
        return stepStatusRepository.findById(Constants.STATUS_TSS_PRE_VALIDATION)
                .orElseThrow(()-> new ResourceNotFoundException("status not found with id :" + Constants.STATUS_TSS_PRE_VALIDATION));
    }
    public StepStatus getWaitingValidationTSS(){
        return stepStatusRepository.findById(Constants.STATUS_TSS_WAITING_VALIDATION)
                .orElseThrow(()-> new ResourceNotFoundException("status not found with id :" + Constants.STATUS_TSS_WAITING_VALIDATION));
    }
    public StepStatus getWaitingValidationAPD(){
        return stepStatusRepository.findById(Constants.STATUS_WAITING_APD)
                .orElseThrow(()-> new ResourceNotFoundException("status not found with id :" + Constants.STATUS_WAITING_APD));
    }
}
