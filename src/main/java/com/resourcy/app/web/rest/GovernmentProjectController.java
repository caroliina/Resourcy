package com.resourcy.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.resourcy.app.service.GovernmentProjectService;
import com.resourcy.app.web.rest.util.HeaderUtil;
import com.resourcy.app.web.rest.dto.GovernmentProjectDTO;
import com.resourcy.app.web.rest.mapper.GovernmentProjectMapper;
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
 * REST controller for managing GovernmentProject.
 */
@RestController
@RequestMapping("/api")
public class GovernmentProjectController {

    private final Logger log = LoggerFactory.getLogger(GovernmentProjectController.class);
        
    @Inject
    private GovernmentProjectService governmentProjectService;
    
    @Inject
    private GovernmentProjectMapper governmentProjectMapper;
    
    /**
     * POST  /governmentProjects -> Create a new governmentProject.
     */
    @RequestMapping(value = "/governmentProjects",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<GovernmentProjectDTO> createGovernmentProject(@RequestBody GovernmentProjectDTO governmentProjectDTO) throws URISyntaxException {
        log.debug("REST request to save GovernmentProject : {}", governmentProjectDTO);
        if (governmentProjectDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("governmentProject", "idexists", "A new governmentProject cannot already have an ID")).body(null);
        }
        GovernmentProjectDTO result = governmentProjectService.save(governmentProjectDTO);
        return ResponseEntity.created(new URI("/api/governmentProjects/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("governmentProject", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /governmentProjects -> Updates an existing governmentProject.
     */
    @RequestMapping(value = "/governmentProjects",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<GovernmentProjectDTO> updateGovernmentProject(@RequestBody GovernmentProjectDTO governmentProjectDTO) throws URISyntaxException {
        log.debug("REST request to update GovernmentProject : {}", governmentProjectDTO);
        if (governmentProjectDTO.getId() == null) {
            return createGovernmentProject(governmentProjectDTO);
        }
        GovernmentProjectDTO result = governmentProjectService.save(governmentProjectDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("governmentProject", governmentProjectDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /governmentProjects -> get all the governmentProjects.
     */
    @RequestMapping(value = "/governmentProjects",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<GovernmentProjectDTO> getAllGovernmentProjects(@RequestParam(required = false) String filter) {
        if ("governmentworkexperience-is-null".equals(filter)) {
            log.debug("REST request to get all GovernmentProjects where governmentWorkExperience is null");
            return governmentProjectService.findAllWhereGovernmentWorkExperienceIsNull();
        }
        log.debug("REST request to get all GovernmentProjects");
        return governmentProjectService.findAll();
            }

    /**
     * GET  /governmentProjects/:id -> get the "id" governmentProject.
     */
    @RequestMapping(value = "/governmentProjects/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<GovernmentProjectDTO> getGovernmentProject(@PathVariable Long id) {
        log.debug("REST request to get GovernmentProject : {}", id);
        GovernmentProjectDTO governmentProjectDTO = governmentProjectService.findOne(id);
        return Optional.ofNullable(governmentProjectDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /governmentProjects/:id -> delete the "id" governmentProject.
     */
    @RequestMapping(value = "/governmentProjects/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteGovernmentProject(@PathVariable Long id) {
        log.debug("REST request to delete GovernmentProject : {}", id);
        governmentProjectService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("governmentProject", id.toString())).build();
    }

    /**
     * SEARCH  /_search/governmentProjects/:query -> search for the governmentProject corresponding
     * to the query.
     */
    @RequestMapping(value = "/_search/governmentProjects/{query:.+}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<GovernmentProjectDTO> searchGovernmentProjects(@PathVariable String query) {
        log.debug("Request to search GovernmentProjects for query {}", query);
        return governmentProjectService.search(query);
    }
}
