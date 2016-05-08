package com.resourcy.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.resourcy.app.service.GovernmentWorkExperienceService;
import com.resourcy.app.web.rest.dto.GovernmentWorkExperienceDTO;
import com.resourcy.app.web.rest.mapper.GovernmentWorkExperienceMapper;
import com.resourcy.app.web.rest.util.HeaderUtil;
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
 * REST controller for managing GovernmentWorkExperience.
 */
@RestController
@RequestMapping("/api")
public class GovernmentWorkExperienceController {

    private final Logger log = LoggerFactory.getLogger(GovernmentWorkExperienceController.class);
        
    @Inject
    private GovernmentWorkExperienceService governmentWorkExperienceService;
    
    @Inject
    private GovernmentWorkExperienceMapper governmentWorkExperienceMapper;
    
    /**
     * POST  /governmentWorkExperiences -> Create a new governmentWorkExperience.
     */
    @RequestMapping(value = "/governmentWorkExperiences",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<GovernmentWorkExperienceDTO> createGovernmentWorkExperience(@RequestBody GovernmentWorkExperienceDTO governmentWorkExperienceDTO) throws URISyntaxException {
        log.debug("REST request to save GovernmentWorkExperience : {}", governmentWorkExperienceDTO);
        if (governmentWorkExperienceDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("governmentWorkExperience", "idexists", "A new governmentWorkExperience cannot already have an ID")).body(null);
        }
        GovernmentWorkExperienceDTO result = governmentWorkExperienceService.save(governmentWorkExperienceDTO);
        return ResponseEntity.created(new URI("/api/governmentWorkExperiences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("governmentWorkExperience", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /governmentWorkExperiences -> Updates an existing governmentWorkExperience.
     */
    @RequestMapping(value = "/governmentWorkExperiences",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<GovernmentWorkExperienceDTO> updateGovernmentWorkExperience(@RequestBody GovernmentWorkExperienceDTO governmentWorkExperienceDTO) throws URISyntaxException {
        log.debug("REST request to update GovernmentWorkExperience : {}", governmentWorkExperienceDTO);
        if (governmentWorkExperienceDTO.getId() == null) {
            return createGovernmentWorkExperience(governmentWorkExperienceDTO);
        }
        GovernmentWorkExperienceDTO result = governmentWorkExperienceService.save(governmentWorkExperienceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("governmentWorkExperience", governmentWorkExperienceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /governmentWorkExperiences -> get all the governmentWorkExperiences.
     */
    @RequestMapping(value = "/governmentWorkExperiences",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<GovernmentWorkExperienceDTO> getAllGovernmentWorkExperiences() {
        log.debug("REST request to get all GovernmentWorkExperiences");
        return governmentWorkExperienceService.findAll();
            }

    /**
     * GET  /governmentWorkExperiences/:id -> get the "id" governmentWorkExperience.
     */
    @RequestMapping(value = "/governmentWorkExperiences/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<GovernmentWorkExperienceDTO> getGovernmentWorkExperience(@PathVariable Long id) {
        log.debug("REST request to get GovernmentWorkExperience : {}", id);
        GovernmentWorkExperienceDTO governmentWorkExperienceDTO = governmentWorkExperienceService.findOne(id);
        return Optional.ofNullable(governmentWorkExperienceDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /governmentWorkExperiences/:id -> delete the "id" governmentWorkExperience.
     */
    @RequestMapping(value = "/governmentWorkExperiences/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteGovernmentWorkExperience(@PathVariable Long id) {
        log.debug("REST request to delete GovernmentWorkExperience : {}", id);
        governmentWorkExperienceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("governmentWorkExperience", id.toString())).build();
    }

    /**
     * SEARCH  /_search/governmentWorkExperiences/:query -> search for the governmentWorkExperience corresponding
     * to the query.
     */
    @RequestMapping(value = "/_search/governmentWorkExperiences/{query:.+}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<GovernmentWorkExperienceDTO> searchGovernmentWorkExperiences(@PathVariable String query) {
        log.debug("Request to search GovernmentWorkExperiences for query {}", query);
        return governmentWorkExperienceService.search(query);
    }

    @RequestMapping(value = "/govWorkExperience", method = RequestMethod.POST)
    public GovernmentWorkExperienceDTO addGovernmentWorkExperience(@RequestBody GovernmentWorkExperienceDTO dto) {
        return governmentWorkExperienceService.addGovernmentWorkExperience(dto);
    }
}
