package com.example.rpo.Services;

import com.example.rpo.Models.Comment;
import com.example.rpo.Services.Operations.OperationsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("operationService")
public class OperationService implements OperationsInterface {
    private final OperationsInterface addOperation;
    private final OperationsInterface deleteOperation;
    private final OperationsInterface editOperation;

    @Autowired
    public OperationService(@Qualifier("addOperation") OperationsInterface addOperation,
                            @Qualifier("deleteOperation") OperationsInterface deleteOperation,
                            @Qualifier("editOperation") OperationsInterface editOperation) {
        this.addOperation = addOperation;
        this.deleteOperation = deleteOperation;
        this.editOperation = editOperation;
    }

    @Override
    public void addComment(Comment comment, String author) {
        addOperation.addComment(comment, author);
    }

    @Override
    public void deleteComment(Long id, String currentUser) {
        deleteOperation.deleteComment(id, currentUser);
    }

    @Override
    public void editComment(Long idComment, String currentUser, String text) {
        editOperation.editComment(idComment, currentUser, text);
    }
}
