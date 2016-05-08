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

        if (education.getInstitution() == null || education.getPeriodStart() == null || education.getSpeciality() == null ||
            education.getDegree() == null) {
            validationResponse.getErrorMessage().add("Some fields are empty in education");
        }
        return validationResponse;
    }


}
