package com.resourcy.app.service.validator;

import com.resourcy.app.web.rest.dto.EducationDTO;
import com.resourcy.app.web.rest.dto.WorkAssignmentDTO;
import org.springframework.stereotype.Service;

/**
 * Created by Salome on 19.04.2016.
 */

@Service("workAssignmentValidatorService")
public class WorkAssignmentValidatorServiceImpl implements ValidatorService<WorkAssignmentDTO> {

    @Override
    public ValidationResponse validate(WorkAssignmentDTO workAssignment) {
        ValidationResponse validationResponse = new ValidationResponse();

        if (workAssignment.getDescription() == null)  {
            validationResponse.getErrorMessage().add("ERROR_WORK-ASSIGNMENT_DESCRIPTION_ISEMPTY");
        }
        return validationResponse;
    }


}
