package com.resourcy.app.web.rest.mapper;

import com.resourcy.app.domain.CurriculumVitae;
import com.resourcy.app.domain.Education;
import com.resourcy.app.web.rest.dto.EducationDTO;

public interface EducationMapper {

    EducationDTO educationToEducationDTO(Education education);

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
