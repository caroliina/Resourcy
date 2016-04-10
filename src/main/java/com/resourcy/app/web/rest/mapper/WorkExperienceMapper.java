package com.resourcy.app.web.rest.mapper;

import com.resourcy.app.domain.CurriculumVitae;
import com.resourcy.app.domain.WorkExperience;
import com.resourcy.app.web.rest.dto.WorkExperienceDTO;

public interface WorkExperienceMapper {

    WorkExperienceDTO workExperienceToWorkExperienceDTO(WorkExperience workExperience);

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
