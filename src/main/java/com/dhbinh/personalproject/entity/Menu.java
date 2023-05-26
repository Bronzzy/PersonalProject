package com.dhbinh.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "starting_price")
    private Double startingPrice;

    @Column(name = "ending_price")
    private Double endingPrice;

    @Column(name = "ingredients", length = 10000)
    @Length(max = 10000)
    private String ingredients;

    @Column(name = "description", length = 10000)
    @Length(max = 10000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "dish_category")
    private DishCategory dishCategory;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}
