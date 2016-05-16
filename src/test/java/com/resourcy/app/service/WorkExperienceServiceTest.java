package com.resourcy.app.service;

import com.resourcy.app.Application;
import com.resourcy.app.domain.Position;
import com.resourcy.app.domain.WorkAssignment;
import com.resourcy.app.domain.WorkExperience;
import com.resourcy.app.repository.WorkAssignmentRepository;
import com.resourcy.app.service.validator.ValidationException;
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
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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

   @Inject
   private WorkAssignmentRepository workAssignmentRepository;

   private WorkExperienceDTO newWorkExperienceDTO() {
      WorkExperience workExperience = new WorkExperience();
      workExperience.setPosition(Position.PROGRAMMER);
      workExperience.setPeriodStart(LocalDate.now());
      workExperience.setPeriodEnd(LocalDate.now());
      workExperience.setLocation("location");
      workExperience.setOrganization("organization");
      workExperience.setId(1L);
      workExperience.setCreatedBy("User");
      workExperience.setCreatedDate(ZonedDateTime.now());
      workExperience.setLastModifiedBy("User");
      workExperience.setLastModifiedDate(ZonedDateTime.now());
      workExperience.setWorkAssignments(newWorkAssignment(workExperience));

      WorkExperienceDTO WorkExperienceDTO = workExperienceMapper.workExperienceToWorkExperienceDTO(workExperience);
      return WorkExperienceDTO;
   }

   public  List<WorkAssignment> newWorkAssignment(WorkExperience workExperience){
      List<WorkAssignment> workAssignments = new ArrayList<>();

      WorkAssignment workAssignment = new WorkAssignment();
      workAssignment.setDescription("description");
      workAssignment.setWorkExperience(workExperience);
      workAssignmentRepository.save(workAssignment);

      workAssignments.add(workAssignment);

      return workAssignments;
   }

   @Test
   @Transactional
   public void testAddWorkExperience(){

      WorkExperienceDTO workExperienceDto = workExperienceService.addWorkExperience(newWorkExperienceDTO());

      assertNotNull(workExperienceDto);

      assertNotNull(workExperienceDto.getId());
      assertNotNull(workExperienceDto.getPeriodEnd());
      assertNotNull(workExperienceDto.getPeriodStart());
      assertNotNull(workExperienceDto.getPosition());
      assertNotNull(workExperienceDto.getLocation());
      assertNotNull(workExperienceDto.getOrganization());
      assertTrue(!workExperienceDto.getWorkAssignments().isEmpty());

      List<WorkExperienceDTO> workExperiences = workExperienceService.findAll();
      assertNotNull(workExperiences);
      assertFalse(workExperiences.isEmpty());
   }

   @Test
   @Transactional
   public void testSaveWorkExperience() throws ValidationException {
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
