package com.resourcy.app.service.impl;

import com.resourcy.app.domain.GovernmentProject;
import com.resourcy.app.repository.GovernmentProjectRepository;
import com.resourcy.app.repository.GovernmentWorkExperienceRepository;
import com.resourcy.app.repository.search.GovernmentProjectSearchRepository;
import com.resourcy.app.service.GovernmentProjectService;
import com.resourcy.app.web.rest.dto.GovernmentProjectDTO;
import com.resourcy.app.web.rest.mapper.GovernmentProjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * Service Implementation for managing GovernmentProject.
 */
@Service
@Transactional
public class GovernmentProjectServiceImpl implements GovernmentProjectService{

    private final Logger log = LoggerFactory.getLogger(GovernmentProjectServiceImpl.class);

    @Inject
    private GovernmentProjectRepository governmentProjectRepository;

    @Inject
    private GovernmentProjectMapper governmentProjectMapper;

    @Inject
    private GovernmentProjectSearchRepository governmentProjectSearchRepository;

    @Inject
    private GovernmentWorkExperienceRepository govWorkExperienceRepository;

    @Inject
    private ValidatorService governmentProjectValidatorService;

    /**
     * Save a governmentProject.
     * @return the persisted entity
     */
    public GovernmentProjectDTO save(GovernmentProjectDTO governmentProjectDTO) throws ValidationException {
        log.debug("Request to save GovernmentProject : {}", governmentProjectDTO);
        ValidationResponse validationResponse = governmentProjectValidatorService.validate(governmentProjectDTO);
        if (CollectionUtils.isNotEmpty(validationResponse.getErrorMessage())) {
            throw new ValidationException(validationResponse);
        }
        GovernmentProject governmentProject = governmentProjectMapper.governmentProjectDTOToGovernmentProject(governmentProjectDTO);
        governmentProject = governmentProjectRepository.save(governmentProject);
        GovernmentProjectDTO result = governmentProjectMapper.governmentProjectToGovernmentProjectDTO(governmentProject);
        governmentProjectSearchRepository.save(governmentProject);
        return result;
    }

    /**
     *  get all the governmentProjects.
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<GovernmentProjectDTO> findAll() {
        log.debug("Request to get all GovernmentProjects");
        List<GovernmentProjectDTO> result = governmentProjectRepository.findAll().stream()
            .map(governmentProjectMapper::governmentProjectToGovernmentProjectDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }


    /**
     *  get all the governmentProjects where GovernmentWorkExperience is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<GovernmentProjectDTO> findAllWhereGovernmentWorkExperienceIsNull() {
        log.debug("Request to get all governmentProjects where GovernmentWorkExperience is null");
        return StreamSupport
            .stream(governmentProjectRepository.findAll().spliterator(), false)
            .filter(governmentProject -> governmentProject.getGovernmentWorkExperience() == null)
            .map(governmentProjectMapper::governmentProjectToGovernmentProjectDTO)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  get one governmentProject by id.
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public GovernmentProjectDTO findOne(Long id) {
        log.debug("Request to get GovernmentProject : {}", id);
        GovernmentProject governmentProject = governmentProjectRepository.findOne(id);
        GovernmentProjectDTO governmentProjectDTO = governmentProjectMapper.governmentProjectToGovernmentProjectDTO(governmentProject);
        return governmentProjectDTO;
    }

    /**
     *  delete the  governmentProject by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete GovernmentProject : {}", id);
        governmentProjectRepository.delete(id);
        governmentProjectSearchRepository.delete(id);
    }

    /**
     * search for the governmentProject corresponding
     * to the query.
     */
    @Transactional(readOnly = true)
    public List<GovernmentProjectDTO> search(String query) {

        log.debug("REST request to search GovernmentProjects for query {}", query);
        return StreamSupport
            .stream(governmentProjectSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(governmentProjectMapper::governmentProjectToGovernmentProjectDTO)
            .collect(Collectors.toList());
    }

   /**
    * get all the governmentProjects where GovernmentWorkExperience is null.
    *
    * @return the list of entities
    */
   @Transactional(readOnly = true)
   public List<GovernmentProjectDTO> findAllWhereGovernmentWorkExperienceIsNull() {
      log.debug("Request to get all governmentProjects where GovernmentWorkExperience is null");
      return StreamSupport
         .stream(governmentProjectRepository.findAll().spliterator(), false)
         .filter(governmentProject -> governmentProject.getGovernmentWorkExperience() == null)
         .map(governmentProjectMapper::governmentProjectToGovernmentProjectDTO)
         .collect(Collectors.toCollection(LinkedList::new));
   }

   /**
    * get one governmentProject by id.
    *
    * @return the entity
    */
   @Transactional(readOnly = true)
   public GovernmentProjectDTO findOne(Long id) {
      log.debug("Request to get GovernmentProject : {}", id);
      GovernmentProject governmentProject = governmentProjectRepository.findOne(id);
      GovernmentProjectDTO governmentProjectDTO =
         governmentProjectMapper.governmentProjectToGovernmentProjectDTO(governmentProject);
      return governmentProjectDTO;
   }

   /**
    * delete the  governmentProject by id.
    */
   public void delete(Long id) {
      log.debug("Request to delete GovernmentProject : {}", id);
      governmentProjectRepository.delete(id);
      governmentProjectSearchRepository.delete(id);
   }

   /**
    * search for the governmentProject corresponding
    * to the query.
    */
   @Transactional(readOnly = true)
   public List<GovernmentProjectDTO> search(String query) {

      log.debug("REST request to search GovernmentProjects for query {}", query);
      return StreamSupport
         .stream(governmentProjectSearchRepository.search(queryStringQuery(query)).spliterator(), false)
         .map(governmentProjectMapper::governmentProjectToGovernmentProjectDTO)
         .collect(Collectors.toList());
   }

   @Override
   public GovernmentProjectDTO addGovernmentProject(GovernmentProjectDTO dto) {
      GovernmentProject govProject = governmentProjectMapper.governmentProjectDTOToGovernmentProject(dto);
      governmentProjectRepository.save(govProject);
      return governmentProjectMapper.governmentProjectToGovernmentProjectDTO(govProject);
   }
}
