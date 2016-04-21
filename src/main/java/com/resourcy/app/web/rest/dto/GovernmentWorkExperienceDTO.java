package com.resourcy.app.web.rest.dto;

import com.resourcy.app.domain.Position;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


public class GovernmentWorkExperienceDTO extends AbstractAuditingEntityDTO implements Serializable {

    private Long id;

    private LocalDate periodStart;

    private LocalDate periodEnd;

    private Integer personalWorkHours;

    private Position position;

    private List<WorkAssignmentDTO> workAssignments;

    private Long curriculumVitaeId;

    private GovernmentProjectDTO governmentProject;

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

    public Long getCurriculumVitaeId() {
        return curriculumVitaeId;
    }

    public void setCurriculumVitaeId(Long curriculumVitaeId) {
        this.curriculumVitaeId = curriculumVitaeId;
    }

    public GovernmentProjectDTO getGovernmentProject() {
        return governmentProject;
    }

    public void setGovernmentProject(GovernmentProjectDTO governmentProject) {
        this.governmentProject = governmentProject;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<WorkAssignmentDTO> getWorkAssignments() {
        return workAssignments;
    }

    public void setWorkAssignments(List<WorkAssignmentDTO> workAssignments) {
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

        GovernmentWorkExperienceDTO governmentWorkExperienceDTO = (GovernmentWorkExperienceDTO) o;

        if ( ! Objects.equals(id, governmentWorkExperienceDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "GovernmentWorkExperienceDTO{" +
            "id=" + id +
            ", periodStart='" + periodStart + "'" +
            ", periodEnd='" + periodEnd + "'" +
            ", personalWorkHours='" + personalWorkHours + "'" +
            '}';
    }
}
