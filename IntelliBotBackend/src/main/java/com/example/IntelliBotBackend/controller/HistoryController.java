package com.example.IntelliBotBackend.controller;

import com.example.IntelliBotBackend.request.HistoryRequest;
import com.example.IntelliBotBackend.response.AllHistoryResponse;
import com.example.IntelliBotBackend.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @PostMapping("/getByUserId")
    public ResponseEntity<?> addPrompts(@RequestBody HistoryRequest historyRequest){
       // HistoryResponse historyResponse=
        AllHistoryResponse historyResponse=historyService.getHistoryOfTheUser(historyRequest);
        return ResponseEntity.ok().body(historyResponse);

    }
}
