package com.example.IntelliBotBackend.service;

import com.example.IntelliBotBackend.client.OpenAIAPIClient;
import com.example.IntelliBotBackend.entity.PromptsEntity;
import com.example.IntelliBotBackend.repository.PromptResultRepository;
import com.example.IntelliBotBackend.repository.PromptsRepository;
import com.example.IntelliBotBackend.request.PromptRequest;
import com.example.IntelliBotBackend.request.PromptSearchRequest;
import com.example.IntelliBotBackend.response.PromptResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Locale;
import java.util.Optional;

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

        String promptConstLang = String.format(TAG_PROMPT_PREFIX, promptsEntity.getLang());
        String promptConstConversion = String.format(CONVERT_TAG_PROMPT_IN_LANG, promptsEntity.getPrompt(), promptsEntity.getLang());
        String tags = openAIAPIClient.getPromptOrTag(promptConstLang, promptConstConversion);
        return tags;
    }

    @Override
    public PromptsEntity getPromptsEntityByPromptReq(PromptRequest userPromptRequest) {
        PromptsEntity promptsEntity = new PromptsEntity();
        promptsEntity.setPrompt(userPromptRequest.getInputText());
        promptsEntity.setCategory(userPromptRequest.getCategory());
        promptsEntity.setSubCategory(userPromptRequest.getSubCategory());
        promptsEntity.setLang(userPromptRequest.getLang());
        promptsEntity.setFromGPT(false);
        return promptsEntity;
    }

    @Override
    public PromptsEntity generatePromptByGptAndSave(PromptsEntity promptsEntity, String tags, PromptRequest promptRequest) throws Exception {

        String promptConst = String.format(TAG_FOR_PROMPT_GENERATION, promptsEntity.getSubCategory(), promptsEntity.getLang().isBlank() ? "English" : promptsEntity.getLang().toLowerCase(Locale.getDefault()));
        String generatedPromptGPT = openAIAPIClient.getPromptOrTag(promptConst, promptsEntity.getPrompt().trim());
        promptsEntity.setTags(tags.split(","));
        promptsEntity.setPrompt(generatedPromptGPT);
        promptsEntity.setUseCase(promptRequest.getInputText());
        promptsEntity.setPromptId(new ObjectId());
        promptsEntity.setUserId(promptRequest.getUserId());
        promptsEntity.setAddedDate(new Date());
        PromptsEntity result = promptsRepository.save(promptsEntity);
        historyService.saveHistoryData(result, null, result.getUserId(), promptRequest.getInputText());
        return result;
    }

    @Override
    public PromptResponse getPromptResult(PromptSearchRequest promptSearchRequest) throws Exception {
        String promptConst = String.format(TAG_FOR_PROMPT_GEN, promptSearchRequest.getLang().isBlank() ? "English" : promptSearchRequest.getLang().toLowerCase(Locale.getDefault()));
        String generatedPromptGPT = openAIAPIClient.getPromptOrTag(promptConst, promptSearchRequest.getPrompts());
        PromptResponse promptResponse = new PromptResponse();
        promptResponse.setUseCase(promptSearchRequest.getPrompts());
        promptResponse.setPrompt(generatedPromptGPT);
        promptResponse.setPromptResponseId(new ObjectId());
        promptResponse.setUserId(promptSearchRequest.getUserId());
        promptResponse.setCategory(promptSearchRequest.getCategory());
        promptResponse.setSubCategory(promptSearchRequest.getSubCategory());
        promptResponse.setAddedDate(new Date());
        PromptResponse response = promptResultRepository.save(promptResponse);
        PromptsEntity historyData = null;

        if (promptSearchRequest.getIsPromptGeneratedAlready()) {
            Optional<PromptsEntity> prompt = promptsRepository.findById(promptSearchRequest.getGenPromptId());
            if (prompt.isPresent()) {
                historyData = prompt.get();
            }
        }
        historyService.saveHistoryData(historyData, response, response.getUserId(), promptSearchRequest.getPrompts());
        return response;

    }
}
