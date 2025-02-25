package com.example.blogAngular.repository;

import com.example.blogAngular.entity.Comment;
import com.example.blogAngular.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);

}
