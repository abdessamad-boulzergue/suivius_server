package com.suivius.services;

import com.suivius.models.History;
import com.suivius.models.Localisation;
import com.suivius.models.Project;
import com.suivius.models.StepStatus;
import com.suivius.repo.HistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HistoryService {

    private final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository){
        this.historyRepository =historyRepository;
    }

    public void addHistory(Project project, StepStatus previousStatus, StepStatus currentStatus, String deviceId, Localisation localisation){
        History history = new History();
        history.setLocalisation(localisation);
        history.setCurrentStatus(currentStatus);
        history.setPreviousStatus(previousStatus);
        history.setDateTime(LocalDateTime.now());
        history.setDeviceId(deviceId);
        history.setProject(project);

        this.historyRepository.save(history);
    }
}
