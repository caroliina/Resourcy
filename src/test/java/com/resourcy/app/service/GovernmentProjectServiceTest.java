package com.resourcy.app.service;

import com.resourcy.app.Application;
import com.resourcy.app.domain.GovernmentProject;
import com.resourcy.app.service.validator.ValidationException;
import com.resourcy.app.web.rest.dto.GovernmentProjectDTO;
import com.resourcy.app.web.rest.mapper.GovernmentProjectMapper;
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
public class GovernmentProjectServiceTest {

   @Inject
   private GovernmentProjectService governmentProjectService;

   @Inject
   private GovernmentProjectMapper governmentProjectMapper;

   private GovernmentProjectDTO newGovernmentProjectDTO() {
      GovernmentProject governmentProject = new GovernmentProject();
      governmentProject.setBuyer("buyer");
      governmentProject.setServiceName("service name");
      governmentProject.setTotalProjectWorkHours(123);
      GovernmentProjectDTO governmentProjectDTO = governmentProjectMapper.governmentProjectToGovernmentProjectDTO(governmentProject);
      return governmentProjectDTO;
   }

   @Test
   @Transactional
   public void testAddGovernmentProject(){
      GovernmentProjectDTO governmentProject = governmentProjectService.addGovernmentProject(newGovernmentProjectDTO());
      assertNotNull(governmentProject);

      assertNotNull(governmentProject.getId());
      assertNotNull(governmentProject.getBuyer());
      assertNotNull(governmentProject.getServiceName());
      assertNotNull(governmentProject.getTotalProjectWorkHours());

      List<GovernmentProjectDTO> governmentProjects = governmentProjectService.findAll();
      assertNotNull(governmentProjects);
      assertFalse(governmentProjects.isEmpty());
   }

   @Test
   @Transactional
   public void testSaveGovernmentProject() throws ValidationException {
      GovernmentProjectDTO governmentProject = governmentProjectService.addGovernmentProject(newGovernmentProjectDTO());
      assertNotNull(governmentProject);

      governmentProject.setBuyer("AwesomeBuyer");

      GovernmentProjectDTO saved = governmentProject;
      governmentProject = governmentProjectService.save(saved);

      assertTrue(governmentProject.getId().equals(saved.getId()));
      assertNotNull(governmentProject.getId());
      assertNotNull(governmentProject.getBuyer());
      assertNotNull(governmentProject.getServiceName());
      assertNotNull(governmentProject.getTotalProjectWorkHours());
      assertEquals("AwesomeBuyer", governmentProject.getBuyer());
   }

   @Test
   @Transactional
   public void testFindOneGovernmentProject(){
      GovernmentProjectDTO governmentProject = governmentProjectService.addGovernmentProject(newGovernmentProjectDTO());
      assertNotNull(governmentProject);

      GovernmentProjectDTO governmentProjectFound = governmentProjectService.findOne(governmentProject.getId());
      assertNotNull(governmentProjectFound);
      assertEquals(governmentProject, governmentProjectFound);
   }

   @Test
   @Transactional
   public void testDeleteGovernmentProject() {
      GovernmentProjectDTO governmentProject = governmentProjectService.addGovernmentProject(newGovernmentProjectDTO());
      assertNotNull(governmentProject);

      governmentProjectService.delete(governmentProject.getId());
   }
}
