package com.resourcy.app.web.rest.mapper;

import com.resourcy.app.domain.*;
import com.resourcy.app.web.rest.dto.CurriculumVitaeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CurriculumVitae and its DTO CurriculumVitaeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CurriculumVitaeMapper {

    @Mapping(source = "employee.id", target = "employeeId")
    CurriculumVitaeDTO curriculumVitaeToCurriculumVitaeDTO(CurriculumVitae curriculumVitae);

    @Mapping(source = "employeeId", target = "employee")
    @Mapping(target = "educations", ignore = true)
    @Mapping(target = "workExperiences", ignore = true)
    @Mapping(target = "governmentWorkExperiences", ignore = true)
    @Mapping(target = "additionalStudys", ignore = true)
    @Mapping(target = "languageSkills", ignore = true)
    @Mapping(target = "additionalSkills", ignore = true)
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
