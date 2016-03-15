package com.resourcy.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "language_skill")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "languageskill")
public class LanguageSkill extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "language")
    private String language;

    @Column(name = "speaking")
    private String speaking;

    @Column(name = "writing")
    private String writing;

    @ManyToOne
    @JoinColumn(name = "curriculum_vitae_id")
    private CurriculumVitae curriculumVitae;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSpeaking() {
        return speaking;
    }

    public void setSpeaking(String speaking) {
        this.speaking = speaking;
    }

    public String getWriting() {
        return writing;
    }

    public void setWriting(String writing) {
        this.writing = writing;
    }

    public CurriculumVitae getCurriculumVitae() {
        return curriculumVitae;
    }

    public void setCurriculumVitae(CurriculumVitae curriculumVitae) {
        this.curriculumVitae = curriculumVitae;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LanguageSkill languageSkill = (LanguageSkill) o;
        if(languageSkill.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, languageSkill.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "LanguageSkill{" +
            "id=" + id +
            ", language='" + language + "'" +
            ", speaking='" + speaking + "'" +
            ", writing='" + writing + "'" +
            '}';
    }
}
