package com.resourcy.app.web.rest.mapper;

import com.resourcy.app.domain.*;
import com.resourcy.app.web.rest.dto.CurriculumVitaeDTO;
import com.resourcy.app.web.rest.dto.EmployeeDTO;

import org.mapstruct.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper for the entity Employee and its DTO EmployeeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EmployeeMapper {

    @Mapping(source = "curriculumVitaes", target = "cvList")
    EmployeeDTO employeeToEmployeeDTO(Employee employee);

    @Mapping(target = "curriculumVitaes", ignore = true)
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
