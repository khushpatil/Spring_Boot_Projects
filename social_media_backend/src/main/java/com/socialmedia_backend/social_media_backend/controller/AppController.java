package com.socialmedia_backend.social_media_backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.socialmedia_backend.social_media_backend.model.Post;
import com.socialmedia_backend.social_media_backend.model.User;
import com.socialmedia_backend.social_media_backend.repository.PostRepository;
import com.socialmedia_backend.social_media_backend.repository.UserRepository;

@RestController
public class AppController {

    @Autowired
    private PostRepository postrepo;

    @Autowired
    private UserRepository userrepo;

    @GetMapping(value = "/posts")
    public List<Post> getposts() {

        return postrepo.findAll();
    }

    @GetMapping(value = "/post/{id}")
    public Optional<Post> getpost(@PathVariable int id) {

        if (postrepo.existsById(id)) {
            return postrepo.findById(id);
        }

        return null;
    }

    @PostMapping(value = "/post")
    public Post publishpost(@RequestBody Post newpost) {

        return postrepo.save(newpost);
    }

    @DeleteMapping(value = "post/{id}")
    public String deletepost(@PathVariable int id) {

        if (postrepo.existsById(id)) {
            postrepo.deleteById(id);
            return "message: the post is deleted successfully";
        }

        return "The element does not exist";
    }

    @GetMapping(value = "/users")
    public List<User> getusers(@RequestBody List<User> users) {

        return userrepo.findAll();
    }

    @PostMapping(value = "/user")
    public User createuser(@RequestBody User newuser) {

        return userrepo.save(newuser);
    }
}
