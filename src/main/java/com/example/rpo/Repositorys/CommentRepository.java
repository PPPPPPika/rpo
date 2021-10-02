package com.example.rpo.Repositorys;

import com.example.rpo.Models.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

}
