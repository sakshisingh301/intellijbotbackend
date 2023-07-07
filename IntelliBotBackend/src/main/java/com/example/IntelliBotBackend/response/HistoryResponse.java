package com.example.IntelliBotBackend.response;

import com.example.IntelliBotBackend.entity.PromptsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryResponse {

    private ObjectId historyId;
    private String useCase;
    private PromptsEntity generatePrompts;
    private PromptResponse searchedPrompts;



}