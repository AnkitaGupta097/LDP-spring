package org.example.crudoperations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.crudoperations.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUserId(String userid);
}
