package com.dhbinh.personalproject.serviceimpl.impl;

import com.dhbinh.personalproject.entity.Post;
import com.dhbinh.personalproject.exception.PersonalProjectException;
import com.dhbinh.personalproject.mapper.PostMapper;
import com.dhbinh.personalproject.repository.PostRepository;
import com.dhbinh.personalproject.repository.RestaurantRepository;
import com.dhbinh.personalproject.serviceimpl.PostService;
import com.dhbinh.personalproject.serviceimpl.RestaurantService;
import com.dhbinh.personalproject.serviceimpl.dto.PostStatisticDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final PostMapper postMapper;

    private final RestaurantService restaurantService;

    private final RestaurantRepository restaurantRepository;

    public List<PostStatisticDTO> getAllPost() {
        List<PostStatisticDTO> result = new ArrayList<>();

        List<Post> postList = postRepository.findAll();
        if (postList.isEmpty())
            throw PersonalProjectException.postNotFound();

        for (Post post : postList) {
            PostStatisticDTO postStatisticDTO = new PostStatisticDTO();
            postStatisticDTO = PostStatisticDTO.builder()
                    .postDate(post.getPostDate())
                    .description(post.getDescription())
                    .rating(post.getRating())
                    .restaurantName(post.getRestaurant().getRestaurantName())
                    .picture(post.getPicture())
                    .adminName(post.getAdminAccount().getAdminName())
                    .build();
            result.add(postStatisticDTO);
        }
        return result;
    }

    public List<PostStatisticDTO> getPostByRestaurantName(String restaurantName) {
        return postRepository.getPostByRestaurantName(restaurantName);
    }

    @Override
    public void deleteByPostID(Long postID) {
        Post existingPost = postRepository.findById(postID)
                .orElseThrow(PersonalProjectException::postNotFound);

        postRepository.delete(existingPost);
    }


}
