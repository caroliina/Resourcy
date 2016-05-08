package com.resourcy.app.web.rest;

import com.resourcy.app.Application;
import com.resourcy.app.domain.Technology;
import com.resourcy.app.repository.TechnologyRepository;
import com.resourcy.app.service.TechnologyService;
import com.resourcy.app.web.rest.dto.TechnologyDTO;
import com.resourcy.app.web.rest.mapper.TechnologyMapper;

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
 * Test class for the TechnologyResource REST controller.
 *
 * @see TechnologyController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class TechnologyControllerIntTest {

    private static final String DEFAULT_TYPE = "AAAAA";
    private static final String UPDATED_TYPE = "BBBBB";
    private static final String DEFAULT_DESCRIPTION = "AAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBB";

    @Inject
    private TechnologyRepository technologyRepository;

    @Inject
    private TechnologyMapper technologyMapper;

    @Inject
    private TechnologyService technologyService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restTechnologyMockMvc;

    private Technology technology;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TechnologyController technologyController = new TechnologyController();
        ReflectionTestUtils.setField(technologyController, "technologyService", technologyService);
        ReflectionTestUtils.setField(technologyController, "technologyMapper", technologyMapper);
        this.restTechnologyMockMvc = MockMvcBuilders.standaloneSetup(technologyController)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        technology = new Technology();
        technology.setType(DEFAULT_TYPE);
        technology.setDescription(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createTechnology() throws Exception {
        int databaseSizeBeforeCreate = technologyRepository.findAll().size();

        // Create the Technology
        TechnologyDTO technologyDTO = technologyMapper.technologyToTechnologyDTO(technology);

        restTechnologyMockMvc.perform(post("/api/technologys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(technologyDTO)))
                .andExpect(status().isCreated());

        // Validate the Technology in the database
        List<Technology> technologys = technologyRepository.findAll();
        assertThat(technologys).hasSize(databaseSizeBeforeCreate + 1);
        Technology testTechnology = technologys.get(technologys.size() - 1);
        assertThat(testTechnology.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testTechnology.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllTechnologys() throws Exception {
        // Initialize the database
        technologyRepository.saveAndFlush(technology);

        // Get all the technologys
        restTechnologyMockMvc.perform(get("/api/technologys?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(technology.getId().intValue())))
                .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getTechnology() throws Exception {
        // Initialize the database
        technologyRepository.saveAndFlush(technology);

        // Get the technology
        restTechnologyMockMvc.perform(get("/api/technologys/{id}", technology.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(technology.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTechnology() throws Exception {
        // Get the technology
        restTechnologyMockMvc.perform(get("/api/technologys/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTechnology() throws Exception {
        // Initialize the database
        technologyRepository.saveAndFlush(technology);

		int databaseSizeBeforeUpdate = technologyRepository.findAll().size();

        // Update the technology
        technology.setType(UPDATED_TYPE);
        technology.setDescription(UPDATED_DESCRIPTION);
        TechnologyDTO technologyDTO = technologyMapper.technologyToTechnologyDTO(technology);

        restTechnologyMockMvc.perform(put("/api/technologys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(technologyDTO)))
                .andExpect(status().isOk());

        // Validate the Technology in the database
        List<Technology> technologys = technologyRepository.findAll();
        assertThat(technologys).hasSize(databaseSizeBeforeUpdate);
        Technology testTechnology = technologys.get(technologys.size() - 1);
        assertThat(testTechnology.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testTechnology.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void deleteTechnology() throws Exception {
        // Initialize the database
        technologyRepository.saveAndFlush(technology);

		int databaseSizeBeforeDelete = technologyRepository.findAll().size();

        // Get the technology
        restTechnologyMockMvc.perform(delete("/api/technologys/{id}", technology.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Technology> technologys = technologyRepository.findAll();
        assertThat(technologys).hasSize(databaseSizeBeforeDelete - 1);
    }
}
