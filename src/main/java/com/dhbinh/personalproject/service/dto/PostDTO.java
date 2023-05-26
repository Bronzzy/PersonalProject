package com.dhbinh.personalproject.service.dto;

import com.dhbinh.personalproject.entity.AdminAccount;
import com.dhbinh.personalproject.entity.Restaurant;
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
    private Long ID;
    private LocalDate createDate;
    private String adminName;
    private String restaurantName;
    private String picture;
    private String description;
    private Double rating;

}
