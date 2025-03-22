package com.jhernandez.backend.bazaar.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "role_id")
    // @OnDelete(action = OnDeleteAction.CASCADE)
    private RoleEntity role;

    @Column(unique = true)
    private String email;

    private String password;
    private String name;
    private String surnames;
    private String address;
    private String city;
    private String province;

    @Column(name = "zip_code", length = 5)
    private String zipCode;
}
