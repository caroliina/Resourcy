package com.resourcy.app.service.validator;

import com.resourcy.app.web.rest.dto.AdditionalSkillDTO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

/**
 * Created by Salome on 19.04.2016.
 */

@Service("additionalSkillValidatorService")
public class AdditionalSkillValidatorServiceImpl implements ValidatorService<AdditionalSkillDTO> {

    @Override
    public ValidationResponse validate(AdditionalSkillDTO skill) {
        ValidationResponse validationResponse = new ValidationResponse();

        if (skill.getDescription() == null) {
            validationResponse.getErrorMessage().add("ERROR_ADD_SKILL_DESCRIPTION_ISEMPTY");
        }
        if (skill.getType() == null) {
            validationResponse.getErrorMessage().add("ERROR_ADD_SKILL_TYPE_ISEMPTY");
        }
        if (skill.getExperience() == 0) {
            validationResponse.getErrorMessage().add("ERROR_ADD_SKILL_EXPERIENCE_ISEMPTY");
        }
        return validationResponse;
    }
}
