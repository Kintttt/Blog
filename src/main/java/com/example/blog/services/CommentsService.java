package com.example.blog.services;


import com.example.blog.model.CommentsModel;
import com.example.blog.model.Person;
import com.example.blog.model.PostModel;
import com.example.blog.repository.CommentsRepository;
import com.example.blog.repository.PersonRepository;
import com.example.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsService {

    private final CommentsRepository commentsRepo;
    private final PostRepository postRepo;
    private final PersonRepository personRepo;

    @Autowired
    public CommentsService(CommentsRepository commentsRepo, PostRepository postRepo, PersonRepository personRepo) {
        this.commentsRepo = commentsRepo;
        this.postRepo = postRepo;
        this.personRepo = personRepo;
    }


    public String createComments(Long postId, Long userId, String text){
        var person = personRepo.findById(userId).get();
        PostModel post = postRepo.findById(postId).get();

        CommentsModel comment = new CommentsModel(text);

        comment.setCommenter(person);
        comment.setPost(post);
        commentsRepo.save(comment);

        return text;
    }


    public String likeComments(Long userId, Long commentId){
        Person user = personRepo.findById(userId).get();
        CommentsModel comment = commentsRepo.findById(commentId).get();

        comment.getCommentLikes().add(user);
        commentsRepo.save(comment);

        return "You have now liked this comment. Number of likes is: " + comment.getCommentLikes().size();
    }


    public String unLikeComment(Long userId, Long commentId){

        var user = personRepo.findById(userId).get();
        CommentsModel comment = commentsRepo.findById(commentId).get();

        if(comment.getCommentLikes().contains(user)){
            comment.getCommentLikes().remove(user);

            return "Comment unliked. Number of likes is now: " + comment.getCommentLikes().size();
        }

        return "You never liked this comment.";
    }
}
