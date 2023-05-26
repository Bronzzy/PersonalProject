package com.dhbinh.personalproject.service.impl;

import com.dhbinh.personalproject.entity.Comment;
import com.dhbinh.personalproject.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommentServiceImplTest {
    @Autowired
    private CommentService commentService;

//    @Test
//    void getByUsername() {
//        List<CustomCommentStatisticDTO> temp = commentService.getByUsername("hohrtmann1@alibaba.com");
//        temp.forEach(System.out::println);
//    }
//
//    @Test
//    void getByUsername1() {
//        List<Object[]> temp = commentService.getByUsername1("hohrtmann1@alibaba.com");
//        temp.forEach(System.out::println);
//    }
}