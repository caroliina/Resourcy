package com.resourcy.app.service.impl;

import com.resourcy.app.domain.CurriculumVitae;
import com.resourcy.app.domain.Employee;
import com.resourcy.app.domain.LanguageType;
import com.resourcy.app.repository.CurriculumVitaeRepository;
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
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * Service Implementation for managing Employee.
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    
    @Inject
    private EmployeeRepository employeeRepository;
    
    @Inject
    private EmployeeMapper employeeMapper;
    
    @Inject
    private EmployeeSearchRepository employeeSearchRepository;

    @Inject
    private CurriculumVitaeRepository curriculumVitaeRepository;

    @Inject
    private UserService userService;
    
    /**
     * Save a employee.
     */
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findOne(employeeDTO.getId());
        employee.setFirstName(employee.getFirstName());
        employee.setLastName(employee.getLastName());
        employee.setBirthday(employeeDTO.getBirthday());
        employee.setIdCode(employeeDTO.getIdCode());
        employee.setNationality(employeeDTO.getNationality());
        employee.setEmail(employeeDTO.getEmail());

        employeeRepository.save(employee);
        return employeeMapper.employeeToEmployeeDTO(employee);
    }

   /**
    * get all the employees.
    *
    * @return the list of entities
    */
   @Transactional(readOnly = true)
   public Page<Employee> findAll(Pageable pageable) {
      log.debug("Request to get all Employees");
      Page<Employee> result = employeeRepository.findAll(pageable);
      return result;
   }

   /**
    * get one employee by id.
    *
    * @return the entity
    */
   @Transactional(readOnly = true)
   public EmployeeDTO findOne(Long id) {
      log.debug("Request to get Employee : {}", id);
      Employee employee = employeeRepository.findOne(id);
      EmployeeDTO employeeDTO = employeeMapper.employeeToEmployeeDTO(employee);
      return employeeDTO;
   }

   /**
    * delete the  employee by id.
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

   @Override
   public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) throws Exception {

      if (employeeDTO.getId() != null) {
         throw new Exception("User already exsists");
      }

      Employee employee = employeeMapper.employeeDTOToEmployee(employeeDTO);
      employee.setUser(userService.getUserWithAuthorities());
      employee = employeeRepository.save(employee);

      Set<CurriculumVitae> curriculums = addCurriculums(employee);

      employee.setCurriculumVitaes(curriculums);
      employee = employeeRepository.save(employee);

      return employeeMapper.employeeToEmployeeDTO(employee);
   }

   private Set<CurriculumVitae> addCurriculums(Employee employee) {
      Set<CurriculumVitae> curriculums = new HashSet<>();
      CurriculumVitae curriculumVitaeENG = createCV(employee, LanguageType.ENG);
      CurriculumVitae curriculumVitaeEST = createCV(employee, LanguageType.EST);
      curriculums.add(curriculumVitaeENG);
      curriculums.add(curriculumVitaeEST);
      return curriculums;
   }

   private CurriculumVitae createCV(Employee employee, LanguageType type) {
      CurriculumVitae cv = new CurriculumVitae();
      cv.setEmployee(employeeRepository.findOne(employee.getId()));
      cv.setLanguageType(type);
      cv.setCreatedDate(ZonedDateTime.now());
      cv.setCreatedBy(userService.getUserWithAuthorities().getUsername());
      cv.setLastModifiedDate(ZonedDateTime.now());
      cv.setLastModifiedBy(userService.getUserWithAuthorities().getUsername());
      curriculumVitaeRepository.save(cv);
      return cv;
   }


}
