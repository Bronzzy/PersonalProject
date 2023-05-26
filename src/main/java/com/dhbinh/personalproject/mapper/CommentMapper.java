package com.dhbinh.personalproject.mapper;

import com.dhbinh.personalproject.entity.Comment;
import com.dhbinh.personalproject.service.dto.CommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "postID", source = "post.ID")
    @Mapping(target = "username", source = "userAccount.username")
    @Mapping(target = "commentID", source = "comment.ID")
    CommentDTO toDTO(Comment comment);

    List<CommentDTO> toDTOs(List<Comment> comments);
}
