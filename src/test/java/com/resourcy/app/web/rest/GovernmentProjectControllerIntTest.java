package com.resourcy.app.web.rest;

import com.resourcy.app.Application;
import com.resourcy.app.domain.GovernmentProject;
import com.resourcy.app.repository.GovernmentProjectRepository;
import com.resourcy.app.service.GovernmentProjectService;
import com.resourcy.app.web.rest.dto.GovernmentProjectDTO;
import com.resourcy.app.web.rest.mapper.GovernmentProjectMapper;

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
 * Test class for the GovernmentProjectResource REST controller.
 *
 * @see GovernmentProjectController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class GovernmentProjectControllerIntTest {

    private static final String DEFAULT_BUYER = "AAAAA";
    private static final String UPDATED_BUYER = "BBBBB";
    private static final String DEFAULT_SERVICE_NAME = "AAAAA";
    private static final String UPDATED_SERVICE_NAME = "BBBBB";

    private static final Integer DEFAULT_TOTAL_PROJECT_WORK_HOURS = 1;
    private static final Integer UPDATED_TOTAL_PROJECT_WORK_HOURS = 2;

    @Inject
    private GovernmentProjectRepository governmentProjectRepository;

    @Inject
    private GovernmentProjectMapper governmentProjectMapper;

    @Inject
    private GovernmentProjectService governmentProjectService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restGovernmentProjectMockMvc;

    private GovernmentProject governmentProject;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        GovernmentProjectController governmentProjectController = new GovernmentProjectController();
        ReflectionTestUtils.setField(governmentProjectController, "governmentProjectService", governmentProjectService);
        ReflectionTestUtils.setField(governmentProjectController, "governmentProjectMapper", governmentProjectMapper);
        this.restGovernmentProjectMockMvc = MockMvcBuilders.standaloneSetup(governmentProjectController)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        governmentProject = new GovernmentProject();
        governmentProject.setBuyer(DEFAULT_BUYER);
        governmentProject.setServiceName(DEFAULT_SERVICE_NAME);
        governmentProject.setTotalProjectWorkHours(DEFAULT_TOTAL_PROJECT_WORK_HOURS);
    }

    @Test
    @Transactional
    public void createGovernmentProject() throws Exception {
        int databaseSizeBeforeCreate = governmentProjectRepository.findAll().size();

        // Create the GovernmentProject
        GovernmentProjectDTO governmentProjectDTO = governmentProjectMapper.governmentProjectToGovernmentProjectDTO(governmentProject);

        restGovernmentProjectMockMvc.perform(post("/api/governmentProjects")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(governmentProjectDTO)))
                .andExpect(status().isCreated());

        // Validate the GovernmentProject in the database
        List<GovernmentProject> governmentProjects = governmentProjectRepository.findAll();
        assertThat(governmentProjects).hasSize(databaseSizeBeforeCreate + 1);
        GovernmentProject testGovernmentProject = governmentProjects.get(governmentProjects.size() - 1);
        assertThat(testGovernmentProject.getBuyer()).isEqualTo(DEFAULT_BUYER);
        assertThat(testGovernmentProject.getServiceName()).isEqualTo(DEFAULT_SERVICE_NAME);
        assertThat(testGovernmentProject.getTotalProjectWorkHours()).isEqualTo(DEFAULT_TOTAL_PROJECT_WORK_HOURS);
    }

    @Test
    @Transactional
    public void getAllGovernmentProjects() throws Exception {
        // Initialize the database
        governmentProjectRepository.saveAndFlush(governmentProject);

        // Get all the governmentProjects
        restGovernmentProjectMockMvc.perform(get("/api/governmentProjects?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(governmentProject.getId().intValue())))
                .andExpect(jsonPath("$.[*].buyer").value(hasItem(DEFAULT_BUYER.toString())))
                .andExpect(jsonPath("$.[*].serviceName").value(hasItem(DEFAULT_SERVICE_NAME.toString())))
                .andExpect(jsonPath("$.[*].totalProjectWorkHours").value(hasItem(DEFAULT_TOTAL_PROJECT_WORK_HOURS)));
    }

    @Test
    @Transactional
    public void getGovernmentProject() throws Exception {
        // Initialize the database
        governmentProjectRepository.saveAndFlush(governmentProject);

        // Get the governmentProject
        restGovernmentProjectMockMvc.perform(get("/api/governmentProjects/{id}", governmentProject.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(governmentProject.getId().intValue()))
            .andExpect(jsonPath("$.buyer").value(DEFAULT_BUYER.toString()))
            .andExpect(jsonPath("$.serviceName").value(DEFAULT_SERVICE_NAME.toString()))
            .andExpect(jsonPath("$.totalProjectWorkHours").value(DEFAULT_TOTAL_PROJECT_WORK_HOURS));
    }

    @Test
    @Transactional
    public void getNonExistingGovernmentProject() throws Exception {
        // Get the governmentProject
        restGovernmentProjectMockMvc.perform(get("/api/governmentProjects/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGovernmentProject() throws Exception {
        // Initialize the database
        governmentProjectRepository.saveAndFlush(governmentProject);

		int databaseSizeBeforeUpdate = governmentProjectRepository.findAll().size();

        // Update the governmentProject
        governmentProject.setBuyer(UPDATED_BUYER);
        governmentProject.setServiceName(UPDATED_SERVICE_NAME);
        governmentProject.setTotalProjectWorkHours(UPDATED_TOTAL_PROJECT_WORK_HOURS);
        GovernmentProjectDTO governmentProjectDTO = governmentProjectMapper.governmentProjectToGovernmentProjectDTO(governmentProject);

        restGovernmentProjectMockMvc.perform(put("/api/governmentProjects")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(governmentProjectDTO)))
                .andExpect(status().isOk());

        // Validate the GovernmentProject in the database
        List<GovernmentProject> governmentProjects = governmentProjectRepository.findAll();
        assertThat(governmentProjects).hasSize(databaseSizeBeforeUpdate);
        GovernmentProject testGovernmentProject = governmentProjects.get(governmentProjects.size() - 1);
        assertThat(testGovernmentProject.getBuyer()).isEqualTo(UPDATED_BUYER);
        assertThat(testGovernmentProject.getServiceName()).isEqualTo(UPDATED_SERVICE_NAME);
        assertThat(testGovernmentProject.getTotalProjectWorkHours()).isEqualTo(UPDATED_TOTAL_PROJECT_WORK_HOURS);
    }

    @Test
    @Transactional
    public void deleteGovernmentProject() throws Exception {
        // Initialize the database
        governmentProjectRepository.saveAndFlush(governmentProject);

		int databaseSizeBeforeDelete = governmentProjectRepository.findAll().size();

        // Get the governmentProject
        restGovernmentProjectMockMvc.perform(delete("/api/governmentProjects/{id}", governmentProject.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<GovernmentProject> governmentProjects = governmentProjectRepository.findAll();
        assertThat(governmentProjects).hasSize(databaseSizeBeforeDelete - 1);
    }
}
