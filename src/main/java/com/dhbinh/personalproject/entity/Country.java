package com.dhbinh.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    @Id
    private String name;
}
