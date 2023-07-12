package com.example.IntelliBotBackend.service;

import com.example.IntelliBotBackend.entity.HistoryEntity;
import com.example.IntelliBotBackend.entity.PromptsEntity;
import com.example.IntelliBotBackend.repository.HistoryEntityRepository;
import com.example.IntelliBotBackend.repository.PromptHistoryByUserRepository;
import com.example.IntelliBotBackend.repository.PromptResultRepository;
import com.example.IntelliBotBackend.repository.PromptsRepository;
import com.example.IntelliBotBackend.request.HistoryRequest;
import com.example.IntelliBotBackend.response.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private PromptsRepository promptsRepository;
    @Autowired
    private HistoryEntityRepository historyEntityRepository;

    @Autowired
    private PromptResultRepository promptResultRepository;

    @Autowired
    private PromptHistoryByUserRepository promptHistoryByUserRepository;


    @Override
    public HistoryEntity saveHistoryData(PromptsEntity promptEntity, PromptResponse promptResponse, ObjectId userId, String inputText) {

        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setAddedDate(new Date());
        historyEntity.setUserId(userId);
        historyEntity.setInputText(inputText);
        historyEntity.setPromptId(Objects.nonNull(promptEntity) ? promptEntity.getPromptId() : null);
        historyEntity.setPromptResponseId(Objects.nonNull(promptResponse) ? promptResponse.getPromptResponseId() : null);
        return historyEntityRepository.save(historyEntity);
    }

    @Override
    public AllHistoryResponse getHistoryOfTheUser(HistoryRequest historyRequest) {
        List<HistoryEntity> historyEntity = historyEntityRepository.findByUserId(historyRequest.getUserId());

        List<HistoryResponse> historyResponseList = historyEntity.stream().map(history -> {

                    HistoryResponse historyResponse = new HistoryResponse();
                    PromptsEntity promptsEntity = null;
                    if (Objects.nonNull(history.getPromptId())) {
                        promptsEntity = promptsRepository.findById(history.getPromptId()).orElse(null);
                    }
                    PromptResponse promptResponse = Objects.nonNull(history.getPromptResponseId())
                            ? promptResultRepository.findById(history.getPromptResponseId()).orElse(null)
                            : null;
                    historyResponse.setGeneratePrompts(promptsEntity);
//                    historyResponse.setUseCase(Objects.nonNull(promptsEntity) ? promptsEntity.getUseCase() : promptResponse.getUseCase());
                    historyResponse.setUseCase(history.getInputText());
                    historyResponse.setSearchedPrompts(promptResponse);
                    historyResponse.setHistoryId(history.getHistoryId());
                    return historyResponse;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        AllHistoryResponse allHistoryResponse = new AllHistoryResponse();
        allHistoryResponse.setHistoryResponse(historyResponseList);
        allHistoryResponse.setUserId(historyRequest.getUserId());
        allHistoryResponse.setHistoryTotalCount(historyResponseList.size());

        return allHistoryResponse;

    }


}

