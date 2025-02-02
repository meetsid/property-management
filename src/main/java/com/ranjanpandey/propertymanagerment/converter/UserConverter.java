package com.ranjanpandey.propertymanagerment.converter;

import com.ranjanpandey.propertymanagerment.dto.UserDTO;
import com.ranjanpandey.propertymanagerment.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

/*
//below code not working - had to use new keyword to create object at method level.
    @Autowired
    UserEntity userEntity;

    @Autowired
    UserDTO userDTO;
*/

    public UserEntity convertUserDTOtoEntity(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setOwnerName(userDTO.getOwnerName());
        userEntity.setOwnerEmail(userDTO.getOwnerEmail());
        userEntity.setOwnerPhone(userDTO.getOwnerPhone());
        userEntity.setPassword(userDTO.getPassword());
        return  userEntity;
    }

    public UserDTO convertUserEntityToDTO(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setOwnerName(userEntity.getOwnerName());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setOwnerEmail(userEntity.getOwnerEmail());
        userDTO.setOwnerPhone(userEntity.getOwnerPhone());
        return userDTO;
    }
}
