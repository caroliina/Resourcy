package com.resourcy.app.service;

import com.resourcy.app.service.validator.ValidationException;
import com.resourcy.app.web.rest.dto.AdditionalStudyDTO;

import java.util.List;

/**
 * Service Interface for managing AdditionalStudy.
 */
public interface AdditionalStudyService {

    /**
     * Save a additionalStudy.
     * @return the persisted entity
     */
    public AdditionalStudyDTO save(AdditionalStudyDTO additionalStudyDTO) throws ValidationException;

    /**
     *  get all the additionalStudys.
     *  @return the list of entities
     */
    public List<AdditionalStudyDTO> findAll();

    /**
     *  get the "id" additionalStudy.
     *  @return the entity
     */
    public AdditionalStudyDTO findOne(Long id);

    /**
     *  delete the "id" additionalStudy.
     */
    public void delete(Long id);

    /**
     * search for the additionalStudy corresponding
     * to the query.
     */
    public List<AdditionalStudyDTO> search(String query);

    AdditionalStudyDTO addStudy(AdditionalStudyDTO additionalStudyDTO);
}
