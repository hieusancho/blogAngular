package com.example.blogAngular.service;

import com.example.blogAngular.entity.Comment;
import com.example.blogAngular.entity.Post;
import com.example.blogAngular.repository.CommentRepository;
import com.example.blogAngular.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl  implements  CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;


    public Comment createComment(Long postId, String postedBy, String content){
        Optional<Post> optionalPost = postRepository.findById(postId);
        if(optionalPost.isPresent()) {
            Comment comment = new Comment();

            comment.setPost(optionalPost.get());
            comment.setContent(content);
            comment.setPostedBy(postedBy);
            comment.setCreatedAt(new Date());

            return commentRepository.save(comment);
        }
        throw new EntityNotFoundException("Post not found");
    }

    public List<Comment> getCommentsByPostId(Long postId){
        return commentRepository.findByPostId(postId);
    }

//    public void deleteComment(Long postId, Long commentId) {
//        Optional<Post> optionalPost = postRepository.findById(postId);
//        if (optionalPost.isPresent()) {
//            Optional<Comment> optionalComment = commentRepository.findById(commentId);
//            if (optionalComment.isPresent() && optionalComment.get().getPost().getId().equals(postId)) {
//                commentRepository.deleteById(commentId);
//                return;
//            }
//        }
//        throw new EntityNotFoundException("Comment not found or does not belong to the specified post");
//    }
}
