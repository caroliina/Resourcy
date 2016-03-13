package com.resourcy.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Technology.
 */
@Entity
@Table(name = "technology")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "technology")
public class Technology implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type")
    private String type;
    
    @Column(name = "description")
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "government_project_id")
    private GovernmentProject governmentProject;

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

    public GovernmentProject getGovernmentProject() {
        return governmentProject;
    }

    public void setGovernmentProject(GovernmentProject governmentProject) {
        this.governmentProject = governmentProject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Technology technology = (Technology) o;
        if(technology.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, technology.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Technology{" +
            "id=" + id +
            ", type='" + type + "'" +
            ", description='" + description + "'" +
            '}';
    }
}
