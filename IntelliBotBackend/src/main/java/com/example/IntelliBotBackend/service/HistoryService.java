package com.example.IntelliBotBackend.service;

import com.example.IntelliBotBackend.entity.HistoryEntity;
import com.example.IntelliBotBackend.entity.PromptsEntity;
import com.example.IntelliBotBackend.request.HistoryRequest;
import com.example.IntelliBotBackend.request.PromptRequest;
import com.example.IntelliBotBackend.response.AllHistoryResponse;
import com.example.IntelliBotBackend.response.PromptResponse;
import org.bson.types.ObjectId;

public interface HistoryService {

    AllHistoryResponse getHistoryOfTheUser(HistoryRequest historyEntity);

    HistoryEntity saveHistoryData(PromptsEntity promptEntity, PromptResponse promptResponse, ObjectId userId, String inputText);
}
