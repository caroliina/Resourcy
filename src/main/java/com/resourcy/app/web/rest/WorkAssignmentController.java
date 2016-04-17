package com.resourcy.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.resourcy.app.service.WorkAssignmentService;
import com.resourcy.app.web.rest.util.HeaderUtil;
import com.resourcy.app.web.rest.dto.WorkAssignmentDTO;
import com.resourcy.app.web.rest.mapper.WorkAssignmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing WorkAssignment.
 */
@RestController
@RequestMapping("/api")
public class WorkAssignmentController {

    private final Logger log = LoggerFactory.getLogger(WorkAssignmentController.class);
        
    @Inject
    private WorkAssignmentService workAssignmentService;
    
    @Inject
    private WorkAssignmentMapper workAssignmentMapper;
    
    /**
     * POST  /workAssignments -> Create a new workAssignment.
     */
    @RequestMapping(value = "/workAssignments",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WorkAssignmentDTO> createWorkAssignment(@RequestBody WorkAssignmentDTO workAssignmentDTO) throws URISyntaxException {
        log.debug("REST request to save WorkAssignment : {}", workAssignmentDTO);
        if (workAssignmentDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("workAssignment", "idexists", "A new workAssignment cannot already have an ID")).body(null);
        }
        WorkAssignmentDTO result = workAssignmentService.save(workAssignmentDTO);
        return ResponseEntity.created(new URI("/api/workAssignments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("workAssignment", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /workAssignments -> Updates an existing workAssignment.
     */
    @RequestMapping(value = "/workAssignments",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WorkAssignmentDTO> updateWorkAssignment(@RequestBody WorkAssignmentDTO workAssignmentDTO) throws URISyntaxException {
        log.debug("REST request to update WorkAssignment : {}", workAssignmentDTO);
        if (workAssignmentDTO.getId() == null) {
            return createWorkAssignment(workAssignmentDTO);
        }
        WorkAssignmentDTO result = workAssignmentService.save(workAssignmentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("workAssignment", workAssignmentDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /workAssignments -> get all the workAssignments.
     */
    @RequestMapping(value = "/workAssignments",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<WorkAssignmentDTO> getAllWorkAssignments() {
        log.debug("REST request to get all WorkAssignments");
        return workAssignmentService.findAll();
            }

    /**
     * GET  /workAssignments/:id -> get the "id" workAssignment.
     */
    @RequestMapping(value = "/workAssignments/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WorkAssignmentDTO> getWorkAssignment(@PathVariable Long id) {
        log.debug("REST request to get WorkAssignment : {}", id);
        WorkAssignmentDTO workAssignmentDTO = workAssignmentService.findOne(id);
        return Optional.ofNullable(workAssignmentDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /workAssignments/:id -> delete the "id" workAssignment.
     */
    @RequestMapping(value = "/workAssignments/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteWorkAssignment(@PathVariable Long id) {
        log.debug("REST request to delete WorkAssignment : {}", id);
        workAssignmentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("workAssignment", id.toString())).build();
    }

    /**
     * SEARCH  /_search/workAssignments/:query -> search for the workAssignment corresponding
     * to the query.
     */
    @RequestMapping(value = "/_search/workAssignments/{query:.+}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<WorkAssignmentDTO> searchWorkAssignments(@PathVariable String query) {
        log.debug("Request to search WorkAssignments for query {}", query);
        return workAssignmentService.search(query);
    }
}
