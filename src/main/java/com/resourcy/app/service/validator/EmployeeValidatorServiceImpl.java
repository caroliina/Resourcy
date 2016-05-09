package com.resourcy.app.service.validator;

import com.resourcy.app.domain.Employee;
import com.resourcy.app.web.rest.dto.EducationDTO;
import com.resourcy.app.web.rest.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

/**
 * Created by Salome on 19.04.2016.
 */

@Service("employeeValidatorService")
public class EmployeeValidatorServiceImpl implements ValidatorService<EmployeeDTO> {

    @Override
    public ValidationResponse validate(EmployeeDTO employee) {
        ValidationResponse validationResponse = new ValidationResponse();

        if (employee.getLastName() == null)  {
            validationResponse.getErrorMessage().add("ERROR_EMPLOYEE_LASTNAME_ISEMPTY");
        }
        if (employee.getFirstName() == null)  {
            validationResponse.getErrorMessage().add("ERROR_EMPLOYEE_FIRSTNAME_ISEMPTY");
        }
        if (employee.getBirthday() == null)  {
            validationResponse.getErrorMessage().add("ERROR_EMPLOYEE_BIRTHDAY_ISEMPTY");
        }
        if (employee.getIdCode() == null)  {
            validationResponse.getErrorMessage().add("ERROR_EMPLOYEE_IDCODE_ISEMPTY");
        }
        if (employee.getEmail() == null)  {
            validationResponse.getErrorMessage().add("ERROR_EMPLOYEE_EMAIL_ISEMPTY");
        }
        return validationResponse;
    }


}
