package com.resourcy.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "government_project")
@Document(indexName = "governmentproject")
public class GovernmentProject extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "buyer")
    private String buyer;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "total_project_work_hours")
    private Integer totalProjectWorkHours;

    @OneToOne(mappedBy = "governmentProject")
    @JsonIgnore
    private GovernmentWorkExperience governmentWorkExperience;

    @OneToMany(mappedBy = "governmentProject")
    @JsonIgnore
    private List<Technology> technologys = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getTotalProjectWorkHours() {
        return totalProjectWorkHours;
    }

    public void setTotalProjectWorkHours(Integer totalProjectWorkHours) {
        this.totalProjectWorkHours = totalProjectWorkHours;
    }

    public GovernmentWorkExperience getGovernmentWorkExperience() {
        return governmentWorkExperience;
    }

    public void setGovernmentWorkExperience(GovernmentWorkExperience governmentWorkExperience) {
        this.governmentWorkExperience = governmentWorkExperience;
    }

    public List<Technology> getTechnologys() {
        return technologys;
    }

    public void setTechnologys(List<Technology> technologys) {
        this.technologys = technologys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GovernmentProject governmentProject = (GovernmentProject) o;
        if(governmentProject.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, governmentProject.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "GovernmentProject{" +
            "id=" + id +
            ", buyer='" + buyer + "'" +
            ", serviceName='" + serviceName + "'" +
            ", totalProjectWorkHours='" + totalProjectWorkHours + "'" +
            '}';
    }
}
