package com.resourcy.app.service;

import com.resourcy.app.domain.WorkExperience;
import com.resourcy.app.web.rest.dto.WorkExperienceDTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing WorkExperience.
 */
public interface WorkExperienceService {

    /**
     * Save a workExperience.
     * @return the persisted entity
     */
    public WorkExperienceDTO save(WorkExperienceDTO workExperienceDTO);

    /**
     *  get all the workExperiences.
     *  @return the list of entities
     */
    public List<WorkExperienceDTO> findAll();

    /**
     *  get the "id" workExperience.
     *  @return the entity
     */
    public WorkExperienceDTO findOne(Long id);

    /**
     *  delete the "id" workExperience.
     */
    public void delete(Long id);

    /**
     * search for the workExperience corresponding
     * to the query.
     */
    public List<WorkExperienceDTO> search(String query);
}
