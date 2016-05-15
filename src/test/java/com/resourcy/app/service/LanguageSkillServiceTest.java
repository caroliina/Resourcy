package com.resourcy.app.service;

import com.resourcy.app.Application;
import com.resourcy.app.domain.LanguageLevel;
import com.resourcy.app.domain.LanguageSkill;
import com.resourcy.app.service.validator.ValidationException;
import com.resourcy.app.web.rest.dto.LanguageSkillDTO;
import com.resourcy.app.web.rest.mapper.LanguageSkillMapper;
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
public class LanguageSkillServiceTest {

   @Inject
   private LanguageSkillService languageSkillService;

   @Inject
   private LanguageSkillMapper languageSkillMapper;

   private LanguageSkillDTO newLanguageSkillDTO() {
      LanguageSkill languageSkill = new LanguageSkill();
      languageSkill.setLanguage("language");
      languageSkill.setSpeaking(LanguageLevel.A1);
      languageSkill.setWriting(LanguageLevel.A1);
      LanguageSkillDTO languageSkillDTO = languageSkillMapper.languageSkillToLanguageSkillDTO(languageSkill);
      return languageSkillDTO;
   }

   @Test
   @Transactional
   public void testAddLanguageSkill() {
      LanguageSkillDTO languageSkill = languageSkillService.addLanguage(newLanguageSkillDTO());
      assertNotNull(languageSkill);

      assertNotNull(languageSkill.getId());
      assertNotNull(languageSkill.getLanguage());
      assertNotNull(languageSkill.getSpeaking());
      assertNotNull(languageSkill.getWriting());

      List<LanguageSkillDTO> languageSkills = languageSkillService.findAll();
      assertNotNull(languageSkills);
      assertFalse(languageSkills.isEmpty());
   }

   @Test
   @Transactional
   public void testSaveLanguageSkill() throws ValidationException {
      LanguageSkillDTO languageSkill = languageSkillService.addLanguage(newLanguageSkillDTO());
      assertNotNull(languageSkill);

      languageSkill.setLanguage("AwesomeLanguage");

      LanguageSkillDTO saved = languageSkill;
      languageSkill = languageSkillService.save(saved);

      assertTrue(languageSkill.getId().equals(saved.getId()));
      assertNotNull(languageSkill.getId());
      assertNotNull(languageSkill.getLanguage());
      assertNotNull(languageSkill.getSpeaking());
      assertNotNull(languageSkill.getWriting());
      assertEquals("AwesomeLanguage", languageSkill.getLanguage());
   }

   @Test
   @Transactional
   public void testFindOneLanguageSkill() {
      LanguageSkillDTO languageSkill = languageSkillService.addLanguage(newLanguageSkillDTO());
      assertNotNull(languageSkill);

      LanguageSkillDTO languageSkillFound = languageSkillService.findOne(languageSkill.getId());
      assertNotNull(languageSkillFound);
      assertEquals(languageSkill, languageSkillFound);
   }

   @Test
   @Transactional
   public void testDeleteLanguageSkill() {
      LanguageSkillDTO languageSkill = languageSkillService.addLanguage(newLanguageSkillDTO());
      assertNotNull(languageSkill);

      languageSkillService.delete(languageSkill.getId());
   }
}
