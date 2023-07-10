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
@Document(collection = "moderation")
public class ModerationEntity {
    @Id
    private ObjectId moderationId;
    private String userInput;
    private boolean isRestricted;

}
