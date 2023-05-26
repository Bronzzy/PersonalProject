package com.dhbinh.personalproject.mapper;

import com.dhbinh.personalproject.entity.Comment;
import com.dhbinh.personalproject.service.dto.CommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(source = "post.ID", target = "postID")
    @Mapping(source = "userAccount.username", target = "username")
    @Mapping(source = "comment.ID", target = "commentID")
    CommentDTO toDTO(Comment comment);

    List<CommentDTO> toDTOs(List<Comment> comments);
}
