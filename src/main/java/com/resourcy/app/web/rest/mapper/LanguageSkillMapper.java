package com.resourcy.app.web.rest.mapper;

import com.resourcy.app.domain.CurriculumVitae;
import com.resourcy.app.domain.LanguageSkill;
import com.resourcy.app.web.rest.dto.LanguageSkillDTO;

public interface LanguageSkillMapper {

    LanguageSkillDTO languageSkillToLanguageSkillDTO(LanguageSkill languageSkill);

    LanguageSkill languageSkillDTOToLanguageSkill(LanguageSkillDTO languageSkillDTO);

    default CurriculumVitae curriculumVitaeFromId(Long id) {
        if (id == null) {
            return null;
        }
        CurriculumVitae curriculumVitae = new CurriculumVitae();
        curriculumVitae.setId(id);
        return curriculumVitae;
    }
}
