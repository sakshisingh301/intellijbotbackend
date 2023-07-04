package com.example.IntelliBotBackend.service;

import com.example.IntelliBotBackend.client.OpenAIAPIClient;
import com.example.IntelliBotBackend.entity.PromptsEntity;
import com.example.IntelliBotBackend.request.PromptRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static com.example.IntelliBotBackend.constants.Constants.TAG_FOR_PROMPT_GENERATION;
import static com.example.IntelliBotBackend.constants.Constants.TAG_PROMPT_PREFIX;

@Service
public class PromptsServiceImpl implements PromptsService {

    @Autowired
    private OpenAIAPIClient openAIAPIClient;


    @Override
    public String generatePrompts(PromptsEntity promptsEntity) throws Exception {
        String tags = openAIAPIClient.getPromptOrTag(TAG_PROMPT_PREFIX, promptsEntity.getPrompt());
        return tags;
    }

    @Override
    public PromptsEntity getPromptsEntityByPromptReq(PromptRequest userPromptRequest) {
        PromptsEntity promptsEntity = new PromptsEntity();
        promptsEntity.setPrompt(userPromptRequest.getInputText());
        promptsEntity.setCategory(userPromptRequest.getCategory());
        promptsEntity.setSubCategory(userPromptRequest.getSubCategory());
        promptsEntity.setFromGPT(false);
        return promptsEntity;
    }

    @Override
    public List<PromptsEntity> generatePromptByGptAndSave(PromptsEntity promptsEntity, String tags) throws Exception {
        List<PromptsEntity> promptsEntities = new ArrayList<>();
        String promptConst = String.format(TAG_FOR_PROMPT_GENERATION, promptsEntity.getSubCategory());
        String generatedPromptGPT = openAIAPIClient.getPromptOrTag(promptConst, promptsEntity.getPrompt().trim());
        promptsEntity.setTags(tags.split(","));
        promptsEntity.setPrompt(generatedPromptGPT);
        promptsEntities.add(promptsEntity);

        return promptsEntities;


    }
}
