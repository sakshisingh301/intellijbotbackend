package com.example.IntelliBotBackend.service;

import com.example.IntelliBotBackend.entity.PromptsEntity;
import com.example.IntelliBotBackend.request.PromptRequest;
import com.example.IntelliBotBackend.request.PromptSearchRequest;
import com.example.IntelliBotBackend.response.PromptResponse;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface PromptsService {
    String generatePrompts(PromptsEntity promptsEntity) throws Exception;

    PromptsEntity getPromptsEntityByPromptReq(PromptRequest userPromptRequest);

    PromptsEntity generatePromptByGptAndSave(PromptsEntity promptsEntity, String tags,PromptRequest promptRequest) throws Exception;

    PromptResponse getPromptResult(PromptSearchRequest promptSearchRequest) throws Exception;
}
