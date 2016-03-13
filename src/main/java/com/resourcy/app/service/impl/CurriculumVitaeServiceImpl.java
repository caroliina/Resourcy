package com.resourcy.app.service.impl;

import com.resourcy.app.service.CurriculumVitaeService;
import com.resourcy.app.domain.CurriculumVitae;
import com.resourcy.app.repository.CurriculumVitaeRepository;
import com.resourcy.app.repository.search.CurriculumVitaeSearchRepository;
import com.resourcy.app.web.rest.dto.CurriculumVitaeDTO;
import com.resourcy.app.web.rest.mapper.CurriculumVitaeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing CurriculumVitae.
 */
@Service
@Transactional
public class CurriculumVitaeServiceImpl implements CurriculumVitaeService{

    private final Logger log = LoggerFactory.getLogger(CurriculumVitaeServiceImpl.class);
    
    @Inject
    private CurriculumVitaeRepository curriculumVitaeRepository;
    
    @Inject
    private CurriculumVitaeMapper curriculumVitaeMapper;
    
    @Inject
    private CurriculumVitaeSearchRepository curriculumVitaeSearchRepository;
    
    /**
     * Save a curriculumVitae.
     * @return the persisted entity
     */
    public CurriculumVitaeDTO save(CurriculumVitaeDTO curriculumVitaeDTO) {
        log.debug("Request to save CurriculumVitae : {}", curriculumVitaeDTO);
        CurriculumVitae curriculumVitae = curriculumVitaeMapper.curriculumVitaeDTOToCurriculumVitae(curriculumVitaeDTO);
        curriculumVitae = curriculumVitaeRepository.save(curriculumVitae);
        CurriculumVitaeDTO result = curriculumVitaeMapper.curriculumVitaeToCurriculumVitaeDTO(curriculumVitae);
        curriculumVitaeSearchRepository.save(curriculumVitae);
        return result;
    }

    /**
     *  get all the curriculumVitaes.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<CurriculumVitae> findAll(Pageable pageable) {
        log.debug("Request to get all CurriculumVitaes");
        Page<CurriculumVitae> result = curriculumVitaeRepository.findAll(pageable); 
        return result;
    }

    /**
     *  get one curriculumVitae by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public CurriculumVitaeDTO findOne(Long id) {
        log.debug("Request to get CurriculumVitae : {}", id);
        CurriculumVitae curriculumVitae = curriculumVitaeRepository.findOne(id);
        CurriculumVitaeDTO curriculumVitaeDTO = curriculumVitaeMapper.curriculumVitaeToCurriculumVitaeDTO(curriculumVitae);
        return curriculumVitaeDTO;
    }

    /**
     *  delete the  curriculumVitae by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete CurriculumVitae : {}", id);
        curriculumVitaeRepository.delete(id);
        curriculumVitaeSearchRepository.delete(id);
    }

    /**
     * search for the curriculumVitae corresponding
     * to the query.
     */
    @Transactional(readOnly = true) 
    public List<CurriculumVitaeDTO> search(String query) {
        
        log.debug("REST request to search CurriculumVitaes for query {}", query);
        return StreamSupport
            .stream(curriculumVitaeSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(curriculumVitaeMapper::curriculumVitaeToCurriculumVitaeDTO)
            .collect(Collectors.toList());
    }
}
