package com.resourcy.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.resourcy.app.domain.Technology;
import com.resourcy.app.service.TechnologyService;
import com.resourcy.app.web.rest.util.HeaderUtil;
import com.resourcy.app.web.rest.dto.TechnologyDTO;
import com.resourcy.app.web.rest.mapper.TechnologyMapper;
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
 * REST controller for managing Technology.
 */
@RestController
@RequestMapping("/api")
public class TechnologyResource {

    private final Logger log = LoggerFactory.getLogger(TechnologyResource.class);
        
    @Inject
    private TechnologyService technologyService;
    
    @Inject
    private TechnologyMapper technologyMapper;
    
    /**
     * POST  /technologys -> Create a new technology.
     */
    @RequestMapping(value = "/technologys",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TechnologyDTO> createTechnology(@RequestBody TechnologyDTO technologyDTO) throws URISyntaxException {
        log.debug("REST request to save Technology : {}", technologyDTO);
        if (technologyDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("technology", "idexists", "A new technology cannot already have an ID")).body(null);
        }
        TechnologyDTO result = technologyService.save(technologyDTO);
        return ResponseEntity.created(new URI("/api/technologys/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("technology", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /technologys -> Updates an existing technology.
     */
    @RequestMapping(value = "/technologys",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TechnologyDTO> updateTechnology(@RequestBody TechnologyDTO technologyDTO) throws URISyntaxException {
        log.debug("REST request to update Technology : {}", technologyDTO);
        if (technologyDTO.getId() == null) {
            return createTechnology(technologyDTO);
        }
        TechnologyDTO result = technologyService.save(technologyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("technology", technologyDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /technologys -> get all the technologys.
     */
    @RequestMapping(value = "/technologys",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<TechnologyDTO> getAllTechnologys() {
        log.debug("REST request to get all Technologys");
        return technologyService.findAll();
            }

    /**
     * GET  /technologys/:id -> get the "id" technology.
     */
    @RequestMapping(value = "/technologys/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TechnologyDTO> getTechnology(@PathVariable Long id) {
        log.debug("REST request to get Technology : {}", id);
        TechnologyDTO technologyDTO = technologyService.findOne(id);
        return Optional.ofNullable(technologyDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /technologys/:id -> delete the "id" technology.
     */
    @RequestMapping(value = "/technologys/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteTechnology(@PathVariable Long id) {
        log.debug("REST request to delete Technology : {}", id);
        technologyService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("technology", id.toString())).build();
    }

    /**
     * SEARCH  /_search/technologys/:query -> search for the technology corresponding
     * to the query.
     */
    @RequestMapping(value = "/_search/technologys/{query:.+}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<TechnologyDTO> searchTechnologys(@PathVariable String query) {
        log.debug("Request to search Technologys for query {}", query);
        return technologyService.search(query);
    }
}
