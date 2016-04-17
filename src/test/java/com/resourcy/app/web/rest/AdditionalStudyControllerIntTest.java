package com.resourcy.app.web.rest;

import com.resourcy.app.Application;
import com.resourcy.app.domain.AdditionalStudy;
import com.resourcy.app.repository.AdditionalStudyRepository;
import com.resourcy.app.service.AdditionalStudyService;
import com.resourcy.app.web.rest.dto.AdditionalStudyDTO;
import com.resourcy.app.web.rest.mapper.AdditionalStudyMapper;

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
 * Test class for the AdditionalStudyResource REST controller.
 *
 * @see AdditionalStudyController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class AdditionalStudyControllerIntTest {


    private static final LocalDate DEFAULT_PERIOD_START = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PERIOD_START = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PERIOD_END = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PERIOD_END = LocalDate.now(ZoneId.systemDefault());
    private static final String DEFAULT_INSTITUTION = "AAAAA";
    private static final String UPDATED_INSTITUTION = "BBBBB";
    private static final String DEFAULT_DESCRIPTION = "AAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBB";

    @Inject
    private AdditionalStudyRepository additionalStudyRepository;

    @Inject
    private AdditionalStudyMapper additionalStudyMapper;

    @Inject
    private AdditionalStudyService additionalStudyService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restAdditionalStudyMockMvc;

    private AdditionalStudy additionalStudy;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AdditionalStudyController additionalStudyController = new AdditionalStudyController();
        ReflectionTestUtils.setField(additionalStudyController, "additionalStudyService", additionalStudyService);
        ReflectionTestUtils.setField(additionalStudyController, "additionalStudyMapper", additionalStudyMapper);
        this.restAdditionalStudyMockMvc = MockMvcBuilders.standaloneSetup(additionalStudyController)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        additionalStudy = new AdditionalStudy();
        additionalStudy.setPeriodStart(DEFAULT_PERIOD_START);
        additionalStudy.setPeriodEnd(DEFAULT_PERIOD_END);
        additionalStudy.setInstitution(DEFAULT_INSTITUTION);
        additionalStudy.setDescription(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createAdditionalStudy() throws Exception {
        int databaseSizeBeforeCreate = additionalStudyRepository.findAll().size();

        // Create the AdditionalStudy
        AdditionalStudyDTO additionalStudyDTO = additionalStudyMapper.additionalStudyToAdditionalStudyDTO(additionalStudy);

        restAdditionalStudyMockMvc.perform(post("/api/additionalStudys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(additionalStudyDTO)))
                .andExpect(status().isCreated());

        // Validate the AdditionalStudy in the database
        List<AdditionalStudy> additionalStudys = additionalStudyRepository.findAll();
        assertThat(additionalStudys).hasSize(databaseSizeBeforeCreate + 1);
        AdditionalStudy testAdditionalStudy = additionalStudys.get(additionalStudys.size() - 1);
        assertThat(testAdditionalStudy.getPeriodStart()).isEqualTo(DEFAULT_PERIOD_START);
        assertThat(testAdditionalStudy.getPeriodEnd()).isEqualTo(DEFAULT_PERIOD_END);
        assertThat(testAdditionalStudy.getInstitution()).isEqualTo(DEFAULT_INSTITUTION);
        assertThat(testAdditionalStudy.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllAdditionalStudys() throws Exception {
        // Initialize the database
        additionalStudyRepository.saveAndFlush(additionalStudy);

        // Get all the additionalStudys
        restAdditionalStudyMockMvc.perform(get("/api/additionalStudys?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(additionalStudy.getId().intValue())))
                .andExpect(jsonPath("$.[*].periodStart").value(hasItem(DEFAULT_PERIOD_START.toString())))
                .andExpect(jsonPath("$.[*].periodEnd").value(hasItem(DEFAULT_PERIOD_END.toString())))
                .andExpect(jsonPath("$.[*].institution").value(hasItem(DEFAULT_INSTITUTION.toString())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getAdditionalStudy() throws Exception {
        // Initialize the database
        additionalStudyRepository.saveAndFlush(additionalStudy);

        // Get the additionalStudy
        restAdditionalStudyMockMvc.perform(get("/api/additionalStudys/{id}", additionalStudy.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(additionalStudy.getId().intValue()))
            .andExpect(jsonPath("$.periodStart").value(DEFAULT_PERIOD_START.toString()))
            .andExpect(jsonPath("$.periodEnd").value(DEFAULT_PERIOD_END.toString()))
            .andExpect(jsonPath("$.institution").value(DEFAULT_INSTITUTION.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAdditionalStudy() throws Exception {
        // Get the additionalStudy
        restAdditionalStudyMockMvc.perform(get("/api/additionalStudys/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdditionalStudy() throws Exception {
        // Initialize the database
        additionalStudyRepository.saveAndFlush(additionalStudy);

		int databaseSizeBeforeUpdate = additionalStudyRepository.findAll().size();

        // Update the additionalStudy
        additionalStudy.setPeriodStart(UPDATED_PERIOD_START);
        additionalStudy.setPeriodEnd(UPDATED_PERIOD_END);
        additionalStudy.setInstitution(UPDATED_INSTITUTION);
        additionalStudy.setDescription(UPDATED_DESCRIPTION);
        AdditionalStudyDTO additionalStudyDTO = additionalStudyMapper.additionalStudyToAdditionalStudyDTO(additionalStudy);

        restAdditionalStudyMockMvc.perform(put("/api/additionalStudys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(additionalStudyDTO)))
                .andExpect(status().isOk());

        // Validate the AdditionalStudy in the database
        List<AdditionalStudy> additionalStudys = additionalStudyRepository.findAll();
        assertThat(additionalStudys).hasSize(databaseSizeBeforeUpdate);
        AdditionalStudy testAdditionalStudy = additionalStudys.get(additionalStudys.size() - 1);
        assertThat(testAdditionalStudy.getPeriodStart()).isEqualTo(UPDATED_PERIOD_START);
        assertThat(testAdditionalStudy.getPeriodEnd()).isEqualTo(UPDATED_PERIOD_END);
        assertThat(testAdditionalStudy.getInstitution()).isEqualTo(UPDATED_INSTITUTION);
        assertThat(testAdditionalStudy.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void deleteAdditionalStudy() throws Exception {
        // Initialize the database
        additionalStudyRepository.saveAndFlush(additionalStudy);

		int databaseSizeBeforeDelete = additionalStudyRepository.findAll().size();

        // Get the additionalStudy
        restAdditionalStudyMockMvc.perform(delete("/api/additionalStudys/{id}", additionalStudy.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<AdditionalStudy> additionalStudys = additionalStudyRepository.findAll();
        assertThat(additionalStudys).hasSize(databaseSizeBeforeDelete - 1);
    }
}
