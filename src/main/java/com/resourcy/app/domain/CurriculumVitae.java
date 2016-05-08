package com.resourcy.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "curriculum_vitae")
@Document(indexName = "curriculumvitae")
public class CurriculumVitae extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "language_type")
    private LanguageType languageType;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "curriculumVitae")
    @JsonIgnore
    private List<Education> educations = new ArrayList<>();

    @OneToMany(mappedBy = "curriculumVitae")
    @JsonIgnore
    private List<WorkExperience> workExperiences = new ArrayList<>();

    @OneToMany(mappedBy = "curriculumVitae")
    @JsonIgnore
    private List<GovernmentWorkExperience> governmentWorkExperiences = new ArrayList<>();

    @OneToMany(mappedBy = "curriculumVitae")
    @JsonIgnore
    private List<AdditionalStudy> additionalStudys = new ArrayList<>();

    @OneToMany(mappedBy = "curriculumVitae")
    @JsonIgnore
    private List<LanguageSkill> languageSkills = new ArrayList<>();

    @OneToMany(mappedBy = "curriculumVitae")
    @JsonIgnore
    private List<AdditionalSkill> additionalSkills = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public List<WorkExperience> getWorkExperiences() {
        return workExperiences;
    }

    public void setWorkExperiences(List<WorkExperience> workExperiences) {
        this.workExperiences = workExperiences;
    }

    public List<GovernmentWorkExperience> getGovernmentWorkExperiences() {
        return governmentWorkExperiences;
    }

    public void setGovernmentWorkExperiences(List<GovernmentWorkExperience> governmentWorkExperiences) {
        this.governmentWorkExperiences = governmentWorkExperiences;
    }

    public List<AdditionalStudy> getAdditionalStudys() {
        return additionalStudys;
    }

    public void setAdditionalStudys(List<AdditionalStudy> additionalStudys) {
        this.additionalStudys = additionalStudys;
    }

    public List<LanguageSkill> getLanguageSkills() {
        return languageSkills;
    }

    public void setLanguageSkills(List<LanguageSkill> languageSkills) {
        this.languageSkills = languageSkills;
    }

    public List<AdditionalSkill> getAdditionalSkills() {
        return additionalSkills;
    }

    public void setAdditionalSkills(List<AdditionalSkill> additionalSkills) {
        this.additionalSkills = additionalSkills;
    }

    public LanguageType getLanguageType() {
        return languageType;
    }

    public void setLanguageType(LanguageType languageType) {
        this.languageType = languageType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CurriculumVitae curriculumVitae = (CurriculumVitae) o;
        if(curriculumVitae.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, curriculumVitae.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CurriculumVitae{" +
            "id=" + id +
            '}';
    }
}
