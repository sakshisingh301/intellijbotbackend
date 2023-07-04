package com.example.IntelliBotBackend.controller;

import com.example.IntelliBotBackend.entity.PromptsEntity;
import com.example.IntelliBotBackend.request.PromptRequest;
import com.example.IntelliBotBackend.service.PromptsService;
import com.example.IntelliBotBackend.service.PromptsServiceImpl;
import com.example.IntelliBotBackend.utilities.Utils;
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
    private PromptsService promptsService;
    private static final Logger logger = LoggerFactory.getLogger(PromptsController.class);

    @PostMapping("/generatePrompts")
    public ResponseEntity<?> generatePrompts(@RequestBody PromptRequest promptRequest) throws Exception {
        //generate prompts
        PromptsEntity promptsEntity = promptsService.getPromptsEntityByPromptReq(promptRequest);
        String tags = promptsService.generatePrompts(promptsEntity);
        List<PromptsEntity> generatePrompts=promptsService.generatePromptByGptAndSave(promptsEntity, tags);
        return ResponseEntity.ok().body(promptsEntity);


    }
}
