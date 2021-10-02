package com.example.rpo.Services.Operations;

import com.example.rpo.Repositorys.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("deleteOperation")
public class DeleteOperation implements OperationsInterface {
    private CommentRepository commentRepository;

    @Autowired
    public DeleteOperation(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void deleteComment() {

    }
}
