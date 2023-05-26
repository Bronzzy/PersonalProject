package com.dhbinh.personalproject.mapper;

import com.dhbinh.personalproject.entity.Post;
import com.dhbinh.personalproject.service.dto.PostDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "restaurant.name", target = "restaurantName")
    @Mapping(source = "adminAccount.adminName", target = "adminName")
    PostDTO toDTO(Post post);

    List<PostDTO> toDTOs(List<Post> postList);

    Post toEntity(PostDTO postDTO);

    List<Post> toEntities(List<PostDTO> postDTOList);
}
