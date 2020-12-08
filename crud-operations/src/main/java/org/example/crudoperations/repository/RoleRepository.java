package org.example.crudoperations.repository;

import org.example.crudoperations.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository< Role, UUID> {
   Optional<Role> findByName(String roleName);
}
