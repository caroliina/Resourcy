package com.resourcy.app.service.validator;

public interface ValidatorService<T> {
    ValidationResponse validate(T elem);
}
