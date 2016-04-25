package com.resourcy.app.web.rest;

import com.resourcy.app.Application;
import com.resourcy.app.domain.LanguageSkill;
import com.resourcy.app.repository.LanguageSkillRepository;
import com.resourcy.app.service.LanguageSkillService;
import com.resourcy.app.web.rest.dto.LanguageSkillDTO;
import com.resourcy.app.web.rest.mapper.LanguageSkillMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the LanguageSkillResource REST controller.
 *
 * @see LanguageSkillController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class LanguageSkillControllerIntTest {

    private static final String DEFAULT_LANGUAGE = "AAAAA";
    private static final String UPDATED_LANGUAGE = "BBBBB";
    private static final String DEFAULT_SPEAKING = "AAAAA";
    private static final String UPDATED_SPEAKING = "BBBBB";
    private static final String DEFAULT_WRITING = "AAAAA";
    private static final String UPDATED_WRITING = "BBBBB";

    @Inject
    private LanguageSkillRepository languageSkillRepository;

    @Inject
    private LanguageSkillMapper languageSkillMapper;

    @Inject
    private LanguageSkillService languageSkillService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restLanguageSkillMockMvc;

    private LanguageSkill languageSkill;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        LanguageSkillController languageSkillController = new LanguageSkillController();
        ReflectionTestUtils.setField(languageSkillController, "languageSkillService", languageSkillService);
        ReflectionTestUtils.setField(languageSkillController, "languageSkillMapper", languageSkillMapper);
        this.restLanguageSkillMockMvc = MockMvcBuilders.standaloneSetup(languageSkillController)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        languageSkill = new LanguageSkill();
        languageSkill.setLanguage(DEFAULT_LANGUAGE);
        languageSkill.setSpeaking(DEFAULT_SPEAKING);
        languageSkill.setWriting(DEFAULT_WRITING);
    }

    @Test
    @Transactional
    public void createLanguageSkill() throws Exception {
        int databaseSizeBeforeCreate = languageSkillRepository.findAll().size();

        // Create the LanguageSkill
        LanguageSkillDTO languageSkillDTO = languageSkillMapper.languageSkillToLanguageSkillDTO(languageSkill);

        restLanguageSkillMockMvc.perform(post("/api/languageSkills")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(languageSkillDTO)))
                .andExpect(status().isCreated());

        // Validate the LanguageSkill in the database
        List<LanguageSkill> languageSkills = languageSkillRepository.findAll();
        assertThat(languageSkills).hasSize(databaseSizeBeforeCreate + 1);
        LanguageSkill testLanguageSkill = languageSkills.get(languageSkills.size() - 1);
        assertThat(testLanguageSkill.getLanguage()).isEqualTo(DEFAULT_LANGUAGE);
        assertThat(testLanguageSkill.getSpeaking()).isEqualTo(DEFAULT_SPEAKING);
        assertThat(testLanguageSkill.getWriting()).isEqualTo(DEFAULT_WRITING);
    }

    @Test
    @Transactional
    public void getAllLanguageSkills() throws Exception {
        // Initialize the database
        languageSkillRepository.saveAndFlush(languageSkill);

        // Get all the languageSkills
        restLanguageSkillMockMvc.perform(get("/api/languageSkills?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(languageSkill.getId().intValue())))
                .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE.toString())))
                .andExpect(jsonPath("$.[*].speaking").value(hasItem(DEFAULT_SPEAKING.toString())))
                .andExpect(jsonPath("$.[*].writing").value(hasItem(DEFAULT_WRITING.toString())));
    }

    @Test
    @Transactional
    public void getLanguageSkill() throws Exception {
        // Initialize the database
        languageSkillRepository.saveAndFlush(languageSkill);

        // Get the languageSkill
        restLanguageSkillMockMvc.perform(get("/api/languageSkills/{id}", languageSkill.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(languageSkill.getId().intValue()))
            .andExpect(jsonPath("$.language").value(DEFAULT_LANGUAGE.toString()))
            .andExpect(jsonPath("$.speaking").value(DEFAULT_SPEAKING.toString()))
            .andExpect(jsonPath("$.writing").value(DEFAULT_WRITING.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLanguageSkill() throws Exception {
        // Get the languageSkill
        restLanguageSkillMockMvc.perform(get("/api/languageSkills/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLanguageSkill() throws Exception {
        // Initialize the database
        languageSkillRepository.saveAndFlush(languageSkill);

		int databaseSizeBeforeUpdate = languageSkillRepository.findAll().size();

        // Update the languageSkill
        languageSkill.setLanguage(UPDATED_LANGUAGE);
        languageSkill.setSpeaking(UPDATED_SPEAKING);
        languageSkill.setWriting(UPDATED_WRITING);
        LanguageSkillDTO languageSkillDTO = languageSkillMapper.languageSkillToLanguageSkillDTO(languageSkill);

        restLanguageSkillMockMvc.perform(put("/api/languageSkills")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(languageSkillDTO)))
                .andExpect(status().isOk());

        // Validate the LanguageSkill in the database
        List<LanguageSkill> languageSkills = languageSkillRepository.findAll();
        assertThat(languageSkills).hasSize(databaseSizeBeforeUpdate);
        LanguageSkill testLanguageSkill = languageSkills.get(languageSkills.size() - 1);
        assertThat(testLanguageSkill.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
        assertThat(testLanguageSkill.getSpeaking()).isEqualTo(UPDATED_SPEAKING);
        assertThat(testLanguageSkill.getWriting()).isEqualTo(UPDATED_WRITING);
    }

    @Test
    @Transactional
    public void deleteLanguageSkill() throws Exception {
        // Initialize the database
        languageSkillRepository.saveAndFlush(languageSkill);

		int databaseSizeBeforeDelete = languageSkillRepository.findAll().size();

        // Get the languageSkill
        restLanguageSkillMockMvc.perform(delete("/api/languageSkills/{id}", languageSkill.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<LanguageSkill> languageSkills = languageSkillRepository.findAll();
        assertThat(languageSkills).hasSize(databaseSizeBeforeDelete - 1);
    }
}
