package com.example.IntelliBotBackend.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromptRequest {
    private ObjectId userId;
    private String inputText;
    private String category;
    private String subCategory;
    private String lang;
}

