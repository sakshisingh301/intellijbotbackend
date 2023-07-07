package com.example.IntelliBotBackend.service;

import com.example.IntelliBotBackend.client.OpenAIAPIClient;
import com.example.IntelliBotBackend.entity.PromptsEntity;
import com.example.IntelliBotBackend.repository.HistoryEntityRepository;
import com.example.IntelliBotBackend.repository.PromptResultRepository;
import com.example.IntelliBotBackend.repository.PromptsRepository;
import com.example.IntelliBotBackend.request.PromptRequest;
import com.example.IntelliBotBackend.request.PromptSearchRequest;
import com.example.IntelliBotBackend.response.PromptResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.example.IntelliBotBackend.constants.Constants.*;

@Service
public class PromptsServiceImpl implements PromptsService {

    @Autowired
    private OpenAIAPIClient openAIAPIClient;

    @Autowired
    private PromptsRepository promptsRepository;

    @Autowired
    private PromptResultRepository promptResultRepository;

    @Autowired
    private HistoryService historyService;


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
    public PromptsEntity generatePromptByGptAndSave(PromptsEntity promptsEntity, String tags,PromptRequest promptRequest) throws Exception {
        String promptConst = String.format(TAG_FOR_PROMPT_GENERATION, promptsEntity.getSubCategory());
        String generatedPromptGPT = openAIAPIClient.getPromptOrTag(promptConst, promptsEntity.getPrompt().trim());
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String formattedDateTime = now.format(formatter);
        promptsEntity.setTags(tags.split(","));
        promptsEntity.setPrompt(generatedPromptGPT);
        promptsEntity.setUseCase(promptRequest.getInputText());
        promptsEntity.setPromptId(new ObjectId());
        promptsEntity.setUserId(promptRequest.getUserId());
        promptsEntity.setAddedDate(new Date());
       PromptsEntity result= promptsRepository.save(promptsEntity);
        historyService.saveHistoryData(result, null, result.getUserId());
        return result;
    }

    @Override
    public PromptResponse getPromptResult(PromptSearchRequest promptSearchRequest) throws Exception {
        String promptConst = String.format(TAG_FOR_PROMPTGEN, promptSearchRequest.getSubCategory());
        String generatedPromptGPT = openAIAPIClient.getPromptOrTag(promptConst, promptSearchRequest.getPrompts());
        PromptResponse promptResponse = new PromptResponse();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        promptResponse.setUseCase(promptSearchRequest.getPrompts());
        promptResponse.setPrompt(generatedPromptGPT);
        promptResponse.setPromptResponseId(new ObjectId());
        promptResponse.setUserId( promptSearchRequest.getUserId());
        promptResponse.setCategory(promptSearchRequest.getCategory());
        promptResponse.setSubCategory(promptSearchRequest.getSubCategory());
        promptResponse.setAddedDate(new Date());
        PromptResponse response = promptResultRepository.save(promptResponse);
        historyService.saveHistoryData(null, response, response.getUserId());
        return response;


    }
}
