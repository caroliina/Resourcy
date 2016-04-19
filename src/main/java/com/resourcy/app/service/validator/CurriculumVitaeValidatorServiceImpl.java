package com.resourcy.app.service.validator;

import com.resourcy.app.web.rest.dto.AdditionalSkillDTO;
import com.resourcy.app.web.rest.dto.CurriculumVitaeDTO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by Salome on 19.04.2016.
 */

@Service("curriculumVitaeValidatorService")
public class CurriculumVitaeValidatorServiceImpl implements ValidatorService<CurriculumVitaeDTO> {

    @Override
    public ValidationResponse validate(CurriculumVitaeDTO curriculumVitaeDTO) {
        ValidationResponse validationResponse = new ValidationResponse();

        if (!CollectionUtils.isEmpty(curriculumVitaeDTO.getAdditionalSkills())) {
            for (AdditionalSkillDTO skill : curriculumVitaeDTO.getAdditionalSkills()) {
                if (skill.getDescription() == null || skill.getType() == null) {
                    validationResponse.getErrorMessage().add("mllllm");
                }
            }
        }
        curriculumVitaeDTO.getAdditionalStudys();
        curriculumVitaeDTO.getEducations();
        curriculumVitaeDTO.getGovernmentWorkExperiences();
        curriculumVitaeDTO.getLanguageSkills();
        curriculumVitaeDTO.getLanguageType();
        curriculumVitaeDTO.getWorkExperiences();

        return null;
    }
}
