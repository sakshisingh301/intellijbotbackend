package com.example.IntelliBotBackend.repository;

import com.example.IntelliBotBackend.entity.RegisteredUser;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<RegisteredUser, ObjectId> {
    Optional<RegisteredUser> findByEmail(String email);
}
