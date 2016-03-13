package com.resourcy.app.service.impl;

import com.resourcy.app.service.WorkAssignmentService;
import com.resourcy.app.domain.WorkAssignment;
import com.resourcy.app.repository.WorkAssignmentRepository;
import com.resourcy.app.repository.search.WorkAssignmentSearchRepository;
import com.resourcy.app.web.rest.dto.WorkAssignmentDTO;
import com.resourcy.app.web.rest.mapper.WorkAssignmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Service Implementation for managing WorkAssignment.
 */
@Service
@Transactional
public class WorkAssignmentServiceImpl implements WorkAssignmentService{

    private final Logger log = LoggerFactory.getLogger(WorkAssignmentServiceImpl.class);
    
    @Inject
    private WorkAssignmentRepository workAssignmentRepository;
    
    @Inject
    private WorkAssignmentMapper workAssignmentMapper;
    
    @Inject
    private WorkAssignmentSearchRepository workAssignmentSearchRepository;
    
    /**
     * Save a workAssignment.
     * @return the persisted entity
     */
    public WorkAssignmentDTO save(WorkAssignmentDTO workAssignmentDTO) {
        log.debug("Request to save WorkAssignment : {}", workAssignmentDTO);
        WorkAssignment workAssignment = workAssignmentMapper.workAssignmentDTOToWorkAssignment(workAssignmentDTO);
        workAssignment = workAssignmentRepository.save(workAssignment);
        WorkAssignmentDTO result = workAssignmentMapper.workAssignmentToWorkAssignmentDTO(workAssignment);
        workAssignmentSearchRepository.save(workAssignment);
        return result;
    }

    /**
     *  get all the workAssignments.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<WorkAssignmentDTO> findAll() {
        log.debug("Request to get all WorkAssignments");
        List<WorkAssignmentDTO> result = workAssignmentRepository.findAll().stream()
            .map(workAssignmentMapper::workAssignmentToWorkAssignmentDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     *  get one workAssignment by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public WorkAssignmentDTO findOne(Long id) {
        log.debug("Request to get WorkAssignment : {}", id);
        WorkAssignment workAssignment = workAssignmentRepository.findOne(id);
        WorkAssignmentDTO workAssignmentDTO = workAssignmentMapper.workAssignmentToWorkAssignmentDTO(workAssignment);
        return workAssignmentDTO;
    }

    /**
     *  delete the  workAssignment by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete WorkAssignment : {}", id);
        workAssignmentRepository.delete(id);
        workAssignmentSearchRepository.delete(id);
    }

    /**
     * search for the workAssignment corresponding
     * to the query.
     */
    @Transactional(readOnly = true) 
    public List<WorkAssignmentDTO> search(String query) {
        
        log.debug("REST request to search WorkAssignments for query {}", query);
        return StreamSupport
            .stream(workAssignmentSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(workAssignmentMapper::workAssignmentToWorkAssignmentDTO)
            .collect(Collectors.toList());
    }
}
