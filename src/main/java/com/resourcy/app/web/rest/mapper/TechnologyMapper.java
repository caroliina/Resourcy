package com.resourcy.app.web.rest.mapper;

import com.resourcy.app.domain.*;
import com.resourcy.app.web.rest.dto.TechnologyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Technology and its DTO TechnologyDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TechnologyMapper {

    @Mapping(source = "governmentProject.id", target = "governmentProjectId")
    TechnologyDTO technologyToTechnologyDTO(Technology technology);

    @Mapping(source = "governmentProjectId", target = "governmentProject")
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
