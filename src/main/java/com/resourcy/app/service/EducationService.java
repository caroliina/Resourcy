package com.resourcy.app.service;

import com.resourcy.app.domain.Education;
import com.resourcy.app.web.rest.dto.EducationDTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing Education.
 */
public interface EducationService {

    /**
     * Save a education.
     * @return the persisted entity
     */
    public EducationDTO save(EducationDTO educationDTO);

    /**
     *  get all the educations.
     *  @return the list of entities
     */
    public List<EducationDTO> findAll();

    /**
     *  get the "id" education.
     *  @return the entity
     */
    public EducationDTO findOne(Long id);

    /**
     *  delete the "id" education.
     */
    public void delete(Long id);

    /**
     * search for the education corresponding
     * to the query.
     */
    public List<EducationDTO> search(String query);
}