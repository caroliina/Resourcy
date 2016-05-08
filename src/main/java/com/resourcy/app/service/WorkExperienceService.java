package com.resourcy.app.service;

import com.resourcy.app.web.rest.dto.WorkExperienceDTO;

import java.util.List;

/**
 * Service Interface for managing WorkExperience.
 */
public interface WorkExperienceService {

   /**
    * Save a workExperience.
    *
    * @return the persisted entity
    */
   WorkExperienceDTO save(WorkExperienceDTO workExperienceDTO);

   /**
    * get all the workExperiences.
    *
    * @return the list of entities
    */
   List<WorkExperienceDTO> findAll();

   /**
    * get the "id" workExperience.
    *
    * @return the entity
    */
   WorkExperienceDTO findOne(Long id);

   /**
    * delete the "id" workExperience.
    */
   void delete(Long id);

   /**
    * search for the workExperience corresponding
    * to the query.
    */
   List<WorkExperienceDTO> search(String query);

   WorkExperienceDTO addWorkExperience(WorkExperienceDTO dto);
}
