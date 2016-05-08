package com.resourcy.app.service;

import com.resourcy.app.domain.Employee;
import com.resourcy.app.web.rest.dto.EmployeeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service Interface for managing Employee.
 */
public interface EmployeeService {

   /**
    * Save a employee.
    *
    * @return the persisted entity
    */
   EmployeeDTO save(EmployeeDTO employeeDTO);

   /**
    * get all the employees.
    *
    * @return the list of entities
    */
   Page<Employee> findAll(Pageable pageable);

   /**
    * get the "id" employee.
    *
    * @return the entity
    */
   EmployeeDTO findOne(Long id);

   /**
    * delete the "id" employee.
    */
   void delete(Long id);

   /**
    * search for the employee corresponding
    * to the query.
    */
   List<EmployeeDTO> search(String query);

   EmployeeDTO getCurrentEmployee();

   EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) throws Exception;
}
