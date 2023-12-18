package com.venkat.springobservability.service;

import com.venkat.springobservability.vo.Post;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//Not used this class
@Service
public class PostService {

    List<Post> posts = List.of(
            new Post(1,1, "working", "I am working on task"),
            new Post(2,2, "Excercise", "I am do doing excercise"),
            new Post(1,1, "Java", "I am reading on java")
    );

    public List<Post> findAll(){
        return posts;
    }

    public void add(Post post){
        posts.add(post);
    }

    public void update(Post post, Integer id){
        Optional<Post> first = posts.stream().filter(p -> p.id().equals(id)).findFirst();
        first.ifPresent(p -> posts.set(posts.indexOf(p), post));
    }

    public void remove(Integer id){
        posts.removeIf(p -> p.id().equals(id));
    }
}
