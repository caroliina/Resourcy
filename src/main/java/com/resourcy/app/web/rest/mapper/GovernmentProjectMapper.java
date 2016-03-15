package com.resourcy.app.web.rest.mapper;

import com.resourcy.app.domain.*;
import com.resourcy.app.web.rest.dto.GovernmentProjectDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity GovernmentProject and its DTO GovernmentProjectDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GovernmentProjectMapper {

    GovernmentProjectDTO governmentProjectToGovernmentProjectDTO(GovernmentProject governmentProject);

    @Mapping(target = "governmentWorkExperience", ignore = true)
    @Mapping(target = "technologys", ignore = true)
    GovernmentProject governmentProjectDTOToGovernmentProject(GovernmentProjectDTO governmentProjectDTO);
}
