package com.resourcy.app.web.rest.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the WorkAssignment entity.
 */
public class WorkAssignmentDTO implements Serializable {

    private Long id;

    private String description;


    private Long workExperienceId;
    private Long governmentWorkExperienceId;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getWorkExperienceId() {
        return workExperienceId;
    }

    public void setWorkExperienceId(Long workExperienceId) {
        this.workExperienceId = workExperienceId;
    }
    public Long getGovernmentWorkExperienceId() {
        return governmentWorkExperienceId;
    }

    public void setGovernmentWorkExperienceId(Long governmentWorkExperienceId) {
        this.governmentWorkExperienceId = governmentWorkExperienceId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WorkAssignmentDTO workAssignmentDTO = (WorkAssignmentDTO) o;

        if ( ! Objects.equals(id, workAssignmentDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "WorkAssignmentDTO{" +
            "id=" + id +
            ", description='" + description + "'" +
            '}';
    }
}
