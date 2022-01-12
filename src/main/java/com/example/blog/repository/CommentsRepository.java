package com.example.blog.repository;

import com.example.blog.model.CommentsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<CommentsModel, Long> {
}
