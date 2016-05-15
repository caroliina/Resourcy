package com.resourcy.app.service.validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Salome on 19.04.2016.
 */

public class ValidationResponse {
    private List<String> errorMessage = new ArrayList<>();



    public List<String> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(List<String> errorMessage) {
        this.errorMessage = errorMessage;
    }
}
