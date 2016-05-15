package com.resourcy.app.service;

import com.resourcy.app.web.rest.dto.GovernmentProjectDTO;

import java.util.List;

/**
 * Service Interface for managing GovernmentProject.
 */
public interface GovernmentProjectService {

    /**
     * Save a governmentProject.
     * @return the persisted entity
     */
    public GovernmentProjectDTO save(GovernmentProjectDTO governmentProjectDTO) throws ValidationException;

    /**
     *  get all the governmentProjects.
     *  @return the list of entities
     */
    public List<GovernmentProjectDTO> findAll();
    /**
     *  get all the governmentProjects where GovernmentWorkExperience is null.
     *  @return the list of entities
     */
    public List<GovernmentProjectDTO> findAllWhereGovernmentWorkExperienceIsNull();

    /**
     *  get the "id" governmentProject.
     *  @return the entity
     */
    public GovernmentProjectDTO findOne(Long id);

    /**
     *  delete the "id" governmentProject.
     */
    public void delete(Long id);

    /**
     * search for the governmentProject corresponding
     * to the query.
     */
    public List<GovernmentProjectDTO> search(String query);

    GovernmentProjectDTO addGovernmentProject(GovernmentProjectDTO dto);
}
