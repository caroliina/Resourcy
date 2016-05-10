package com.resourcy.app.service;

import com.resourcy.app.web.rest.dto.GovernmentProjectDTO;

import java.util.List;

/**
 * Service Interface for managing GovernmentProject.
 */
public interface GovernmentProjectService {

   /**
    * Save a governmentProject.
    *
    * @return the persisted entity
    */
   GovernmentProjectDTO save(GovernmentProjectDTO governmentProjectDTO);

   /**
    * get all the governmentProjects.
    *
    * @return the list of entities
    */
   List<GovernmentProjectDTO> findAll();

   /**
    * get all the governmentProjects where GovernmentWorkExperience is null.
    *
    * @return the list of entities
    */
   List<GovernmentProjectDTO> findAllWhereGovernmentWorkExperienceIsNull();

   /**
    * get the "id" governmentProject.
    *
    * @return the entity
    */
   GovernmentProjectDTO findOne(Long id);

   /**
    * delete the "id" governmentProject.
    */
   void delete(Long id);

   /**
    * search for the governmentProject corresponding
    * to the query.
    */
   List<GovernmentProjectDTO> search(String query);

   GovernmentProjectDTO addGovernmentProject(GovernmentProjectDTO dto);
}
