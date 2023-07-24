package com.nocountry.movenow.service;

import com.nocountry.movenow.model.Comment;
import com.nocountry.movenow.model.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CommentService {
    Comment save(Comment comment);

    Comment findById(Long id);

    boolean delete(Long id);

    Comment update(Comment comment);

    List<Comment> getAll();
}
