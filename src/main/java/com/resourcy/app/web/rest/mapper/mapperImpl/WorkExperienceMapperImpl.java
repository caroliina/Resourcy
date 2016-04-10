package com.resourcy.app.web.rest.mapper.mapperImpl;

import com.resourcy.app.domain.CurriculumVitae;
import com.resourcy.app.domain.WorkAssignment;
import com.resourcy.app.domain.WorkExperience;
import com.resourcy.app.web.rest.dto.WorkAssignmentDTO;
import com.resourcy.app.web.rest.dto.WorkExperienceDTO;
import com.resourcy.app.web.rest.mapper.WorkAssignmentMapper;
import com.resourcy.app.web.rest.mapper.WorkExperienceMapper;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkExperienceMapperImpl implements WorkExperienceMapper {

    @Inject
    WorkAssignmentMapper workAssignmentMapper;

    @Override
    public WorkExperienceDTO workExperienceToWorkExperienceDTO(WorkExperience workExperience) {
        if ( workExperience == null ) {
            return null;
        }

        WorkExperienceDTO workExperienceDTO = new WorkExperienceDTO();

        workExperienceDTO.setCurriculumVitaeId( workExperienceCurriculumVitaeId( workExperience ) );
        workExperienceDTO.setCreatedDate( workExperience.getCreatedDate() );
        workExperienceDTO.setLastModifiedDate( workExperience.getLastModifiedDate() );
        workExperienceDTO.setCreatedBy( workExperience.getCreatedBy() );
        workExperienceDTO.setLastModifiedBy( workExperience.getLastModifiedBy() );
        workExperienceDTO.setId( workExperience.getId() );
        workExperienceDTO.setPosition( workExperience.getPosition() );
        workExperienceDTO.setPeriodStart( workExperience.getPeriodStart() );
        workExperienceDTO.setPeriodEnd( workExperience.getPeriodEnd() );
        workExperienceDTO.setLocation( workExperience.getLocation() );
        workExperienceDTO.setOrganization( workExperience.getOrganization() );

        if(workExperience.getWorkAssignments()!=null){

        List<WorkAssignmentDTO> res = workExperience.getWorkAssignments().stream()
           .map(workAssignment -> workAssignmentMapper.workAssignmentToWorkAssignmentDTO(workAssignment))
           .collect(Collectors.toList());

        workExperienceDTO.setWorkAssignments(res);
        }

        return workExperienceDTO;
    }

    @Override
    public WorkExperience workExperienceDTOToWorkExperience(WorkExperienceDTO workExperienceDTO) {
        if ( workExperienceDTO == null ) {
            return null;
        }

        WorkExperience workExperience = new WorkExperience();

        workExperience.setCurriculumVitae( curriculumVitaeFromId( workExperienceDTO.getCurriculumVitaeId() ) );
        workExperience.setCreatedBy( workExperienceDTO.getCreatedBy() );
        workExperience.setCreatedDate( workExperienceDTO.getCreatedDate() );
        workExperience.setLastModifiedBy( workExperienceDTO.getLastModifiedBy() );
        workExperience.setLastModifiedDate( workExperienceDTO.getLastModifiedDate() );
        workExperience.setId( workExperienceDTO.getId() );
        workExperience.setPosition( workExperienceDTO.getPosition() );
        workExperience.setPeriodStart( workExperienceDTO.getPeriodStart() );
        workExperience.setPeriodEnd( workExperienceDTO.getPeriodEnd() );
        workExperience.setLocation( workExperienceDTO.getLocation() );
        workExperience.setOrganization( workExperienceDTO.getOrganization() );

        if(workExperienceDTO.getWorkAssignments()!=null){

        List<WorkAssignment> res = workExperienceDTO.getWorkAssignments().stream()
           .map(workAssignment -> workAssignmentMapper.workAssignmentDTOToWorkAssignment(workAssignment))
           .collect(Collectors.toList());

        workExperience.setWorkAssignments(res);

        }
        return workExperience;
    }

    private Long workExperienceCurriculumVitaeId(WorkExperience workExperience) {

        if ( workExperience == null ) {
            return null;
        }
        CurriculumVitae curriculumVitae = workExperience.getCurriculumVitae();
        if ( curriculumVitae == null ) {
            return null;
        }
        Long id = curriculumVitae.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
