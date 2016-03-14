package com.resourcy.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A GovernmentWorkExperience.
 */
@Entity
@Table(name = "government_work_experience")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "governmentworkexperience")
public class GovernmentWorkExperience extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "period_start")
    private LocalDate periodStart;

    @Column(name = "period_end")
    private LocalDate periodEnd;

    @Column(name = "personal_work_hours")
    private Integer personalWorkHours;

    @ManyToOne
    @JoinColumn(name = "curriculum_vitae_id")
    private CurriculumVitae curriculumVitae;

    @OneToOne
    private GovernmentProject governmentProject;

    @OneToMany(mappedBy = "governmentWorkExperience")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<WorkAssignment> workAssignments = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(LocalDate periodStart) {
        this.periodStart = periodStart;
    }

    public LocalDate getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(LocalDate periodEnd) {
        this.periodEnd = periodEnd;
    }

    public Integer getPersonalWorkHours() {
        return personalWorkHours;
    }

    public void setPersonalWorkHours(Integer personalWorkHours) {
        this.personalWorkHours = personalWorkHours;
    }

    public CurriculumVitae getCurriculumVitae() {
        return curriculumVitae;
    }

    public void setCurriculumVitae(CurriculumVitae curriculumVitae) {
        this.curriculumVitae = curriculumVitae;
    }

    public GovernmentProject getGovernmentProject() {
        return governmentProject;
    }

    public void setGovernmentProject(GovernmentProject governmentProject) {
        this.governmentProject = governmentProject;
    }

    public Set<WorkAssignment> getWorkAssignments() {
        return workAssignments;
    }

    public void setWorkAssignments(Set<WorkAssignment> workAssignments) {
        this.workAssignments = workAssignments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GovernmentWorkExperience governmentWorkExperience = (GovernmentWorkExperience) o;
        if(governmentWorkExperience.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, governmentWorkExperience.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "GovernmentWorkExperience{" +
            "id=" + id +
            ", periodStart='" + periodStart + "'" +
            ", periodEnd='" + periodEnd + "'" +
            ", personalWorkHours='" + personalWorkHours + "'" +
            '}';
    }
}
