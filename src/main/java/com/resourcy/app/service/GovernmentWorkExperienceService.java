package com.resourcy.app.service;

import com.resourcy.app.web.rest.dto.GovernmentWorkExperienceDTO;

import java.util.List;

/**
 * Service Interface for managing GovernmentWorkExperience.
 */
public interface GovernmentWorkExperienceService {

   /**
    * Save a governmentWorkExperience.
    *
    * @return the persisted entity
    */
   GovernmentWorkExperienceDTO save(GovernmentWorkExperienceDTO governmentWorkExperienceDTO);

   /**
    * get all the governmentWorkExperiences.
    *
    * @return the list of entities
    */
   List<GovernmentWorkExperienceDTO> findAll();

   /**
    * get the "id" governmentWorkExperience.
    *
    * @return the entity
    */
   GovernmentWorkExperienceDTO findOne(Long id);

   /**
    * delete the "id" governmentWorkExperience.
    */
   void delete(Long id);

   /**
    * search for the governmentWorkExperience corresponding
    * to the query.
    */
   List<GovernmentWorkExperienceDTO> search(String query);

   GovernmentWorkExperienceDTO addGovernmentWorkExperience(GovernmentWorkExperienceDTO dto);
}
