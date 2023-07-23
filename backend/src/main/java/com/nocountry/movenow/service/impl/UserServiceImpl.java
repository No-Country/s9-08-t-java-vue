package com.nocountry.movenow.service.impl;

import com.nocountry.movenow.exception.UserNotFoundException;
import com.nocountry.movenow.model.Comment;
import com.nocountry.movenow.model.Moving;
import com.nocountry.movenow.model.UserEntity;
import com.nocountry.movenow.repository.UserRepository;
import com.nocountry.movenow.service.MovingService;
import com.nocountry.movenow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MovingService movingService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, MovingService movingService) {
        this.userRepository = userRepository;
        this.movingService = movingService;
    }

    @Override
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        Optional<UserEntity> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty() || userOptional.get().getSoftDelete()){
            throw new RuntimeException("User not found");
        }

        return userOptional;
    }

    @Override
    public boolean delete(Long id) {
        Optional<UserEntity> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty() || userOptional.get().getSoftDelete()){
            throw new RuntimeException("User not found");
        }

        UserEntity user = userOptional.get();
        user.setSoftDelete(true);
        userRepository.save(user);
        return true;
    }

    @Override
    public UserEntity update(UserEntity user) {
        Optional<UserEntity> userOptional = userRepository.findById(user.getId());
        if (userOptional.isEmpty() || userOptional.get().getSoftDelete()){
            throw new RuntimeException("User not found");
        }

        UserEntity userUpdated = userOptional.get();

        if (user.getUsername() != null){
            userUpdated.setEmail(user.getUsername());
        }

        if (user.getPassword() != null){
            userUpdated.setPassword(user.getPassword());
        }

        if (user.getRole() != null){
            userUpdated.setRole(user.getRole());
        }

        return userRepository.save(userUpdated);
    }

    @Override
   /* public List<UserEntity> getAll() {
        List<UserEntity> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new RuntimeException("No users found");
        }

        return users.stream().filter(user -> !user.getSoftDelete()).collect(Collectors.toList());
    } */

    public List<UserEntity> getAll() {
        List<UserEntity> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users found");
        }
        return users;
    }

    @Override
    public Comment makeAComment(int stars, String feedback, Long idUser, Long idMoving) {
        UserEntity user = userRepository.findById(idUser)
                .orElseThrow(() -> new UserNotFoundException("No user found with that id"));

        Moving moving = movingService.findById(idMoving);

        if (!user.getMovings().contains(moving)){
            throw new RuntimeException("This moving does not belongs to this user");
        }

        return Comment.builder()
                .stars(stars)
                .userName(user.getUsername())
                .feedBack(feedback)
                .idMoving(moving.getId())
                .build();
    }
}
