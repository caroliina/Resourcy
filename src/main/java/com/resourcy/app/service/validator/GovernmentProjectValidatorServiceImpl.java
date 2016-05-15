package com.resourcy.app.service.validator;

import com.resourcy.app.domain.GovernmentProject;
import com.resourcy.app.web.rest.dto.EducationDTO;
import com.resourcy.app.web.rest.dto.GovernmentProjectDTO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

/**
 * Created by Salome on 19.04.2016.
 */

@Service("governmentProjectValidatorService")
public class GovernmentProjectValidatorServiceImpl implements ValidatorService<GovernmentProjectDTO> {

    @Override
    public ValidationResponse validate(GovernmentProjectDTO governmentProject) {
        ValidationResponse validationResponse = new ValidationResponse();

        if (governmentProject.getBuyer() == null)  {
            validationResponse.getErrorMessage().add("validationerrors.ERROR_GOV-PROJECT_BUYER_ISEMPTY");
        }
        if (governmentProject.getServiceName() == null)  {
            validationResponse.getErrorMessage().add("validationerrors.ERROR_GOV-PROJECT_SERVICENAME_ISEMPTY");
        }
        if (governmentProject.getTotalProjectWorkHours() == null)  {
            validationResponse.getErrorMessage().add("validationerrors.ERROR_GOV-PROJECT_WORK-HOURS_ISEMPTY");
        }
        return validationResponse;
    }


}
