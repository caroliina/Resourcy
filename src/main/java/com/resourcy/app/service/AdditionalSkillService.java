package com.resourcy.app.service;

import com.resourcy.app.web.rest.dto.AdditionalSkillDTO;

import java.util.List;

/**
 * Service Interface for managing AdditionalSkill.
 */
public interface AdditionalSkillService {

   /**
    * Save a additionalSkill.
    *
    * @return the persisted entity
    */
   AdditionalSkillDTO save(AdditionalSkillDTO additionalSkillDTO);

   /**
    * get all the additionalSkills.
    *
    * @return the list of entities
    */
   List<AdditionalSkillDTO> findAll();

   /**
    * get the "id" additionalSkill.
    *
    * @return the entity
    */
   AdditionalSkillDTO findOne(Long id);

   /**
    * delete the "id" additionalSkill.
    */
   void delete(Long id);

   /**
    * search for the additionalSkill corresponding
    * to the query.
    */
   List<AdditionalSkillDTO> search(String query);

   AdditionalSkillDTO addSkill(AdditionalSkillDTO additionalSkillDTO);
}
