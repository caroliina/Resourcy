package com.resourcy.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "government_work_experience")
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

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    private Position position;

    @ManyToOne
    @JoinColumn(name = "curriculum_vitae_id")
    private CurriculumVitae curriculumVitae;

    @OneToOne
    private GovernmentProject governmentProject;

    @OneToMany(mappedBy = "governmentWorkExperience")
    @JsonIgnore
    private List<WorkAssignment> workAssignments = new ArrayList<>();

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

    public List<WorkAssignment> getWorkAssignments() {
        return workAssignments;
    }

    public void setWorkAssignments(List<WorkAssignment> workAssignments) {
        this.workAssignments = workAssignments;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
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
