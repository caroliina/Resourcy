package com.resourcy.app.service;

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
    public TechnologyDTO save(TechnologyDTO technologyDTO);

    /**
     *  get all the technologys.
     *  @return the list of entities
     */
    public List<TechnologyDTO> findAll();

    /**
     *  get the "id" technology.
     *  @return the entity
     */
    public TechnologyDTO findOne(Long id);

    /**
     *  delete the "id" technology.
     */
    public void delete(Long id);

    /**
     * search for the technology corresponding
     * to the query.
     */
    public List<TechnologyDTO> search(String query);

    TechnologyDTO addTechnology(TechnologyDTO dto);
}
