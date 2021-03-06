package com.resourcy.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.resourcy.app.domain.Employee;
import com.resourcy.app.service.EmployeeService;
import com.resourcy.app.service.validator.ValidationException;
import com.resourcy.app.web.rest.dto.EmployeeDTO;
import com.resourcy.app.web.rest.mapper.EmployeeMapper;
import com.resourcy.app.web.rest.util.HeaderUtil;
import com.resourcy.app.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Employee.
 */
@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Inject
    private EmployeeService employeeService;

    @Inject
    private EmployeeMapper employeeMapper;

    @RequestMapping(value = "/employee",method = RequestMethod.PUT)
    public EmployeeDTO save(@RequestBody EmployeeDTO employeeDTO) throws ValidationException {
        return employeeService.save(employeeDTO);
    }

    /**
     * GET  /employees -> get all the employees.
     */
    @RequestMapping(value = "/employees",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Employees");
        Page<Employee> page = employeeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/employees");
        return new ResponseEntity<>(page.getContent().stream()
            .map(employeeMapper::employeeToEmployeeDTO)
            .collect(Collectors.toCollection(LinkedList::new)), headers, HttpStatus.OK);
    }

    /**
     * GET  /employees/:id -> get the "id" employee.
     */
    @RequestMapping(value = "/employees/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long id) {
        log.debug("REST request to get Employee : {}", id);
        EmployeeDTO employeeDTO = employeeService.findOne(id);
        return Optional.ofNullable(employeeDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /employees/:id -> delete the "id" employee.
     */
    @RequestMapping(value = "/employees/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        log.debug("REST request to delete Employee : {}", id);
        employeeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("employee", id.toString())).build();
    }

    /**
     * SEARCH  /_search/employees/:query -> search for the employee corresponding
     * to the query.
     */
    @RequestMapping(value = "/_search/employees/{query:.+}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<EmployeeDTO> searchEmployees(@PathVariable String query) {
        log.debug("Request to search Employees for query {}", query);
        return employeeService.search(query);
    }

    @RequestMapping(value = "/currentEmployee", method = RequestMethod.GET)
    public EmployeeDTO getCurrentEmployee() {
        return employeeService.getCurrentEmployee();
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public EmployeeDTO saveEmployee (@RequestBody EmployeeDTO employeeDTO) throws Exception {
        return employeeService.saveEmployee(employeeDTO);
    }
}
