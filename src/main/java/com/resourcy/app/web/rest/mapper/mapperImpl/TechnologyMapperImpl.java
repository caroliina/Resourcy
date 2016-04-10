package com.resourcy.app.web.rest.mapper.mapperImpl;

import com.resourcy.app.domain.GovernmentProject;
import com.resourcy.app.domain.Technology;
import com.resourcy.app.web.rest.dto.TechnologyDTO;
import com.resourcy.app.web.rest.mapper.TechnologyMapper;
import org.springframework.stereotype.Component;


@Component
public class TechnologyMapperImpl implements TechnologyMapper {

    @Override
    public TechnologyDTO technologyToTechnologyDTO(Technology technology) {
        if ( technology == null ) {
            return null;
        }

        TechnologyDTO technologyDTO = new TechnologyDTO();

        technologyDTO.setGovernmentProjectId( technologyGovernmentProjectId( technology ) );
        technologyDTO.setCreatedDate( technology.getCreatedDate() );
        technologyDTO.setLastModifiedDate( technology.getLastModifiedDate() );
        technologyDTO.setCreatedBy( technology.getCreatedBy() );
        technologyDTO.setLastModifiedBy( technology.getLastModifiedBy() );
        technologyDTO.setId( technology.getId() );
        technologyDTO.setType( technology.getType() );
        technologyDTO.setDescription( technology.getDescription() );

        return technologyDTO;
    }

    @Override
    public Technology technologyDTOToTechnology(TechnologyDTO technologyDTO) {
        if ( technologyDTO == null ) {
            return null;
        }

        Technology technology = new Technology();

        technology.setGovernmentProject( governmentProjectFromId( technologyDTO.getGovernmentProjectId() ) );
        technology.setCreatedBy( technologyDTO.getCreatedBy() );
        technology.setCreatedDate( technologyDTO.getCreatedDate() );
        technology.setLastModifiedBy( technologyDTO.getLastModifiedBy() );
        technology.setLastModifiedDate( technologyDTO.getLastModifiedDate() );
        technology.setId( technologyDTO.getId() );
        technology.setType( technologyDTO.getType() );
        technology.setDescription( technologyDTO.getDescription() );

        return technology;
    }

    private Long technologyGovernmentProjectId(Technology technology) {

        if ( technology == null ) {
            return null;
        }
        GovernmentProject governmentProject = technology.getGovernmentProject();
        if ( governmentProject == null ) {
            return null;
        }
        Long id = governmentProject.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
