package com.resourcy.app.web.rest.mapper;

import com.resourcy.app.domain.GovernmentProject;
import com.resourcy.app.domain.Technology;
import com.resourcy.app.web.rest.dto.TechnologyDTO;

public interface TechnologyMapper {

    TechnologyDTO technologyToTechnologyDTO(Technology technology);

    Technology technologyDTOToTechnology(TechnologyDTO technologyDTO);

    default GovernmentProject governmentProjectFromId(Long id) {
        if (id == null) {
            return null;
        }
        GovernmentProject governmentProject = new GovernmentProject();
        governmentProject.setId(id);
        return governmentProject;
    }
}
