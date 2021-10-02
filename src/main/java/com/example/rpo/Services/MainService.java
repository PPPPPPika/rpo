package com.example.rpo.Services;

import com.example.rpo.Models.Comment;
import com.example.rpo.Repositorys.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;

@Service("mainService")
public class MainService {
    private CommentRepository commentRepository;

    @Autowired
    public MainService(@Qualifier("commentRepository") CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public ArrayList<Comment> getAllComments(){
        return (ArrayList<Comment>) commentRepository.findAll();
    }
}
