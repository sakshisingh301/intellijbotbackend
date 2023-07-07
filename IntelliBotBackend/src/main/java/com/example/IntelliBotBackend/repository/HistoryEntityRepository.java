package com.example.IntelliBotBackend.repository;

import com.example.IntelliBotBackend.entity.HistoryEntity;
import com.example.IntelliBotBackend.entity.UserHistory;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HistoryEntityRepository extends MongoRepository<HistoryEntity, ObjectId> {
    List<HistoryEntity> findByUserId(ObjectId userId);
}
