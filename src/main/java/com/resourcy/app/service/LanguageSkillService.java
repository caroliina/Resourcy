package com.resourcy.app.service;

import com.resourcy.app.domain.LanguageSkill;
import com.resourcy.app.web.rest.dto.LanguageSkillDTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing LanguageSkill.
 */
public interface LanguageSkillService {

    /**
     * Save a languageSkill.
     * @return the persisted entity
     */
    public LanguageSkillDTO save(LanguageSkillDTO languageSkillDTO);

    /**
     *  get all the languageSkills.
     *  @return the list of entities
     */
    public List<LanguageSkillDTO> findAll();

    /**
     *  get the "id" languageSkill.
     *  @return the entity
     */
    public LanguageSkillDTO findOne(Long id);

    /**
     *  delete the "id" languageSkill.
     */
    public void delete(Long id);

    /**
     * search for the languageSkill corresponding
     * to the query.
     */
    public List<LanguageSkillDTO> search(String query);
}
