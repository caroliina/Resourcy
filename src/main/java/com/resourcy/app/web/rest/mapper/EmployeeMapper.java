package com.resourcy.app.web.rest.mapper;

import com.resourcy.app.domain.*;
import com.resourcy.app.web.rest.dto.EmployeeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Employee and its DTO EmployeeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EmployeeMapper {

    EmployeeDTO employeeToEmployeeDTO(Employee employee);

    @Mapping(target = "curriculumVitaes", ignore = true)
    Employee employeeDTOToEmployee(EmployeeDTO employeeDTO);
}
