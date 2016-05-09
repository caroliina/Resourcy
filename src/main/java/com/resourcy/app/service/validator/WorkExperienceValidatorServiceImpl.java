package com.resourcy.app.service.validator;

import com.resourcy.app.web.rest.dto.EducationDTO;
import com.resourcy.app.web.rest.dto.WorkExperienceDTO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

/**
 * Created by Salome on 19.04.2016.
 */

@Service("workExperienceValidatorService")
public class WorkExperienceValidatorServiceImpl implements ValidatorService<WorkExperienceDTO> {

    @Override
    public ValidationResponse validate(WorkExperienceDTO workExperience) {
        ValidationResponse validationResponse = new ValidationResponse();

        if (workExperience.getPosition() == null)  {
            validationResponse.getErrorMessage().add("ERROR_WORK-EXPERIENCE_POSITION_ISEMPTY");
        }
        if (workExperience.getPeriodStart() == null)  {
            validationResponse.getErrorMessage().add("ERROR_WORK-EXPERIENCE_PERIODSTART_ISEMPTY");
        }
        if (workExperience.getOrganization() == null)  {
            validationResponse.getErrorMessage().add("ERROR_WORK-EXPERIENCE_ORGANIZATION_ISEMPTY");
        }
        if (workExperience.getLocation() == null)  {
            validationResponse.getErrorMessage().add("ERROR_WORK-EXPERIENCE_LOCATION_ISEMPTY");
        }
        if (CollectionUtils.isEmpty(workExperience.getWorkAssignments()))  {
            validationResponse.getErrorMessage().add("ERROR_WORK-EXPERIENCE_WORK-ASSIGNMENTS_ISEMPTY");
        }
        return validationResponse;
    }


}
