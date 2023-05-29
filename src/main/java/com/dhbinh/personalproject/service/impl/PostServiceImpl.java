package com.dhbinh.personalproject.service.impl;

import com.dhbinh.personalproject.entity.Comment;
import com.dhbinh.personalproject.entity.Post;
import com.dhbinh.personalproject.entity.Restaurant;
import com.dhbinh.personalproject.exception.PersonalProjectException;
import com.dhbinh.personalproject.mapper.CommentMapper;
import com.dhbinh.personalproject.mapper.PostMapper;
import com.dhbinh.personalproject.repository.CommentRepository;
import com.dhbinh.personalproject.repository.PostRepository;
import com.dhbinh.personalproject.repository.RestaurantRepository;
import com.dhbinh.personalproject.repository.UserAccountRepository;
import com.dhbinh.personalproject.service.CommentService;
import com.dhbinh.personalproject.service.PostService;
import com.dhbinh.personalproject.service.dto.PostDTO;
import com.dhbinh.personalproject.service.dto.PostWithAllCommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final PostMapper postMapper;

    private final RestaurantRepository restaurantRepository;

    private final UserAccountRepository userAccountRepository;

    private final CommentService commentService;

    private final CommentMapper commentMapper;


    @Override
    public PostDTO createPost(PostDTO postDTO) {


        if (restaurantRepository.findByName(postDTO.getRestaurantName()).isEmpty())
            throw PersonalProjectException.restaurantNotFound();

//        if (userAccountRepository.findByLastName(postDTO.getAdminName()).isEmpty())
//            throw PersonalProjectException.adminNotFound();

        if(postDTO.getDescription() == null || postDTO.getDescription().isBlank() || postDTO.getDescription().isEmpty())
            throw PersonalProjectException.badRequest("DescriptionInvalid","Description is required");

        if(postDTO.getRating() == null)
            throw PersonalProjectException.badRequest("RatingInvalid","Rating is required");

        if(postDTO.getRating() < 0 || postDTO.getRating() > 10)
            throw PersonalProjectException.badRequest("RatingInvalid","Rating must be between 0 and 10");


        Post post = Post.builder()
                .createDate(LocalDate.now())
                .description(postDTO.getDescription().trim())
                .rating(postDTO.getRating())
                .restaurant(restaurantRepository.findByName(postDTO.getRestaurantName().trim())
                        .orElseThrow(PersonalProjectException::restaurantNotFound))
                .userAccount(userAccountRepository.findByLastName(postDTO.getAdminName().trim())
                        .orElseThrow(PersonalProjectException::adminNotFound))
                .picture(postDTO.getPicture())
                .build();

        return postMapper.toDTO(postRepository.save(post));
    }

    public List<PostDTO> getAllPost() {

        List<PostDTO> result = new ArrayList<>();

        List<Post> postList = postRepository.findAll();
        if (postList.isEmpty())
            throw PersonalProjectException.postNotFound();

        for (Post post : postList) {
            PostDTO dto = PostDTO.builder()
                    .ID(post.getID())
                    .createDate(post.getCreateDate())
                    .description(post.getDescription())
                    .rating(post.getRating())
                    .restaurantName(post.getRestaurant().getName())
                    .picture(post.getPicture())
                    .adminName(post.getUserAccount().getLastName())
                    .build();
            result.add(dto);
        }
        return result;
    }

    public PostDTO getPostByID(Long postID) {
        Post existingPost = postRepository.findById(postID)
                .orElseThrow(PersonalProjectException::postNotFound);

        return postMapper.toDTO(existingPost);
    }

    @Override
    public PostDTO updatePost(Long postID, PostDTO postDTO) {

        Post existingPost = postRepository.findById(postID)
                .orElseThrow(PersonalProjectException::postNotFound);

        if (restaurantRepository.findByName(postDTO.getRestaurantName()).isEmpty())
            throw PersonalProjectException.restaurantNotFound();

        if(postDTO.getDescription().isBlank() || postDTO.getDescription().isEmpty() || postDTO.getDescription() == null)
            throw PersonalProjectException.badRequest("DescriptionInvalid","Description is required");

        if(postDTO.getRating() < 0 || postDTO.getRating() > 10)
            throw PersonalProjectException.badRequest("RatingInvalid","Rating must be between 0 and 10");

        existingPost.setCreateDate(LocalDate.now());
        existingPost.setDescription(postDTO.getDescription() == null ? existingPost.getDescription() : postDTO.getDescription().trim());
        existingPost.setRating(postDTO.getRating() == null ? existingPost.getRating() : postDTO.getRating());
        existingPost.setPicture(postDTO.getPicture() == null ? existingPost.getPicture() : postDTO.getPicture());
        existingPost.setRestaurant(postDTO.getRestaurantName() == null ? existingPost.getRestaurant() :
                restaurantRepository.findByName(postDTO.getRestaurantName())
                        .orElseThrow(PersonalProjectException::restaurantNotFound));

        return postMapper.toDTO(postRepository.save(existingPost));
    }

    @Override
    public void deleteByPostID(Long postID) {

        Post existingPost = postRepository.findById(postID)
                .orElseThrow(PersonalProjectException::postNotFound);

        postRepository.delete(existingPost);
    }

    public PostWithAllCommentDTO getPostWithAllCommentByRestaurantName(String restaurantName) {

        Restaurant existingRestaurant = restaurantRepository.findByName(restaurantName.trim())
                .orElseThrow(PersonalProjectException::restaurantNotFound);

        Post existingPost = postRepository.findByRestaurant(existingRestaurant)
                .orElseThrow(PersonalProjectException::postNotFound);

        List<Comment> comments = commentService.findByPost(existingPost);

        return PostWithAllCommentDTO.builder()
                .postDTO(postMapper.toDTO(existingPost))
                .comments(commentMapper.toDTOs(comments))
                .build();
    }
}
