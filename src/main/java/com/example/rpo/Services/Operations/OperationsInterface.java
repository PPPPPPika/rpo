package com.example.rpo.Services.Operations;

import com.example.rpo.Models.Comment;

public interface OperationsInterface {
    default void addComment(Comment comment, String author){}
    default void deleteComment(){}
    default void editComment(Long idComment, String currentUser, String text){}
}
