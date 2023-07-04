package com.example.IntelliBotBackend.repository;


import com.example.IntelliBotBackend.entity.PromptsEntity;
import org.bson.types.ObjectId;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PromptsRepository extends MongoRepository<PromptsEntity, ObjectId> {
   // Optional<RegisteredUser> findByEmail(String email);
}
