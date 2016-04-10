package com.resourcy.app.service.impl;

import com.resourcy.app.domain.Employee;
import com.resourcy.app.repository.EmployeeRepository;
import com.resourcy.app.repository.search.EmployeeSearchRepository;
import com.resourcy.app.service.EmployeeService;
import com.resourcy.app.service.UserService;
import com.resourcy.app.web.rest.dto.EmployeeDTO;
import com.resourcy.app.web.rest.mapper.EmployeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * Service Implementation for managing Employee.
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

    private final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    
    @Inject
    private EmployeeRepository employeeRepository;
    
    @Inject
    private EmployeeMapper employeeMapper;
    
    @Inject
    private EmployeeSearchRepository employeeSearchRepository;

    @Inject
    private UserService userService;
    
    /**
     * Save a employee.
     * @return the persisted entity
     */
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        log.debug("Request to save Employee : {}", employeeDTO);
        Employee employee = employeeMapper.employeeDTOToEmployee(employeeDTO);
        employee.setUser(userService.getUserWithAuthorities());
        employee = employeeRepository.save(employee);
        EmployeeDTO result = employeeMapper.employeeToEmployeeDTO(employee);
        employeeSearchRepository.save(employee);
        return result;
    }

    /**
     *  get all the employees.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Employee> findAll(Pageable pageable) {
        log.debug("Request to get all Employees");
        Page<Employee> result = employeeRepository.findAll(pageable); 
        return result;
    }

    /**
     *  get one employee by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public EmployeeDTO findOne(Long id) {
        log.debug("Request to get Employee : {}", id);
        Employee employee = employeeRepository.findOne(id);
        EmployeeDTO employeeDTO = employeeMapper.employeeToEmployeeDTO(employee);
        return employeeDTO;
    }

    /**
     *  delete the  employee by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete Employee : {}", id);
        employeeRepository.delete(id);
        employeeSearchRepository.delete(id);
    }

    /**
     * search for the employee corresponding
     * to the query.
     */
    @Transactional(readOnly = true) 
    public List<EmployeeDTO> search(String query) {
        
        log.debug("REST request to search Employees for query {}", query);
        return StreamSupport
            .stream(employeeSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(employeeMapper::employeeToEmployeeDTO)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EmployeeDTO getCurrentEmployee() {
        Employee employee = employeeRepository.findOne(userService.getUserWithAuthorities().getEmployee().getId());
        return employeeMapper.employeeToEmployeeDTO(employee);
    }
}
