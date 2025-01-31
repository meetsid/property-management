package com.ranjanpandey.propertymanagerment.controller;

import com.ranjanpandey.propertymanagerment.dto.CalculatorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/calculator") //class-level mapping of a url to a controller class
public class CalculatorController {


    //http://localhost:8080/api/v1/calculator/add/9?num1=8&num2=8
    @GetMapping("/add/{num3}") //method level mapping of a url to controller function
    public Double add(@RequestParam(name = "num1") Double num1,@RequestParam(name = "num2") Double num2, @PathVariable(name="num3") Double num3){
        return  num1 + num2 + num3;
    }

    //http://localhost:8080/api/v1/calculator/add?num1=8&num2=8
    @GetMapping("/add") //method level mapping of a url to controller function
    public Double add(@RequestParam(name = "num1") Double num1,@RequestParam(name = "num2") Double num2){
        return  num1 + num2 ;
    }

    //http://localhost:8080/api/v1/calculator/sub/10/12
    @GetMapping("/sub/{num1}/{num2}") // Map the values of url to java variable by Path variable method
    public Double subtract(@PathVariable(name = "num1") Double num1,@PathVariable(name="num2") Double num2){
        Double result = null;
        if(num1 > num2){
            result = num1 - num2;
        }
        else {
            result = num2- num1;
        }
        return  result;
    }

    @PostMapping("/mul")
    public ResponseEntity<Double>  Multiply(@RequestBody CalculatorDTO calculatorDTO){
        Double result = null;
        System.out.println(calculatorDTO.getNum1());
        result = calculatorDTO.getNum1() * calculatorDTO.getNum2() * calculatorDTO.getNum3() * calculatorDTO.getNum4();
        //return ResponseEntity.ok(result);
        ResponseEntity<Double> responseEntity = new ResponseEntity<Double>(result, HttpStatus.CREATED);
        return  responseEntity;

    }



}
