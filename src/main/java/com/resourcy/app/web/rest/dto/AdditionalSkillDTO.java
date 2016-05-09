package com.resourcy.app.web.rest.dto;

import java.io.Serializable;
import java.util.Objects;


public class AdditionalSkillDTO extends AbstractAuditingEntityDTO implements Serializable {

    private Long id;

    private String type;


    private String description;

    private Double experience;

    private Long curriculumVitaeId;

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

    public Double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public Long getCurriculumVitaeId() {
        return curriculumVitaeId;
    }

    public void setCurriculumVitaeId(Long curriculumVitaeId) {
        this.curriculumVitaeId = curriculumVitaeId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdditionalSkillDTO additionalSkillDTO = (AdditionalSkillDTO) o;

        if ( ! Objects.equals(id, additionalSkillDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "AdditionalSkillDTO{" +
            "id=" + id +
            ", type='" + type + "'" +
            ", description='" + description + "'" +
            '}';
    }
}
