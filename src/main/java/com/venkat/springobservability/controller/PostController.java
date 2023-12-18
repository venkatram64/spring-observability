package com.venkat.springobservability.controller;

import com.venkat.springobservability.service.JsonPlaceholderService;
import com.venkat.springobservability.vo.Post;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final JsonPlaceholderService jsonPlaceholderService;

    public PostController(JsonPlaceholderService jsonPlaceholderService) {
        this.jsonPlaceholderService = jsonPlaceholderService;
    }

    @GetMapping("")
    List<Post> findAll(){
        return jsonPlaceholderService.findAll();
    }

    @GetMapping("/{id}")
    Post findById(@PathVariable Integer id){
        return jsonPlaceholderService.findBy(id);
    }

    /*@Autowired
    private PostService postService;

    @GetMapping("")
    List<Post> findAll(){
        return postService.findAll();
    }

    @GetMapping("{id}")
    public Optional<Post> findById(@PathVariable Integer id){
        return postService.findAll().stream().filter(p -> p.id().equals(id)).findFirst();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Post post){
        postService.add(post);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Post post, @PathVariable Integer id){
        postService.update(post, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        postService.remove(id);
    }*/
}

