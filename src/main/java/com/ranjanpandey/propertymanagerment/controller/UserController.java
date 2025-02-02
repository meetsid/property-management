package com.ranjanpandey.propertymanagerment.controller;

import com.ranjanpandey.propertymanagerment.dto.UserDTO;
import com.ranjanpandey.propertymanagerment.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    UserService userService;
    @Autowired
    public UserController( UserService userService){
    this.userService = userService;
    }
    /**
     * http://localhost:8080/api/v1/user/register
     {
     "password" : "admin",
     "ownerName" : "admin",
     "ownerEmail" : "admin@gmail.com",
     "ownerPhone" : "9999999999"
     }
     */
    @PostMapping("/register")
    public ResponseEntity<UserDTO>  register(@Valid @RequestBody UserDTO userDTO){
        userDTO =  userService.register(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);

        //ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(userDTO, HttpStatus.OK);
        //return responseEntity;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@Valid @RequestBody UserDTO userDTO){
        userDTO = userService.login(userDTO.getOwnerEmail(),userDTO.getPassword());
        return  new ResponseEntity<>(userDTO,HttpStatus.OK);
    }
}
