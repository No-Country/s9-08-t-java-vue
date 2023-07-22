package com.nocountry.movenow.repository;

import com.nocountry.movenow.model.UserEntity;
import com.nocountry.movenow.model.enums.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername( String username);
}
