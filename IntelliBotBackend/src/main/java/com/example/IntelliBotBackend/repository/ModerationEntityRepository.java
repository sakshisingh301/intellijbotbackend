package com.example.IntelliBotBackend.repository;

import com.example.IntelliBotBackend.entity.HistoryEntity;
import com.example.IntelliBotBackend.entity.ModerationEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModerationEntityRepository extends MongoRepository<ModerationEntity, ObjectId> {
}
