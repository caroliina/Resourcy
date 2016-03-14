package com.resourcy.app.web.rest.dto;

import java.io.Serializable;
import java.util.Objects;


/**
 * A DTO for the CurriculumVitae entity.
 */
public class CurriculumVitaeDTO extends AbstractAuditingEntityDTO implements Serializable {

    private Long id;

    private Long employeeId;
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
