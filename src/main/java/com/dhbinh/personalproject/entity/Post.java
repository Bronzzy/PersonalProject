package com.dhbinh.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "create_date")
    private LocalDate createDate;

    @NotNull
    @Column(name = "description", length = 10000)
    @Length(max = 10000)
    private String description;

    @NotNull
    @PositiveOrZero
    @Min(0)
    @Max(10)
    @Column(name = "rating")
    private Double rating;

    @Column(name = "picture", length = 10000)
    @Length(max = 10000)
    private String picture;

    @ManyToOne
    @JoinColumn(name = "user_account")
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}
