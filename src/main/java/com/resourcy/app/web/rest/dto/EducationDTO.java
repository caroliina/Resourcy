package com.resourcy.app.web.rest.dto;

import com.resourcy.app.domain.EducationDegrees;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


public class EducationDTO extends AbstractAuditingEntityDTO implements Serializable {

    private Long id;

    private String institution;


    private LocalDate periodStart;


    private LocalDate periodEnd;


    private String speciality;


    private EducationDegrees degree;

    private Long curriculumVitaeId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
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
    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
    public EducationDegrees getDegree() {
        return degree;
    }

    public void setDegree(EducationDegrees degree) {
        this.degree = degree;
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

        EducationDTO educationDTO = (EducationDTO) o;

        if ( ! Objects.equals(id, educationDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EducationDTO{" +
            "id=" + id +
            ", institution='" + institution + "'" +
            ", periodStart='" + periodStart + "'" +
            ", periodEnd='" + periodEnd + "'" +
            ", speciality='" + speciality + "'" +
            ", degree='" + degree + "'" +
            '}';
    }
}
