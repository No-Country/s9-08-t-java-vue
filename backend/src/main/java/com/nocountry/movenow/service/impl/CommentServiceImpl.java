package com.nocountry.movenow.service.impl;

import com.nocountry.movenow.model.Comment;
import com.nocountry.movenow.model.Vehicle;
import com.nocountry.movenow.repository.CommentRepository;
import com.nocountry.movenow.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        if (!commentRepository.existsById(id)){
            throw new RuntimeException("Comment not found");
        }
        return commentRepository.findById(id);
    }

    @Override
    public boolean delete(Long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (commentOptional.isEmpty()) {
            throw new RuntimeException("Vehicle not found");
        }

        Comment comment = commentOptional.get();
        comment.setSoftDelete(true);
        commentRepository.save(comment);
        return true;
    }

    @Override
    public Comment update(Comment comment) {
        Optional<Comment> commentOptional = commentRepository.findById(comment.getId());
        if (commentOptional.isEmpty()) {
            throw new RuntimeException("Comment not found");
        }

        Comment commentUpdated = commentOptional.get();

        if (comment.getStars() != 0){
            commentUpdated.setStars(comment.getStars());
        }

        if (comment.getUserName() != null){
            commentUpdated.setUserName(comment.getUserName());
        }

        if (comment.getFeedBack() != null){
            commentUpdated.setFeedBack(comment.getFeedBack());
        }

        if (comment.getIdMoving() != 0){
            commentUpdated.setIdMoving(comment.getIdMoving());
        }

        return commentRepository.save(commentUpdated);
    }

    @Override
    public List<Comment> getAll() {
        List<Comment> comments = commentRepository.findAll();
        if (comments.isEmpty()){
            throw new RuntimeException("No comments found");
        }
        return comments;
    }
}
