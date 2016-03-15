package com.resourcy.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.resourcy.app.domain.WorkExperience;
import com.resourcy.app.service.WorkExperienceService;
import com.resourcy.app.web.rest.util.HeaderUtil;
import com.resourcy.app.web.rest.dto.WorkExperienceDTO;
import com.resourcy.app.web.rest.mapper.WorkExperienceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing WorkExperience.
 */
@RestController
@RequestMapping("/api")
public class WorkExperienceResource {

    private final Logger log = LoggerFactory.getLogger(WorkExperienceResource.class);
        
    @Inject
    private WorkExperienceService workExperienceService;
    
    @Inject
    private WorkExperienceMapper workExperienceMapper;
    
    /**
     * POST  /workExperiences -> Create a new workExperience.
     */
    @RequestMapping(value = "/workExperiences",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WorkExperienceDTO> createWorkExperience(@RequestBody WorkExperienceDTO workExperienceDTO) throws URISyntaxException {
        log.debug("REST request to save WorkExperience : {}", workExperienceDTO);
        if (workExperienceDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("workExperience", "idexists", "A new workExperience cannot already have an ID")).body(null);
        }
        WorkExperienceDTO result = workExperienceService.save(workExperienceDTO);
        return ResponseEntity.created(new URI("/api/workExperiences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("workExperience", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /workExperiences -> Updates an existing workExperience.
     */
    @RequestMapping(value = "/workExperiences",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WorkExperienceDTO> updateWorkExperience(@RequestBody WorkExperienceDTO workExperienceDTO) throws URISyntaxException {
        log.debug("REST request to update WorkExperience : {}", workExperienceDTO);
        if (workExperienceDTO.getId() == null) {
            return createWorkExperience(workExperienceDTO);
        }
        WorkExperienceDTO result = workExperienceService.save(workExperienceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("workExperience", workExperienceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /workExperiences -> get all the workExperiences.
     */
    @RequestMapping(value = "/workExperiences",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<WorkExperienceDTO> getAllWorkExperiences() {
        log.debug("REST request to get all WorkExperiences");
        return workExperienceService.findAll();
            }

    /**
     * GET  /workExperiences/:id -> get the "id" workExperience.
     */
    @RequestMapping(value = "/workExperiences/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WorkExperienceDTO> getWorkExperience(@PathVariable Long id) {
        log.debug("REST request to get WorkExperience : {}", id);
        WorkExperienceDTO workExperienceDTO = workExperienceService.findOne(id);
        return Optional.ofNullable(workExperienceDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /workExperiences/:id -> delete the "id" workExperience.
     */
    @RequestMapping(value = "/workExperiences/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteWorkExperience(@PathVariable Long id) {
        log.debug("REST request to delete WorkExperience : {}", id);
        workExperienceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("workExperience", id.toString())).build();
    }

    /**
     * SEARCH  /_search/workExperiences/:query -> search for the workExperience corresponding
     * to the query.
     */
    @RequestMapping(value = "/_search/workExperiences/{query:.+}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<WorkExperienceDTO> searchWorkExperiences(@PathVariable String query) {
        log.debug("Request to search WorkExperiences for query {}", query);
        return workExperienceService.search(query);
    }
}
