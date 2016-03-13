package com.resourcy.app.web.rest;

import com.resourcy.app.Application;
import com.resourcy.app.domain.WorkAssignment;
import com.resourcy.app.repository.WorkAssignmentRepository;
import com.resourcy.app.service.WorkAssignmentService;
import com.resourcy.app.web.rest.dto.WorkAssignmentDTO;
import com.resourcy.app.web.rest.mapper.WorkAssignmentMapper;

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
 * Test class for the WorkAssignmentResource REST controller.
 *
 * @see WorkAssignmentResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class WorkAssignmentResourceIntTest {

    private static final String DEFAULT_DESCRIPTION = "AAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBB";

    @Inject
    private WorkAssignmentRepository workAssignmentRepository;

    @Inject
    private WorkAssignmentMapper workAssignmentMapper;

    @Inject
    private WorkAssignmentService workAssignmentService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restWorkAssignmentMockMvc;

    private WorkAssignment workAssignment;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        WorkAssignmentResource workAssignmentResource = new WorkAssignmentResource();
        ReflectionTestUtils.setField(workAssignmentResource, "workAssignmentService", workAssignmentService);
        ReflectionTestUtils.setField(workAssignmentResource, "workAssignmentMapper", workAssignmentMapper);
        this.restWorkAssignmentMockMvc = MockMvcBuilders.standaloneSetup(workAssignmentResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        workAssignment = new WorkAssignment();
        workAssignment.setDescription(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createWorkAssignment() throws Exception {
        int databaseSizeBeforeCreate = workAssignmentRepository.findAll().size();

        // Create the WorkAssignment
        WorkAssignmentDTO workAssignmentDTO = workAssignmentMapper.workAssignmentToWorkAssignmentDTO(workAssignment);

        restWorkAssignmentMockMvc.perform(post("/api/workAssignments")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(workAssignmentDTO)))
                .andExpect(status().isCreated());

        // Validate the WorkAssignment in the database
        List<WorkAssignment> workAssignments = workAssignmentRepository.findAll();
        assertThat(workAssignments).hasSize(databaseSizeBeforeCreate + 1);
        WorkAssignment testWorkAssignment = workAssignments.get(workAssignments.size() - 1);
        assertThat(testWorkAssignment.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllWorkAssignments() throws Exception {
        // Initialize the database
        workAssignmentRepository.saveAndFlush(workAssignment);

        // Get all the workAssignments
        restWorkAssignmentMockMvc.perform(get("/api/workAssignments?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(workAssignment.getId().intValue())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getWorkAssignment() throws Exception {
        // Initialize the database
        workAssignmentRepository.saveAndFlush(workAssignment);

        // Get the workAssignment
        restWorkAssignmentMockMvc.perform(get("/api/workAssignments/{id}", workAssignment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(workAssignment.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWorkAssignment() throws Exception {
        // Get the workAssignment
        restWorkAssignmentMockMvc.perform(get("/api/workAssignments/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWorkAssignment() throws Exception {
        // Initialize the database
        workAssignmentRepository.saveAndFlush(workAssignment);

		int databaseSizeBeforeUpdate = workAssignmentRepository.findAll().size();

        // Update the workAssignment
        workAssignment.setDescription(UPDATED_DESCRIPTION);
        WorkAssignmentDTO workAssignmentDTO = workAssignmentMapper.workAssignmentToWorkAssignmentDTO(workAssignment);

        restWorkAssignmentMockMvc.perform(put("/api/workAssignments")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(workAssignmentDTO)))
                .andExpect(status().isOk());

        // Validate the WorkAssignment in the database
        List<WorkAssignment> workAssignments = workAssignmentRepository.findAll();
        assertThat(workAssignments).hasSize(databaseSizeBeforeUpdate);
        WorkAssignment testWorkAssignment = workAssignments.get(workAssignments.size() - 1);
        assertThat(testWorkAssignment.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void deleteWorkAssignment() throws Exception {
        // Initialize the database
        workAssignmentRepository.saveAndFlush(workAssignment);

		int databaseSizeBeforeDelete = workAssignmentRepository.findAll().size();

        // Get the workAssignment
        restWorkAssignmentMockMvc.perform(delete("/api/workAssignments/{id}", workAssignment.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<WorkAssignment> workAssignments = workAssignmentRepository.findAll();
        assertThat(workAssignments).hasSize(databaseSizeBeforeDelete - 1);
    }
}
