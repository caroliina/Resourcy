package com.resourcy.app.web.rest;

import com.resourcy.app.Application;
import com.resourcy.app.domain.CurriculumVitae;
import com.resourcy.app.repository.CurriculumVitaeRepository;
import com.resourcy.app.service.CurriculumVitaeService;
import com.resourcy.app.web.rest.dto.CurriculumVitaeDTO;
import com.resourcy.app.web.rest.mapper.CurriculumVitaeMapper;

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
 * Test class for the CurriculumVitaeResource REST controller.
 *
 * @see CurriculumVitaeResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class CurriculumVitaeResourceIntTest {


    @Inject
    private CurriculumVitaeRepository curriculumVitaeRepository;

    @Inject
    private CurriculumVitaeMapper curriculumVitaeMapper;

    @Inject
    private CurriculumVitaeService curriculumVitaeService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restCurriculumVitaeMockMvc;

    private CurriculumVitae curriculumVitae;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CurriculumVitaeResource curriculumVitaeResource = new CurriculumVitaeResource();
        ReflectionTestUtils.setField(curriculumVitaeResource, "curriculumVitaeService", curriculumVitaeService);
        ReflectionTestUtils.setField(curriculumVitaeResource, "curriculumVitaeMapper", curriculumVitaeMapper);
        this.restCurriculumVitaeMockMvc = MockMvcBuilders.standaloneSetup(curriculumVitaeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        curriculumVitae = new CurriculumVitae();
    }

    @Test
    @Transactional
    public void createCurriculumVitae() throws Exception {
        int databaseSizeBeforeCreate = curriculumVitaeRepository.findAll().size();

        // Create the CurriculumVitae
        CurriculumVitaeDTO curriculumVitaeDTO = curriculumVitaeMapper.curriculumVitaeToCurriculumVitaeDTO(curriculumVitae);

        restCurriculumVitaeMockMvc.perform(post("/api/curriculumVitaes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(curriculumVitaeDTO)))
                .andExpect(status().isCreated());

        // Validate the CurriculumVitae in the database
        List<CurriculumVitae> curriculumVitaes = curriculumVitaeRepository.findAll();
        assertThat(curriculumVitaes).hasSize(databaseSizeBeforeCreate + 1);
        CurriculumVitae testCurriculumVitae = curriculumVitaes.get(curriculumVitaes.size() - 1);
    }

    @Test
    @Transactional
    public void getAllCurriculumVitaes() throws Exception {
        // Initialize the database
        curriculumVitaeRepository.saveAndFlush(curriculumVitae);

        // Get all the curriculumVitaes
        restCurriculumVitaeMockMvc.perform(get("/api/curriculumVitaes?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(curriculumVitae.getId().intValue())));
    }

    @Test
    @Transactional
    public void getCurriculumVitae() throws Exception {
        // Initialize the database
        curriculumVitaeRepository.saveAndFlush(curriculumVitae);

        // Get the curriculumVitae
        restCurriculumVitaeMockMvc.perform(get("/api/curriculumVitaes/{id}", curriculumVitae.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(curriculumVitae.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCurriculumVitae() throws Exception {
        // Get the curriculumVitae
        restCurriculumVitaeMockMvc.perform(get("/api/curriculumVitaes/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCurriculumVitae() throws Exception {
        // Initialize the database
        curriculumVitaeRepository.saveAndFlush(curriculumVitae);

		int databaseSizeBeforeUpdate = curriculumVitaeRepository.findAll().size();

        // Update the curriculumVitae
        CurriculumVitaeDTO curriculumVitaeDTO = curriculumVitaeMapper.curriculumVitaeToCurriculumVitaeDTO(curriculumVitae);

        restCurriculumVitaeMockMvc.perform(put("/api/curriculumVitaes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(curriculumVitaeDTO)))
                .andExpect(status().isOk());

        // Validate the CurriculumVitae in the database
        List<CurriculumVitae> curriculumVitaes = curriculumVitaeRepository.findAll();
        assertThat(curriculumVitaes).hasSize(databaseSizeBeforeUpdate);
        CurriculumVitae testCurriculumVitae = curriculumVitaes.get(curriculumVitaes.size() - 1);
    }

    @Test
    @Transactional
    public void deleteCurriculumVitae() throws Exception {
        // Initialize the database
        curriculumVitaeRepository.saveAndFlush(curriculumVitae);

		int databaseSizeBeforeDelete = curriculumVitaeRepository.findAll().size();

        // Get the curriculumVitae
        restCurriculumVitaeMockMvc.perform(delete("/api/curriculumVitaes/{id}", curriculumVitae.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<CurriculumVitae> curriculumVitaes = curriculumVitaeRepository.findAll();
        assertThat(curriculumVitaes).hasSize(databaseSizeBeforeDelete - 1);
    }
}
