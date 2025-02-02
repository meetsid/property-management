package com.ranjanpandey.propertymanagerment.repository;

import com.ranjanpandey.propertymanagerment.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    //@Query("SELECT u FROM user_table u WHERE u.owner_email = ?1 and b.owner_password = ?2")
    Optional<UserEntity> findByOwnerEmailAndPassword(String email, String password);

    Optional<UserEntity> findByOwnerEmail(String email);
}
