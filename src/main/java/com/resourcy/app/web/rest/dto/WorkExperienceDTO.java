package com.resourcy.app.web.rest.dto;

import com.resourcy.app.domain.Position;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


public class WorkExperienceDTO extends AbstractAuditingEntityDTO implements Serializable {

    private Long id;

    private Position position;


    private LocalDate periodStart;


    private LocalDate periodEnd;


    private String location;


    private String organization;

    private List<WorkAssignmentDTO> workAssignments;

    private Long curriculumVitaeId;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
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

    public Long getCurriculumVitaeId() {
        return curriculumVitaeId;
    }

    public void setCurriculumVitaeId(Long curriculumVitaeId) {
        this.curriculumVitaeId = curriculumVitaeId;
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

        WorkExperienceDTO workExperienceDTO = (WorkExperienceDTO) o;

        if ( ! Objects.equals(id, workExperienceDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "WorkExperienceDTO{" +
            "id=" + id +
            ", position='" + position + "'" +
            ", periodStart='" + periodStart + "'" +
            ", periodEnd='" + periodEnd + "'" +
            ", location='" + location + "'" +
            ", organization='" + organization + "'" +
            '}';
    }
}
