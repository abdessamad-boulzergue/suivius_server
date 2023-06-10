package com.suivius.services;

import com.suivius.exceptions.ResourceNotFoundException;
import com.suivius.models.Project;
import com.suivius.models.Squad;
import com.suivius.models.StaffMember;
import com.suivius.repo.ProjectRepository;
import com.suivius.repo.SquadRepository;
import com.suivius.repo.StaffRepository;
import com.suivius.rest.dto.PostSquadDto;
import com.suivius.rest.dto.SquadDto;
import com.suivius.rest.dto.StaffDto;
import com.suivius.rest.mappers.SquadMapper;
import com.suivius.rest.mappers.StaffMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StaffService {

    private final StaffRepository staffRepository;
    private final SquadRepository squadRepository;
    private final StaffMapper staffMapper;
    private final SquadMapper squadMapper;

    private final ProjectRepository projectRepository;

    public StaffService(StaffRepository staffRepository,StaffMapper staffMapper,
                        SquadRepository squadRepository, SquadMapper squadMapper,
                        ProjectRepository projectRepository){
        this.staffRepository =staffRepository;
        this.staffMapper =staffMapper;
        this.squadRepository=squadRepository;
        this.squadMapper = squadMapper;
        this.projectRepository = projectRepository;
    }


    public List<StaffDto> getStaff() {
        List<StaffMember> staff = this.staffRepository.findAll();
        return staff.stream().map(staffMember -> this.staffMapper.toDTO(staffMember))
                .collect(Collectors.toList());

    }

    public List<SquadDto> getSquad(Long projectId) {
        List<Squad> staff = this.squadRepository.findByProjectId(projectId);
        return staff.stream().map(squad -> this.squadMapper.toDTO(squad))
                .collect(Collectors.toList());
    }

    public void addToSquad(Long projectId, PostSquadDto postSquadDto) {
        if(postSquadDto!=null && postSquadDto.squad!=null){
            postSquadDto.squad.forEach(squadDto -> {

                Squad squad =this.squadRepository.findByProjectIdAndMemberId(projectId,squadDto.memberId);
                if(squad==null) squad = new Squad();

                StaffMember staffMember = staffRepository.findById(squadDto.memberId)
                        .orElseThrow(()-> new ResourceNotFoundException("member not found with id "+ squadDto.memberId));
                Project project = projectRepository.findById(projectId)
                        .orElseThrow(()-> new ResourceNotFoundException("project not found with id "+ projectId));
                squad.setMember(staffMember);
                squad.setProject(project);
                squad.setNormalHours(squadDto.normalHours);
                squad.setAdditionalHours(squadDto.additionalHours);

                squadRepository.save(squad);
            });
        }
    }
}
