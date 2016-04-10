package com.resourcy.app.web.rest.mapper.mapperImpl;

import com.resourcy.app.domain.GovernmentWorkExperience;
import com.resourcy.app.domain.WorkAssignment;
import com.resourcy.app.domain.WorkExperience;
import com.resourcy.app.web.rest.dto.WorkAssignmentDTO;
import com.resourcy.app.web.rest.mapper.WorkAssignmentMapper;
import org.springframework.stereotype.Component;


@Component
public class WorkAssignmentMapperImpl implements WorkAssignmentMapper {

    @Override
    public WorkAssignmentDTO workAssignmentToWorkAssignmentDTO(WorkAssignment workAssignment) {
        if ( workAssignment == null ) {
            return null;
        }

        WorkAssignmentDTO workAssignmentDTO = new WorkAssignmentDTO();

        workAssignmentDTO.setWorkExperienceId( workAssignmentWorkExperienceId( workAssignment ) );
        workAssignmentDTO.setGovernmentWorkExperienceId( workAssignmentGovernmentWorkExperienceId( workAssignment ) );
        workAssignmentDTO.setCreatedDate( workAssignment.getCreatedDate() );
        workAssignmentDTO.setLastModifiedDate( workAssignment.getLastModifiedDate() );
        workAssignmentDTO.setCreatedBy( workAssignment.getCreatedBy() );
        workAssignmentDTO.setLastModifiedBy( workAssignment.getLastModifiedBy() );
        workAssignmentDTO.setId( workAssignment.getId() );
        workAssignmentDTO.setDescription( workAssignment.getDescription() );

        return workAssignmentDTO;
    }

    @Override
    public WorkAssignment workAssignmentDTOToWorkAssignment(WorkAssignmentDTO workAssignmentDTO) {
        if ( workAssignmentDTO == null ) {
            return null;
        }

        WorkAssignment workAssignment = new WorkAssignment();

        workAssignment.setWorkExperience( workExperienceFromId( workAssignmentDTO.getWorkExperienceId() ) );
        workAssignment.setGovernmentWorkExperience( governmentWorkExperienceFromId( workAssignmentDTO.getGovernmentWorkExperienceId() ) );
        workAssignment.setCreatedBy( workAssignmentDTO.getCreatedBy() );
        workAssignment.setCreatedDate( workAssignmentDTO.getCreatedDate() );
        workAssignment.setLastModifiedBy( workAssignmentDTO.getLastModifiedBy() );
        workAssignment.setLastModifiedDate( workAssignmentDTO.getLastModifiedDate() );
        workAssignment.setId( workAssignmentDTO.getId() );
        workAssignment.setDescription( workAssignmentDTO.getDescription() );

        return workAssignment;
    }

    private Long workAssignmentWorkExperienceId(WorkAssignment workAssignment) {

        if ( workAssignment == null ) {
            return null;
        }
        WorkExperience workExperience = workAssignment.getWorkExperience();
        if ( workExperience == null ) {
            return null;
        }
        Long id = workExperience.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long workAssignmentGovernmentWorkExperienceId(WorkAssignment workAssignment) {

        if ( workAssignment == null ) {
            return null;
        }
        GovernmentWorkExperience governmentWorkExperience = workAssignment.getGovernmentWorkExperience();
        if ( governmentWorkExperience == null ) {
            return null;
        }
        Long id = governmentWorkExperience.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
