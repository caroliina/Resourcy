package com.resourcy.app.web.rest.mapper;

import com.resourcy.app.domain.CurriculumVitae;
import com.resourcy.app.domain.Employee;
import com.resourcy.app.web.rest.dto.CurriculumVitaeDTO;


public interface CurriculumVitaeMapper {

    CurriculumVitaeDTO curriculumVitaeToCurriculumVitaeDTO(CurriculumVitae curriculumVitae);

    CurriculumVitae curriculumVitaeDTOToCurriculumVitae(CurriculumVitaeDTO curriculumVitaeDTO);

    default Employee employeeFromId(Long id) {
        if (id == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setId(id);
        return employee;
    }
}
