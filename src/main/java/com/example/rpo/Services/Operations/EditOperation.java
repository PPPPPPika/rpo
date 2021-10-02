package com.example.rpo.Services.Operations;

import com.example.rpo.Models.Comment;
import com.example.rpo.Repositorys.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("editOperation")
public class EditOperation implements OperationsInterface {
    private final CommentRepository commentRepository;

    @Autowired
    public EditOperation(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    private Comment searchComment(Long idComment){
        return commentRepository.findById(idComment).get();
    }
    
    @Override
    public void editComment(Long idComment, String currentUser, String text) {
        try {
            Comment comment = searchComment(idComment);
            if (!comment.getText().equals(text) && currentUser.equals(comment.getAuthor().getName())){
                comment.setText(text);
                commentRepository.save(comment);
            }
        } catch (Exception exception){
            exception.printStackTrace(System.out);
        }
    }
}
