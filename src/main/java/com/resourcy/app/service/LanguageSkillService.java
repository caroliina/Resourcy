package com.resourcy.app.service;

import com.resourcy.app.web.rest.dto.LanguageSkillDTO;

import java.util.List;

/**
 * Service Interface for managing LanguageSkill.
 */
public interface LanguageSkillService {

   /**
    * Save a languageSkill.
    *
    * @return the persisted entity
    */
   LanguageSkillDTO save(LanguageSkillDTO languageSkillDTO);

   /**
    * get all the languageSkills.
    *
    * @return the list of entities
    */
   List<LanguageSkillDTO> findAll();

   /**
    * get the "id" languageSkill.
    *
    * @return the entity
    */
   LanguageSkillDTO findOne(Long id);

   /**
    * delete the "id" languageSkill.
    */
   void delete(Long id);

   /**
    * search for the languageSkill corresponding
    * to the query.
    */
   List<LanguageSkillDTO> search(String query);

   LanguageSkillDTO addLanguage(LanguageSkillDTO languageSkillDTO);
}
