package com.resourcy.app.web.rest.mapper;

import com.resourcy.app.domain.CurriculumVitae;
import com.resourcy.app.domain.Employee;
import com.resourcy.app.web.rest.dto.CurriculumVitaeDTO;
import com.resourcy.app.web.rest.dto.EmployeeDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface EmployeeMapper {

    EmployeeDTO employeeToEmployeeDTO(Employee employee);

    Employee employeeDTOToEmployee(EmployeeDTO employeeDTO);

    default List<CurriculumVitaeDTO> map(Set<CurriculumVitae> value) {
        if (value != null) {
            return value.stream()
                    .map(o->{
                        CurriculumVitaeDTO dto = new CurriculumVitaeDTO();
                        dto.setId(o.getId());
                        dto.setLanguageType(o.getLanguageType());
                        return dto;
                    }).collect(Collectors.toList());
        }
        return null;
    }

}
