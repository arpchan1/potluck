package com.app.potluck.repository;

import com.app.potluck.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    @SuppressWarnings("null")
    Optional<User> findById(Long userId);
}