package com.example.IntelliBotBackend.repository;

import com.example.IntelliBotBackend.entity.UserHistory;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PromptHistoryByUserRepository extends MongoRepository<UserHistory, ObjectId> {
}
