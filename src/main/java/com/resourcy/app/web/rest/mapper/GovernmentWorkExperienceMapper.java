package com.resourcy.app.web.rest.mapper;

import com.resourcy.app.domain.*;
import com.resourcy.app.web.rest.dto.GovernmentWorkExperienceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity GovernmentWorkExperience and its DTO GovernmentWorkExperienceDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GovernmentWorkExperienceMapper {

    @Mapping(source = "curriculumVitae.id", target = "curriculumVitaeId")
    @Mapping(source = "governmentProject.id", target = "governmentProjectId")
    GovernmentWorkExperienceDTO governmentWorkExperienceToGovernmentWorkExperienceDTO(GovernmentWorkExperience governmentWorkExperience);

    @Mapping(source = "curriculumVitaeId", target = "curriculumVitae")
    @Mapping(source = "governmentProjectId", target = "governmentProject")
    @Mapping(target = "workAssignments", ignore = true)
    GovernmentWorkExperience governmentWorkExperienceDTOToGovernmentWorkExperience(GovernmentWorkExperienceDTO governmentWorkExperienceDTO);

    default CurriculumVitae curriculumVitaeFromId(Long id) {
        if (id == null) {
            return null;
        }
        CurriculumVitae curriculumVitae = new CurriculumVitae();
        curriculumVitae.setId(id);
        return curriculumVitae;
    }

    default GovernmentProject governmentProjectFromId(Long id) {
        if (id == null) {
            return null;
        }
        GovernmentProject governmentProject = new GovernmentProject();
        governmentProject.setId(id);
        return governmentProject;
    }
}
