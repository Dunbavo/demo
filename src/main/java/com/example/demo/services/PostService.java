package com.example.demo.services;

import com.example.demo.models.Activity;
import com.example.demo.models.Post;
import com.example.demo.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired private PostRepository postRepository;

    public List<Post> findAll() {
        ArrayList<Post> posts = new ArrayList<>();
        postRepository.findAll().forEach(posts::add);
        return posts;
    }

    public Page<Post> findAll(int page, int size) {
        return postRepository.findAll(PageRequest.of(page, size, Sort.by("date")));
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public Optional<Post> findById(long id) {
        return  postRepository.findById(id);
    }

    public boolean existsById(long id) {
        return postRepository.existsById(id);
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }
}
