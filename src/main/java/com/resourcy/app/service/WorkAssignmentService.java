package com.resourcy.app.service;

import com.resourcy.app.service.validator.ValidationException;
import com.resourcy.app.web.rest.dto.WorkAssignmentDTO;

import java.util.List;

/**
 * Service Interface for managing WorkAssignment.
 */
public interface WorkAssignmentService {

    /**
     * Save a workAssignment.
     * @return the persisted entity
     */
    WorkAssignmentDTO save(WorkAssignmentDTO workAssignmentDTO) throws  ValidationException;

    /**
     *  get all the workAssignments.
     *  @return the list of entities
     */
    List<WorkAssignmentDTO> findAll();

    /**
     *  get the "id" workAssignment.
     *  @return the entity
     */
    WorkAssignmentDTO findOne(Long id);

    /**
     *  delete the "id" workAssignment.
     */
    void delete(Long id);

    /**
     * search for the workAssignment corresponding
     * to the query.
     */
    List<WorkAssignmentDTO> search(String query);

    WorkAssignmentDTO addWorkAssignment(WorkAssignmentDTO dto);
}
