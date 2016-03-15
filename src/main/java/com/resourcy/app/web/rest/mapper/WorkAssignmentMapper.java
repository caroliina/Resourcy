package com.resourcy.app.web.rest.mapper;

import com.resourcy.app.domain.*;
import com.resourcy.app.web.rest.dto.WorkAssignmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity WorkAssignment and its DTO WorkAssignmentDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WorkAssignmentMapper {

    @Mapping(source = "workExperience.id", target = "workExperienceId")
    @Mapping(source = "governmentWorkExperience.id", target = "governmentWorkExperienceId")
    WorkAssignmentDTO workAssignmentToWorkAssignmentDTO(WorkAssignment workAssignment);

    @Mapping(source = "workExperienceId", target = "workExperience")
    @Mapping(source = "governmentWorkExperienceId", target = "governmentWorkExperience")
    WorkAssignment workAssignmentDTOToWorkAssignment(WorkAssignmentDTO workAssignmentDTO);

    default WorkExperience workExperienceFromId(Long id) {
        if (id == null) {
            return null;
        }
        WorkExperience workExperience = new WorkExperience();
        workExperience.setId(id);
        return workExperience;
    }

    default GovernmentWorkExperience governmentWorkExperienceFromId(Long id) {
        if (id == null) {
            return null;
        }
        GovernmentWorkExperience governmentWorkExperience = new GovernmentWorkExperience();
        governmentWorkExperience.setId(id);
        return governmentWorkExperience;
    }
}
