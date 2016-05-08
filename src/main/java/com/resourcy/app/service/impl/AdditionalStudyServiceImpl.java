package com.resourcy.app.service.impl;

import com.resourcy.app.service.AdditionalStudyService;
import com.resourcy.app.domain.AdditionalStudy;
import com.resourcy.app.repository.AdditionalStudyRepository;
import com.resourcy.app.repository.search.AdditionalStudySearchRepository;
import com.resourcy.app.service.validator.ValidatorService;
import com.resourcy.app.web.rest.dto.AdditionalStudyDTO;
import com.resourcy.app.web.rest.mapper.AdditionalStudyMapper;
import org.apache.commons.collections.CollectionUtils;
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
 * Service Implementation for managing AdditionalStudy.
 */
@Service
@Transactional
public class AdditionalStudyServiceImpl implements AdditionalStudyService{

    private final Logger log = LoggerFactory.getLogger(AdditionalStudyServiceImpl.class);

    @Inject
    private AdditionalStudyRepository additionalStudyRepository;

    @Inject
    private AdditionalStudyMapper additionalStudyMapper;

    @Inject
    private AdditionalStudySearchRepository additionalStudySearchRepository;

    @Inject
    private ValidatorService additionalStudyValidatorService;
    /**
     * Save a additionalStudy.
     * @return the persisted entity
     */
    public AdditionalStudyDTO save(AdditionalStudyDTO additionalStudyDTO) {
        log.debug("Request to save AdditionalStudy : {}", additionalStudyDTO);
        if (CollectionUtils.isEmpty(additionalStudyValidatorService.validate(additionalStudyDTO).getErrorMessage())) {
            throw new UnsupportedOperationException();
        }
        AdditionalStudy additionalStudy = additionalStudyMapper.additionalStudyDTOToAdditionalStudy(additionalStudyDTO);
        additionalStudy = additionalStudyRepository.save(additionalStudy);
        AdditionalStudyDTO result = additionalStudyMapper.additionalStudyToAdditionalStudyDTO(additionalStudy);
        additionalStudySearchRepository.save(additionalStudy);
        if (additionalStudy.getCurriculumVitae() != null) {
            additionalStudy.getCurriculumVitae().setLastModifiedDate(ZonedDateTime.now(ZoneId.systemDefault()));
        }
        return result;
    }

    /**
     *  get all the additionalStudys.
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<AdditionalStudyDTO> findAll() {
        log.debug("Request to get all AdditionalStudys");
        List<AdditionalStudyDTO> result = additionalStudyRepository.findAll().stream()
            .map(additionalStudyMapper::additionalStudyToAdditionalStudyDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     *  get one additionalStudy by id.
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public AdditionalStudyDTO findOne(Long id) {
        log.debug("Request to get AdditionalStudy : {}", id);
        AdditionalStudy additionalStudy = additionalStudyRepository.findOne(id);
        AdditionalStudyDTO additionalStudyDTO = additionalStudyMapper.additionalStudyToAdditionalStudyDTO(additionalStudy);
        return additionalStudyDTO;
    }

    /**
     *  delete the  additionalStudy by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete AdditionalStudy : {}", id);
        additionalStudyRepository.delete(id);
        additionalStudySearchRepository.delete(id);
    }

    /**
     * search for the additionalStudy corresponding
     * to the query.
     */
    @Transactional(readOnly = true)
    public List<AdditionalStudyDTO> search(String query) {

        log.debug("REST request to search AdditionalStudys for query {}", query);
        return StreamSupport
            .stream(additionalStudySearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(additionalStudyMapper::additionalStudyToAdditionalStudyDTO)
            .collect(Collectors.toList());
    }
}
