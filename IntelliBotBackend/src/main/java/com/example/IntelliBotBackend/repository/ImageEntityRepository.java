package com.example.IntelliBotBackend.repository;

import com.example.IntelliBotBackend.entity.HistoryEntity;
import com.example.IntelliBotBackend.entity.ImageEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageEntityRepository extends MongoRepository<ImageEntity, ObjectId> {
}
