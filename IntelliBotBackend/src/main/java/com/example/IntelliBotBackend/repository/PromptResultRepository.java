package com.example.IntelliBotBackend.repository;

import com.example.IntelliBotBackend.entity.PromptsEntity;
import com.example.IntelliBotBackend.response.PromptResponse;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface PromptResultRepository extends MongoRepository<PromptResponse, ObjectId> {
    List<PromptResponse> findByUserId(ObjectId userId);
    Optional<PromptResponse> findByPromptResponseId(ObjectId promptRespId);

}
