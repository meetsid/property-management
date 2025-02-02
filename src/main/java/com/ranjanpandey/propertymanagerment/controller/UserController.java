package com.ranjanpandey.propertymanagerment.controller;

import com.ranjanpandey.propertymanagerment.dto.PropertyDTO;
import com.ranjanpandey.propertymanagerment.dto.UserDTO;
import com.ranjanpandey.propertymanagerment.repository.UserRepository;
import com.ranjanpandey.propertymanagerment.service.UserService;
import com.ranjanpandey.propertymanagerment.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO>  register(@RequestBody UserDTO userDTO){
        userDTO =  userService.register(userDTO);
        //ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(userDTO, HttpStatus.OK);
        //return responseEntity;

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

}
