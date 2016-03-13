package com.resourcy.app.service;

import com.resourcy.app.domain.CurriculumVitae;
import com.resourcy.app.web.rest.dto.CurriculumVitaeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing CurriculumVitae.
 */
public interface CurriculumVitaeService {

    /**
     * Save a curriculumVitae.
     * @return the persisted entity
     */
    public CurriculumVitaeDTO save(CurriculumVitaeDTO curriculumVitaeDTO);

    /**
     *  get all the curriculumVitaes.
     *  @return the list of entities
     */
    public Page<CurriculumVitae> findAll(Pageable pageable);

    /**
     *  get the "id" curriculumVitae.
     *  @return the entity
     */
    public CurriculumVitaeDTO findOne(Long id);

    /**
     *  delete the "id" curriculumVitae.
     */
    public void delete(Long id);

    /**
     * search for the curriculumVitae corresponding
     * to the query.
     */
    public List<CurriculumVitaeDTO> search(String query);
}
