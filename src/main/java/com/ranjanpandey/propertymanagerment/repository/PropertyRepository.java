package com.ranjanpandey.propertymanagerment.repository;

import com.ranjanpandey.propertymanagerment.entity.PropertyEntity;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository<PropertyEntity,Long> {

}
