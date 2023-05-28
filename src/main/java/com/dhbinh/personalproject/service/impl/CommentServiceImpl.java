package com.dhbinh.personalproject.service.impl;

import com.dhbinh.personalproject.entity.Comment;
import com.dhbinh.personalproject.exception.PersonalProjectException;
import com.dhbinh.personalproject.mapper.CommentMapper;
import com.dhbinh.personalproject.repository.CommentRepository;
import com.dhbinh.personalproject.repository.PostRepository;
import com.dhbinh.personalproject.repository.UserAccountRepository;
import com.dhbinh.personalproject.security.jwt.JwtUtils;
import com.dhbinh.personalproject.service.CommentService;
import com.dhbinh.personalproject.service.dto.CommentDTO;
import com.dhbinh.personalproject.service.dto.MonthlyUserCountDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    private final UserAccountRepository userAccountRepository;

    private final CommentMapper commentMapper;

    private final JwtUtils jwtUtils;

    @Override
    public CommentDTO userCreateComment(String token, CommentDTO commentDTO) {

        String nameToken = "";
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            nameToken = token.substring(7);
        }
        String username = jwtUtils.getUserNameFromJwtToken(nameToken);

        if (postRepository.findById(commentDTO.getPostID()).isEmpty())
            throw PersonalProjectException.postNotFound();

        if(commentDTO.getContent().isBlank() || commentDTO.getContent() == null)
            throw PersonalProjectException.badRequest("ContentEmptyOrNull", "Content is empty or null");

        Comment comment = Comment.builder()
                .ID(commentDTO.getCommentID())
                .content(commentDTO.getContent().trim())
                .createDate(LocalDate.now())
                .userAccount(userAccountRepository.findByUsername(username.trim()).get())
                .post(postRepository.findById(commentDTO.getPostID()).get())
                .build();

        return commentMapper.toDTO(commentRepository.save(comment));

    }

    @Override
    public List<CommentDTO> getAllComment() {

        List<CommentDTO> resultList = new ArrayList<>();

        List<Comment> commentList = commentRepository.findAll();

        for (Comment comment : commentList) {
            CommentDTO dto = CommentDTO.builder()
                    .commentID(comment.getID())
                    .content(comment.getContent().trim())
                    .createDate(comment.getCreateDate())
                    .username(comment.getUserAccount().getUsername().trim())
                    .postID(comment.getPost().getID())
                    .build();
            resultList.add(dto);
        }
        return resultList;
    }

    @Override
    public List<CommentDTO> getByUsername(String token) {

        String nameToken = "";
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            nameToken = token.substring(7);
        }

        String username = jwtUtils.getUserNameFromJwtToken(nameToken);
        log.info("username: {}", username);

        List<Comment> comments = commentRepository.getByUsername(username);
        return commentMapper.toDTOs(comments);
    }

    public void deleteComment(Long commentID) {
        if (!commentRepository.existsById(commentID))
            throw PersonalProjectException.commentNotFound();
        commentRepository.deleteById(commentID);
    }

    @Override
    public List<Object[]> getTopUserWithMostComment(Pageable pageable) {
        return commentRepository.getTopUserWithMostComment(pageable);
    }


    @Override
    public List<MonthlyUserCountDTO> getMonthlyUserCount() {
        return commentRepository.getMonthlyUserCount();
    }


}
