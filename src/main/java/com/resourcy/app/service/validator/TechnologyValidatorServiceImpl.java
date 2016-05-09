package com.resourcy.app.service.validator;

import com.resourcy.app.web.rest.dto.EducationDTO;
import com.resourcy.app.web.rest.dto.TechnologyDTO;
import org.springframework.stereotype.Service;

/**
 * Created by Salome on 19.04.2016.
 */

@Service("technologyValidatorService")
public class TechnologyValidatorServiceImpl implements ValidatorService<TechnologyDTO> {

    @Override
    public ValidationResponse validate(TechnologyDTO technology) {
        ValidationResponse validationResponse = new ValidationResponse();

        if (technology.getDescription() == null)  {
            validationResponse.getErrorMessage().add("ERROR_TECHNOLOGY_DESCRIPTION_ISEMPTY");
        }
        if (technology.getType() == null)  {
            validationResponse.getErrorMessage().add("ERROR_TECHNOLOGY_TYPE_ISEMPTY");
        }
        return validationResponse;
    }


}
