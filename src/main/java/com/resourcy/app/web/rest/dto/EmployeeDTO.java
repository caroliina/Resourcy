package com.resourcy.app.web.rest.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


public class EmployeeDTO extends AbstractAuditingEntityDTO implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate birthday;

    private String nationality;

    private String email;

    private String idCode;

    private Long userId;

    private List<CurriculumVitaeDTO> cvList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long user) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmployeeDTO employeeDTO = (EmployeeDTO) o;

        if ( ! Objects.equals(id, employeeDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
            "id=" + id +
            ", firstName='" + firstName + "'" +
            ", lastName='" + lastName + "'" +
            ", birthday='" + birthday + "'" +
            ", nationality='" + nationality + "'" +
            ", email='" + email + "'" +
            '}';
    }

    public List<CurriculumVitaeDTO> getCvList() {
        return cvList;
    }

    public void setCvList(List<CurriculumVitaeDTO> cvList) {
        this.cvList = cvList;
    }
}
