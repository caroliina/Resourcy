package com.resourcy.app.service;

import com.resourcy.app.Application;
import com.resourcy.app.domain.WorkExperience;
import com.resourcy.app.domain.Position;
import com.resourcy.app.web.rest.dto.WorkExperienceDTO;
import com.resourcy.app.web.rest.mapper.WorkExperienceMapper;
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
public class WorkExperienceServiceTest {

   @Inject
   private WorkExperienceService workExperienceService;

   @Inject
   private WorkExperienceMapper workExperienceMapper;

   private WorkExperienceDTO newWorkExperienceDTO() {
      WorkExperience workExperience = new WorkExperience();
      workExperience.setPosition(Position.PROGRAMMER);
      workExperience.setPeriodStart(LocalDate.now());
      workExperience.setPeriodEnd(LocalDate.now());
      workExperience.setLocation("location");
      workExperience.setOrganization("organization");
      WorkExperienceDTO WorkExperienceDTO = workExperienceMapper.workExperienceToWorkExperienceDTO(workExperience);
      return WorkExperienceDTO;
   }

   @Test
   @Transactional
   public void testAddWorkExperience(){
      WorkExperienceDTO workExperience = workExperienceService.addWorkExperience(newWorkExperienceDTO());
      assertNotNull(workExperience);

      assertNotNull(workExperience.getId());
      assertNotNull(workExperience.getPeriodEnd());
      assertNotNull(workExperience.getPeriodStart());
      assertNotNull(workExperience.getPosition());
      assertNotNull(workExperience.getLocation());
      assertNotNull(workExperience.getOrganization());

      List<WorkExperienceDTO> WorkExperiences = workExperienceService.findAll();
      assertNotNull(WorkExperiences);
      assertFalse(WorkExperiences.isEmpty());
   }

   @Test
   @Transactional
   public void testSaveWorkExperience() {
      WorkExperienceDTO workExperience = workExperienceService.addWorkExperience(newWorkExperienceDTO());
      assertNotNull(workExperience);

      workExperience.setPosition(Position.ANALYST);

      WorkExperienceDTO saved = workExperience;
      workExperience = workExperienceService.save(saved);

      assertTrue(workExperience.getId().equals(saved.getId()));
      assertNotNull(workExperience.getId());
      assertNotNull(workExperience.getPeriodEnd());
      assertNotNull(workExperience.getPeriodStart());
      assertNotNull(workExperience.getPosition());
      assertNotNull(workExperience.getLocation());
      assertNotNull(workExperience.getOrganization());
      assertEquals(Position.ANALYST, workExperience.getPosition());
   }

   @Test
   @Transactional
   public void testFindOneWorkExperience(){
      WorkExperienceDTO workExperience = workExperienceService.addWorkExperience(newWorkExperienceDTO());
      assertNotNull(workExperience);

      WorkExperienceDTO WorkExperienceFound = workExperienceService.findOne(workExperience.getId());
      assertNotNull(WorkExperienceFound);
      assertEquals(workExperience, WorkExperienceFound);
   }

   @Test
   @Transactional
   public void testDeleteWorkExperience() {
      WorkExperienceDTO workExperience = workExperienceService.addWorkExperience(newWorkExperienceDTO());
      assertNotNull(workExperience);

      workExperienceService.delete(workExperience.getId());
   }
}
