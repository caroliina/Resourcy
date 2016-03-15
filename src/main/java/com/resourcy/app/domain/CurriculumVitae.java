package com.resourcy.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

@Entity
@Table(name = "curriculum_vitae")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "curriculumvitae")
public class CurriculumVitae extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "curriculumVitae")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Education> educations = new HashSet<>();

    @OneToMany(mappedBy = "curriculumVitae")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<WorkExperience> workExperiences = new HashSet<>();

    @OneToMany(mappedBy = "curriculumVitae")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<GovernmentWorkExperience> governmentWorkExperiences = new HashSet<>();

    @OneToMany(mappedBy = "curriculumVitae")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AdditionalStudy> additionalStudys = new HashSet<>();

    @OneToMany(mappedBy = "curriculumVitae")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<LanguageSkill> languageSkills = new HashSet<>();

    @OneToMany(mappedBy = "curriculumVitae")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AdditionalSkill> additionalSkills = new HashSet<>();

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

    public Set<Education> getEducations() {
        return educations;
    }

    public void setEducations(Set<Education> educations) {
        this.educations = educations;
    }

    public Set<WorkExperience> getWorkExperiences() {
        return workExperiences;
    }

    public void setWorkExperiences(Set<WorkExperience> workExperiences) {
        this.workExperiences = workExperiences;
    }

    public Set<GovernmentWorkExperience> getGovernmentWorkExperiences() {
        return governmentWorkExperiences;
    }

    public void setGovernmentWorkExperiences(Set<GovernmentWorkExperience> governmentWorkExperiences) {
        this.governmentWorkExperiences = governmentWorkExperiences;
    }

    public Set<AdditionalStudy> getAdditionalStudys() {
        return additionalStudys;
    }

    public void setAdditionalStudys(Set<AdditionalStudy> additionalStudys) {
        this.additionalStudys = additionalStudys;
    }

    public Set<LanguageSkill> getLanguageSkills() {
        return languageSkills;
    }

    public void setLanguageSkills(Set<LanguageSkill> languageSkills) {
        this.languageSkills = languageSkills;
    }

    public Set<AdditionalSkill> getAdditionalSkills() {
        return additionalSkills;
    }

    public void setAdditionalSkills(Set<AdditionalSkill> additionalSkills) {
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
