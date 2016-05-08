package com.resourcy.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.resourcy.app.domain.LanguageLevel;
import com.resourcy.app.service.LanguageSkillService;
import com.resourcy.app.web.rest.dto.LanguageSkillDTO;
import com.resourcy.app.web.rest.mapper.LanguageSkillMapper;
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
 * REST controller for managing LanguageSkill.
 */
@RestController
@RequestMapping("/api")
public class LanguageSkillController {

    private final Logger log = LoggerFactory.getLogger(LanguageSkillController.class);
        
    @Inject
    private LanguageSkillService languageSkillService;
    
    @Inject
    private LanguageSkillMapper languageSkillMapper;
    
    /**
     * POST  /languageSkills -> Create a new languageSkill.
     */
    @RequestMapping(value = "/languageSkills",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<LanguageSkillDTO> createLanguageSkill(@RequestBody LanguageSkillDTO languageSkillDTO) throws URISyntaxException {
        log.debug("REST request to save LanguageSkill : {}", languageSkillDTO);
        if (languageSkillDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("languageSkill", "idexists", "A new languageSkill cannot already have an ID")).body(null);
        }
        LanguageSkillDTO result = languageSkillService.save(languageSkillDTO);
        return ResponseEntity.created(new URI("/api/languageSkills/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("languageSkill", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /languageSkills -> Updates an existing languageSkill.
     */
    @RequestMapping(value = "/languageSkills",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<LanguageSkillDTO> updateLanguageSkill(@RequestBody LanguageSkillDTO languageSkillDTO) throws URISyntaxException {
        log.debug("REST request to update LanguageSkill : {}", languageSkillDTO);
        if (languageSkillDTO.getId() == null) {
            return createLanguageSkill(languageSkillDTO);
        }
        LanguageSkillDTO result = languageSkillService.save(languageSkillDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("languageSkill", languageSkillDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /languageSkills -> get all the languageSkills.
     */
    @RequestMapping(value = "/languageSkills",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<LanguageSkillDTO> getAllLanguageSkills() {
        log.debug("REST request to get all LanguageSkills");
        return languageSkillService.findAll();
            }

    /**
     * GET  /languageSkills/:id -> get the "id" languageSkill.
     */
    @RequestMapping(value = "/languageSkills/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<LanguageSkillDTO> getLanguageSkill(@PathVariable Long id) {
        log.debug("REST request to get LanguageSkill : {}", id);
        LanguageSkillDTO languageSkillDTO = languageSkillService.findOne(id);
        return Optional.ofNullable(languageSkillDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /languageSkills/:id -> delete the "id" languageSkill.
     */
    @RequestMapping(value = "/languageSkills/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteLanguageSkill(@PathVariable Long id) {
        log.debug("REST request to delete LanguageSkill : {}", id);
        languageSkillService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("languageSkill", id.toString())).build();
    }

    /**
     * SEARCH  /_search/languageSkills/:query -> search for the languageSkill corresponding
     * to the query.
     */
    @RequestMapping(value = "/_search/languageSkills/{query:.+}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<LanguageSkillDTO> searchLanguageSkills(@PathVariable String query) {
        log.debug("Request to search LanguageSkills for query {}", query);
        return languageSkillService.search(query);
    }

    @RequestMapping(value = "/addLanguage", method = RequestMethod.POST)
    public LanguageSkillDTO addLanguages(@RequestBody LanguageSkillDTO languageSkillDTO) {
        return languageSkillService.addLanguage(languageSkillDTO);
    }

    @RequestMapping(value = "/languageSkills/languageLevels", method = RequestMethod.GET)
    @ResponseBody
    public String[] languageLevels(){
        return Stream.of(LanguageLevel.values()).map(LanguageLevel::name).toArray(String[]::new);
    }
}
