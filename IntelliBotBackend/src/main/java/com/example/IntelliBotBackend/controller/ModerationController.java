package com.example.IntelliBotBackend.controller;

import com.example.IntelliBotBackend.service.ModerationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/moderation")
public class ModerationController {

    @Autowired
    private ModerationServiceImpl moderationService;


    @PostMapping("/check-restrict")
    public boolean checkRestriction(@RequestBody String inputText) throws IOException {

        return moderationService.checkModeration(inputText);
    }
}
