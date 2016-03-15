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

@Entity
@Table(name = "work_experience")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "workexperience")
public class WorkExperience extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "position")
    private String position;

    @Column(name = "period_start")
    private LocalDate periodStart;

    @Column(name = "period_end")
    private LocalDate periodEnd;

    @Column(name = "location")
    private String location;

    @Column(name = "organization")
    private String organization;

    @ManyToOne
    @JoinColumn(name = "curriculum_vitae_id")
    private CurriculumVitae curriculumVitae;

    @OneToMany(mappedBy = "workExperience")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<WorkAssignment> workAssignments = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public CurriculumVitae getCurriculumVitae() {
        return curriculumVitae;
    }

    public void setCurriculumVitae(CurriculumVitae curriculumVitae) {
        this.curriculumVitae = curriculumVitae;
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
        WorkExperience workExperience = (WorkExperience) o;
        if(workExperience.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, workExperience.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "WorkExperience{" +
            "id=" + id +
            ", position='" + position + "'" +
            ", periodStart='" + periodStart + "'" +
            ", periodEnd='" + periodEnd + "'" +
            ", location='" + location + "'" +
            ", organization='" + organization + "'" +
            '}';
    }
}
