package com.resourcy.app.service;

import com.resourcy.app.Application;
import com.resourcy.app.domain.AdditionalStudy;
import com.resourcy.app.web.rest.dto.AdditionalStudyDTO;
import com.resourcy.app.web.rest.mapper.AdditionalStudyMapper;
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
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@Transactional
public class AdditionalStudyServiceTest {

   @Inject
   private AdditionalStudyService additionalStudyService;

   @Inject
   private AdditionalStudyMapper additionalStudyMapper;

   private AdditionalStudyDTO newAdditionalStudyDTO() {
      AdditionalStudy additionalStudy = new AdditionalStudy();
      additionalStudy.setInstitution("institution");
      additionalStudy.setDescription("description");
      additionalStudy.setPeriodStart(LocalDate.now());
      additionalStudy.setPeriodEnd(LocalDate.now());
      AdditionalStudyDTO additionalStudyDTO = additionalStudyMapper.additionalStudyToAdditionalStudyDTO(additionalStudy);
      return additionalStudyDTO;
   }

   @Test
   @Transactional
   public void testAddAdditionalStudy(){
      AdditionalStudyDTO additionalStudy = additionalStudyService.addStudy(newAdditionalStudyDTO());
      assertNotNull(additionalStudy);

      assertNotNull(additionalStudy.getId());
      assertNotNull(additionalStudy.getDescription());
      assertNotNull(additionalStudy.getPeriodStart());
      assertNotNull(additionalStudy.getPeriodEnd());
      assertNotNull(additionalStudy.getInstitution());

      List<AdditionalStudyDTO> additionalStudies = additionalStudyService.findAll();
      assertNotNull(additionalStudies);
      assertFalse(additionalStudies.isEmpty());
   }

   @Test
   @Transactional
   public void testSaveAdditionalStudy() {
      AdditionalStudyDTO additionalStudy = additionalStudyService.addStudy(newAdditionalStudyDTO());
      assertNotNull(additionalStudy);

      additionalStudy.setInstitution("AwesomeInstitution");

      AdditionalStudyDTO saved = additionalStudy;
      additionalStudy = additionalStudyService.save(saved);

      assertTrue(additionalStudy.getId().equals(saved.getId()));
      assertNotNull(additionalStudy.getId());
      assertNotNull(additionalStudy.getDescription());
      assertNotNull(additionalStudy.getPeriodStart());
      assertNotNull(additionalStudy.getPeriodEnd());
      assertNotNull(additionalStudy.getInstitution());
      assertEquals("AwesomeInstitution", additionalStudy.getInstitution());
   }

   @Test
   @Transactional
   public void testFindOneAdditionalSkill(){
      AdditionalStudyDTO additionalStudy = additionalStudyService.addStudy(newAdditionalStudyDTO());
      assertNotNull(additionalStudy);

      AdditionalStudyDTO additionalStudyFound = additionalStudyService.findOne(additionalStudy.getId());
      assertNotNull(additionalStudyFound);
      assertEquals(additionalStudy, additionalStudyFound);
   }

   @Test
   @Transactional
   public void testDeleteAdditionalStudy() {
      AdditionalStudyDTO additionalStudy = additionalStudyService.addStudy(newAdditionalStudyDTO());
      assertNotNull(additionalStudy);

      additionalStudyService.delete(additionalStudy.getId());
   }
}
