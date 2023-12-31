package com.example.IntelliBotBackend.repository;

import com.example.IntelliBotBackend.entity.HistoryEntity;
import com.example.IntelliBotBackend.entity.UserHistory;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HistoryEntityRepository extends MongoRepository<HistoryEntity, ObjectId> {
    List<HistoryEntity> findByUserId(ObjectId userId);
}
