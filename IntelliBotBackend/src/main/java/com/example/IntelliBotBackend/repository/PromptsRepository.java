package com.example.IntelliBotBackend.repository;


import com.example.IntelliBotBackend.entity.PromptsEntity;
import org.bson.types.ObjectId;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PromptsRepository extends MongoRepository<PromptsEntity, ObjectId> {
    List<PromptsEntity> findByUserId(ObjectId userId);
}
