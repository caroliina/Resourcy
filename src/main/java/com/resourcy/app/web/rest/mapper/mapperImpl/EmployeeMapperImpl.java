package com.resourcy.app.web.rest.mapper.mapperImpl;

import com.resourcy.app.domain.Employee;
import com.resourcy.app.web.rest.dto.EmployeeDTO;
import com.resourcy.app.web.rest.mapper.EmployeeMapper;
import org.springframework.stereotype.Component;


@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeDTO employeeToEmployeeDTO(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setCvList( map( employee.getCurriculumVitaes() ) );
        employeeDTO.setCreatedDate( employee.getCreatedDate() );
        employeeDTO.setLastModifiedDate( employee.getLastModifiedDate() );
        employeeDTO.setCreatedBy( employee.getCreatedBy() );
        employeeDTO.setLastModifiedBy( employee.getLastModifiedBy() );
        employeeDTO.setId( employee.getId() );
        employeeDTO.setFirstName( employee.getFirstName() );
        employeeDTO.setLastName( employee.getLastName() );
        employeeDTO.setBirthday( employee.getBirthday() );
        employeeDTO.setNationality( employee.getNationality() );
        employeeDTO.setEmail( employee.getEmail() );
        employeeDTO.setIdCode( employee.getIdCode() );

        return employeeDTO;
    }

    @Override
    public Employee employeeDTOToEmployee(EmployeeDTO employeeDTO) {
        if ( employeeDTO == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setCreatedBy( employeeDTO.getCreatedBy() );
        employee.setCreatedDate( employeeDTO.getCreatedDate() );
        employee.setLastModifiedBy( employeeDTO.getLastModifiedBy() );
        employee.setLastModifiedDate( employeeDTO.getLastModifiedDate() );
        employee.setId( employeeDTO.getId() );
        employee.setFirstName( employeeDTO.getFirstName() );
        employee.setLastName( employeeDTO.getLastName() );
        employee.setBirthday( employeeDTO.getBirthday() );
        employee.setNationality( employeeDTO.getNationality() );
        employee.setEmail( employeeDTO.getEmail() );
        employee.setIdCode( employeeDTO.getIdCode() );

        return employee;
    }
}
