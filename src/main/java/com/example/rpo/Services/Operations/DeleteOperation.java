package com.example.rpo.Services.Operations;

import com.example.rpo.Models.Comment;
import com.example.rpo.Repositorys.CommentRepository;
import com.example.rpo.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("deleteOperation")
public class DeleteOperation implements OperationsInterface {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Autowired
    public DeleteOperation(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    private Comment searchComment(Long id){
        return commentRepository.findById(id).get();
    }

    @Override
    public void deleteComment(Long id, String currentUser) {
        try {
            Comment comment = searchComment(id);
            if (comment.getAuthor().getName().equals(currentUser)){
                comment.setAuthor(null);
                commentRepository.delete(comment);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
