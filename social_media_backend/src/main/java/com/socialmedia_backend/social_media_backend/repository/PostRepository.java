package com.socialmedia_backend.social_media_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.socialmedia_backend.social_media_backend.model.Post;

@Component
public interface PostRepository extends JpaRepository<Post, Integer> {

}
