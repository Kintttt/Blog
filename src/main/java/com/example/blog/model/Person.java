package com.example.blog.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany
    @JoinColumn
    @JsonBackReference(value = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Person> friends;

    @OneToMany
    @JoinColumn
    @JsonBackReference(value = "person")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<PostModel> favorites;

    public Person(Long userId) {
        this.userId = userId;
    }

    public Person(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Person(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
