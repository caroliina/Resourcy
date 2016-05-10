package com.resourcy.app.service;

import com.resourcy.app.Application;
import com.resourcy.app.domain.AdditionalSkill;
import com.resourcy.app.domain.Type;
import com.resourcy.app.web.rest.dto.AdditionalSkillDTO;
import com.resourcy.app.web.rest.mapper.AdditionalSkillMapper;
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
public class AdditionalSkillServiceTest {

   @Inject
   private AdditionalSkillService  additionalSkillService;

   @Inject
   private AdditionalSkillMapper additionalSkillMapper;

   private AdditionalSkillDTO newAdditionalSkillDTO() {
      AdditionalSkill additionalSkill = new AdditionalSkill();
      additionalSkill.setType(Type.PROGRAMMING_LANGUAGE);
      additionalSkill.setDescription("description");
      additionalSkill.setExperience(10.00);
      AdditionalSkillDTO additionalSkillDTO = additionalSkillMapper.additionalSkillToAdditionalSkillDTO(additionalSkill);
      return additionalSkillDTO;
   }

   @Test
   @Transactional
   public void testAddAdditionalSkill(){
      AdditionalSkillDTO additionalSkill = additionalSkillService.addSkill(newAdditionalSkillDTO());
      assertNotNull(additionalSkill);

      assertNotNull(additionalSkill.getId());
      assertNotNull(additionalSkill.getDescription());
      assertNotNull(additionalSkill.getType());
      assertNotNull(additionalSkill.getExperience());

      List<AdditionalSkillDTO> additionalSkills = additionalSkillService.findAll();
      assertNotNull(additionalSkills);
      assertFalse(additionalSkills.isEmpty());
   }

   @Test
   @Transactional
   public void testSaveAdditionalSkill() {
      AdditionalSkillDTO additionalSkill = additionalSkillService.addSkill(newAdditionalSkillDTO());
      assertNotNull(additionalSkill);

      additionalSkill.setDescription("AwesomeDescription");

      AdditionalSkillDTO saved = additionalSkill;
      additionalSkill = additionalSkillService.save(saved);

      assertTrue(additionalSkill.getId().equals(saved.getId()));
      assertNotNull(additionalSkill.getId());
      assertNotNull(additionalSkill.getExperience());
      assertNotNull(additionalSkill.getType());
      assertNotNull(additionalSkill.getDescription());
      assertEquals("AwesomeDescription", additionalSkill.getDescription());
   }

   @Test
   @Transactional
   public void testFindOneAdditionalSkill(){
      AdditionalSkillDTO additionalSkill = additionalSkillService.addSkill(newAdditionalSkillDTO());
      assertNotNull(additionalSkill);

      AdditionalSkillDTO additionalSkillFound = additionalSkillService.findOne(additionalSkill.getId());
      assertNotNull(additionalSkillFound);
      assertEquals(additionalSkill, additionalSkillFound);
   }

   @Test
   @Transactional
   public void testDeleteAdditionalSkill() {
      AdditionalSkillDTO additionalSkill = additionalSkillService.addSkill(newAdditionalSkillDTO());
      assertNotNull(additionalSkill);

      additionalSkillService.delete(additionalSkill.getId());
   }
}
