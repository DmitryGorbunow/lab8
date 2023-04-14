package com.example.lab8.repository;

import com.example.lab8.entity.UserActions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActionsRepository extends JpaRepository<UserActions, Long> {
}
