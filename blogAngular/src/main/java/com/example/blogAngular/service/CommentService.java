package com.example.blogAngular.service;

import com.example.blogAngular.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment createComment(Long postId, String postedBy, String content);

    List<Comment> getCommentsByPostId(Long postId);
    //void deleteComment(Long postId, Long commentId);
}
