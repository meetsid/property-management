package com.ranjanpandey.propertymanagerment.service.impl;

import com.ranjanpandey.propertymanagerment.converter.UserConverter;
import com.ranjanpandey.propertymanagerment.dto.UserDTO;
import com.ranjanpandey.propertymanagerment.entity.UserEntity;
import com.ranjanpandey.propertymanagerment.exception.BusinessException;
import com.ranjanpandey.propertymanagerment.exception.ErrorModel;
import com.ranjanpandey.propertymanagerment.repository.UserRepository;
import com.ranjanpandey.propertymanagerment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO register(UserDTO userDTO) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByOwnerEmail(userDTO.getOwnerEmail());

        if(optionalUserEntity.isPresent()){
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("EMAIL_ALREADY_EXISTS");
            errorModel.setMessaage("Incorrect Email message");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }

        UserEntity userEntity = userConverter.convertUserDTOtoEntity(userDTO);
        userEntity = userRepository.save(userEntity);
        return userConverter.convertUserEntityToDTO(userEntity);
    }


    @Override
    public UserDTO login(String email, String password) {
        UserDTO userDTO = null;

       Optional<UserEntity>  optionalUserEntity = userRepository.findByOwnerEmailAndPassword(email,password);
       if(optionalUserEntity.isPresent()){
           userDTO  = userConverter.convertUserEntityToDTO(optionalUserEntity.get());
       }
       else{
           List<ErrorModel> errorModelList = new ArrayList<>();
           ErrorModel errorModel = new ErrorModel();
           errorModel.setCode("INVALID_LOGIN");
           errorModel.setMessaage("Incorrect Email");
           errorModelList.add(errorModel);

           throw new BusinessException(errorModelList);
       }
       return  userDTO;
    }
}
