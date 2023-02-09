package com.socialmedia_backend.social_media_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.socialmedia_backend.social_media_backend.model.User;

@Component
public interface UserRepository extends JpaRepository<User, Integer> {

}
