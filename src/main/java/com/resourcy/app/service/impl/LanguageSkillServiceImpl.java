package com.resourcy.app.service.impl;

import com.resourcy.app.service.LanguageSkillService;
import com.resourcy.app.domain.LanguageSkill;
import com.resourcy.app.repository.LanguageSkillRepository;
import com.resourcy.app.repository.search.LanguageSkillSearchRepository;
import com.resourcy.app.web.rest.dto.LanguageSkillDTO;
import com.resourcy.app.web.rest.mapper.LanguageSkillMapper;
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
 * Service Implementation for managing LanguageSkill.
 */
@Service
@Transactional
public class LanguageSkillServiceImpl implements LanguageSkillService{

    private final Logger log = LoggerFactory.getLogger(LanguageSkillServiceImpl.class);
    
    @Inject
    private LanguageSkillRepository languageSkillRepository;
    
    @Inject
    private LanguageSkillMapper languageSkillMapper;
    
    @Inject
    private LanguageSkillSearchRepository languageSkillSearchRepository;
    
    /**
     * Save a languageSkill.
     * @return the persisted entity
     */
    public LanguageSkillDTO save(LanguageSkillDTO languageSkillDTO) {
        log.debug("Request to save LanguageSkill : {}", languageSkillDTO);
        LanguageSkill languageSkill = languageSkillMapper.languageSkillDTOToLanguageSkill(languageSkillDTO);
        languageSkill = languageSkillRepository.save(languageSkill);
        LanguageSkillDTO result = languageSkillMapper.languageSkillToLanguageSkillDTO(languageSkill);
        languageSkillSearchRepository.save(languageSkill);
        languageSkill.getCurriculumVitae().setLastModifiedDate(ZonedDateTime.now(ZoneId.systemDefault()));
        return result;
    }

    /**
     *  get all the languageSkills.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<LanguageSkillDTO> findAll() {
        log.debug("Request to get all LanguageSkills");
        List<LanguageSkillDTO> result = languageSkillRepository.findAll().stream()
            .map(languageSkillMapper::languageSkillToLanguageSkillDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     *  get one languageSkill by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public LanguageSkillDTO findOne(Long id) {
        log.debug("Request to get LanguageSkill : {}", id);
        LanguageSkill languageSkill = languageSkillRepository.findOne(id);
        LanguageSkillDTO languageSkillDTO = languageSkillMapper.languageSkillToLanguageSkillDTO(languageSkill);
        return languageSkillDTO;
    }

    /**
     *  delete the  languageSkill by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete LanguageSkill : {}", id);
        languageSkillRepository.delete(id);
        languageSkillSearchRepository.delete(id);
    }

    /**
     * search for the languageSkill corresponding
     * to the query.
     */
    @Transactional(readOnly = true) 
    public List<LanguageSkillDTO> search(String query) {
        
        log.debug("REST request to search LanguageSkills for query {}", query);
        return StreamSupport
            .stream(languageSkillSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(languageSkillMapper::languageSkillToLanguageSkillDTO)
            .collect(Collectors.toList());
    }
}
