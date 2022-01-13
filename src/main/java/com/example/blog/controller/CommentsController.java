package com.example.blog.controller;

import com.example.blog.repository.PersonRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentsController {

    private final CommentsService commentsService;
    private final PersonRepository personRepo;
    private final PostRepository postRepo;

    @Autowired
    public CommentsController(CommentsService commentsService, PersonRepository personRepo, PostRepository postRepo) {
        this.commentsService = commentsService;
        this.personRepo = personRepo;
        this.postRepo = postRepo;
    }

    @PostMapping("/comment/{userId}/{postId}")
    public String saveComment(@PathVariable Long postId, @PathVariable Long userId, @RequestBody String comment){

        //if(personRepo.findById(postId).get() == null)
        commentsService.createComments(postId, userId, comment);

        return comment + " added for, POST: " + postRepo.findById(postId).get().getPostText();
    }

    @PostMapping("/likeComment/{userId}/{commentId}")
    public String likeComments(@PathVariable Long commentId, @PathVariable Long userId){
        return commentsService.likeComments(userId, commentId);
    }


    @PostMapping("/unLikeComment/{userId}/{commentId}")
    public String unLikeComments(@PathVariable Long commentId, @PathVariable Long userId){
        return commentsService.unLikeComment(userId, commentId);
    }



}
