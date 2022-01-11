package com.example.blog.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Comments {
    @Id
    private Long commentsId;
}
