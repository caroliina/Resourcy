package com.resourcy.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.resourcy.app.domain.Education;
import com.resourcy.app.service.EducationService;
import com.resourcy.app.web.rest.util.HeaderUtil;
import com.resourcy.app.web.rest.dto.EducationDTO;
import com.resourcy.app.web.rest.mapper.EducationMapper;
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
 * REST controller for managing Education.
 */
@RestController
@RequestMapping("/api")
public class EducationResource {

    private final Logger log = LoggerFactory.getLogger(EducationResource.class);
        
    @Inject
    private EducationService educationService;
    
    @Inject
    private EducationMapper educationMapper;
    
    /**
     * POST  /educations -> Create a new education.
     */
    @RequestMapping(value = "/educations",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<EducationDTO> createEducation(@RequestBody EducationDTO educationDTO) throws URISyntaxException {
        log.debug("REST request to save Education : {}", educationDTO);
        if (educationDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("education", "idexists", "A new education cannot already have an ID")).body(null);
        }
        EducationDTO result = educationService.save(educationDTO);
        return ResponseEntity.created(new URI("/api/educations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("education", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /educations -> Updates an existing education.
     */
    @RequestMapping(value = "/educations",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<EducationDTO> updateEducation(@RequestBody EducationDTO educationDTO) throws URISyntaxException {
        log.debug("REST request to update Education : {}", educationDTO);
        if (educationDTO.getId() == null) {
            return createEducation(educationDTO);
        }
        EducationDTO result = educationService.save(educationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("education", educationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /educations -> get all the educations.
     */
    @RequestMapping(value = "/educations",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<EducationDTO> getAllEducations() {
        log.debug("REST request to get all Educations");
        return educationService.findAll();
            }

    /**
     * GET  /educations/:id -> get the "id" education.
     */
    @RequestMapping(value = "/educations/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<EducationDTO> getEducation(@PathVariable Long id) {
        log.debug("REST request to get Education : {}", id);
        EducationDTO educationDTO = educationService.findOne(id);
        return Optional.ofNullable(educationDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /educations/:id -> delete the "id" education.
     */
    @RequestMapping(value = "/educations/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteEducation(@PathVariable Long id) {
        log.debug("REST request to delete Education : {}", id);
        educationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("education", id.toString())).build();
    }

    /**
     * SEARCH  /_search/educations/:query -> search for the education corresponding
     * to the query.
     */
    @RequestMapping(value = "/_search/educations/{query:.+}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<EducationDTO> searchEducations(@PathVariable String query) {
        log.debug("Request to search Educations for query {}", query);
        return educationService.search(query);
    }
}
