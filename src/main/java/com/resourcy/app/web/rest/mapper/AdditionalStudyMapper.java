package com.resourcy.app.web.rest.mapper;

import com.resourcy.app.domain.AdditionalStudy;
import com.resourcy.app.domain.CurriculumVitae;
import com.resourcy.app.web.rest.dto.AdditionalStudyDTO;

public interface AdditionalStudyMapper {

    AdditionalStudyDTO additionalStudyToAdditionalStudyDTO(AdditionalStudy additionalStudy);

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
