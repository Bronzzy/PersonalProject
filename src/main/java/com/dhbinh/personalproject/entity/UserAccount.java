package com.dhbinh.personalproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_account")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userAccountID;

    @Column(name = "user_first_name")
    private String userAccountFirstName;

    @Column(name = "user_last_name")
    private String userAccountLastName;

    @Column(name = "user_email",nullable = false)
    private String userAccountEmail;

    @JsonIgnore
    @Column(name = "user_password",nullable = false)
    private String userAccountPassword;

    @OneToMany(mappedBy = "users")
    private List<UserRoleAssignment> roles = new ArrayList<>();

}
