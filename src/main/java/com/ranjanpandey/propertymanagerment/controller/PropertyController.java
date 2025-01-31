package com.ranjanpandey.propertymanagerment.controller;

import com.ranjanpandey.propertymanagerment.dto.PropertyDTO;
import com.ranjanpandey.propertymanagerment.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PropertyController {

    @Value("${pmsdb.retrieveEnvironment}")
    private String retrieveEnvironment;

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/hello")
    public String Hello(){
        System.out.println(retrieveEnvironment);
        return  "hello " + retrieveEnvironment;
    }

    //http://localhost:8080/api/v1/property
    @PostMapping("/property")
    public  ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO propertyDTO){
        propertyDTO = propertyService.saveProperty(propertyDTO);
        //System.out.printf(propertyDTO.toString());
        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);
        return responseEntity;
    }

    //http://localhost:8080/api/v1/properties
    @GetMapping("/properties")
    public ResponseEntity<List<PropertyDTO>>  getAllProperties(){
       List<PropertyDTO> propertyList =  propertyService.getAllProperties();
        return new ResponseEntity<>(propertyList,HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/properties/1
    @PutMapping("/properties/{propertyId}")
    public  ResponseEntity<PropertyDTO> updateProperty(@RequestBody PropertyDTO propertyDTO,@PathVariable Long propertyId){
        propertyDTO = propertyService.updateProperty(propertyDTO,propertyId);
        return new ResponseEntity<>(propertyDTO,HttpStatus.OK);

    }

    //http://localhost:8080/api/v1/properties/update-description/2
    @PatchMapping("/properties/update-description/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyDescription(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId){
        propertyDTO = propertyService.updatePropertyDescription(propertyDTO,propertyId);
        return new ResponseEntity<>(propertyDTO,HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/properties/update-price/2
    @PatchMapping("/properties/update-price/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyPrice(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId){
        propertyDTO = propertyService.updatePropertyPrice(propertyDTO,propertyId);
        return new ResponseEntity<>(propertyDTO,HttpStatus.OK);
    }

    @DeleteMapping("/properties/{propertyId}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long propertyId){
        propertyService.deleteProperty(propertyId);

        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }
}
