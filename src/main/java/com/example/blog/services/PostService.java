package com.example.blog.services;


import com.example.blog.model.Person;
import com.example.blog.model.PostModel;
import com.example.blog.repository.PersonRepository;
import com.example.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepo;
    private final PersonRepository personRepo;

    @Autowired
    public PostService(PostRepository postRepo, PersonRepository personRepo) {
        this.postRepo = postRepo;
        this.personRepo = personRepo;
    }

    public String createPost(Long posterId, String postText){

        var personId = personRepo.findById(posterId).get();
        PostModel newPost = new PostModel(postText);

        newPost.setPerson(personId);

        postRepo.save(newPost);
        return "Post successfully created";
    }

    public List<PostModel> getAllPosts(){
        List<PostModel> postList =  postRepo.findAll();
        Collections.reverse(postList);
        return postList;
    }

    public String likePosts(Long userId, Long post){

        var user = personRepo.findById(userId).get();
        PostModel postId = postRepo.findById(post).get();

        if(postId.getPostLikes() == null){
//            List<Person> persons = postId.getPostLikes();
//            persons.add(user);
//            postId.setPostLikes(persons);
            postId.getPostLikes().add(user);
            postRepo.save(postId);
        }

        if(postId.getPostLikes().contains(user)){
            System.out.println(postId.getPostLikes());
            return "Post was already liked by you. Number of likes is: "+ postId.getPostLikes().size();
        }
        System.out.println(postId.getPostLikes().size());
        postId.getPostLikes().add(user);
        System.out.println(postId.getPostLikes());
        return "You have now liked this post. Number of likes is now: "+ postId.getPostLikes().size();


    }

}
