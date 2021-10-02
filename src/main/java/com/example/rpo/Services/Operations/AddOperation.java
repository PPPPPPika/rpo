package com.example.rpo.Services.Operations;

import com.example.rpo.Models.Comment;
import com.example.rpo.Models.User;
import com.example.rpo.Repositorys.CommentRepository;
import com.example.rpo.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("addOperation")
public class AddOperation implements OperationsInterface {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Autowired
    public AddOperation(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    private User searchAuthor(String author){
        return userRepository.findByName(author).get();
    }

    @Override
    public void addComment(Comment comment, String author) {
        try {
            comment.setAuthor(searchAuthor(author));
            commentRepository.save(comment);
        } catch (Exception exception){
            exception.printStackTrace(System.out);
        }
    }
}
