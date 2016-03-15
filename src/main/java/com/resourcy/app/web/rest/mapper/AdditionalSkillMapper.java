package com.resourcy.app.web.rest.mapper;

import com.resourcy.app.domain.*;
import com.resourcy.app.web.rest.dto.AdditionalSkillDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AdditionalSkill and its DTO AdditionalSkillDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdditionalSkillMapper {

    @Mapping(source = "curriculumVitae.id", target = "curriculumVitaeId")
    AdditionalSkillDTO additionalSkillToAdditionalSkillDTO(AdditionalSkill additionalSkill);

    @Mapping(source = "curriculumVitaeId", target = "curriculumVitae")
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
