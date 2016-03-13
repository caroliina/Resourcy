package com.resourcy.app.service;

import com.resourcy.app.domain.AdditionalSkill;
import com.resourcy.app.web.rest.dto.AdditionalSkillDTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing AdditionalSkill.
 */
public interface AdditionalSkillService {

    /**
     * Save a additionalSkill.
     * @return the persisted entity
     */
    public AdditionalSkillDTO save(AdditionalSkillDTO additionalSkillDTO);

    /**
     *  get all the additionalSkills.
     *  @return the list of entities
     */
    public List<AdditionalSkillDTO> findAll();

    /**
     *  get the "id" additionalSkill.
     *  @return the entity
     */
    public AdditionalSkillDTO findOne(Long id);

    /**
     *  delete the "id" additionalSkill.
     */
    public void delete(Long id);

    /**
     * search for the additionalSkill corresponding
     * to the query.
     */
    public List<AdditionalSkillDTO> search(String query);
}
