package com.resourcy.app.web.rest.dto;

import com.resourcy.app.domain.LanguageType;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


public class CurriculumVitaeDTO extends AbstractAuditingEntityDTO implements Serializable {

    private Long id;

    private LanguageType languageType;

    private Long employeeId;

    private List<EducationDTO> educations;
    private List<WorkExperienceDTO> workExperiences;
    private List<GovernmentWorkExperienceDTO> governmentWorkExperiences;
    private List<AdditionalStudyDTO> additionalStudys;
    private List<LanguageSkillDTO> languageSkills;
    private List<AdditionalSkillDTO> additionalSkills;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LanguageType getLanguageType() {
        return languageType;
    }

    public void setLanguageType(LanguageType languageType) {
        this.languageType = languageType;
    }

    public List<EducationDTO> getEducations() {
        return educations;
    }

    public void setEducations(List<EducationDTO> educations) {
        this.educations = educations;
    }

    public List<WorkExperienceDTO> getWorkExperiences() {
        return workExperiences;
    }

    public void setWorkExperiences(List<WorkExperienceDTO> workExperiences) {
        this.workExperiences = workExperiences;
    }

    public List<GovernmentWorkExperienceDTO> getGovernmentWorkExperiences() {
        return governmentWorkExperiences;
    }

    public void setGovernmentWorkExperiences(
       List<GovernmentWorkExperienceDTO> governmentWorkExperiences) {
        this.governmentWorkExperiences = governmentWorkExperiences;
    }

    public List<AdditionalStudyDTO> getAdditionalStudys() {
        return additionalStudys;
    }

    public void setAdditionalStudys(List<AdditionalStudyDTO> additionalStudys) {
        this.additionalStudys = additionalStudys;
    }

    public List<LanguageSkillDTO> getLanguageSkills() {
        return languageSkills;
    }

    public void setLanguageSkills(List<LanguageSkillDTO> languageSkills) {
        this.languageSkills = languageSkills;
    }

    public List<AdditionalSkillDTO> getAdditionalSkills() {
        return additionalSkills;
    }

    public void setAdditionalSkills(List<AdditionalSkillDTO> additionalSkills) {
        this.additionalSkills = additionalSkills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CurriculumVitaeDTO curriculumVitaeDTO = (CurriculumVitaeDTO) o;

        if ( ! Objects.equals(id, curriculumVitaeDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CurriculumVitaeDTO{" +
            "id=" + id +
            '}';
    }
}
