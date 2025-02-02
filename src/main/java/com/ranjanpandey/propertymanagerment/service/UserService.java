package com.ranjanpandey.propertymanagerment.service;

import com.ranjanpandey.propertymanagerment.dto.UserDTO;
import org.apache.catalina.User;

public interface UserService {
    UserDTO register(UserDTO userDTO);
    UserDTO login(String email, String password);
}
