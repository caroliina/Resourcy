package com.resourcy.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.resourcy.app.domain.Type;
import com.resourcy.app.service.AdditionalSkillService;
import com.resourcy.app.web.rest.dto.AdditionalSkillDTO;
import com.resourcy.app.web.rest.mapper.AdditionalSkillMapper;
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
import java.util.stream.Stream;

/**
 * REST controller for managing AdditionalSkill.
 */
@RestController
@RequestMapping("/api")
public class AdditionalSkillController {

    private final Logger log = LoggerFactory.getLogger(AdditionalSkillController.class);
        
    @Inject
    private AdditionalSkillService additionalSkillService;
    
    @Inject
    private AdditionalSkillMapper additionalSkillMapper;
    
    /**
     * POST  /additionalSkills -> Create a new additionalSkill.
     */
    @RequestMapping(value = "/additionalSkills",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<AdditionalSkillDTO> createAdditionalSkill(@RequestBody AdditionalSkillDTO additionalSkillDTO) throws URISyntaxException {
        log.debug("REST request to save AdditionalSkill : {}", additionalSkillDTO);
        if (additionalSkillDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("additionalSkill", "idexists", "A new additionalSkill cannot already have an ID")).body(null);
        }
        AdditionalSkillDTO result = additionalSkillService.save(additionalSkillDTO);
        return ResponseEntity.created(new URI("/api/additionalSkills/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("additionalSkill", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /additionalSkills -> Updates an existing additionalSkill.
     */
    @RequestMapping(value = "/additionalSkills",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<AdditionalSkillDTO> updateAdditionalSkill(@RequestBody AdditionalSkillDTO additionalSkillDTO) throws URISyntaxException {
        log.debug("REST request to update AdditionalSkill : {}", additionalSkillDTO);
        if (additionalSkillDTO.getId() == null) {
            return createAdditionalSkill(additionalSkillDTO);
        }
        AdditionalSkillDTO result = additionalSkillService.save(additionalSkillDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("additionalSkill", additionalSkillDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /additionalSkills -> get all the additionalSkills.
     */
    @RequestMapping(value = "/additionalSkills",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<AdditionalSkillDTO> getAllAdditionalSkills() {
        log.debug("REST request to get all AdditionalSkills");
        return additionalSkillService.findAll();
            }

    /**
     * GET  /additionalSkills/:id -> get the "id" additionalSkill.
     */
    @RequestMapping(value = "/additionalSkills/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<AdditionalSkillDTO> getAdditionalSkill(@PathVariable Long id) {
        log.debug("REST request to get AdditionalSkill : {}", id);
        AdditionalSkillDTO additionalSkillDTO = additionalSkillService.findOne(id);
        return Optional.ofNullable(additionalSkillDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /additionalSkills/:id -> delete the "id" additionalSkill.
     */
    @RequestMapping(value = "/additionalSkills/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteAdditionalSkill(@PathVariable Long id) {
        log.debug("REST request to delete AdditionalSkill : {}", id);
        additionalSkillService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("additionalSkill", id.toString())).build();
    }

    /**
     * SEARCH  /_search/additionalSkills/:query -> search for the additionalSkill corresponding
     * to the query.
     */
    @RequestMapping(value = "/_search/additionalSkills/{query:.+}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<AdditionalSkillDTO> searchAdditionalSkills(@PathVariable String query) {
        log.debug("Request to search AdditionalSkills for query {}", query);
        return additionalSkillService.search(query);
    }

    @RequestMapping(value = "/addSkill", method = RequestMethod.POST)
    public AdditionalSkillDTO addSkills(@RequestBody AdditionalSkillDTO additionalSkillDTO) {
        return additionalSkillService.addSkill(additionalSkillDTO);
    }

    @RequestMapping(value = "/additionalSkills/types", method = RequestMethod.GET)
    @ResponseBody
    public String[] Types(){
        return Stream.of(Type.values()).map(Type::name).toArray(String[]::new);
    }
}
