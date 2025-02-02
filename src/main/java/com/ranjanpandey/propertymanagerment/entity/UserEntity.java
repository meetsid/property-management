package com.ranjanpandey.propertymanagerment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "USER_TABLE")
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "OwnerName", nullable = false)
    private String ownerName;

    private String ownerEmail;

    private String ownerPhone;

    private String password;
}
