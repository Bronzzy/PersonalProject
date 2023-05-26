package com.dhbinh.personalproject.service.impl;

import com.dhbinh.personalproject.entity.Comment;
import com.dhbinh.personalproject.entity.UserAccount;
import com.dhbinh.personalproject.exception.PersonalProjectException;
import com.dhbinh.personalproject.mapper.CommentMapper;
import com.dhbinh.personalproject.repository.CommentRepository;
import com.dhbinh.personalproject.repository.PostRepository;
import com.dhbinh.personalproject.repository.UserAccountRepository;
import com.dhbinh.personalproject.security.jwt.JwtUtils;
import com.dhbinh.personalproject.service.CommentService;
import com.dhbinh.personalproject.service.dto.CommentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        String username = jwtUtils.getUserNameFromJwtToken(token);

        if(postRepository.findById(commentDTO.getPostID()).isEmpty())
            throw PersonalProjectException.postNotFound();

        Comment comment = Comment.builder()
                .commentID(commentDTO.getCommentID())
                .comment(commentDTO.getComment())
                .userAccount(userAccountRepository.findByUsername(username).get())
                .commentDate(LocalDate.now())
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
                    .commentID(comment.getCommentID())
                    .comment(comment.getComment())
                    .commentDate(comment.getCommentDate())
                    .username(comment.getUserAccount().getUsername())
                    .postID(comment.getPost().getPostID())
                    .build();
            resultList.add(dto);
        }
        return resultList;
    }

    @Override
    public List<CommentDTO> getByUsername(String token) {

        String username = jwtUtils.getUserNameFromJwtToken(token);
        log.info("username: {}", username);

        List<Comment> comments = commentRepository.getByUsername(username);
        return commentMapper.toDTOs(comments);
    }

    public void deleteComment(Long commentID) {
        if(!commentRepository.existsById(commentID))
            throw PersonalProjectException.commentNotFound();
        commentRepository.deleteById(commentID);
    }
}
