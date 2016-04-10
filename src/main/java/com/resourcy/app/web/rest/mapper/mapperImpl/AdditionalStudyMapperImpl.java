package com.resourcy.app.web.rest.mapper.mapperImpl;

import com.resourcy.app.domain.AdditionalStudy;
import com.resourcy.app.domain.CurriculumVitae;
import com.resourcy.app.web.rest.dto.AdditionalStudyDTO;
import com.resourcy.app.web.rest.mapper.AdditionalStudyMapper;
import org.springframework.stereotype.Component;


@Component
public class AdditionalStudyMapperImpl implements AdditionalStudyMapper {

    @Override
    public AdditionalStudyDTO additionalStudyToAdditionalStudyDTO(AdditionalStudy additionalStudy) {
        if ( additionalStudy == null ) {
            return null;
        }

        AdditionalStudyDTO additionalStudyDTO = new AdditionalStudyDTO();

        additionalStudyDTO.setCurriculumVitaeId( additionalStudyCurriculumVitaeId( additionalStudy ) );
        additionalStudyDTO.setCreatedDate( additionalStudy.getCreatedDate() );
        additionalStudyDTO.setLastModifiedDate( additionalStudy.getLastModifiedDate() );
        additionalStudyDTO.setCreatedBy( additionalStudy.getCreatedBy() );
        additionalStudyDTO.setLastModifiedBy( additionalStudy.getLastModifiedBy() );
        additionalStudyDTO.setId( additionalStudy.getId() );
        additionalStudyDTO.setPeriodStart( additionalStudy.getPeriodStart() );
        additionalStudyDTO.setPeriodEnd( additionalStudy.getPeriodEnd() );
        additionalStudyDTO.setInstitution( additionalStudy.getInstitution() );
        additionalStudyDTO.setDescription( additionalStudy.getDescription() );

        return additionalStudyDTO;
    }

    @Override
    public AdditionalStudy additionalStudyDTOToAdditionalStudy(AdditionalStudyDTO additionalStudyDTO) {
        if ( additionalStudyDTO == null ) {
            return null;
        }

        AdditionalStudy additionalStudy = new AdditionalStudy();

        additionalStudy.setCurriculumVitae( curriculumVitaeFromId( additionalStudyDTO.getCurriculumVitaeId() ) );
        additionalStudy.setCreatedBy( additionalStudyDTO.getCreatedBy() );
        additionalStudy.setCreatedDate( additionalStudyDTO.getCreatedDate() );
        additionalStudy.setLastModifiedBy( additionalStudyDTO.getLastModifiedBy() );
        additionalStudy.setLastModifiedDate( additionalStudyDTO.getLastModifiedDate() );
        additionalStudy.setId( additionalStudyDTO.getId() );
        additionalStudy.setPeriodStart( additionalStudyDTO.getPeriodStart() );
        additionalStudy.setPeriodEnd( additionalStudyDTO.getPeriodEnd() );
        additionalStudy.setInstitution( additionalStudyDTO.getInstitution() );
        additionalStudy.setDescription( additionalStudyDTO.getDescription() );

        return additionalStudy;
    }

    private Long additionalStudyCurriculumVitaeId(AdditionalStudy additionalStudy) {

        if ( additionalStudy == null ) {
            return null;
        }
        CurriculumVitae curriculumVitae = additionalStudy.getCurriculumVitae();
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
