package com.dhbinh.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postCommentID;

    @Column(name = "post_comment_comment", length = 10000,nullable = false)
    @Length(max = 10000)
    private String postCommentComment;

    @Column(name = "post_comment_date")
    private LocalDate postCommentDate;

    @ManyToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

}
