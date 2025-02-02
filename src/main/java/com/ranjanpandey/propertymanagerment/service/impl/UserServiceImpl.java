package com.ranjanpandey.propertymanagerment.service.impl;

import com.ranjanpandey.propertymanagerment.converter.UserConverter;
import com.ranjanpandey.propertymanagerment.dto.UserDTO;
import com.ranjanpandey.propertymanagerment.entity.UserEntity;
import com.ranjanpandey.propertymanagerment.repository.UserRepository;
import com.ranjanpandey.propertymanagerment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO register(UserDTO userDTO) {
        UserEntity userEntity = userConverter.convertUserDTOtoEntity(userDTO);
        userEntity = userRepository.save(userEntity);
        return userConverter.convertUserEntityToDTO(userEntity);
    }


    @Override
    public UserDTO login(String email, String password) {
        return null;
    }
}
