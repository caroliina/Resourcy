package com.resourcy.app.service.impl;

import com.resourcy.app.service.EducationService;
import com.resourcy.app.domain.Education;
import com.resourcy.app.repository.CurriculumVitaeRepository;
import com.resourcy.app.repository.EducationRepository;
import com.resourcy.app.repository.search.EducationSearchRepository;
import com.resourcy.app.web.rest.dto.EducationDTO;
import com.resourcy.app.web.rest.mapper.EducationMapper;
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

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Education.
 */
@Service
@Transactional
public class EducationServiceImpl implements EducationService {

    private final Logger log = LoggerFactory.getLogger(EducationServiceImpl.class);

    @Inject
    private EducationRepository educationRepository;

    @Inject
    private EducationMapper educationMapper;

    @Inject
    private EducationSearchRepository educationSearchRepository;

    @Inject
    private CurriculumVitaeRepository cvRepository;

    @Inject
    private UserService userService;


    @Inject
    private ValidatorService educationValidatorService;

    /**
     * Save a education.
     *
     * @return the persisted entity
     */
    public EducationDTO save(EducationDTO educationDTO) {
        log.debug("Request to save Education : {}", educationDTO);
        if (CollectionUtils.isEmpty(educationValidatorService.validate(educationDTO).getErrorMessage())) {
            throw new UnsupportedOperationException();
        }
        Education education = educationMapper.educationDTOToEducation(educationDTO);
        education = educationRepository.save(education);
        EducationDTO result = educationMapper.educationToEducationDTO(education);
        educationSearchRepository.save(education);
        if (education.getCurriculumVitae() != null) {
            education.getCurriculumVitae().setLastModifiedDate(ZonedDateTime.now(ZoneId.systemDefault()));
        }
        return result;
    }

    /**
     * get all the educations.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<EducationDTO> findAll() {
        log.debug("Request to get all Educations");
        List<EducationDTO> result = educationRepository.findAll().stream()
                .map(educationMapper::educationToEducationDTO)
                .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     * get one education by id.
     *
     * @return the entity
     */
    @Transactional(readOnly = true)
    public EducationDTO findOne(Long id) {
        log.debug("Request to get Education : {}", id);
        Education education = educationRepository.findOne(id);
        EducationDTO educationDTO = educationMapper.educationToEducationDTO(education);
        return educationDTO;
    }

    /**
     * delete the  education by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete Education : {}", id);
        educationRepository.delete(id);
        educationSearchRepository.delete(id);
    }

    /**
     * search for the education corresponding
     * to the query.
     */
    @Transactional(readOnly = true)
    public List<EducationDTO> search(String query) {

        log.debug("REST request to search Educations for query {}", query);
        return StreamSupport
                .stream(educationSearchRepository.search(queryStringQuery(query)).spliterator(), false)
                .map(educationMapper::educationToEducationDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public EducationDTO addEducation(EducationDTO educationDTO) {
        Education education = educationMapper.educationDTOToEducation(educationDTO);
        education.setCurriculumVitae(cvRepository.findOne(educationDTO.getCurriculumVitaeId()));
        educationRepository.save(education);
        if (education.getCurriculumVitae() != null) {
            education.getCurriculumVitae().setLastModifiedDate(ZonedDateTime.now(ZoneId.systemDefault()));
        }
        return educationMapper.educationToEducationDTO(education);
    }
}
