package com.resourcy.app.service.impl;

import com.resourcy.app.domain.WorkExperience;
import com.resourcy.app.repository.CurriculumVitaeRepository;
import com.resourcy.app.repository.WorkExperienceRepository;
import com.resourcy.app.repository.search.WorkExperienceSearchRepository;
import com.resourcy.app.service.WorkExperienceService;
import com.resourcy.app.web.rest.dto.WorkExperienceDTO;
import com.resourcy.app.web.rest.mapper.WorkExperienceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * Service Implementation for managing WorkExperience.
 */
@Service
@Transactional
public class WorkExperienceServiceImpl implements WorkExperienceService {

   private final Logger log = LoggerFactory.getLogger(WorkExperienceServiceImpl.class);

   @Inject
   private WorkExperienceRepository workExperienceRepository;

   @Inject
   private WorkExperienceMapper workExperienceMapper;

   @Inject
   private WorkExperienceSearchRepository workExperienceSearchRepository;

   @Inject
   private CurriculumVitaeRepository cvRepository;

   /**
    * Save a workExperience.
    *
    * @return the persisted entity
    */
   public WorkExperienceDTO save(WorkExperienceDTO workExperienceDTO) {
      log.debug("Request to save WorkExperience : {}", workExperienceDTO);
      WorkExperience workExperience = workExperienceMapper.workExperienceDTOToWorkExperience(workExperienceDTO);
      workExperience = workExperienceRepository.save(workExperience);
      WorkExperienceDTO result = workExperienceMapper.workExperienceToWorkExperienceDTO(workExperience);
      workExperienceSearchRepository.save(workExperience);
      if (workExperience.getCurriculumVitae() != null) {
         workExperience.getCurriculumVitae().setLastModifiedDate(ZonedDateTime.now(ZoneId.systemDefault()));
      }
      return result;
   }

   /**
    * get all the workExperiences.
    *
    * @return the list of entities
    */
   @Transactional(readOnly = true)
   public List<WorkExperienceDTO> findAll() {
      log.debug("Request to get all WorkExperiences");
      List<WorkExperienceDTO> result = workExperienceRepository.findAll().stream()
         .map(workExperienceMapper::workExperienceToWorkExperienceDTO)
         .collect(Collectors.toCollection(LinkedList::new));
      return result;
   }

   /**
    * get one workExperience by id.
    *
    * @return the entity
    */
   @Transactional(readOnly = true)
   public WorkExperienceDTO findOne(Long id) {
      log.debug("Request to get WorkExperience : {}", id);
      WorkExperience workExperience = workExperienceRepository.findOne(id);
      WorkExperienceDTO workExperienceDTO = workExperienceMapper.workExperienceToWorkExperienceDTO(workExperience);
      return workExperienceDTO;
   }

   /**
    * delete the  workExperience by id.
    */
   public void delete(Long id) {
      log.debug("Request to delete WorkExperience : {}", id);
      workExperienceRepository.delete(id);
      workExperienceSearchRepository.delete(id);
   }

   /**
    * search for the workExperience corresponding
    * to the query.
    */
   @Transactional(readOnly = true)
   public List<WorkExperienceDTO> search(String query) {

      log.debug("REST request to search WorkExperiences for query {}", query);
      return StreamSupport
         .stream(workExperienceSearchRepository.search(queryStringQuery(query)).spliterator(), false)
         .map(workExperienceMapper::workExperienceToWorkExperienceDTO)
         .collect(Collectors.toList());
   }

   @Override
   public WorkExperienceDTO addWorkExperience(WorkExperienceDTO dto) {
      WorkExperience workExperience = workExperienceMapper.workExperienceDTOToWorkExperience(dto);
      if(dto.getCurriculumVitaeId() != null){
         workExperience.setCurriculumVitae(cvRepository.findOne(dto.getCurriculumVitaeId()));
      }
      workExperienceRepository.save(workExperience);
      if (workExperience.getCurriculumVitae() != null) {
         workExperience.getCurriculumVitae().setLastModifiedDate(ZonedDateTime.now(ZoneId.systemDefault()));
      }
      return workExperienceMapper.workExperienceToWorkExperienceDTO(workExperience);
   }
}
