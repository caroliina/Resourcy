package com.resourcy.app.service;

import com.resourcy.app.Application;
import com.resourcy.app.domain.Education;
import com.resourcy.app.domain.EducationDegrees;
import com.resourcy.app.web.rest.dto.EducationDTO;
import com.resourcy.app.web.rest.mapper.EducationMapper;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@Transactional
public class EducationServiceTest {

   @Inject
   private EducationService educationService;

   @Inject
   private EducationMapper educationMapper;

   private EducationDTO newEducationDTO() {
      Education education = new Education();
      education.setSpeciality("speciality");
      education.setInstitution("institution");
      education.setDegree(EducationDegrees.BACHELOR);
      education.setPeriodStart(LocalDate.now());
      education.setPeriodEnd(LocalDate.now());
      EducationDTO educationDTO = educationMapper.educationToEducationDTO(education);
      return educationDTO;
   }

   @Test
   @Transactional
   public void testAddEducation(){
      EducationDTO education = educationService.addEducation(newEducationDTO());
      assertNotNull(education);

      assertNotNull(education.getId());
      assertNotNull(education.getSpeciality());
      assertNotNull(education.getPeriodStart());
      assertNotNull(education.getPeriodEnd());
      assertNotNull(education.getDegree());
      assertNotNull(education.getInstitution());

      List<EducationDTO> educations = educationService.findAll();
      assertNotNull(educations);
      assertFalse(educations.isEmpty());
   }

   @Test
   @Transactional
   public void testSaveEducation() {
      EducationDTO education = educationService.addEducation(newEducationDTO());
      assertNotNull(education);

      education.setInstitution("AwesomeInstitution");

      EducationDTO saved = education;
      education = educationService.save(saved);

      assertTrue(education.getId().equals(saved.getId()));
      assertNotNull(education.getId());
      assertNotNull(education.getSpeciality());
      assertNotNull(education.getPeriodStart());
      assertNotNull(education.getPeriodEnd());
      assertNotNull(education.getDegree());
      assertNotNull(education.getInstitution());
      assertEquals("AwesomeInstitution", education.getInstitution());
   }

   @Test
   @Transactional
   public void testFindOneEducation(){
      EducationDTO education = educationService.addEducation(newEducationDTO());
      assertNotNull(education);

      EducationDTO educationFound = educationService.findOne(education.getId());
      assertNotNull(educationFound);
      assertEquals(education, educationFound);
   }

   @Test
   @Transactional
   public void testDeleteEducation() {
      EducationDTO education = educationService.addEducation(newEducationDTO());
      assertNotNull(education);

      educationService.delete(education.getId());
   }
}
