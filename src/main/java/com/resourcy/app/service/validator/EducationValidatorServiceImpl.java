package com.resourcy.app.service.validator;

import com.resourcy.app.web.rest.dto.AdditionalStudyDTO;
import com.resourcy.app.web.rest.dto.EducationDTO;
import org.springframework.stereotype.Service;

/**
 * Created by Salome on 19.04.2016.
 */

@Service("educationValidatorService")
public class EducationValidatorServiceImpl implements ValidatorService<EducationDTO> {

    @Override
    public ValidationResponse validate(EducationDTO education) {
        ValidationResponse validationResponse = new ValidationResponse();

        if (education.getInstitution() == null)  {
            validationResponse.getErrorMessage().add("ERROR_EDUCATION_INSTITUTION_ISEMPTY");
        }
        if (education.getPeriodStart() == null)  {
            validationResponse.getErrorMessage().add("ERROR_EDUCATION_PERIODSTART_ISEMPTY");
        }
        if (education.getSpeciality() == null)  {
            validationResponse.getErrorMessage().add("ERROR_EDUCATION_SPECIALITY_ISEMPTY");
        }
        if (education.getDegree() == null)  {
            validationResponse.getErrorMessage().add("ERROR_EDUCATION_DEGREE_ISEMPTY");
        }

        return validationResponse;
    }


}
