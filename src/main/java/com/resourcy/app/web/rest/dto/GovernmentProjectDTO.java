package com.resourcy.app.web.rest.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


public class GovernmentProjectDTO extends AbstractAuditingEntityDTO  implements Serializable {

    private Long id;

    private String buyer;


    private String serviceName;

    private Integer totalProjectWorkHours;

    private List<TechnologyDTO> technologies;

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

    public List<TechnologyDTO> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<TechnologyDTO> technologies) {
        this.technologies = technologies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GovernmentProjectDTO governmentProjectDTO = (GovernmentProjectDTO) o;

        if ( ! Objects.equals(id, governmentProjectDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "GovernmentProjectDTO{" +
            "id=" + id +
            ", buyer='" + buyer + "'" +
            ", serviceName='" + serviceName + "'" +
            ", totalProjectWorkHours='" + totalProjectWorkHours + "'" +
            '}';
    }
}
