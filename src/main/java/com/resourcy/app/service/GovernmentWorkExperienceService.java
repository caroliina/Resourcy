package com.resourcy.app.service;

import com.resourcy.app.domain.GovernmentWorkExperience;
import com.resourcy.app.web.rest.dto.GovernmentWorkExperienceDTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing GovernmentWorkExperience.
 */
public interface GovernmentWorkExperienceService {

    /**
     * Save a governmentWorkExperience.
     * @return the persisted entity
     */
    public GovernmentWorkExperienceDTO save(GovernmentWorkExperienceDTO governmentWorkExperienceDTO);

    /**
     *  get all the governmentWorkExperiences.
     *  @return the list of entities
     */
    public List<GovernmentWorkExperienceDTO> findAll();

    /**
     *  get the "id" governmentWorkExperience.
     *  @return the entity
     */
    public GovernmentWorkExperienceDTO findOne(Long id);

    /**
     *  delete the "id" governmentWorkExperience.
     */
    public void delete(Long id);

    /**
     * search for the governmentWorkExperience corresponding
     * to the query.
     */
    public List<GovernmentWorkExperienceDTO> search(String query);
}
