package com.dhbinh.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {

    @Id
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_name")
    private Country country;
}
