package com.ranjanpandey.propertymanagerment.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {
    //slf4j logger added
    //Both below lines are valid CustomExceptionHandler.class is equivalent to this.getClass()
    //private final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldValdiation(MethodArgumentNotValidException manv){
        List<FieldError> fieldErrorsList = manv.getBindingResult().getFieldErrors();
        List<ErrorModel> errorModelList = new ArrayList<>();
        ErrorModel errorModel;

        for(FieldError fe: fieldErrorsList){
            logger.debug("Inside field validation {} - {}",fe.getField(),fe.getDefaultMessage());
            logger.info("Inside field validation {} - {}",fe.getField(),fe.getDefaultMessage());
            errorModel = new ErrorModel();
            errorModel.setCode(fe.getField());
            errorModel.setMessaage(fe.getDefaultMessage());
            errorModelList.add(errorModel);
        }
        return new ResponseEntity<List<ErrorModel>>(errorModelList,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>>  handleBusinessException(BusinessException businessException){
        for(ErrorModel em: businessException.getErrors()){
            //System.out.println("Business Exception is thrown");
            logger.debug("Inside field validation {} - {}",em.getCode(),em.getMessaage());
            logger.info("Inside field validation {} - {}",em.getCode(),em.getMessaage());
            logger.warn("Inside field validation {} - {}",em.getCode(),em.getMessaage());
            logger.error("Inside field validation {} - {}",em.getCode(),em.getMessaage());
        }

        return new ResponseEntity<>(businessException.getErrors(), HttpStatus.BAD_REQUEST);
    }
}
