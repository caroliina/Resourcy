package com.resourcy.app.web.rest.dto;

import java.io.Serializable;
import java.util.Objects;


/**
 * A DTO for the Technology entity.
 */
public class TechnologyDTO extends AbstractAuditingEntityDTO implements Serializable {

    private Long id;

    private String type;


    private String description;


    private Long governmentProjectId;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getGovernmentProjectId() {
        return governmentProjectId;
    }

    public void setGovernmentProjectId(Long governmentProjectId) {
        this.governmentProjectId = governmentProjectId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TechnologyDTO technologyDTO = (TechnologyDTO) o;

        if ( ! Objects.equals(id, technologyDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TechnologyDTO{" +
            "id=" + id +
            ", type='" + type + "'" +
            ", description='" + description + "'" +
            '}';
    }
}
