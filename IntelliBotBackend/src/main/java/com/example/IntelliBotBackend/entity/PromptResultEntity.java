package com.example.IntelliBotBackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "prompt_result")
public class PromptResultEntity {
    @Id
    private ObjectId promptResponseId;
    private String prompt;
}
