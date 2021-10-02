package com.example.rpo.Repositorys;

import com.example.rpo.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByName(String name);
    boolean existsUserByName(String name);

}
