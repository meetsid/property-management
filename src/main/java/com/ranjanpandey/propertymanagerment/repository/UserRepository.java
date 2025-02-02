package com.ranjanpandey.propertymanagerment.repository;
import com.ranjanpandey.propertymanagerment.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
