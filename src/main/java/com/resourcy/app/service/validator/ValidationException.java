package com.resourcy.app.service.validator;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Salome on 08.05.2016.
*/

public class ValidationException extends Exception {
    ValidationResponse validationResponse;

    public ValidationException(ValidationResponse validationResponse) {
        super(StringUtils.join(validationResponse.getErrorMessage(), ", "));
        this.validationResponse = validationResponse;
    }

    public ValidationResponse getValidationResponse() {
        return validationResponse;
    }

    public void setValidationResponse(ValidationResponse validationResponse) {
        this.validationResponse = validationResponse;
    }
}
