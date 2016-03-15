package com.resourcy.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.resourcy.app.domain.CurriculumVitae;
import com.resourcy.app.service.CurriculumVitaeService;
import com.resourcy.app.web.rest.util.HeaderUtil;
import com.resourcy.app.web.rest.util.PaginationUtil;
import com.resourcy.app.web.rest.dto.CurriculumVitaeDTO;
import com.resourcy.app.web.rest.mapper.CurriculumVitaeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
 * REST controller for managing CurriculumVitae.
 */
@RestController
@RequestMapping("/api")
public class CurriculumVitaeResource {

    private final Logger log = LoggerFactory.getLogger(CurriculumVitaeResource.class);
        
    @Inject
    private CurriculumVitaeService curriculumVitaeService;
    
    @Inject
    private CurriculumVitaeMapper curriculumVitaeMapper;
    
    /**
     * POST  /curriculumVitaes -> Create a new curriculumVitae.
     */
    @RequestMapping(value = "/curriculumVitaes",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CurriculumVitaeDTO> createCurriculumVitae(@RequestBody CurriculumVitaeDTO curriculumVitaeDTO) throws URISyntaxException {
        log.debug("REST request to save CurriculumVitae : {}", curriculumVitaeDTO);
        if (curriculumVitaeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("curriculumVitae", "idexists", "A new curriculumVitae cannot already have an ID")).body(null);
        }
        CurriculumVitaeDTO result = curriculumVitaeService.save(curriculumVitaeDTO);
        return ResponseEntity.created(new URI("/api/curriculumVitaes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("curriculumVitae", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /curriculumVitaes -> Updates an existing curriculumVitae.
     */
    @RequestMapping(value = "/curriculumVitaes",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CurriculumVitaeDTO> updateCurriculumVitae(@RequestBody CurriculumVitaeDTO curriculumVitaeDTO) throws URISyntaxException {
        log.debug("REST request to update CurriculumVitae : {}", curriculumVitaeDTO);
        if (curriculumVitaeDTO.getId() == null) {
            return createCurriculumVitae(curriculumVitaeDTO);
        }
        CurriculumVitaeDTO result = curriculumVitaeService.save(curriculumVitaeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("curriculumVitae", curriculumVitaeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /curriculumVitaes -> get all the curriculumVitaes.
     */
    @RequestMapping(value = "/curriculumVitaes",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<List<CurriculumVitaeDTO>> getAllCurriculumVitaes(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of CurriculumVitaes");
        Page<CurriculumVitae> page = curriculumVitaeService.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/curriculumVitaes");
        return new ResponseEntity<>(page.getContent().stream()
            .map(curriculumVitaeMapper::curriculumVitaeToCurriculumVitaeDTO)
            .collect(Collectors.toCollection(LinkedList::new)), headers, HttpStatus.OK);
    }

    /**
     * GET  /curriculumVitaes/:id -> get the "id" curriculumVitae.
     */
    @RequestMapping(value = "/curriculumVitaes/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CurriculumVitaeDTO> getCurriculumVitae(@PathVariable Long id) {
        log.debug("REST request to get CurriculumVitae : {}", id);
        CurriculumVitaeDTO curriculumVitaeDTO = curriculumVitaeService.findOne(id);
        return Optional.ofNullable(curriculumVitaeDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /curriculumVitaes/:id -> delete the "id" curriculumVitae.
     */
    @RequestMapping(value = "/curriculumVitaes/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteCurriculumVitae(@PathVariable Long id) {
        log.debug("REST request to delete CurriculumVitae : {}", id);
        curriculumVitaeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("curriculumVitae", id.toString())).build();
    }

    /**
     * SEARCH  /_search/curriculumVitaes/:query -> search for the curriculumVitae corresponding
     * to the query.
     */
    @RequestMapping(value = "/_search/curriculumVitaes/{query:.+}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<CurriculumVitaeDTO> searchCurriculumVitaes(@PathVariable String query) {
        log.debug("Request to search CurriculumVitaes for query {}", query);
        return curriculumVitaeService.search(query);
    }
}
