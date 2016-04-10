package com.resourcy.app.web.rest.mapper.mapperImpl;

import com.resourcy.app.domain.CurriculumVitae;
import com.resourcy.app.domain.Education;
import com.resourcy.app.web.rest.dto.EducationDTO;
import com.resourcy.app.web.rest.mapper.EducationMapper;
import org.springframework.stereotype.Component;


@Component
public class EducationMapperImpl implements EducationMapper {

    @Override
    public EducationDTO educationToEducationDTO(Education education) {
        if ( education == null ) {
            return null;
        }

        EducationDTO educationDTO = new EducationDTO();

        educationDTO.setCurriculumVitaeId( educationCurriculumVitaeId( education ) );
        educationDTO.setCreatedDate( education.getCreatedDate() );
        educationDTO.setLastModifiedDate( education.getLastModifiedDate() );
        educationDTO.setCreatedBy( education.getCreatedBy() );
        educationDTO.setLastModifiedBy( education.getLastModifiedBy() );
        educationDTO.setId( education.getId() );
        educationDTO.setInstitution( education.getInstitution() );
        educationDTO.setPeriodStart( education.getPeriodStart() );
        educationDTO.setPeriodEnd( education.getPeriodEnd() );
        educationDTO.setSpeciality( education.getSpeciality() );
        educationDTO.setDegree( education.getDegree() );

        return educationDTO;
    }

    @Override
    public Education educationDTOToEducation(EducationDTO educationDTO) {
        if ( educationDTO == null ) {
            return null;
        }

        Education education = new Education();

        education.setCurriculumVitae( curriculumVitaeFromId( educationDTO.getCurriculumVitaeId() ) );
        education.setCreatedBy( educationDTO.getCreatedBy() );
        education.setCreatedDate( educationDTO.getCreatedDate() );
        education.setLastModifiedBy( educationDTO.getLastModifiedBy() );
        education.setLastModifiedDate( educationDTO.getLastModifiedDate() );
        education.setId( educationDTO.getId() );
        education.setInstitution( educationDTO.getInstitution() );
        education.setPeriodStart( educationDTO.getPeriodStart() );
        education.setPeriodEnd( educationDTO.getPeriodEnd() );
        education.setSpeciality( educationDTO.getSpeciality() );
        education.setDegree( educationDTO.getDegree() );

        return education;
    }

    private Long educationCurriculumVitaeId(Education education) {

        if ( education == null ) {
            return null;
        }
        CurriculumVitae curriculumVitae = education.getCurriculumVitae();
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
