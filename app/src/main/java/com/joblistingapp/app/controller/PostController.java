package com.joblistingapp.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joblistingapp.app.PostRepository;
import com.joblistingapp.app.model.PostSchema;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

    @Autowired
    PostRepository repo;

    @RequestMapping(value = "/")
    public String homepage() {
        return "This is the homepage";
    }

    @GetMapping("/allPosts")
    @CrossOrigin
    public List<PostSchema> getPosts() {

        return repo.findAll();
    }

    @PostMapping("/post")
    public PostSchema sharepost(@RequestBody PostSchema newpost) {

        return repo.save(newpost);
    }
}
