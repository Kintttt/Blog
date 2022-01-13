package com.example.blog.services;


import com.example.blog.model.Person;
import com.example.blog.model.PostModel;
import com.example.blog.repository.PersonRepository;
import com.example.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepo;
    private final PersonRepository personRepo;
    private final PersonService personService;
    // add person service dependency

    @Autowired
    public PostService(PostRepository postRepo, PersonRepository personRepo, PersonService personService) {
        this.postRepo = postRepo;
        this.personRepo = personRepo;
        this.personService = personService;
    }

    public String createPost(Long posterId, String postText) {

        var personId = personRepo.findById(posterId).get();
        PostModel newPost = new PostModel(postText);

        newPost.setPerson(personId);

        postRepo.save(newPost);
        return "Post successfully created";
    }

    public List<PostModel> getAllPosts() {
        List<PostModel> postList = postRepo.findAll();
        Collections.reverse(postList);
        return postList;
    }

    public String likePosts(Long userId, Long postId) {

        var user = personRepo.findById(userId).get();
        PostModel post = postRepo.findById(postId).get();

        post.getPostLikes().add(user);
        postRepo.save(post);
        return "Post liked now. Number of likes is: " + post.getPostLikes().size();

    }

    public String unLikePosts(Long userId, Long postId){
        var user = personRepo.findById(userId).get();
        PostModel post = postRepo.findById(postId).get();

        if(post.getPostLikes().contains(user)){
            post.getPostLikes().remove(user);

            return "Post unliked. Number of likes is now: " + post.getPostLikes().size();
        }
        return "You never liked this post.";
    }

    public List<PostModel> getAllPostByUserId(Long userId) {
        Person user = personRepo.findById(userId).get();

        return postRepo.findAllByPerson(user);
    }


    public List<PostModel> getFriendsPosts(Long userId) {
        var user = personRepo.findById(userId).get();

        List<PostModel> friendsPosts = new ArrayList<>();

        user.getFriends().forEach(friend -> {
            var friendsPost = getAllPostByUserId(friend.getUserId());
            friendsPosts.addAll(friendsPost);
        });
        return friendsPosts;
    }
}