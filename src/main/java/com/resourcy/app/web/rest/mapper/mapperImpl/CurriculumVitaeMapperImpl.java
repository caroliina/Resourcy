package com.resourcy.app.web.rest.mapper.mapperImpl;

import com.resourcy.app.domain.*;
import com.resourcy.app.web.rest.dto.*;
import com.resourcy.app.web.rest.mapper.*;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CurriculumVitaeMapperImpl implements CurriculumVitaeMapper {

    @Inject
    EducationMapper educationMapper;

    @Inject
    WorkExperienceMapper workExperienceMapper;

    @Inject
    GovernmentWorkExperienceMapper governmentWorkExperienceMapper;

    @Inject
    AdditionalStudyMapper additionalStudyMapper;

    @Inject
    AdditionalSkillMapper additionalSkillMapper;

    @Inject
    LanguageSkillMapper languageSkillMapper;

    @Override
    public CurriculumVitaeDTO curriculumVitaeToCurriculumVitaeDTO(CurriculumVitae curriculumVitae) {
        if ( curriculumVitae == null ) {
            return null;
        }

        CurriculumVitaeDTO curriculumVitaeDTO = new CurriculumVitaeDTO();
        curriculumVitaeDTO.setId( curriculumVitae.getId() );
        curriculumVitaeDTO.setEmployeeId( curriculumVitaeEmployeeId( curriculumVitae ) );
        curriculumVitaeDTO.setLanguageType( curriculumVitae.getLanguageType() );
        curriculumVitaeDTO.setCreatedDate( curriculumVitae.getCreatedDate() );
        curriculumVitaeDTO.setLastModifiedDate( curriculumVitae.getLastModifiedDate() );
        curriculumVitaeDTO.setCreatedBy( curriculumVitae.getCreatedBy() );
        curriculumVitaeDTO.setLastModifiedBy( curriculumVitae.getLastModifiedBy() );

        if( curriculumVitae.getEducations()!= null){

        List<EducationDTO> educations = curriculumVitae.getEducations().stream()
           .map(education -> educationMapper.educationToEducationDTO(education))
           .collect(Collectors.toList());

        curriculumVitaeDTO.setEducations(educations);
        }

        if(curriculumVitae.getWorkExperiences()!= null){

        List<WorkExperienceDTO> workExperiences = curriculumVitae.getWorkExperiences().stream()
           .map(workExperience -> workExperienceMapper.workExperienceToWorkExperienceDTO(workExperience))
           .collect(Collectors.toList());

        curriculumVitaeDTO.setWorkExperiences(workExperiences);
        }

        if(curriculumVitae.getGovernmentWorkExperiences()!= null){

        List<GovernmentWorkExperienceDTO> governmentWorkExperiences = curriculumVitae.getGovernmentWorkExperiences().stream()
           .map(governmentWorkExperience -> governmentWorkExperienceMapper.governmentWorkExperienceToGovernmentWorkExperienceDTO(governmentWorkExperience))
           .collect(Collectors.toList());

        curriculumVitaeDTO.setGovernmentWorkExperiences(governmentWorkExperiences);
        }


        if(curriculumVitae.getAdditionalStudys() != null){

        List<AdditionalStudyDTO> additionalStudies = curriculumVitae.getAdditionalStudys().stream()
           .map(additionalStudy -> additionalStudyMapper.additionalStudyToAdditionalStudyDTO(additionalStudy))
           .collect(Collectors.toList());

        curriculumVitaeDTO.setAdditionalStudys(additionalStudies);
        }

        if(curriculumVitae.getAdditionalSkills()!= null){

        List<AdditionalSkillDTO> additionalSkills = curriculumVitae.getAdditionalSkills().stream()
           .map(additionalSkill -> additionalSkillMapper.additionalSkillToAdditionalSkillDTO(additionalSkill))
           .collect(Collectors.toList());

        curriculumVitaeDTO.setAdditionalSkills(additionalSkills);
        }

        if(curriculumVitae.getLanguageSkills()!= null){

        List<LanguageSkillDTO> languageSkills = curriculumVitae.getLanguageSkills().stream()
           .map(languageSkill -> languageSkillMapper.languageSkillToLanguageSkillDTO(languageSkill))
           .collect(Collectors.toList());

        curriculumVitaeDTO.setLanguageSkills(languageSkills);
        }



        return curriculumVitaeDTO;
    }

    @Override
    public CurriculumVitae curriculumVitaeDTOToCurriculumVitae(CurriculumVitaeDTO curriculumVitaeDTO) {
        if ( curriculumVitaeDTO == null ) {
            return null;
        }

        CurriculumVitae curriculumVitae = new CurriculumVitae();

        curriculumVitae.setEmployee( employeeFromId( curriculumVitaeDTO.getEmployeeId() ) );
        curriculumVitae.setCreatedBy( curriculumVitaeDTO.getCreatedBy() );
        curriculumVitae.setCreatedDate( curriculumVitaeDTO.getCreatedDate() );
        curriculumVitae.setLastModifiedBy( curriculumVitaeDTO.getLastModifiedBy() );
        curriculumVitae.setLastModifiedDate( curriculumVitaeDTO.getLastModifiedDate() );
        curriculumVitae.setId( curriculumVitaeDTO.getId() );
        curriculumVitae.setLanguageType( curriculumVitaeDTO.getLanguageType() );

        if(curriculumVitaeDTO.getEducations() != null){

        List<Education> educations = curriculumVitaeDTO.getEducations().stream()
           .map(education -> educationMapper.educationDTOToEducation(education))
           .collect(Collectors.toList());

        curriculumVitae.setEducations(educations);
        }

        if(curriculumVitaeDTO.getWorkExperiences() != null){

        List<WorkExperience> workExperiences = curriculumVitaeDTO.getWorkExperiences().stream()
           .map(workExperience -> workExperienceMapper.workExperienceDTOToWorkExperience(workExperience))
           .collect(Collectors.toList());

        curriculumVitae.setWorkExperiences(workExperiences);
        }

        if(curriculumVitaeDTO.getGovernmentWorkExperiences() != null){

        List<GovernmentWorkExperience> governmentWorkExperiences = curriculumVitaeDTO.getGovernmentWorkExperiences().stream()
           .map(governmentWorkExperience -> governmentWorkExperienceMapper.governmentWorkExperienceDTOToGovernmentWorkExperience(governmentWorkExperience))
           .collect(Collectors.toList());

        curriculumVitae.setGovernmentWorkExperiences(governmentWorkExperiences);
        }

        if(curriculumVitaeDTO.getAdditionalStudys() != null){

        List<AdditionalStudy> additionalStudies = curriculumVitaeDTO.getAdditionalStudys().stream()
           .map(additionalStudy -> additionalStudyMapper.additionalStudyDTOToAdditionalStudy(additionalStudy))
           .collect(Collectors.toList());

        curriculumVitae.setAdditionalStudys(additionalStudies);
        }

        if(curriculumVitaeDTO.getAdditionalSkills() != null){

        List<AdditionalSkill> additionalSkills = curriculumVitaeDTO.getAdditionalSkills().stream()
           .map(additionalSkill -> additionalSkillMapper.additionalSkillDTOToAdditionalSkill(additionalSkill))
           .collect(Collectors.toList());

        curriculumVitae.setAdditionalSkills(additionalSkills);
        }

        if(curriculumVitaeDTO.getLanguageSkills()!= null){

        List<LanguageSkill> languageSkills = curriculumVitaeDTO.getLanguageSkills().stream()
           .map(languageSkill -> languageSkillMapper.languageSkillDTOToLanguageSkill(languageSkill))
           .collect(Collectors.toList());

        curriculumVitae.setLanguageSkills(languageSkills);
        }

        return curriculumVitae;
    }

    private Long curriculumVitaeEmployeeId(CurriculumVitae curriculumVitae) {

        if ( curriculumVitae == null ) {
            return null;
        }
        Employee employee = curriculumVitae.getEmployee();
        if ( employee == null ) {
            return null;
        }
        Long id = employee.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
