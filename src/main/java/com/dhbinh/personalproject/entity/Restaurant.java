package com.dhbinh.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantID;

    @Column(name = "restaurant_name", nullable = false)
    private String restaurantName;

    @Column(name = "restaurant_address", length = 1000, nullable = false)
    @Length(max = 1000)
    private String restaurantAddress;

    @Column(name = "description", length = 10000)
    @Length(max = 10000)
    private String description;

    @Column(name = "telephone_number", length = 50)
    @Length(max = 50)
    private String telephoneNumber;

    @Column(name = "open_hour",nullable = false)
    private LocalTime openHour;

    @Column(name = "closing_hour",nullable = false)
    private LocalTime privateHour;

    @Column(name = "picture", length = 1000)
    @Length(max = 1000)
    private String picture;

    @ManyToOne
    @JoinColumn(name = "district_name")
    private District district;

    @ManyToOne
    @JoinColumn(name = "food_brand_name")
    private FoodBrand foodBrand;

}
