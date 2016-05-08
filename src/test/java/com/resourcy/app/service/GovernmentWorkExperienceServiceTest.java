package com.resourcy.app.service;

import com.resourcy.app.Application;
import com.resourcy.app.domain.GovernmentWorkExperience;
import com.resourcy.app.domain.Position;
import com.resourcy.app.web.rest.dto.GovernmentWorkExperienceDTO;
import com.resourcy.app.web.rest.mapper.GovernmentWorkExperienceMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@Transactional
public class GovernmentWorkExperienceServiceTest {
   
   @Inject
   private GovernmentWorkExperienceService governmentWorkExperienceService;

   @Inject
   private GovernmentWorkExperienceMapper governmentWorkExperienceMapper;

   private GovernmentWorkExperienceDTO newGovernmentWorkExperienceDTO() {
      GovernmentWorkExperience governmentWorkExperience = new GovernmentWorkExperience();
      governmentWorkExperience.setPosition(Position.PROGRAMMER);
      governmentWorkExperience.setPeriodStart(LocalDate.now());
      governmentWorkExperience.setPeriodEnd(LocalDate.now());
      governmentWorkExperience.setPersonalWorkHours(123);
      GovernmentWorkExperienceDTO governmentWorkExperienceDTO = governmentWorkExperienceMapper.governmentWorkExperienceToGovernmentWorkExperienceDTO(governmentWorkExperience);
      return governmentWorkExperienceDTO;
   }

   @Test
   @Transactional
   public void testAddGovernmentWorkExperience(){
      GovernmentWorkExperienceDTO governmentWorkExperience = governmentWorkExperienceService.addGovernmentWorkExperience(newGovernmentWorkExperienceDTO());
      assertNotNull(governmentWorkExperience);

      assertNotNull(governmentWorkExperience.getId());
      assertNotNull(governmentWorkExperience.getPeriodEnd());
      assertNotNull(governmentWorkExperience.getPeriodStart());
      assertNotNull(governmentWorkExperience.getPosition());
      assertNotNull(governmentWorkExperience.getPersonalWorkHours());

      List<GovernmentWorkExperienceDTO> governmentWorkExperiences = governmentWorkExperienceService.findAll();
      assertNotNull(governmentWorkExperiences);
      assertFalse(governmentWorkExperiences.isEmpty());
   }

   @Test
   @Transactional
   public void testSaveGovernmentWorkExperience() {
      GovernmentWorkExperienceDTO governmentWorkExperience = governmentWorkExperienceService.addGovernmentWorkExperience(newGovernmentWorkExperienceDTO());
      assertNotNull(governmentWorkExperience);

      governmentWorkExperience.setPosition(Position.ANALYST);

      GovernmentWorkExperienceDTO saved = governmentWorkExperience;
      governmentWorkExperience = governmentWorkExperienceService.save(saved);

      assertTrue(governmentWorkExperience.getId().equals(saved.getId()));
      assertNotNull(governmentWorkExperience.getId());
      assertNotNull(governmentWorkExperience.getPeriodEnd());
      assertNotNull(governmentWorkExperience.getPeriodStart());
      assertNotNull(governmentWorkExperience.getPosition());
      assertNotNull(governmentWorkExperience.getPersonalWorkHours());
      assertEquals(Position.ANALYST, governmentWorkExperience.getPosition());
   }

   @Test
   @Transactional
   public void testFindOneGovernmentWorkExperience(){
      GovernmentWorkExperienceDTO governmentWorkExperience = governmentWorkExperienceService.addGovernmentWorkExperience(newGovernmentWorkExperienceDTO());
      assertNotNull(governmentWorkExperience);

      GovernmentWorkExperienceDTO governmentWorkExperienceFound = governmentWorkExperienceService.findOne(governmentWorkExperience.getId());
      assertNotNull(governmentWorkExperienceFound);
      assertEquals(governmentWorkExperience, governmentWorkExperienceFound);
   }

   @Test
   @Transactional
   public void testDeleteGovernmentWorkExperience() {
      GovernmentWorkExperienceDTO governmentWorkExperience = governmentWorkExperienceService.addGovernmentWorkExperience(newGovernmentWorkExperienceDTO());
      assertNotNull(governmentWorkExperience);

      governmentWorkExperienceService.delete(governmentWorkExperience.getId());
   }
}
