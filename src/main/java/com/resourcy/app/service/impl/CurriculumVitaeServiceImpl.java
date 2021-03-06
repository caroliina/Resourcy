package com.resourcy.app.service.impl;

import com.resourcy.app.domain.CurriculumVitae;
import com.resourcy.app.domain.LanguageType;
import com.resourcy.app.repository.CurriculumVitaeRepository;
import com.resourcy.app.repository.search.CurriculumVitaeSearchRepository;
import com.resourcy.app.service.CurriculumVitaeService;
import com.resourcy.app.service.UserService;
import com.resourcy.app.service.validator.ValidationException;
import com.resourcy.app.service.validator.ValidationResponse;
import com.resourcy.app.service.validator.ValidatorService;
import com.resourcy.app.web.rest.dto.CurriculumVitaeDTO;
import com.resourcy.app.web.rest.mapper.CurriculumVitaeMapper;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

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

    @Inject
    private UserService userService;

    @Inject
    private ValidatorService curriculumVitaeValidatorService;

    /**
     * Save a curriculumVitae.
     * @return the persisted entity
     */
    public CurriculumVitaeDTO save(CurriculumVitaeDTO curriculumVitaeDTO) throws ValidationException {
        log.debug("Request to save CurriculumVitae : {}", curriculumVitaeDTO);
        ValidationResponse validationResponse = curriculumVitaeValidatorService.validate(curriculumVitaeDTO);
        if (CollectionUtils.isNotEmpty(validationResponse.getErrorMessage())) {
            throw new ValidationException(validationResponse);
        }
        CurriculumVitae curriculumVitae = curriculumVitaeMapper.curriculumVitaeDTOToCurriculumVitae(curriculumVitaeDTO);
        curriculumVitae.setEmployee(userService.getUserWithAuthorities().getEmployee());
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


    @Transactional(readOnly = true)
    public CurriculumVitaeDTO getEmployeeCvEst() {

        CurriculumVitae result = curriculumVitaeRepository
           .findByEmployeeIdAndLanguageType(userService.getUserWithAuthorities().getEmployee().getId(), LanguageType.EST);

        return curriculumVitaeMapper.curriculumVitaeToCurriculumVitaeDTO(result);
    }
}
