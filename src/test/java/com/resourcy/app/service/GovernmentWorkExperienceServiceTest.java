package com.resourcy.app.service;

import com.resourcy.app.Application;
import com.resourcy.app.domain.GovernmentWorkExperience;
import com.resourcy.app.domain.Position;
import com.resourcy.app.domain.WorkAssignment;
import com.resourcy.app.repository.WorkAssignmentRepository;
import com.resourcy.app.service.validator.ValidationException;
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
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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

   @Inject
   private WorkAssignmentRepository workAssignmentRepository;

   private GovernmentWorkExperienceDTO newGovernmentWorkExperienceDTO() {
      GovernmentWorkExperience governmentWorkExperience = new GovernmentWorkExperience();
      governmentWorkExperience.setPosition(Position.PROGRAMMER);
      governmentWorkExperience.setPeriodStart(LocalDate.now());
      governmentWorkExperience.setPeriodEnd(LocalDate.now());
      governmentWorkExperience.setPersonalWorkHours(123);
      governmentWorkExperience.setId(1L);
      governmentWorkExperience.setCreatedBy("User");
      governmentWorkExperience.setCreatedDate(ZonedDateTime.now());
      governmentWorkExperience.setLastModifiedBy("User");
      governmentWorkExperience.setLastModifiedDate(ZonedDateTime.now());
      governmentWorkExperience.setWorkAssignments(newWorkAssignment(governmentWorkExperience));
      GovernmentWorkExperienceDTO governmentWorkExperienceDTO = governmentWorkExperienceMapper.governmentWorkExperienceToGovernmentWorkExperienceDTO(governmentWorkExperience);
      return governmentWorkExperienceDTO;
   }

   public  List<WorkAssignment> newWorkAssignment(GovernmentWorkExperience governmentWorkExperience){
      List<WorkAssignment> workAssignments = new ArrayList<>();

      WorkAssignment workAssignment = new WorkAssignment();
      workAssignment.setDescription("description");
      workAssignment.setGovernmentWorkExperience(governmentWorkExperience);
      workAssignmentRepository.save(workAssignment);

      workAssignments.add(workAssignment);

      return workAssignments;
   }

   @Test
   @Transactional
   public void testAddGovernmentWorkExperience() throws ValidationException {
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
   public void testSaveGovernmentWorkExperience() throws ValidationException {
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
   public void testFindOneGovernmentWorkExperience() throws ValidationException {
      GovernmentWorkExperienceDTO governmentWorkExperience = governmentWorkExperienceService.addGovernmentWorkExperience(newGovernmentWorkExperienceDTO());
      assertNotNull(governmentWorkExperience);

      GovernmentWorkExperienceDTO governmentWorkExperienceFound = governmentWorkExperienceService.findOne(governmentWorkExperience.getId());
      assertNotNull(governmentWorkExperienceFound);
      assertEquals(governmentWorkExperience, governmentWorkExperienceFound);
   }

   @Test
   @Transactional
   public void testDeleteGovernmentWorkExperience() throws ValidationException {
      GovernmentWorkExperienceDTO governmentWorkExperience = governmentWorkExperienceService.addGovernmentWorkExperience(newGovernmentWorkExperienceDTO());
      assertNotNull(governmentWorkExperience);

      governmentWorkExperienceService.delete(governmentWorkExperience.getId());
   }
}
