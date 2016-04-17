package com.resourcy.app.web.rest;

import com.resourcy.app.Application;
import com.resourcy.app.domain.AdditionalSkill;
import com.resourcy.app.repository.AdditionalSkillRepository;
import com.resourcy.app.service.AdditionalSkillService;
import com.resourcy.app.web.rest.dto.AdditionalSkillDTO;
import com.resourcy.app.web.rest.mapper.AdditionalSkillMapper;

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
 * Test class for the AdditionalSkillResource REST controller.
 *
 * @see AdditionalSkillController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class AdditionalSkillControllerIntTest {

    private static final String DEFAULT_TYPE = "AAAAA";
    private static final String UPDATED_TYPE = "BBBBB";
    private static final String DEFAULT_DESCRIPTION = "AAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBB";

    @Inject
    private AdditionalSkillRepository additionalSkillRepository;

    @Inject
    private AdditionalSkillMapper additionalSkillMapper;

    @Inject
    private AdditionalSkillService additionalSkillService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restAdditionalSkillMockMvc;

    private AdditionalSkill additionalSkill;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AdditionalSkillController additionalSkillController = new AdditionalSkillController();
        ReflectionTestUtils.setField(additionalSkillController, "additionalSkillService", additionalSkillService);
        ReflectionTestUtils.setField(additionalSkillController, "additionalSkillMapper", additionalSkillMapper);
        this.restAdditionalSkillMockMvc = MockMvcBuilders.standaloneSetup(additionalSkillController)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        additionalSkill = new AdditionalSkill();
        additionalSkill.setType(DEFAULT_TYPE);
        additionalSkill.setDescription(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createAdditionalSkill() throws Exception {
        int databaseSizeBeforeCreate = additionalSkillRepository.findAll().size();

        // Create the AdditionalSkill
        AdditionalSkillDTO additionalSkillDTO = additionalSkillMapper.additionalSkillToAdditionalSkillDTO(additionalSkill);

        restAdditionalSkillMockMvc.perform(post("/api/additionalSkills")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(additionalSkillDTO)))
                .andExpect(status().isCreated());

        // Validate the AdditionalSkill in the database
        List<AdditionalSkill> additionalSkills = additionalSkillRepository.findAll();
        assertThat(additionalSkills).hasSize(databaseSizeBeforeCreate + 1);
        AdditionalSkill testAdditionalSkill = additionalSkills.get(additionalSkills.size() - 1);
        assertThat(testAdditionalSkill.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testAdditionalSkill.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllAdditionalSkills() throws Exception {
        // Initialize the database
        additionalSkillRepository.saveAndFlush(additionalSkill);

        // Get all the additionalSkills
        restAdditionalSkillMockMvc.perform(get("/api/additionalSkills?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(additionalSkill.getId().intValue())))
                .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getAdditionalSkill() throws Exception {
        // Initialize the database
        additionalSkillRepository.saveAndFlush(additionalSkill);

        // Get the additionalSkill
        restAdditionalSkillMockMvc.perform(get("/api/additionalSkills/{id}", additionalSkill.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(additionalSkill.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAdditionalSkill() throws Exception {
        // Get the additionalSkill
        restAdditionalSkillMockMvc.perform(get("/api/additionalSkills/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdditionalSkill() throws Exception {
        // Initialize the database
        additionalSkillRepository.saveAndFlush(additionalSkill);

		int databaseSizeBeforeUpdate = additionalSkillRepository.findAll().size();

        // Update the additionalSkill
        additionalSkill.setType(UPDATED_TYPE);
        additionalSkill.setDescription(UPDATED_DESCRIPTION);
        AdditionalSkillDTO additionalSkillDTO = additionalSkillMapper.additionalSkillToAdditionalSkillDTO(additionalSkill);

        restAdditionalSkillMockMvc.perform(put("/api/additionalSkills")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(additionalSkillDTO)))
                .andExpect(status().isOk());

        // Validate the AdditionalSkill in the database
        List<AdditionalSkill> additionalSkills = additionalSkillRepository.findAll();
        assertThat(additionalSkills).hasSize(databaseSizeBeforeUpdate);
        AdditionalSkill testAdditionalSkill = additionalSkills.get(additionalSkills.size() - 1);
        assertThat(testAdditionalSkill.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testAdditionalSkill.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void deleteAdditionalSkill() throws Exception {
        // Initialize the database
        additionalSkillRepository.saveAndFlush(additionalSkill);

		int databaseSizeBeforeDelete = additionalSkillRepository.findAll().size();

        // Get the additionalSkill
        restAdditionalSkillMockMvc.perform(delete("/api/additionalSkills/{id}", additionalSkill.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<AdditionalSkill> additionalSkills = additionalSkillRepository.findAll();
        assertThat(additionalSkills).hasSize(databaseSizeBeforeDelete - 1);
    }
}
