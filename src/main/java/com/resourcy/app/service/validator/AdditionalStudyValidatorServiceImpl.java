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

        if (study.getInstitution() == null || study.getPeriodStart() == null || study.getDescription() == null) {
            validationResponse.getErrorMessage().add("Some fields are empty in additional study");
        }
        return validationResponse;
    }
}
