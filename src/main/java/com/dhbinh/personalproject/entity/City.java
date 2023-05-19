package com.dhbinh.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
//import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class City {

    @Id
    private String cityName;

    @ManyToOne
    @JoinColumn(name = "country_name")
    private Country country;
}
