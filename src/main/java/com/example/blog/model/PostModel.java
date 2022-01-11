package com.example.blog.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
public class PostModel {

    @Id
    @SequenceGenerator(name = "post_sequence", sequenceName = "post_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_sequence")
    private Long postId;

    @Column(nullable = false, columnDefinition = "text")
    private String postText;

    @Column(nullable = false)
    private int noOfComments;

    @OneToOne
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Person person;

    @OneToMany
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Likes> likes;

    @OneToMany
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Comments> comments;


}
