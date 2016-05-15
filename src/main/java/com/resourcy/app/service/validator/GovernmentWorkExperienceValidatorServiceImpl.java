package com.resourcy.app.service.validator;

import com.resourcy.app.web.rest.dto.EducationDTO;
import com.resourcy.app.web.rest.dto.GovernmentWorkExperienceDTO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by Salome on 19.04.2016.
 */

@Service("governmentWorkExperienceValidatorService")
public class GovernmentWorkExperienceValidatorServiceImpl implements ValidatorService<GovernmentWorkExperienceDTO> {

    @Override
    public ValidationResponse validate(GovernmentWorkExperienceDTO governmentWorkExperience) {
        ValidationResponse validationResponse = new ValidationResponse();

        if (governmentWorkExperience.getPeriodStart() == null)  {
            validationResponse.getErrorMessage().add("validationerrors.ERROR_GOV-WORK-EXP_PERIODSTART_ISEMPTY");
        }
        if (governmentWorkExperience.getPosition() == null)  {
            validationResponse.getErrorMessage().add("validationerrors.ERROR_GOV-WORK-EXP_POSITION_ISEMPTY");
        }
        if (governmentWorkExperience.getPersonalWorkHours() == null)  {
            validationResponse.getErrorMessage().add("validationerrors.ERROR_GOV-WORK-EXP_WORK-HOURS_ISEMPTY");
        }
        if (CollectionUtils.isEmpty(governmentWorkExperience.getWorkAssignments()))  {
            validationResponse.getErrorMessage().add("validationerrors.ERROR_GOV-WORK-EXP_ASSIGNMENTS_ISEMPTY");
        }
        return validationResponse;
    }


}
