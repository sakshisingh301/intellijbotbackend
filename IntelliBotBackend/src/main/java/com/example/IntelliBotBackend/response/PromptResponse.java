package com.example.IntelliBotBackend.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "promptResponse")
public class PromptResponse {

    @Id
    private ObjectId promptResponseId;
    private String useCase;
    private String prompt;
    private ObjectId userId;
    private String category;
    private String subCategory;
    private Date addedDate;
}
