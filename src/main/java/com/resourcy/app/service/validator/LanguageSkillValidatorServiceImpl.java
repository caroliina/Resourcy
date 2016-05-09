package com.resourcy.app.service.validator;

import com.resourcy.app.web.rest.dto.EducationDTO;
import com.resourcy.app.web.rest.dto.LanguageSkillDTO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

/**
 * Created by Salome on 19.04.2016.
 */

@Service("languageSkillValidatorService")
public class LanguageSkillValidatorServiceImpl implements ValidatorService<LanguageSkillDTO> {

    @Override
    public ValidationResponse validate(LanguageSkillDTO languageSkill) {
        ValidationResponse validationResponse = new ValidationResponse();

        if (languageSkill.getLanguage() == null)  {
            validationResponse.getErrorMessage().add("ERROR_LANGUAGE_LANGUAGE_ISEMPTY");
        }
        if (languageSkill.getSpeaking() == null)  {
            validationResponse.getErrorMessage().add("ERROR_LANGUAGE_SPEAKING_ISEMPTY");
        }
        if (languageSkill.getWriting() == null)  {
            validationResponse.getErrorMessage().add("ERROR_LANGUAGE_WRITING_ISEMPTY");
        }
        return validationResponse;
    }


}
