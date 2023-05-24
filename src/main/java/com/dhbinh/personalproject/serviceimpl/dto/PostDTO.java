package com.dhbinh.personalproject.serviceimpl.dto;

import com.dhbinh.personalproject.entity.AdminAccount;
import com.dhbinh.personalproject.entity.Restaurant;
import com.dhbinh.personalproject.entity.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private LocalDate postDate;
    private String description;
    private Double rating;
    private String picture;
    private AdminAccount adminAccount;
    private Restaurant restaurant;

}
