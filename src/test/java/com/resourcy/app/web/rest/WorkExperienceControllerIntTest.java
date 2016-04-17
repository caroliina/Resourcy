package com.resourcy.app.web.rest;

import com.resourcy.app.Application;
import com.resourcy.app.domain.WorkExperience;
import com.resourcy.app.repository.WorkExperienceRepository;
import com.resourcy.app.service.WorkExperienceService;
import com.resourcy.app.web.rest.dto.WorkExperienceDTO;
import com.resourcy.app.web.rest.mapper.WorkExperienceMapper;

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
 * Test class for the WorkExperienceResource REST controller.
 *
 * @see WorkExperienceController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class WorkExperienceControllerIntTest {

    private static final String DEFAULT_POSITION = "AAAAA";
    private static final String UPDATED_POSITION = "BBBBB";

    private static final LocalDate DEFAULT_PERIOD_START = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PERIOD_START = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PERIOD_END = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PERIOD_END = LocalDate.now(ZoneId.systemDefault());
    private static final String DEFAULT_LOCATION = "AAAAA";
    private static final String UPDATED_LOCATION = "BBBBB";
    private static final String DEFAULT_ORGANIZATION = "AAAAA";
    private static final String UPDATED_ORGANIZATION = "BBBBB";

    @Inject
    private WorkExperienceRepository workExperienceRepository;

    @Inject
    private WorkExperienceMapper workExperienceMapper;

    @Inject
    private WorkExperienceService workExperienceService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restWorkExperienceMockMvc;

    private WorkExperience workExperience;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        WorkExperienceController workExperienceController = new WorkExperienceController();
        ReflectionTestUtils.setField(workExperienceController, "workExperienceService", workExperienceService);
        ReflectionTestUtils.setField(workExperienceController, "workExperienceMapper", workExperienceMapper);
        this.restWorkExperienceMockMvc = MockMvcBuilders.standaloneSetup(workExperienceController)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        workExperience = new WorkExperience();
        workExperience.setPosition(DEFAULT_POSITION);
        workExperience.setPeriodStart(DEFAULT_PERIOD_START);
        workExperience.setPeriodEnd(DEFAULT_PERIOD_END);
        workExperience.setLocation(DEFAULT_LOCATION);
        workExperience.setOrganization(DEFAULT_ORGANIZATION);
    }

    @Test
    @Transactional
    public void createWorkExperience() throws Exception {
        int databaseSizeBeforeCreate = workExperienceRepository.findAll().size();

        // Create the WorkExperience
        WorkExperienceDTO workExperienceDTO = workExperienceMapper.workExperienceToWorkExperienceDTO(workExperience);

        restWorkExperienceMockMvc.perform(post("/api/workExperiences")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(workExperienceDTO)))
                .andExpect(status().isCreated());

        // Validate the WorkExperience in the database
        List<WorkExperience> workExperiences = workExperienceRepository.findAll();
        assertThat(workExperiences).hasSize(databaseSizeBeforeCreate + 1);
        WorkExperience testWorkExperience = workExperiences.get(workExperiences.size() - 1);
        assertThat(testWorkExperience.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testWorkExperience.getPeriodStart()).isEqualTo(DEFAULT_PERIOD_START);
        assertThat(testWorkExperience.getPeriodEnd()).isEqualTo(DEFAULT_PERIOD_END);
        assertThat(testWorkExperience.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testWorkExperience.getOrganization()).isEqualTo(DEFAULT_ORGANIZATION);
    }

    @Test
    @Transactional
    public void getAllWorkExperiences() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get all the workExperiences
        restWorkExperienceMockMvc.perform(get("/api/workExperiences?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(workExperience.getId().intValue())))
                .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION.toString())))
                .andExpect(jsonPath("$.[*].periodStart").value(hasItem(DEFAULT_PERIOD_START.toString())))
                .andExpect(jsonPath("$.[*].periodEnd").value(hasItem(DEFAULT_PERIOD_END.toString())))
                .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION.toString())))
                .andExpect(jsonPath("$.[*].organization").value(hasItem(DEFAULT_ORGANIZATION.toString())));
    }

    @Test
    @Transactional
    public void getWorkExperience() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

        // Get the workExperience
        restWorkExperienceMockMvc.perform(get("/api/workExperiences/{id}", workExperience.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(workExperience.getId().intValue()))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION.toString()))
            .andExpect(jsonPath("$.periodStart").value(DEFAULT_PERIOD_START.toString()))
            .andExpect(jsonPath("$.periodEnd").value(DEFAULT_PERIOD_END.toString()))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION.toString()))
            .andExpect(jsonPath("$.organization").value(DEFAULT_ORGANIZATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWorkExperience() throws Exception {
        // Get the workExperience
        restWorkExperienceMockMvc.perform(get("/api/workExperiences/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWorkExperience() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

		int databaseSizeBeforeUpdate = workExperienceRepository.findAll().size();

        // Update the workExperience
        workExperience.setPosition(UPDATED_POSITION);
        workExperience.setPeriodStart(UPDATED_PERIOD_START);
        workExperience.setPeriodEnd(UPDATED_PERIOD_END);
        workExperience.setLocation(UPDATED_LOCATION);
        workExperience.setOrganization(UPDATED_ORGANIZATION);
        WorkExperienceDTO workExperienceDTO = workExperienceMapper.workExperienceToWorkExperienceDTO(workExperience);

        restWorkExperienceMockMvc.perform(put("/api/workExperiences")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(workExperienceDTO)))
                .andExpect(status().isOk());

        // Validate the WorkExperience in the database
        List<WorkExperience> workExperiences = workExperienceRepository.findAll();
        assertThat(workExperiences).hasSize(databaseSizeBeforeUpdate);
        WorkExperience testWorkExperience = workExperiences.get(workExperiences.size() - 1);
        assertThat(testWorkExperience.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testWorkExperience.getPeriodStart()).isEqualTo(UPDATED_PERIOD_START);
        assertThat(testWorkExperience.getPeriodEnd()).isEqualTo(UPDATED_PERIOD_END);
        assertThat(testWorkExperience.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testWorkExperience.getOrganization()).isEqualTo(UPDATED_ORGANIZATION);
    }

    @Test
    @Transactional
    public void deleteWorkExperience() throws Exception {
        // Initialize the database
        workExperienceRepository.saveAndFlush(workExperience);

		int databaseSizeBeforeDelete = workExperienceRepository.findAll().size();

        // Get the workExperience
        restWorkExperienceMockMvc.perform(delete("/api/workExperiences/{id}", workExperience.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<WorkExperience> workExperiences = workExperienceRepository.findAll();
        assertThat(workExperiences).hasSize(databaseSizeBeforeDelete - 1);
    }
}
