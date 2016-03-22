package com.resourcy.app.service.impl;

import com.resourcy.app.service.GovernmentWorkExperienceService;
import com.resourcy.app.domain.GovernmentWorkExperience;
import com.resourcy.app.repository.GovernmentWorkExperienceRepository;
import com.resourcy.app.repository.search.GovernmentWorkExperienceSearchRepository;
import com.resourcy.app.web.rest.dto.GovernmentWorkExperienceDTO;
import com.resourcy.app.web.rest.mapper.GovernmentWorkExperienceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing GovernmentWorkExperience.
 */
@Service
@Transactional
public class GovernmentWorkExperienceServiceImpl implements GovernmentWorkExperienceService{

    private final Logger log = LoggerFactory.getLogger(GovernmentWorkExperienceServiceImpl.class);
    
    @Inject
    private GovernmentWorkExperienceRepository governmentWorkExperienceRepository;
    
    @Inject
    private GovernmentWorkExperienceMapper governmentWorkExperienceMapper;
    
    @Inject
    private GovernmentWorkExperienceSearchRepository governmentWorkExperienceSearchRepository;
    
    /**
     * Save a governmentWorkExperience.
     * @return the persisted entity
     */
    public GovernmentWorkExperienceDTO save(GovernmentWorkExperienceDTO governmentWorkExperienceDTO) {
        log.debug("Request to save GovernmentWorkExperience : {}", governmentWorkExperienceDTO);
        GovernmentWorkExperience governmentWorkExperience = governmentWorkExperienceMapper.governmentWorkExperienceDTOToGovernmentWorkExperience(governmentWorkExperienceDTO);
        governmentWorkExperience = governmentWorkExperienceRepository.save(governmentWorkExperience);
        GovernmentWorkExperienceDTO result = governmentWorkExperienceMapper.governmentWorkExperienceToGovernmentWorkExperienceDTO(governmentWorkExperience);
        governmentWorkExperienceSearchRepository.save(governmentWorkExperience);
        governmentWorkExperience.getCurriculumVitae().setLastModifiedDate(ZonedDateTime.now(ZoneId.systemDefault()));
        return result;
    }

    /**
     *  get all the governmentWorkExperiences.
     *  @return the list of entities
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
     *  get one governmentWorkExperience by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public GovernmentWorkExperienceDTO findOne(Long id) {
        log.debug("Request to get GovernmentWorkExperience : {}", id);
        GovernmentWorkExperience governmentWorkExperience = governmentWorkExperienceRepository.findOne(id);
        GovernmentWorkExperienceDTO governmentWorkExperienceDTO = governmentWorkExperienceMapper.governmentWorkExperienceToGovernmentWorkExperienceDTO(governmentWorkExperience);
        return governmentWorkExperienceDTO;
    }

    /**
     *  delete the  governmentWorkExperience by id.
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
}
