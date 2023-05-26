package com.dhbinh.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class District {

    @Id
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_name")
    private City city;
}
