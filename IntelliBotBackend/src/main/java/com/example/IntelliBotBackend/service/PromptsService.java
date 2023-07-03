package com.example.IntelliBotBackend.service;

import com.example.IntelliBotBackend.entity.PromptsEntity;
import com.example.IntelliBotBackend.request.PromptRequest;
import org.springframework.boot.configurationprocessor.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;

public interface PromptsService {
    String generatePrompts(PromptsEntity promptsEntity) throws Exception;
    PromptsEntity getPromptsEntityByPromptReq(PromptRequest userPromptRequest);
}
