package com.ranjanpandey.propertymanagerment.service.impl;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.ranjanpandey.propertymanagerment.controller.PropertyController;
import com.ranjanpandey.propertymanagerment.converter.PropertyConverter;
import com.ranjanpandey.propertymanagerment.dto.PropertyDTO;
import com.ranjanpandey.propertymanagerment.entity.PropertyEntity;
import com.ranjanpandey.propertymanagerment.repository.PropertyRepository;
import com.ranjanpandey.propertymanagerment.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyConverter propertyConverter;

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
        PropertyEntity propertyEntity = propertyConverter.convertDTOtoEntity(propertyDTO);
        propertyEntity = propertyRepository.save(propertyEntity);

        return propertyConverter.convertEntityToDTo(propertyEntity);
    }

    @Override
    public List<PropertyDTO> getAllProperties(){
       List<PropertyEntity> listOfProperties = (List<PropertyEntity>) propertyRepository.findAll();
       List<PropertyDTO> propList = new ArrayList<>();

        for(PropertyEntity pe: listOfProperties){
            PropertyDTO dto = propertyConverter.convertEntityToDTo(pe);
            propList.add(dto);
        }
        return  propList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
        PropertyDTO pDTO = null;
        Optional<PropertyEntity> optionalPropertyEntity = propertyRepository.findById(propertyId);
        if(optionalPropertyEntity.isPresent()){
            //Record from database
            PropertyEntity propertyEntity = optionalPropertyEntity.get();
            //Change field from controller
            propertyEntity.setTitle(propertyDTO.getTitle());
            propertyEntity.setDescription(propertyDTO.getDescription());
            propertyEntity.setPrice(propertyDTO.getPrice());
            //propertyEntity.setOwnerName(propertyDTO.getOwnerName());
            //propertyEntity.setOwnerEmail(propertyDTO.getOwnerEmail());

            pDTO = propertyConverter.convertEntityToDTo(propertyEntity);
            //Save record to the database
            propertyEntity =  propertyRepository.save(propertyEntity);

        }
        return  pDTO;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
        PropertyDTO dto = null;
        Optional<PropertyEntity>  optionalPropertyEntity = propertyRepository.findById(propertyId);
        if(optionalPropertyEntity.isPresent()){
            PropertyEntity pe = optionalPropertyEntity.get();
            pe.setDescription(propertyDTO.getDescription());
            dto = propertyConverter.convertEntityToDTo(pe);
            propertyRepository.save(pe);
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {
        PropertyDTO dto = null;
      Optional<PropertyEntity>   optionalPropertyEntity = propertyRepository.findById(propertyId);
       if(optionalPropertyEntity.isPresent()){
           PropertyEntity propertyEntity = optionalPropertyEntity.get();
           propertyEntity.setPrice(propertyDTO.getPrice());
           dto = propertyConverter.convertEntityToDTo(propertyEntity);
           propertyRepository.save(propertyEntity);
       }
       return dto;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }

}
