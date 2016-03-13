package com.resourcy.app.web.rest.mapper;

import com.resourcy.app.domain.*;
import com.resourcy.app.web.rest.dto.AdditionalStudyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AdditionalStudy and its DTO AdditionalStudyDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdditionalStudyMapper {

    @Mapping(source = "curriculumVitae.id", target = "curriculumVitaeId")
    AdditionalStudyDTO additionalStudyToAdditionalStudyDTO(AdditionalStudy additionalStudy);

    @Mapping(source = "curriculumVitaeId", target = "curriculumVitae")
    AdditionalStudy additionalStudyDTOToAdditionalStudy(AdditionalStudyDTO additionalStudyDTO);

    default CurriculumVitae curriculumVitaeFromId(Long id) {
        if (id == null) {
            return null;
        }
        CurriculumVitae curriculumVitae = new CurriculumVitae();
        curriculumVitae.setId(id);
        return curriculumVitae;
    }
}
