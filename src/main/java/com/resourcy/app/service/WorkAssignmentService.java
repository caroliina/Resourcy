package com.resourcy.app.service;

import com.resourcy.app.domain.WorkAssignment;
import com.resourcy.app.web.rest.dto.WorkAssignmentDTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing WorkAssignment.
 */
public interface WorkAssignmentService {

    /**
     * Save a workAssignment.
     * @return the persisted entity
     */
    public WorkAssignmentDTO save(WorkAssignmentDTO workAssignmentDTO);

    /**
     *  get all the workAssignments.
     *  @return the list of entities
     */
    public List<WorkAssignmentDTO> findAll();

    /**
     *  get the "id" workAssignment.
     *  @return the entity
     */
    public WorkAssignmentDTO findOne(Long id);

    /**
     *  delete the "id" workAssignment.
     */
    public void delete(Long id);

    /**
     * search for the workAssignment corresponding
     * to the query.
     */
    public List<WorkAssignmentDTO> search(String query);
}
