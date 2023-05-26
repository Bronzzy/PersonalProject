package com.dhbinh.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @NotNull
    @Column(name = "content", length = 10000)
    @Length(max = 10000)
    private String content;

    @Column(name = "create_date")
    private LocalDate createDate = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

}
