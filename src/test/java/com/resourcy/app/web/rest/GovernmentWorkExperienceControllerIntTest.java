package com.resourcy.app.web.rest;

import com.resourcy.app.Application;
import com.resourcy.app.domain.GovernmentWorkExperience;
import com.resourcy.app.repository.GovernmentWorkExperienceRepository;
import com.resourcy.app.service.GovernmentWorkExperienceService;
import com.resourcy.app.web.rest.dto.GovernmentWorkExperienceDTO;
import com.resourcy.app.web.rest.mapper.GovernmentWorkExperienceMapper;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the GovernmentWorkExperienceResource REST controller.
 *
 * @see GovernmentWorkExperienceController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class GovernmentWorkExperienceControllerIntTest {


    private static final LocalDate DEFAULT_PERIOD_START = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PERIOD_START = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PERIOD_END = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PERIOD_END = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_PERSONAL_WORK_HOURS = 1;
    private static final Integer UPDATED_PERSONAL_WORK_HOURS = 2;

    @Inject
    private GovernmentWorkExperienceRepository governmentWorkExperienceRepository;

    @Inject
    private GovernmentWorkExperienceMapper governmentWorkExperienceMapper;

    @Inject
    private GovernmentWorkExperienceService governmentWorkExperienceService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restGovernmentWorkExperienceMockMvc;

    private GovernmentWorkExperience governmentWorkExperience;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        GovernmentWorkExperienceController governmentWorkExperienceController = new GovernmentWorkExperienceController();
        ReflectionTestUtils.setField(governmentWorkExperienceController, "governmentWorkExperienceService", governmentWorkExperienceService);
        ReflectionTestUtils.setField(governmentWorkExperienceController, "governmentWorkExperienceMapper", governmentWorkExperienceMapper);
        this.restGovernmentWorkExperienceMockMvc = MockMvcBuilders.standaloneSetup(governmentWorkExperienceController)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        governmentWorkExperience = new GovernmentWorkExperience();
        governmentWorkExperience.setPeriodStart(DEFAULT_PERIOD_START);
        governmentWorkExperience.setPeriodEnd(DEFAULT_PERIOD_END);
        governmentWorkExperience.setPersonalWorkHours(DEFAULT_PERSONAL_WORK_HOURS);
    }

    @Test
    @Transactional
    public void createGovernmentWorkExperience() throws Exception {
        int databaseSizeBeforeCreate = governmentWorkExperienceRepository.findAll().size();

        // Create the GovernmentWorkExperience
        GovernmentWorkExperienceDTO governmentWorkExperienceDTO = governmentWorkExperienceMapper.governmentWorkExperienceToGovernmentWorkExperienceDTO(governmentWorkExperience);

        restGovernmentWorkExperienceMockMvc.perform(post("/api/governmentWorkExperiences")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(governmentWorkExperienceDTO)))
                .andExpect(status().isCreated());

        // Validate the GovernmentWorkExperience in the database
        List<GovernmentWorkExperience> governmentWorkExperiences = governmentWorkExperienceRepository.findAll();
        assertThat(governmentWorkExperiences).hasSize(databaseSizeBeforeCreate + 1);
        GovernmentWorkExperience testGovernmentWorkExperience = governmentWorkExperiences.get(governmentWorkExperiences.size() - 1);
        assertThat(testGovernmentWorkExperience.getPeriodStart()).isEqualTo(DEFAULT_PERIOD_START);
        assertThat(testGovernmentWorkExperience.getPeriodEnd()).isEqualTo(DEFAULT_PERIOD_END);
        assertThat(testGovernmentWorkExperience.getPersonalWorkHours()).isEqualTo(DEFAULT_PERSONAL_WORK_HOURS);
    }

    @Test
    @Transactional
    public void getAllGovernmentWorkExperiences() throws Exception {
        // Initialize the database
        governmentWorkExperienceRepository.saveAndFlush(governmentWorkExperience);

        // Get all the governmentWorkExperiences
        restGovernmentWorkExperienceMockMvc.perform(get("/api/governmentWorkExperiences?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(governmentWorkExperience.getId().intValue())))
                .andExpect(jsonPath("$.[*].periodStart").value(hasItem(DEFAULT_PERIOD_START.toString())))
                .andExpect(jsonPath("$.[*].periodEnd").value(hasItem(DEFAULT_PERIOD_END.toString())))
                .andExpect(jsonPath("$.[*].personalWorkHours").value(hasItem(DEFAULT_PERSONAL_WORK_HOURS)));
    }

    @Test
    @Transactional
    public void getGovernmentWorkExperience() throws Exception {
        // Initialize the database
        governmentWorkExperienceRepository.saveAndFlush(governmentWorkExperience);

        // Get the governmentWorkExperience
        restGovernmentWorkExperienceMockMvc.perform(get("/api/governmentWorkExperiences/{id}", governmentWorkExperience.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(governmentWorkExperience.getId().intValue()))
            .andExpect(jsonPath("$.periodStart").value(DEFAULT_PERIOD_START.toString()))
            .andExpect(jsonPath("$.periodEnd").value(DEFAULT_PERIOD_END.toString()))
            .andExpect(jsonPath("$.personalWorkHours").value(DEFAULT_PERSONAL_WORK_HOURS));
    }

    @Test
    @Transactional
    public void getNonExistingGovernmentWorkExperience() throws Exception {
        // Get the governmentWorkExperience
        restGovernmentWorkExperienceMockMvc.perform(get("/api/governmentWorkExperiences/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGovernmentWorkExperience() throws Exception {
        // Initialize the database
        governmentWorkExperienceRepository.saveAndFlush(governmentWorkExperience);

		int databaseSizeBeforeUpdate = governmentWorkExperienceRepository.findAll().size();

        // Update the governmentWorkExperience
        governmentWorkExperience.setPeriodStart(UPDATED_PERIOD_START);
        governmentWorkExperience.setPeriodEnd(UPDATED_PERIOD_END);
        governmentWorkExperience.setPersonalWorkHours(UPDATED_PERSONAL_WORK_HOURS);
        GovernmentWorkExperienceDTO governmentWorkExperienceDTO = governmentWorkExperienceMapper.governmentWorkExperienceToGovernmentWorkExperienceDTO(governmentWorkExperience);

        restGovernmentWorkExperienceMockMvc.perform(put("/api/governmentWorkExperiences")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(governmentWorkExperienceDTO)))
                .andExpect(status().isOk());

        // Validate the GovernmentWorkExperience in the database
        List<GovernmentWorkExperience> governmentWorkExperiences = governmentWorkExperienceRepository.findAll();
        assertThat(governmentWorkExperiences).hasSize(databaseSizeBeforeUpdate);
        GovernmentWorkExperience testGovernmentWorkExperience = governmentWorkExperiences.get(governmentWorkExperiences.size() - 1);
        assertThat(testGovernmentWorkExperience.getPeriodStart()).isEqualTo(UPDATED_PERIOD_START);
        assertThat(testGovernmentWorkExperience.getPeriodEnd()).isEqualTo(UPDATED_PERIOD_END);
        assertThat(testGovernmentWorkExperience.getPersonalWorkHours()).isEqualTo(UPDATED_PERSONAL_WORK_HOURS);
    }

    @Test
    @Transactional
    public void deleteGovernmentWorkExperience() throws Exception {
        // Initialize the database
        governmentWorkExperienceRepository.saveAndFlush(governmentWorkExperience);

		int databaseSizeBeforeDelete = governmentWorkExperienceRepository.findAll().size();

        // Get the governmentWorkExperience
        restGovernmentWorkExperienceMockMvc.perform(delete("/api/governmentWorkExperiences/{id}", governmentWorkExperience.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<GovernmentWorkExperience> governmentWorkExperiences = governmentWorkExperienceRepository.findAll();
        assertThat(governmentWorkExperiences).hasSize(databaseSizeBeforeDelete - 1);
    }
}
