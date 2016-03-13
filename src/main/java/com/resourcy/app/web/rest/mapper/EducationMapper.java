package com.resourcy.app.web.rest.mapper;

import com.resourcy.app.domain.*;
import com.resourcy.app.web.rest.dto.EducationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Education and its DTO EducationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EducationMapper {

    @Mapping(source = "curriculumVitae.id", target = "curriculumVitaeId")
    EducationDTO educationToEducationDTO(Education education);

    @Mapping(source = "curriculumVitaeId", target = "curriculumVitae")
    Education educationDTOToEducation(EducationDTO educationDTO);

    default CurriculumVitae curriculumVitaeFromId(Long id) {
        if (id == null) {
            return null;
        }
        CurriculumVitae curriculumVitae = new CurriculumVitae();
        curriculumVitae.setId(id);
        return curriculumVitae;
    }
}
