package com.resourcy.app.service.validator;

/**
 * Created by Salome on 19.04.2016.
 */
public interface ValidatorService<T> {
    ValidationResponse validate(T elem);
}
