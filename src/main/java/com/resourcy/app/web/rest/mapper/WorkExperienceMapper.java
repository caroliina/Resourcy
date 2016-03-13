package com.resourcy.app.web.rest.mapper;

import com.resourcy.app.domain.*;
import com.resourcy.app.web.rest.dto.WorkExperienceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity WorkExperience and its DTO WorkExperienceDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WorkExperienceMapper {

    @Mapping(source = "curriculumVitae.id", target = "curriculumVitaeId")
    WorkExperienceDTO workExperienceToWorkExperienceDTO(WorkExperience workExperience);

    @Mapping(source = "curriculumVitaeId", target = "curriculumVitae")
    @Mapping(target = "workAssignments", ignore = true)
    WorkExperience workExperienceDTOToWorkExperience(WorkExperienceDTO workExperienceDTO);

    default CurriculumVitae curriculumVitaeFromId(Long id) {
        if (id == null) {
            return null;
        }
        CurriculumVitae curriculumVitae = new CurriculumVitae();
        curriculumVitae.setId(id);
        return curriculumVitae;
    }
}
