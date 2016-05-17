package com.resourcy.app.service;

import com.resourcy.app.domain.CurriculumVitae;
import com.resourcy.app.service.validator.ValidationException;
import com.resourcy.app.web.rest.dto.CurriculumVitaeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service Interface for managing CurriculumVitae.
 */
public interface CurriculumVitaeService {

   /**
    * Save a curriculumVitae.
    *
    * @return the persisted entity
    */
   CurriculumVitaeDTO save(CurriculumVitaeDTO curriculumVitaeDTO) throws ValidationException;

   /**
    * get all the curriculumVitaes.
    *
    * @return the list of entities
    */
   Page<CurriculumVitae> findAll(Pageable pageable);

   /**
    * get the "id" curriculumVitae.
    *
    * @return the entity
    */
   CurriculumVitaeDTO findOne(Long id);

   /**
    * delete the "id" curriculumVitae.
    */
   void delete(Long id);

   /**
    * search for the curriculumVitae corresponding
    * to the query.
    */
   List<CurriculumVitaeDTO> search(String query);

   CurriculumVitaeDTO getEmployeeCvEst(Long employeeId);
}
