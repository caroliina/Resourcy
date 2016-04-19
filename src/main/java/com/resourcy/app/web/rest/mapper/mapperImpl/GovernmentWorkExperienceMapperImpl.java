package com.resourcy.app.web.rest.mapper.mapperImpl;

import com.resourcy.app.domain.CurriculumVitae;
import com.resourcy.app.domain.GovernmentWorkExperience;
import com.resourcy.app.domain.WorkAssignment;
import com.resourcy.app.web.rest.dto.GovernmentWorkExperienceDTO;
import com.resourcy.app.web.rest.dto.WorkAssignmentDTO;
import com.resourcy.app.web.rest.mapper.GovernmentProjectMapper;
import com.resourcy.app.web.rest.mapper.GovernmentWorkExperienceMapper;
import com.resourcy.app.web.rest.mapper.WorkAssignmentMapper;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GovernmentWorkExperienceMapperImpl implements GovernmentWorkExperienceMapper {

    @Inject
    WorkAssignmentMapper workAssignmentMapper;

    @Inject
    GovernmentProjectMapper governmentProjectMapper;

    @Override
    public GovernmentWorkExperienceDTO governmentWorkExperienceToGovernmentWorkExperienceDTO(GovernmentWorkExperience governmentWorkExperience) {
        if ( governmentWorkExperience == null ) {
            return null;
        }

        GovernmentWorkExperienceDTO governmentWorkExperienceDTO = new GovernmentWorkExperienceDTO();

        if(governmentWorkExperience.getGovernmentProject() != null ){
            governmentWorkExperienceDTO.setGovernmentProject(governmentProjectMapper.governmentProjectToGovernmentProjectDTO(governmentWorkExperience.getGovernmentProject()));
        }

        governmentWorkExperienceDTO.setCurriculumVitaeId( governmentWorkExperienceCurriculumVitaeId( governmentWorkExperience ) );
        governmentWorkExperienceDTO.setCreatedDate( governmentWorkExperience.getCreatedDate() );
        governmentWorkExperienceDTO.setLastModifiedDate( governmentWorkExperience.getLastModifiedDate() );
        governmentWorkExperienceDTO.setCreatedBy( governmentWorkExperience.getCreatedBy() );
        governmentWorkExperienceDTO.setLastModifiedBy( governmentWorkExperience.getLastModifiedBy() );
        governmentWorkExperienceDTO.setId( governmentWorkExperience.getId() );
        governmentWorkExperienceDTO.setPeriodStart( governmentWorkExperience.getPeriodStart() );
        governmentWorkExperienceDTO.setPeriodEnd( governmentWorkExperience.getPeriodEnd() );
        governmentWorkExperienceDTO.setPersonalWorkHours( governmentWorkExperience.getPersonalWorkHours() );
        governmentWorkExperienceDTO.setPosition( governmentWorkExperience.getPosition() );

        if(governmentWorkExperience.getWorkAssignments()!=null){

        List<WorkAssignmentDTO> res = governmentWorkExperience.getWorkAssignments().stream()
           .map(workAssignment -> workAssignmentMapper.workAssignmentToWorkAssignmentDTO(workAssignment))
           .collect(Collectors.toList());

        governmentWorkExperienceDTO.setWorkAssignments(res);
        }

        return governmentWorkExperienceDTO;
    }

    @Override
    public GovernmentWorkExperience governmentWorkExperienceDTOToGovernmentWorkExperience(GovernmentWorkExperienceDTO governmentWorkExperienceDTO) {
        if ( governmentWorkExperienceDTO == null ) {
            return null;
        }

        GovernmentWorkExperience governmentWorkExperience = new GovernmentWorkExperience();

        if (governmentWorkExperienceDTO.getGovernmentProject() != null) {
            governmentWorkExperience.setGovernmentProject(governmentProjectFromId(governmentWorkExperienceDTO.getGovernmentProject().getId()));
        }
        governmentWorkExperience.setCurriculumVitae( curriculumVitaeFromId( governmentWorkExperienceDTO.getCurriculumVitaeId() ) );
        governmentWorkExperience.setCreatedBy( governmentWorkExperienceDTO.getCreatedBy() );
        governmentWorkExperience.setCreatedDate( governmentWorkExperienceDTO.getCreatedDate() );
        governmentWorkExperience.setLastModifiedBy( governmentWorkExperienceDTO.getLastModifiedBy() );
        governmentWorkExperience.setLastModifiedDate( governmentWorkExperienceDTO.getLastModifiedDate() );
        governmentWorkExperience.setId( governmentWorkExperienceDTO.getId() );
        governmentWorkExperience.setPeriodStart( governmentWorkExperienceDTO.getPeriodStart() );
        governmentWorkExperience.setPeriodEnd( governmentWorkExperienceDTO.getPeriodEnd() );
        governmentWorkExperience.setPersonalWorkHours( governmentWorkExperienceDTO.getPersonalWorkHours() );
        governmentWorkExperience.setPosition( governmentWorkExperienceDTO.getPosition() );


        if(governmentWorkExperienceDTO.getWorkAssignments()!=null){

        List<WorkAssignment> res = governmentWorkExperienceDTO.getWorkAssignments().stream()
           .map(workAssignment -> workAssignmentMapper.workAssignmentDTOToWorkAssignment(workAssignment))
           .collect(Collectors.toList());

        governmentWorkExperience.setWorkAssignments(res);
        }


        return governmentWorkExperience;
    }


    private Long governmentWorkExperienceCurriculumVitaeId(GovernmentWorkExperience governmentWorkExperience) {

        if ( governmentWorkExperience == null ) {
            return null;
        }
        CurriculumVitae curriculumVitae = governmentWorkExperience.getCurriculumVitae();
        if ( curriculumVitae == null ) {
            return null;
        }
        Long id = curriculumVitae.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
