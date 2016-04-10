package com.resourcy.app.web.rest.mapper;

import com.resourcy.app.domain.CurriculumVitae;
import com.resourcy.app.domain.GovernmentProject;
import com.resourcy.app.domain.GovernmentWorkExperience;
import com.resourcy.app.web.rest.dto.GovernmentWorkExperienceDTO;

public interface GovernmentWorkExperienceMapper {

    GovernmentWorkExperienceDTO governmentWorkExperienceToGovernmentWorkExperienceDTO(GovernmentWorkExperience governmentWorkExperience);

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
