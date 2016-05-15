package com.resourcy.app.service;

import com.resourcy.app.Application;
import com.resourcy.app.domain.WorkAssignment;
import com.resourcy.app.service.validator.ValidationException;
import com.resourcy.app.web.rest.dto.WorkAssignmentDTO;
import com.resourcy.app.web.rest.mapper.WorkAssignmentMapper;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@Transactional
public class WorkAssignmentServiceTest {

   @Inject
   private WorkAssignmentService workAssignmentService;

   @Inject
   private WorkAssignmentMapper workAssignmentMapper;

   private WorkAssignmentDTO newWorkAssignmentDTO() {
      WorkAssignment workAssignment = new WorkAssignment();
      workAssignment.setDescription("description");
      WorkAssignmentDTO WorkAssignmentDTO = workAssignmentMapper.workAssignmentToWorkAssignmentDTO(workAssignment);
      return WorkAssignmentDTO;
   }

   @Test
   @Transactional
   public void testAddWorkAssignment(){
      WorkAssignmentDTO workAssignment = workAssignmentService.addWorkAssignment(newWorkAssignmentDTO());
      assertNotNull(workAssignment);

      assertNotNull(workAssignment.getId());
      assertNotNull(workAssignment.getDescription());

      List<WorkAssignmentDTO> WorkAssignments = workAssignmentService.findAll();
      assertNotNull(WorkAssignments);
      assertFalse(WorkAssignments.isEmpty());
   }

   @Test
   @Transactional
   public void testSaveWorkAssignment() throws ValidationException {
      WorkAssignmentDTO workAssignment = workAssignmentService.addWorkAssignment(newWorkAssignmentDTO());
      assertNotNull(workAssignment);

      workAssignment.setDescription("awesomeDescription");

      WorkAssignmentDTO saved = workAssignment;
      workAssignment = workAssignmentService.save(saved);

      assertTrue(workAssignment.getId().equals(saved.getId()));
      assertNotNull(workAssignment.getId());
      assertNotNull(workAssignment.getDescription());
      assertEquals("awesomeDescription", workAssignment.getDescription());
   }

   @Test
   @Transactional
   public void testFindOneWorkAssignment(){
      WorkAssignmentDTO workAssignment = workAssignmentService.addWorkAssignment(newWorkAssignmentDTO());
      assertNotNull(workAssignment);

      WorkAssignmentDTO WorkAssignmentFound = workAssignmentService.findOne(workAssignment.getId());
      assertNotNull(WorkAssignmentFound);
      assertEquals(workAssignment, WorkAssignmentFound);
   }

   @Test
   @Transactional
   public void testDeleteWorkAssignment() {
      WorkAssignmentDTO workAssignment = workAssignmentService.addWorkAssignment(newWorkAssignmentDTO());
      assertNotNull(workAssignment);

      workAssignmentService.delete(workAssignment.getId());
   }
}
