package com.resourcy.app.web.rest.mapper.mapperImpl;

import com.resourcy.app.domain.CurriculumVitae;
import com.resourcy.app.domain.LanguageSkill;
import com.resourcy.app.web.rest.dto.LanguageSkillDTO;
import com.resourcy.app.web.rest.mapper.LanguageSkillMapper;
import org.springframework.stereotype.Component;


@Component
public class LanguageSkillMapperImpl implements LanguageSkillMapper {

    @Override
    public LanguageSkillDTO languageSkillToLanguageSkillDTO(LanguageSkill languageSkill) {
        if ( languageSkill == null ) {
            return null;
        }

        LanguageSkillDTO languageSkillDTO = new LanguageSkillDTO();

        languageSkillDTO.setCurriculumVitaeId( languageSkillCurriculumVitaeId( languageSkill ) );
        languageSkillDTO.setCreatedDate( languageSkill.getCreatedDate() );
        languageSkillDTO.setLastModifiedDate( languageSkill.getLastModifiedDate() );
        languageSkillDTO.setCreatedBy( languageSkill.getCreatedBy() );
        languageSkillDTO.setLastModifiedBy( languageSkill.getLastModifiedBy() );
        languageSkillDTO.setId( languageSkill.getId() );
        languageSkillDTO.setLanguage( languageSkill.getLanguage() );
        languageSkillDTO.setSpeaking( languageSkill.getSpeaking() );
        languageSkillDTO.setWriting( languageSkill.getWriting() );

        return languageSkillDTO;
    }

    @Override
    public LanguageSkill languageSkillDTOToLanguageSkill(LanguageSkillDTO languageSkillDTO) {
        if ( languageSkillDTO == null ) {
            return null;
        }

        LanguageSkill languageSkill = new LanguageSkill();

        languageSkill.setCurriculumVitae( curriculumVitaeFromId( languageSkillDTO.getCurriculumVitaeId() ) );
        languageSkill.setCreatedBy( languageSkillDTO.getCreatedBy() );
        languageSkill.setCreatedDate( languageSkillDTO.getCreatedDate() );
        languageSkill.setLastModifiedBy( languageSkillDTO.getLastModifiedBy() );
        languageSkill.setLastModifiedDate( languageSkillDTO.getLastModifiedDate() );
        languageSkill.setId( languageSkillDTO.getId() );
        languageSkill.setLanguage( languageSkillDTO.getLanguage() );
        languageSkill.setSpeaking( languageSkillDTO.getSpeaking() );
        languageSkill.setWriting( languageSkillDTO.getWriting() );

        return languageSkill;
    }

    private Long languageSkillCurriculumVitaeId(LanguageSkill languageSkill) {

        if ( languageSkill == null ) {
            return null;
        }
        CurriculumVitae curriculumVitae = languageSkill.getCurriculumVitae();
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
