package com.dhbinh.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalTime;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "address", length = 1000)
    @Length(max = 1000)
    private String address;

    @Column(name = "description", length = 10000)
    @Length(max = 10000)
    private String description;

    @Pattern(regexp = "^\\d{1,15}$",message = "Phone number can't contain characters")
    @Column(name = "phone_number", length = 15)
    @Length(max = 15)
    private String phoneNumber;

    @NotNull
    @Column(name = "open_hour")
    private LocalTime openHour;

    @NotNull
    @Column(name = "closing_hour")
    private LocalTime closingHour;

    @Column(name = "picture", length = 1000)
    @Length(max = 1000)
    private String picture;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "district_name")
    private District district;

    @ManyToOne
    @JoinColumn(name = "food_brand_name")
    private FoodBrand foodBrand;

}
