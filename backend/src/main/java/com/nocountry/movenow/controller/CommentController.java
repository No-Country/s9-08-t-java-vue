package com.nocountry.movenow.controller;

import com.nocountry.movenow.model.Comment;
import com.nocountry.movenow.model.Vehicle;
import com.nocountry.movenow.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentServiceImpl commentService;

    @Autowired
    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @PostMapping("")
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment) {
        try {
            if (comment == null) {
                return ResponseEntity.badRequest().body(null);
            }

            Comment savedComment = commentService.save(comment);
            return ResponseEntity.ok(savedComment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        try {
            Optional<Comment> optionalComment = commentService.findById(id);
            if (optionalComment.isPresent()) {
                Comment comment = optionalComment.get();
                return ResponseEntity.ok(comment);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Comment>> getAllComments() {
        try {
            List<Comment> comments = commentService.getAll();
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        try {
            if (commentService.findById(id).isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Comment updatedComment = commentService.update(comment);
            return ResponseEntity.ok(updatedComment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        try {
            if (commentService.findById(id).isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            commentService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
