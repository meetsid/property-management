package com.ranjanpandey.propertymanagerment.exception;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class BusinessException extends RuntimeException {
    private  List<ErrorModel> errors;

    public BusinessException(){

    }

    public BusinessException(List<ErrorModel> errors){
        this.errors = errors;
    }

    public List<ErrorModel> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorModel> errors) {
        this.errors = errors;
    }
}