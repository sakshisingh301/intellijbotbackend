package com.example.IntelliBotBackend.controller;

import com.example.IntelliBotBackend.client.OpenApiModerationAPIClient;
import com.example.IntelliBotBackend.entity.PromptsEntity;
import com.example.IntelliBotBackend.repository.HistoryEntityRepository;
import com.example.IntelliBotBackend.request.PromptRequest;
import com.example.IntelliBotBackend.request.PromptSearchRequest;
import com.example.IntelliBotBackend.response.PromptResponse;
import com.example.IntelliBotBackend.service.HistoryService;
import com.example.IntelliBotBackend.service.HistoryServiceImpl;
import com.example.IntelliBotBackend.service.PromptsService;
import com.example.IntelliBotBackend.service.PromptsServiceImpl;
import com.example.IntelliBotBackend.utilities.Utils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static com.example.IntelliBotBackend.constants.Constants.METHOD_CALLED;

@RestController
@CrossOrigin
@RequestMapping("/prompt")
public class PromptsController {

    @Autowired
    private Utils utils;
    @Autowired
    private OpenApiModerationAPIClient openApiModerationAPIClient;

    @Autowired
    private PromptsService promptsService;

    @Autowired
    private HistoryService historyService;
    private static final Logger logger = LoggerFactory.getLogger(PromptsController.class);

    @PostMapping("/generatePrompts")
    public ResponseEntity<?> generatePrompts(@RequestBody PromptRequest promptRequest) throws Exception {
        //generate prompts
        if(promptRequest.getIsSafeMode())
        {
            if(openApiModerationAPIClient.IsInputTextVoilated(promptRequest.getInputText()))
            {
                historyService.saveHistoryData(null, null, promptRequest.getUserId(), promptRequest.getInputText());
                return ResponseEntity.ok().body("Content is not valid !!");
            }
        }
        PromptsEntity promptsEntity = promptsService.getPromptsEntityByPromptReq(promptRequest);
        String tags = promptsService.generatePrompts(promptsEntity);
        PromptsEntity generatePrompts=promptsService.generatePromptByGptAndSave(promptsEntity, tags,promptRequest);
        return ResponseEntity.ok().body(generatePrompts);
    }

    @PostMapping("/searchPrompts")
    public ResponseEntity<?> searchPrompts(@RequestBody PromptSearchRequest promptSearchRequest) throws Exception {

        if(promptSearchRequest.getIsSafeMode())
        {
            if(openApiModerationAPIClient.IsInputTextVoilated(promptSearchRequest.getPrompts()))
            {
                return ResponseEntity.ok().body("Content is not valid !!");
            }
        }
        //search for the prompt
        if(!promptSearchRequest.getPrompts().isBlank())
        {
            PromptResponse promptResponse=promptsService.getPromptResult(promptSearchRequest);
            return ResponseEntity.ok().body(promptResponse);
        }
        else {
            throw new Exception("please provide prompt");
        }
    }
}
