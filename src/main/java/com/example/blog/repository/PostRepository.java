package com.example.blog.repository;


import com.example.blog.model.Person;
import com.example.blog.model.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostModel, Long> {
    List<PostModel> findAllByPerson(Person person);

}
