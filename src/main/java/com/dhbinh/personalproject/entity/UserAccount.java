package com.dhbinh.personalproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_account")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "user_first_name")
    private String firstName;

    @Column(name = "user_last_name")
    private String lastName;

    @Column(name = "username",nullable = false)
    private String username;

    @JsonIgnore
    @Column(name = "password",nullable = false)
    private String password;

    private Boolean active;

    @OneToMany(mappedBy = "userAccounts",cascade = CascadeType.PERSIST)
    private List<UserRoleAssignment> roles = new ArrayList<>();

}
