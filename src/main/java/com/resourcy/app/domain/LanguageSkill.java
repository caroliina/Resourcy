package com.resourcy.app.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "language_skill")
@Document(indexName = "languageskill")
public class LanguageSkill extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "language")
    private String language;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "speaking")
    private LanguageLevel speaking;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "writing")
    private LanguageLevel writing;

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

    public LanguageLevel getSpeaking() {
        return speaking;
    }

    public void setSpeaking(LanguageLevel speaking) {
        this.speaking = speaking;
    }

    public LanguageLevel getWriting() {
        return writing;
    }

    public void setWriting(LanguageLevel writing) {
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
