package com.ba.controller;

import com.ba.domain.Post;
import com.ba.domain.Tag;
import com.ba.repository.PostRepository;
import com.ba.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping("/add")
    public String addPost() {

        Tag tag1 = tagRepository.findById(1L).get();
        Tag tag2 = tagRepository.findById(3L).get();
        Tag tag3 = tagRepository.findById(5L).get();

        Post post = new Post();
        post.setTitle("Authentication nasıl yapılır");
        post.setDescription("Spring ile auth");
        post.setTags(Stream.of(tag1, tag2, tag3).collect(Collectors.toSet()));

        Post savedPost = postRepository.save(post);

        return savedPost.toString();
    }

    @GetMapping("/add-tags")
    public String addTags() {

        Tag tag = new Tag();
        tag.setName("Kotlin");

        Tag savedTag = tagRepository.save(tag);

        return savedTag.toString();
    }

    @GetMapping("/list")
    public List<Post> getAllPost() {

        return postRepository.findAll();
    }

    @GetMapping("/list-tags")
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.isPresent() ? optionalPost.get() : null;
    }

    @DeleteMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return "içerik silindi. id : " + id;
    }
}
