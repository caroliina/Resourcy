package com.resourcy.app.web.rest.mapper.mapperImpl;

import com.resourcy.app.domain.AdditionalSkill;
import com.resourcy.app.domain.CurriculumVitae;
import com.resourcy.app.web.rest.dto.AdditionalSkillDTO;
import com.resourcy.app.web.rest.mapper.AdditionalSkillMapper;
import org.springframework.stereotype.Component;


@Component
public class AdditionalSkillMapperImpl implements AdditionalSkillMapper {

    @Override
    public AdditionalSkillDTO additionalSkillToAdditionalSkillDTO(AdditionalSkill additionalSkill) {
        if ( additionalSkill == null ) {
            return null;
        }

        AdditionalSkillDTO additionalSkillDTO = new AdditionalSkillDTO();

        additionalSkillDTO.setCurriculumVitaeId( additionalSkillCurriculumVitaeId( additionalSkill ) );
        additionalSkillDTO.setCreatedDate( additionalSkill.getCreatedDate() );
        additionalSkillDTO.setLastModifiedDate( additionalSkill.getLastModifiedDate() );
        additionalSkillDTO.setCreatedBy( additionalSkill.getCreatedBy() );
        additionalSkillDTO.setLastModifiedBy( additionalSkill.getLastModifiedBy() );
        additionalSkillDTO.setId( additionalSkill.getId() );
        additionalSkillDTO.setType( additionalSkill.getType() );
        additionalSkillDTO.setDescription( additionalSkill.getDescription() );
        additionalSkillDTO.setExperience( additionalSkill.getExperience() );

        return additionalSkillDTO;
    }

    @Override
    public AdditionalSkill additionalSkillDTOToAdditionalSkill(AdditionalSkillDTO additionalSkillDTO) {
        if ( additionalSkillDTO == null ) {
            return null;
        }

        AdditionalSkill additionalSkill = new AdditionalSkill();

        additionalSkill.setCurriculumVitae( curriculumVitaeFromId( additionalSkillDTO.getCurriculumVitaeId() ) );
        additionalSkill.setCreatedBy( additionalSkillDTO.getCreatedBy() );
        additionalSkill.setCreatedDate( additionalSkillDTO.getCreatedDate() );
        additionalSkill.setLastModifiedBy( additionalSkillDTO.getLastModifiedBy() );
        additionalSkill.setLastModifiedDate( additionalSkillDTO.getLastModifiedDate() );
        additionalSkill.setId( additionalSkillDTO.getId() );
        additionalSkill.setType( additionalSkillDTO.getType() );
        additionalSkill.setDescription( additionalSkillDTO.getDescription() );
        additionalSkill.setExperience( additionalSkillDTO.getExperience() );

        return additionalSkill;
    }

    private Long additionalSkillCurriculumVitaeId(AdditionalSkill additionalSkill) {

        if ( additionalSkill == null ) {
            return null;
        }
        CurriculumVitae curriculumVitae = additionalSkill.getCurriculumVitae();
        if ( curriculumVitae == null ) {
            return null;
        }
        Long id = curriculumVitae.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
