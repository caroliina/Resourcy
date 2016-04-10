package com.resourcy.app.web.rest.mapper;

import com.resourcy.app.domain.AdditionalSkill;
import com.resourcy.app.domain.CurriculumVitae;
import com.resourcy.app.web.rest.dto.AdditionalSkillDTO;


public interface AdditionalSkillMapper {

    AdditionalSkillDTO additionalSkillToAdditionalSkillDTO(AdditionalSkill additionalSkill);

    AdditionalSkill additionalSkillDTOToAdditionalSkill(AdditionalSkillDTO additionalSkillDTO);

    default CurriculumVitae curriculumVitaeFromId(Long id) {
        if (id == null) {
            return null;
        }
        CurriculumVitae curriculumVitae = new CurriculumVitae();
        curriculumVitae.setId(id);
        return curriculumVitae;
    }
}
