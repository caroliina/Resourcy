package com.resourcy.app.service;

import com.resourcy.app.Application;
import com.resourcy.app.domain.Technology;
import com.resourcy.app.web.rest.dto.TechnologyDTO;
import com.resourcy.app.web.rest.mapper.TechnologyMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@Transactional
public class TechnologyServiceTest {

   @Inject
   private TechnologyService technologyService;

   @Inject
   private TechnologyMapper technologyMapper;

   private TechnologyDTO newTechnologyDTO() {
      Technology technology = new Technology();
      technology.setType("type");
      technology.setDescription("description");
      TechnologyDTO technologyDTO = technologyMapper.technologyToTechnologyDTO(technology);
      return technologyDTO;
   }

   @Test
   @Transactional
   public void testAddTechnology() {
      TechnologyDTO technology = technologyService.addTechnology(newTechnologyDTO());
      assertNotNull(technology);

      assertNotNull(technology.getId());
      assertNotNull(technology.getType());
      assertNotNull(technology.getDescription());

      List<TechnologyDTO> technologies = technologyService.findAll();
      assertNotNull(technologies);
      assertFalse(technologies.isEmpty());
   }

   @Test
   @Transactional
   public void testSaveTechnology() {
      TechnologyDTO technology = technologyService.addTechnology(newTechnologyDTO());
      assertNotNull(technology);

      technology.setDescription("AwesomeDescription");

      TechnologyDTO saved = technology;
      technology = technologyService.save(saved);

      assertTrue(technology.getId().equals(saved.getId()));
      assertNotNull(technology.getId());
      assertNotNull(technology.getType());
      assertNotNull(technology.getDescription());
      assertEquals("AwesomeDescription", technology.getDescription());
   }

   @Test
   @Transactional
   public void testFindOneTechnology() {
      TechnologyDTO technology = technologyService.addTechnology(newTechnologyDTO());
      assertNotNull(technology);

      TechnologyDTO technologyFound = technologyService.findOne(technology.getId());
      assertNotNull(technologyFound);
      assertEquals(technology, technologyFound);
   }

   @Test
   @Transactional
   public void testDeleteTechnology() {
      TechnologyDTO technology = technologyService.addTechnology(newTechnologyDTO());
      assertNotNull(technology);

      technologyService.delete(technology.getId());
   }
}
