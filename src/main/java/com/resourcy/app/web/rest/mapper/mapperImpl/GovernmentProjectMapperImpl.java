package com.resourcy.app.web.rest.mapper.mapperImpl;

import com.resourcy.app.domain.GovernmentProject;
import com.resourcy.app.domain.Technology;
import com.resourcy.app.web.rest.dto.GovernmentProjectDTO;
import com.resourcy.app.web.rest.dto.TechnologyDTO;
import com.resourcy.app.web.rest.mapper.GovernmentProjectMapper;
import com.resourcy.app.web.rest.mapper.TechnologyMapper;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GovernmentProjectMapperImpl implements GovernmentProjectMapper {

    @Inject
    TechnologyMapper technologyMapper;

    @Override
    public GovernmentProjectDTO governmentProjectToGovernmentProjectDTO(GovernmentProject governmentProject) {
        if ( governmentProject == null ) {
            return null;
        }

        GovernmentProjectDTO governmentProjectDTO = new GovernmentProjectDTO();

        governmentProjectDTO.setCreatedDate( governmentProject.getCreatedDate() );
        governmentProjectDTO.setLastModifiedDate( governmentProject.getLastModifiedDate() );
        governmentProjectDTO.setCreatedBy( governmentProject.getCreatedBy() );
        governmentProjectDTO.setLastModifiedBy( governmentProject.getLastModifiedBy() );
        governmentProjectDTO.setId( governmentProject.getId() );
        governmentProjectDTO.setBuyer( governmentProject.getBuyer() );
        governmentProjectDTO.setServiceName( governmentProject.getServiceName() );
        governmentProjectDTO.setTotalProjectWorkHours( governmentProject.getTotalProjectWorkHours() );

        if(governmentProject.getTechnologys()!=null){

        List<TechnologyDTO> res = governmentProject.getTechnologys().stream()
           .map(technology -> technologyMapper.technologyToTechnologyDTO(technology))
           .collect(Collectors.toList());

        governmentProjectDTO.setTechnologies(res);
        }

        return governmentProjectDTO;
    }

    @Override
    public GovernmentProject governmentProjectDTOToGovernmentProject(GovernmentProjectDTO governmentProjectDTO) {
        if ( governmentProjectDTO == null ) {
            return null;
        }

        GovernmentProject governmentProject = new GovernmentProject();

        governmentProject.setCreatedBy( governmentProjectDTO.getCreatedBy() );
        governmentProject.setCreatedDate( governmentProjectDTO.getCreatedDate() );
        governmentProject.setLastModifiedBy( governmentProjectDTO.getLastModifiedBy() );
        governmentProject.setLastModifiedDate( governmentProjectDTO.getLastModifiedDate() );
        governmentProject.setId( governmentProjectDTO.getId() );
        governmentProject.setBuyer( governmentProjectDTO.getBuyer() );
        governmentProject.setServiceName( governmentProjectDTO.getServiceName() );
        governmentProject.setTotalProjectWorkHours( governmentProjectDTO.getTotalProjectWorkHours() );

        if(governmentProjectDTO.getTechnologies()!=null){

        List<Technology> res = governmentProjectDTO.getTechnologies().stream()
           .map(technology -> technologyMapper.technologyDTOToTechnology(technology))
           .collect(Collectors.toList());

        governmentProject.setTechnologys(res);
        }

        return governmentProject;
    }
}
