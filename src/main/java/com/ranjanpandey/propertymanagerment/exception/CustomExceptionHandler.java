package com.ranjanpandey.propertymanagerment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldValdiation(MethodArgumentNotValidException manv){
        List<FieldError> fieldErrorsList = manv.getBindingResult().getFieldErrors();
        List<ErrorModel> errorModelList = new ArrayList<>();
        ErrorModel errorModel;

        for(FieldError fe: fieldErrorsList){
            errorModel = new ErrorModel();
            errorModel.setCode(fe.getField());
            errorModel.setMessaage(fe.getDefaultMessage());
            errorModelList.add(errorModel);
        }
        return new ResponseEntity<List<ErrorModel>>(errorModelList,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>>  handleBusinessException(BusinessException businessException){
        System.out.println("Business Exception is thrown");
        return new ResponseEntity<>(businessException.getErrors(), HttpStatus.BAD_REQUEST);
    }


}
