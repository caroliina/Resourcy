package com.resourcy.app.web.rest.mapper;

import com.resourcy.app.domain.*;
import com.resourcy.app.web.rest.dto.LanguageSkillDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity LanguageSkill and its DTO LanguageSkillDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LanguageSkillMapper {

    @Mapping(source = "curriculumVitae.id", target = "curriculumVitaeId")
    LanguageSkillDTO languageSkillToLanguageSkillDTO(LanguageSkill languageSkill);

    @Mapping(source = "curriculumVitaeId", target = "curriculumVitae")
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
