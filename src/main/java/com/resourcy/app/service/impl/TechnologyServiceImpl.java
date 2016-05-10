package com.resourcy.app.service.impl;

import com.resourcy.app.domain.Technology;
import com.resourcy.app.repository.GovernmentProjectRepository;
import com.resourcy.app.repository.TechnologyRepository;
import com.resourcy.app.repository.search.TechnologySearchRepository;
import com.resourcy.app.service.TechnologyService;
import com.resourcy.app.web.rest.dto.TechnologyDTO;
import com.resourcy.app.web.rest.mapper.TechnologyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * Service Implementation for managing Technology.
 */
@Service
@Transactional
public class TechnologyServiceImpl implements TechnologyService {

   private final Logger log = LoggerFactory.getLogger(TechnologyServiceImpl.class);

   @Inject
   private TechnologyRepository technologyRepository;

   @Inject
   private TechnologyMapper technologyMapper;

   @Inject
   private GovernmentProjectRepository governmentProjectRepository;

   @Inject
   private TechnologySearchRepository technologySearchRepository;

   /**
    * Save a technology.
    *
    * @return the persisted entity
    */
   public TechnologyDTO save(TechnologyDTO technologyDTO) {
      log.debug("Request to save Technology : {}", technologyDTO);
      Technology technology = technologyMapper.technologyDTOToTechnology(technologyDTO);
      technology = technologyRepository.save(technology);
      TechnologyDTO result = technologyMapper.technologyToTechnologyDTO(technology);
      technologySearchRepository.save(technology);
      return result;
   }

   /**
    * get all the technologys.
    *
    * @return the list of entities
    */
   @Transactional(readOnly = true)
   public List<TechnologyDTO> findAll() {
      log.debug("Request to get all Technologys");
      List<TechnologyDTO> result = technologyRepository.findAll().stream()
         .map(technologyMapper::technologyToTechnologyDTO)
         .collect(Collectors.toCollection(LinkedList::new));
      return result;
   }

   /**
    * get one technology by id.
    *
    * @return the entity
    */
   @Transactional(readOnly = true)
   public TechnologyDTO findOne(Long id) {
      log.debug("Request to get Technology : {}", id);
      Technology technology = technologyRepository.findOne(id);
      TechnologyDTO technologyDTO = technologyMapper.technologyToTechnologyDTO(technology);
      return technologyDTO;
   }

   /**
    * delete the  technology by id.
    */
   public void delete(Long id) {
      log.debug("Request to delete Technology : {}", id);
      technologyRepository.delete(id);
      technologySearchRepository.delete(id);
   }

   /**
    * search for the technology corresponding
    * to the query.
    */
   @Transactional(readOnly = true)
   public List<TechnologyDTO> search(String query) {

      log.debug("REST request to search Technologys for query {}", query);
      return StreamSupport
         .stream(technologySearchRepository.search(queryStringQuery(query)).spliterator(), false)
         .map(technologyMapper::technologyToTechnologyDTO)
         .collect(Collectors.toList());
   }

   @Override
   public TechnologyDTO addTechnology(TechnologyDTO dto) {
      Technology tech = technologyMapper.technologyDTOToTechnology(dto);
      if (dto.getGovernmentProjectId() != null) {
         tech.setGovernmentProject(governmentProjectRepository.findOne(dto.getGovernmentProjectId()));
      }
      technologyRepository.save(tech);
      return technologyMapper.technologyToTechnologyDTO(tech);
   }
}
