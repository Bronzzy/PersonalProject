package com.dhbinh.personalproject.serviceimpl.dto;

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
public class PostStatisticDTO {
    private LocalDate postDate;
    private String adminName;
    private String restaurantName;
    private String picture;
    private String description;
    private Double rating;
}
