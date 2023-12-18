package com.venkat.springobservability.service;

import com.venkat.springobservability.vo.Post;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface JsonPlaceholderService {

    @GetExchange("posts")
    List<Post> findAll();

    @GetExchange("posts/{id}")
    Post findBy(@PathVariable Integer id);
}
