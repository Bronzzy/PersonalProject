package com.dhbinh.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postID;

    @Column(name = "post_date")
    private LocalDate postDate;

    @Column(name = "description", length = 10000,nullable = false)
    @Length(max = 10000)
    private String description;

    @Column(name = "rating",nullable = false)
    private Double rating;

    @Column(name = "picture", length = 10000)
    @Length(max = 10000)
    private String picture;

    @ManyToOne
    @JoinColumn(name = "admin_name")
    private AdminAccount adminAccount;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}
