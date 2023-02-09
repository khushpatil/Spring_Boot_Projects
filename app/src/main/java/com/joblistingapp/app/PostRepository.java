package com.joblistingapp.app;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.joblistingapp.app.model.PostSchema;

public interface PostRepository extends MongoRepository<PostSchema, String> {

}
