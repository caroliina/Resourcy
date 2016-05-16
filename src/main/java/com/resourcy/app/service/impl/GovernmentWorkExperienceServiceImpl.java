package com.resourcy.app.service.impl;

import com.resourcy.app.domain.GovernmentWorkExperience;
import com.resourcy.app.repository.CurriculumVitaeRepository;
import com.resourcy.app.repository.GovernmentProjectRepository;
import com.resourcy.app.repository.GovernmentWorkExperienceRepository;
import com.resourcy.app.repository.search.GovernmentWorkExperienceSearchRepository;
import com.resourcy.app.service.GovernmentWorkExperienceService;
import com.resourcy.app.service.validator.ValidationException;
import com.resourcy.app.service.validator.ValidationResponse;
import com.resourcy.app.service.validator.ValidatorService;
import com.resourcy.app.web.rest.dto.GovernmentWorkExperienceDTO;
import com.resourcy.app.web.rest.mapper.GovernmentWorkExperienceMapper;
import org.apache.commons.collections.CollectionUtils;
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
 * Service Implementation for managing GovernmentWorkExperience.
 */
@Service
@Transactional
public class GovernmentWorkExperienceServiceImpl implements GovernmentWorkExperienceService {

    private final Logger log = LoggerFactory.getLogger(GovernmentWorkExperienceServiceImpl.class);

    @Inject
    private GovernmentWorkExperienceRepository governmentWorkExperienceRepository;

    @Inject
    private GovernmentWorkExperienceMapper governmentWorkExperienceMapper;

    @Inject
    private GovernmentWorkExperienceSearchRepository governmentWorkExperienceSearchRepository;

    @Inject
    private CurriculumVitaeRepository cvRepository;

    @Inject
    private GovernmentProjectRepository govProjectRepository;

    @Inject
    private ValidatorService governmentWorkExperienceValidatorService;
    /**
     * Save a governmentWorkExperience.
     *
     * @return the persisted entity
     */
    public GovernmentWorkExperienceDTO save(GovernmentWorkExperienceDTO governmentWorkExperienceDTO) throws ValidationException {
        log.debug("Request to save GovernmentWorkExperience : {}", governmentWorkExperienceDTO);
        ValidationResponse validationResponse = governmentWorkExperienceValidatorService.validate(governmentWorkExperienceDTO);
        if (CollectionUtils.isNotEmpty(validationResponse.getErrorMessage())) {
            throw new ValidationException(validationResponse);
        }
        GovernmentWorkExperience governmentWorkExperience = governmentWorkExperienceMapper.governmentWorkExperienceDTOToGovernmentWorkExperience(governmentWorkExperienceDTO);
        governmentWorkExperience = governmentWorkExperienceRepository.save(governmentWorkExperience);
        GovernmentWorkExperienceDTO result = governmentWorkExperienceMapper.governmentWorkExperienceToGovernmentWorkExperienceDTO(governmentWorkExperience);
        governmentWorkExperienceSearchRepository.save(governmentWorkExperience);
        if (governmentWorkExperience.getCurriculumVitae() != null) {
            governmentWorkExperience.getCurriculumVitae().setLastModifiedDate(ZonedDateTime.now(ZoneId.systemDefault()));
        }
        return result;
    }

    /**
     * get all the governmentWorkExperiences.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<GovernmentWorkExperienceDTO> findAll() {
        log.debug("Request to get all GovernmentWorkExperiences");
        List<GovernmentWorkExperienceDTO> result = governmentWorkExperienceRepository.findAll().stream()
                .map(governmentWorkExperienceMapper::governmentWorkExperienceToGovernmentWorkExperienceDTO)
                .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

   /**
    * get one governmentWorkExperience by id.
    *
    * @return the entity
    */
   @Transactional(readOnly = true)
   public GovernmentWorkExperienceDTO findOne(Long id) {
      log.debug("Request to get GovernmentWorkExperience : {}", id);
      GovernmentWorkExperience governmentWorkExperience = governmentWorkExperienceRepository.findOne(id);
      GovernmentWorkExperienceDTO governmentWorkExperienceDTO =
         governmentWorkExperienceMapper.governmentWorkExperienceToGovernmentWorkExperienceDTO(governmentWorkExperience);
      return governmentWorkExperienceDTO;
   }

    /**
     * delete the  governmentWorkExperience by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete GovernmentWorkExperience : {}", id);
        governmentWorkExperienceRepository.delete(id);
        governmentWorkExperienceSearchRepository.delete(id);
    }

    /**
     * search for the governmentWorkExperience corresponding
     * to the query.
     */
    @Transactional(readOnly = true)
    public List<GovernmentWorkExperienceDTO> search(String query) {

        log.debug("REST request to search GovernmentWorkExperiences for query {}", query);
        return StreamSupport
                .stream(governmentWorkExperienceSearchRepository.search(queryStringQuery(query)).spliterator(), false)
                .map(governmentWorkExperienceMapper::governmentWorkExperienceToGovernmentWorkExperienceDTO)
                .collect(Collectors.toList());
    }

   @Override
   public GovernmentWorkExperienceDTO addGovernmentWorkExperience(GovernmentWorkExperienceDTO dto) throws ValidationException {
       ValidationResponse validationResponse = governmentWorkExperienceValidatorService.validate(dto);
       if (CollectionUtils.isNotEmpty(validationResponse.getErrorMessage())) {
           throw new ValidationException(validationResponse);
       }

      GovernmentWorkExperience govWorkExperience =
         governmentWorkExperienceMapper.governmentWorkExperienceDTOToGovernmentWorkExperience(dto);
      if (dto.getCurriculumVitaeId() != null) {
         govWorkExperience.setCurriculumVitae(cvRepository.findOne(dto.getCurriculumVitaeId()));
      }
      if (dto.getGovernmentProject() != null) {
         govWorkExperience.setGovernmentProject(govProjectRepository.findOne(dto.getGovernmentProject().getId()));
      }
      governmentWorkExperienceRepository.save(govWorkExperience);
      if (govWorkExperience.getCurriculumVitae() != null) {
         govWorkExperience.getCurriculumVitae().setLastModifiedDate(ZonedDateTime.now(ZoneId.systemDefault()));
      }
      return governmentWorkExperienceMapper.governmentWorkExperienceToGovernmentWorkExperienceDTO(govWorkExperience);
   }
}
