package com.dhbinh.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
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
}
