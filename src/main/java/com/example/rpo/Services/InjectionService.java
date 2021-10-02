package com.example.rpo.Services;

import com.example.rpo.Models.Comment;
import com.example.rpo.Repositorys.CommentRepository;
import com.example.rpo.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("injectionService")
public class InjectionService {
    private CommentRepository commentRepository;
    private UserRepository userRepository;

    @Autowired
    public InjectionService(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public Comment extractComment(Long idComment){
        return commentRepository.findById(idComment).get();
    }


}
