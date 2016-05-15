package com.resourcy.app.service.validator;

import com.resourcy.app.web.rest.dto.AdditionalSkillDTO;
import com.resourcy.app.web.rest.dto.AdditionalStudyDTO;
import org.springframework.stereotype.Service;

/**
 * Created by Salome on 19.04.2016.
 */

@Service("additionalStudyValidatorService")
public class AdditionalStudyValidatorServiceImpl implements ValidatorService<AdditionalStudyDTO> {

    @Override
    public ValidationResponse validate(AdditionalStudyDTO study) {
        ValidationResponse validationResponse = new ValidationResponse();

        if (study.getInstitution() == null)  {
            validationResponse.getErrorMessage().add("validationerrors.ERROR_ADD_STUDY_INSTITUTION_ISEMPTY");
        }
        if (study.getPeriodStart() == null)  {
            validationResponse.getErrorMessage().add("validationerrors.ERROR_ADD_STUDY_PERIODSTART_ISEMPTY");
        }
        if (study.getDescription() == null)  {
            validationResponse.getErrorMessage().add("validationerrors.ERROR_ADD_STUDY_DESCRIPTION_ISEMPTY");
        }
        return validationResponse;
    }
}
