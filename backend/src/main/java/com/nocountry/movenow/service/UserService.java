package com.nocountry.movenow.service;

import com.nocountry.movenow.model.Comment;
import com.nocountry.movenow.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserEntity save(UserEntity user);

    Optional<UserEntity> findById(Long id);

    boolean delete(Long id);

    UserEntity update(UserEntity user);

    List<UserEntity> getAll();

    Comment makeAComment(int stars, String feedback, Long idUser, Long idMoving);
}
