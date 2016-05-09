package com.resourcy.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.resourcy.app.service.AdditionalStudyService;
import com.resourcy.app.service.validator.ValidationException;
import com.resourcy.app.web.rest.dto.AdditionalStudyDTO;
import com.resourcy.app.web.rest.mapper.AdditionalStudyMapper;
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
 * REST controller for managing AdditionalStudy.
 */
@RestController
@RequestMapping("/api")
public class AdditionalStudyController {

    private final Logger log = LoggerFactory.getLogger(AdditionalStudyController.class);

    @Inject
    private AdditionalStudyService additionalStudyService;

    @Inject
    private AdditionalStudyMapper additionalStudyMapper;

    /**
     * POST  /additionalStudys -> Create a new additionalStudy.
     */
    @RequestMapping(value = "/additionalStudys",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<AdditionalStudyDTO> createAdditionalStudy(@RequestBody AdditionalStudyDTO additionalStudyDTO) throws URISyntaxException, ValidationException {
        log.debug("REST request to save AdditionalStudy : {}", additionalStudyDTO);
        if (additionalStudyDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("additionalStudy", "idexists", "A new additionalStudy cannot already have an ID")).body(null);
        }
        AdditionalStudyDTO result = additionalStudyService.save(additionalStudyDTO);
        return ResponseEntity.created(new URI("/api/additionalStudys/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("additionalStudy", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /additionalStudys -> Updates an existing additionalStudy.
     */
    @RequestMapping(value = "/additionalStudys",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<AdditionalStudyDTO> updateAdditionalStudy(@RequestBody AdditionalStudyDTO additionalStudyDTO) throws URISyntaxException, ValidationException {
        log.debug("REST request to update AdditionalStudy : {}", additionalStudyDTO);
        if (additionalStudyDTO.getId() == null) {
            return createAdditionalStudy(additionalStudyDTO);
        }
        AdditionalStudyDTO result = additionalStudyService.save(additionalStudyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("additionalStudy", additionalStudyDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /additionalStudys -> get all the additionalStudys.
     */
    @RequestMapping(value = "/additionalStudys",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<AdditionalStudyDTO> getAllAdditionalStudys() {
        log.debug("REST request to get all AdditionalStudys");
        return additionalStudyService.findAll();
            }

    /**
     * GET  /additionalStudys/:id -> get the "id" additionalStudy.
     */
    @RequestMapping(value = "/additionalStudys/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<AdditionalStudyDTO> getAdditionalStudy(@PathVariable Long id) {
        log.debug("REST request to get AdditionalStudy : {}", id);
        AdditionalStudyDTO additionalStudyDTO = additionalStudyService.findOne(id);
        return Optional.ofNullable(additionalStudyDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /additionalStudys/:id -> delete the "id" additionalStudy.
     */
    @RequestMapping(value = "/additionalStudys/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteAdditionalStudy(@PathVariable Long id) {
        log.debug("REST request to delete AdditionalStudy : {}", id);
        additionalStudyService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("additionalStudy", id.toString())).build();
    }

    /**
     * SEARCH  /_search/additionalStudys/:query -> search for the additionalStudy corresponding
     * to the query.
     */
    @RequestMapping(value = "/_search/additionalStudys/{query:.+}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<AdditionalStudyDTO> searchAdditionalStudys(@PathVariable String query) {
        log.debug("Request to search AdditionalStudys for query {}", query);
        return additionalStudyService.search(query);
    }

    @RequestMapping(value = "/addStudy", method = RequestMethod.POST)
    public AdditionalStudyDTO addStudies(@RequestBody AdditionalStudyDTO additionalStudyDTO) {
        return additionalStudyService.addStudy(additionalStudyDTO);
    }
}
