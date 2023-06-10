package com.suivius.services;

import com.suivius.exceptions.ResourceNotFoundException;
import com.suivius.helpers.Constants;
import com.suivius.models.StepStatus;
import com.suivius.repo.StepStatusRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StepStatusService {

    private final StepStatusRepository stepStatusRepository;

    public StepStatusService(StepStatusRepository stepStatusRepository) {
        this.stepStatusRepository = stepStatusRepository;

    }

    public StepStatus getPreValidationTSS() {
        return stepStatusRepository.findById(Constants.STATUS_TSS_PRE_VALIDATION)
                .orElseThrow(() -> new ResourceNotFoundException("status not found with id :" + Constants.STATUS_TSS_PRE_VALIDATION));
    }

    public StepStatus getWaitingValidationTSS() {
        return stepStatusRepository.findById(Constants.STATUS_TSS_WAITING_VALIDATION)
                .orElseThrow(() -> new ResourceNotFoundException("status not found with id :" + Constants.STATUS_TSS_WAITING_VALIDATION));
    }

    public StepStatus getWaitingValidationAPD() {
        return stepStatusRepository.findById(Constants.STATUS_APD_WAITING_VALIDATION)
                .orElseThrow(() -> new ResourceNotFoundException("status not found with id :" + Constants.STATUS_WAITING_APD));
    }
    public StepStatus getWaitingAPD() {
        return stepStatusRepository.findById(Constants.STATUS_WAITING_APD)
                .orElseThrow(() -> new ResourceNotFoundException("status not found with id :" + Constants.STATUS_WAITING_APD));
    }

    public StepStatus getWaitingMAJTSS() {
        return stepStatusRepository.findById(Constants.STATUS_TSS_WAITING_MAJ)
                .orElseThrow(() -> new ResourceNotFoundException("status not found with id :" + Constants.STATUS_TSS_WAITING_MAJ));
    }

    public StepStatus getWaitingMAJAPD() {
        return stepStatusRepository.findById(Constants.STATUS_APD_WAITING_MAJ)
                .orElseThrow(() -> new ResourceNotFoundException("status not found with id :" + Constants.STATUS_APD_WAITING_MAJ));
    }

    public StepStatus getPreValidationAPD() {
        return stepStatusRepository.findById(Constants.STATUS_APD_PRE_VALIDATION)
                .orElseThrow(() -> new ResourceNotFoundException("status not found with id :" + Constants.STATUS_APD_PRE_VALIDATION));
    }

    public Optional<StepStatus> findById(Long stepStatusId) {
       return this.stepStatusRepository.findById(stepStatusId);
    }

    public StepStatus getStartStudy() {
        return stepStatusRepository.findById(Constants.STATUS_STUDY_START)
                .orElseThrow(() -> new ResourceNotFoundException("status not found with id :" + Constants.STATUS_STUDY_START));

    }

    public StepStatus getTSSEdition() {
        return stepStatusRepository.findById(Constants.STATUS_TSS_IN_EDITION)
                .orElseThrow(() -> new ResourceNotFoundException("status not found with id :" + Constants.STATUS_TSS_IN_EDITION));

    }

    public StepStatus getAuthorizationWaiting() {
        return stepStatusRepository.findById(Constants.STATUS_AUTHORIZATION_WAITING)
                .orElseThrow(() -> new ResourceNotFoundException("status not found with id :" + Constants.STATUS_AUTHORIZATION_WAITING));

    }
}
