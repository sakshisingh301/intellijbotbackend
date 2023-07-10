package com.example.IntelliBotBackend.service;

import com.example.IntelliBotBackend.client.OpenApiModerationAPIClient;
import com.example.IntelliBotBackend.entity.ModerationEntity;
import com.example.IntelliBotBackend.repository.ModerationEntityRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class ModerationServiceImpl implements ModerationService {

    @Autowired
    private OpenApiModerationAPIClient openApiModerationAPIClient;

    @Autowired
    private ModerationEntityRepository moderationEntityRepository;

    @Override
    public boolean checkModeration(String inputText) throws IOException {
        boolean inValidText=openApiModerationAPIClient.IsInputTextVoilated(inputText);
        ModerationEntity moderationEntity=new ModerationEntity();
        moderationEntity.setModerationId(new ObjectId());
        moderationEntity.setRestricted(inValidText);
        moderationEntity.setUserInput(inputText);
        moderationEntityRepository.save(moderationEntity);
        return inValidText;
    }
}
