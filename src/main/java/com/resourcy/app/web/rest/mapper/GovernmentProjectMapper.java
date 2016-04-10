package com.resourcy.app.web.rest.mapper;

import com.resourcy.app.domain.GovernmentProject;
import com.resourcy.app.web.rest.dto.GovernmentProjectDTO;

public interface GovernmentProjectMapper {

    GovernmentProjectDTO governmentProjectToGovernmentProjectDTO(GovernmentProject governmentProject);

    GovernmentProject governmentProjectDTOToGovernmentProject(GovernmentProjectDTO governmentProjectDTO);
}
