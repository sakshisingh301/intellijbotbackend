package com.example.IntelliBotBackend.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromptSearchRequest {

    private String category;
    private String subCategory;
    private ObjectId userId;
    private String prompts;
    private Boolean isPromptGeneratedAlready;
    private ObjectId genPromptId;
    private String lang;
    private Boolean isSafeMode;
}
