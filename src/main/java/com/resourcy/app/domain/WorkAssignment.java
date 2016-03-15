package com.resourcy.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "work_assignment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "workassignment")
public class WorkAssignment extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "work_experience_id")
    private WorkExperience workExperience;

    @ManyToOne
    @JoinColumn(name = "government_work_experience_id")
    private GovernmentWorkExperience governmentWorkExperience;

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

    public WorkExperience getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(WorkExperience workExperience) {
        this.workExperience = workExperience;
    }

    public GovernmentWorkExperience getGovernmentWorkExperience() {
        return governmentWorkExperience;
    }

    public void setGovernmentWorkExperience(GovernmentWorkExperience governmentWorkExperience) {
        this.governmentWorkExperience = governmentWorkExperience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WorkAssignment workAssignment = (WorkAssignment) o;
        if(workAssignment.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, workAssignment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "WorkAssignment{" +
            "id=" + id +
            ", description='" + description + "'" +
            '}';
    }
}
