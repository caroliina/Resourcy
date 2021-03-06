package com.resourcy.app.service.impl;

import com.resourcy.app.service.AdditionalSkillService;
import com.resourcy.app.domain.AdditionalSkill;
import com.resourcy.app.repository.AdditionalSkillRepository;
import com.resourcy.app.repository.CurriculumVitaeRepository;
import com.resourcy.app.repository.search.AdditionalSkillSearchRepository;
import com.resourcy.app.service.validator.ValidationException;
import com.resourcy.app.service.validator.ValidationResponse;
import com.resourcy.app.service.validator.ValidatorService;
import com.resourcy.app.web.rest.dto.AdditionalSkillDTO;
import com.resourcy.app.web.rest.mapper.AdditionalSkillMapper;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * Service Implementation for managing AdditionalSkill.
 */
@Service
@Transactional
public class AdditionalSkillServiceImpl implements AdditionalSkillService{

    private final Logger log = LoggerFactory.getLogger(AdditionalSkillServiceImpl.class);

    @Inject
    private AdditionalSkillRepository additionalSkillRepository;

    @Inject
    private CurriculumVitaeRepository cvRepository;

    @Inject
    private AdditionalSkillMapper additionalSkillMapper;

    @Inject
    private AdditionalSkillSearchRepository additionalSkillSearchRepository;

    @Inject
    private ValidatorService additionalSkillValidatorService;

    /**
     * Save a additionalSkill.
     * @return the persisted entity
     */
    public AdditionalSkillDTO save(AdditionalSkillDTO additionalSkillDTO) throws ValidationException {
        log.debug("Request to save AdditionalSkill : {}", additionalSkillDTO);
        ValidationResponse validationResponse = additionalSkillValidatorService.validate(additionalSkillDTO);
        if (CollectionUtils.isNotEmpty(validationResponse.getErrorMessage())) {
            throw new ValidationException(validationResponse);
        }
        AdditionalSkill additionalSkill = additionalSkillMapper.additionalSkillDTOToAdditionalSkill(additionalSkillDTO);
        additionalSkill = additionalSkillRepository.save(additionalSkill);
        AdditionalSkillDTO result = additionalSkillMapper.additionalSkillToAdditionalSkillDTO(additionalSkill);
        additionalSkillSearchRepository.save(additionalSkill);
        if (additionalSkill.getCurriculumVitae() != null) {
            additionalSkill.getCurriculumVitae().setLastModifiedDate(ZonedDateTime.now(ZoneId.systemDefault()));
        }
        return result;
    }

    /**
     *  get all the additionalSkills.
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<AdditionalSkillDTO> findAll() {
        log.debug("Request to get all AdditionalSkills");
        List<AdditionalSkillDTO> result = additionalSkillRepository.findAll().stream()
            .map(additionalSkillMapper::additionalSkillToAdditionalSkillDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     *  get one additionalSkill by id.
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public AdditionalSkillDTO findOne(Long id) {
        log.debug("Request to get AdditionalSkill : {}", id);
        AdditionalSkill additionalSkill = additionalSkillRepository.findOne(id);
        AdditionalSkillDTO additionalSkillDTO = additionalSkillMapper.additionalSkillToAdditionalSkillDTO(additionalSkill);
        return additionalSkillDTO;
    }

    /**
     *  delete the  additionalSkill by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete AdditionalSkill : {}", id);
        additionalSkillRepository.delete(id);
        additionalSkillSearchRepository.delete(id);
    }

    /**
     * search for the additionalSkill corresponding
     * to the query.
     */
    @Transactional(readOnly = true)
    public List<AdditionalSkillDTO> search(String query) {

        log.debug("REST request to search AdditionalSkills for query {}", query);
        return StreamSupport
            .stream(additionalSkillSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(additionalSkillMapper::additionalSkillToAdditionalSkillDTO)
            .collect(Collectors.toList());
    }

   @Override
   public AdditionalSkillDTO addSkill(AdditionalSkillDTO dto) {
      AdditionalSkill skill = additionalSkillMapper.additionalSkillDTOToAdditionalSkill(dto);
      if (dto.getCurriculumVitaeId() != null) {
         skill.setCurriculumVitae(cvRepository.findOne(dto.getCurriculumVitaeId()));
      }
      additionalSkillRepository.save(skill);
      if (skill.getCurriculumVitae() != null) {
         skill.getCurriculumVitae().setLastModifiedDate(ZonedDateTime.now(ZoneId.systemDefault()));
      }
      return additionalSkillMapper.additionalSkillToAdditionalSkillDTO(skill);

    }
}
