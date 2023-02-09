package com.fullstack.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.project.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
