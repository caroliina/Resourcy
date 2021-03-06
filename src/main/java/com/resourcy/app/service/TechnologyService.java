package com.resourcy.app.service;

import com.resourcy.app.service.validator.ValidationException;
import com.resourcy.app.web.rest.dto.TechnologyDTO;

import java.util.List;

/**
 * Service Interface for managing Technology.
 */
public interface TechnologyService {

    /**
     * Save a technology.
     * @return the persisted entity
     */
    TechnologyDTO save(TechnologyDTO technologyDTO) throws ValidationException;

    /**
     *  get all the technologys.
     *  @return the list of entities
     */
    List<TechnologyDTO> findAll();

    /**
     *  get the "id" technology.
     *  @return the entity
     */
    TechnologyDTO findOne(Long id);

    /**
     *  delete the "id" technology.
     */
    void delete(Long id);

    /**
     * search for the technology corresponding
     * to the query.
     */
    List<TechnologyDTO> search(String query);

    TechnologyDTO addTechnology(TechnologyDTO dto);
}
