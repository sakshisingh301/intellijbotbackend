package com.example.IntelliBotBackend.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromptSearchRequest {

    private String category;
    private String subCategory;
    private Object userId;
    private String prompts;
}
