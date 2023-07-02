package com.example.IntelliBotBackend.repository;

import com.example.IntelliBotBackend.entity.RegisteredUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<RegisteredUser,Integer> {
    Optional<RegisteredUser> findByEmail(String email);
}
