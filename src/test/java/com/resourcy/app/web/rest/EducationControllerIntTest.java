package com.resourcy.app.web.rest;

import com.resourcy.app.Application;
import com.resourcy.app.domain.Education;
import com.resourcy.app.domain.EducationDegrees;
import com.resourcy.app.repository.EducationRepository;
import com.resourcy.app.service.EducationService;
import com.resourcy.app.web.rest.dto.EducationDTO;
import com.resourcy.app.web.rest.mapper.EducationMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the EducationResource REST controller.
 *
 * @see EducationController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class EducationControllerIntTest {

    private static final String DEFAULT_INSTITUTION = "AAAAA";
    private static final String UPDATED_INSTITUTION = "BBBBB";

    private static final LocalDate DEFAULT_PERIOD_START = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PERIOD_START = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PERIOD_END = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PERIOD_END = LocalDate.now(ZoneId.systemDefault());
    private static final String DEFAULT_SPECIALITY = "AAAAA";
    private static final String UPDATED_SPECIALITY = "BBBBB";
    private static final EducationDegrees DEFAULT_DEGREE = EducationDegrees.BACHELOR;
    private static final EducationDegrees UPDATED_DEGREE = EducationDegrees.MASTER;

    @Inject
    private EducationRepository educationRepository;

    @Inject
    private EducationMapper educationMapper;

    @Inject
    private EducationService educationService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restEducationMockMvc;

    private Education education;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EducationController educationController = new EducationController();
        ReflectionTestUtils.setField(educationController, "educationService", educationService);
        ReflectionTestUtils.setField(educationController, "educationMapper", educationMapper);
        this.restEducationMockMvc = MockMvcBuilders.standaloneSetup(educationController)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        education = new Education();
        education.setInstitution(DEFAULT_INSTITUTION);
        education.setPeriodStart(DEFAULT_PERIOD_START);
        education.setPeriodEnd(DEFAULT_PERIOD_END);
        education.setSpeciality(DEFAULT_SPECIALITY);
        education.setDegree(DEFAULT_DEGREE);
    }

    @Test
    @Transactional
    public void createEducation() throws Exception {
        int databaseSizeBeforeCreate = educationRepository.findAll().size();

        // Create the Education
        EducationDTO educationDTO = educationMapper.educationToEducationDTO(education);

        restEducationMockMvc.perform(post("/api/educations")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(educationDTO)))
                .andExpect(status().isCreated());

        // Validate the Education in the database
        List<Education> educations = educationRepository.findAll();
        assertThat(educations).hasSize(databaseSizeBeforeCreate + 1);
        Education testEducation = educations.get(educations.size() - 1);
        assertThat(testEducation.getInstitution()).isEqualTo(DEFAULT_INSTITUTION);
        assertThat(testEducation.getPeriodStart()).isEqualTo(DEFAULT_PERIOD_START);
        assertThat(testEducation.getPeriodEnd()).isEqualTo(DEFAULT_PERIOD_END);
        assertThat(testEducation.getSpeciality()).isEqualTo(DEFAULT_SPECIALITY);
        assertThat(testEducation.getDegree()).isEqualTo(DEFAULT_DEGREE);
    }

    @Test
    @Transactional
    public void getAllEducations() throws Exception {
        // Initialize the database
        educationRepository.saveAndFlush(education);

        // Get all the educations
        restEducationMockMvc.perform(get("/api/educations?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(education.getId().intValue())))
                .andExpect(jsonPath("$.[*].institution").value(hasItem(DEFAULT_INSTITUTION.toString())))
                .andExpect(jsonPath("$.[*].periodStart").value(hasItem(DEFAULT_PERIOD_START.toString())))
                .andExpect(jsonPath("$.[*].periodEnd").value(hasItem(DEFAULT_PERIOD_END.toString())))
                .andExpect(jsonPath("$.[*].speciality").value(hasItem(DEFAULT_SPECIALITY.toString())))
                .andExpect(jsonPath("$.[*].degree").value(hasItem(DEFAULT_DEGREE.toString())));
    }

    @Test
    @Transactional
    public void getEducation() throws Exception {
        // Initialize the database
        educationRepository.saveAndFlush(education);

        // Get the education
        restEducationMockMvc.perform(get("/api/educations/{id}", education.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(education.getId().intValue()))
            .andExpect(jsonPath("$.institution").value(DEFAULT_INSTITUTION.toString()))
            .andExpect(jsonPath("$.periodStart").value(DEFAULT_PERIOD_START.toString()))
            .andExpect(jsonPath("$.periodEnd").value(DEFAULT_PERIOD_END.toString()))
            .andExpect(jsonPath("$.speciality").value(DEFAULT_SPECIALITY.toString()))
            .andExpect(jsonPath("$.degree").value(DEFAULT_DEGREE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEducation() throws Exception {
        // Get the education
        restEducationMockMvc.perform(get("/api/educations/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEducation() throws Exception {
        // Initialize the database
        educationRepository.saveAndFlush(education);

		int databaseSizeBeforeUpdate = educationRepository.findAll().size();

        // Update the education
        education.setInstitution(UPDATED_INSTITUTION);
        education.setPeriodStart(UPDATED_PERIOD_START);
        education.setPeriodEnd(UPDATED_PERIOD_END);
        education.setSpeciality(UPDATED_SPECIALITY);
        education.setDegree(UPDATED_DEGREE);
        EducationDTO educationDTO = educationMapper.educationToEducationDTO(education);

        restEducationMockMvc.perform(put("/api/educations")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(educationDTO)))
                .andExpect(status().isOk());

        // Validate the Education in the database
        List<Education> educations = educationRepository.findAll();
        assertThat(educations).hasSize(databaseSizeBeforeUpdate);
        Education testEducation = educations.get(educations.size() - 1);
        assertThat(testEducation.getInstitution()).isEqualTo(UPDATED_INSTITUTION);
        assertThat(testEducation.getPeriodStart()).isEqualTo(UPDATED_PERIOD_START);
        assertThat(testEducation.getPeriodEnd()).isEqualTo(UPDATED_PERIOD_END);
        assertThat(testEducation.getSpeciality()).isEqualTo(UPDATED_SPECIALITY);
        assertThat(testEducation.getDegree()).isEqualTo(UPDATED_DEGREE);
    }

    @Test
    @Transactional
    public void deleteEducation() throws Exception {
        // Initialize the database
        educationRepository.saveAndFlush(education);

		int databaseSizeBeforeDelete = educationRepository.findAll().size();

        // Get the education
        restEducationMockMvc.perform(delete("/api/educations/{id}", education.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Education> educations = educationRepository.findAll();
        assertThat(educations).hasSize(databaseSizeBeforeDelete - 1);
    }
}
