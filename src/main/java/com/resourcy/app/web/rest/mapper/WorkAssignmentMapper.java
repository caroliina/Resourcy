package com.resourcy.app.web.rest.mapper;

import com.resourcy.app.domain.GovernmentWorkExperience;
import com.resourcy.app.domain.WorkAssignment;
import com.resourcy.app.domain.WorkExperience;
import com.resourcy.app.web.rest.dto.WorkAssignmentDTO;

public interface WorkAssignmentMapper {

    WorkAssignmentDTO workAssignmentToWorkAssignmentDTO(WorkAssignment workAssignment);

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
