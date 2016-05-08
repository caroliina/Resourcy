package com.resourcy.app.web.rest.dto;

import com.resourcy.app.domain.LanguageLevel;

import java.io.Serializable;
import java.util.Objects;


public class LanguageSkillDTO extends AbstractAuditingEntityDTO implements Serializable {

    private Long id;

    private String language;


    private LanguageLevel speaking;


    private LanguageLevel writing;


    private Long curriculumVitaeId;
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

        LanguageSkillDTO languageSkillDTO = (LanguageSkillDTO) o;

        if ( ! Objects.equals(id, languageSkillDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "LanguageSkillDTO{" +
            "id=" + id +
            ", language='" + language + "'" +
            ", speaking='" + speaking + "'" +
            ", writing='" + writing + "'" +
            '}';
    }
}
