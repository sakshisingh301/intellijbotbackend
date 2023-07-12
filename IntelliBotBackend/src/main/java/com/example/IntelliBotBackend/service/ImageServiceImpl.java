package com.example.IntelliBotBackend.service;

import com.example.IntelliBotBackend.client.OpenAIAPIimageClient;
import com.example.IntelliBotBackend.entity.ImageEntity;
import com.example.IntelliBotBackend.repository.ImageEntityRepository;
import com.example.IntelliBotBackend.request.ImageRequest;
import com.example.IntelliBotBackend.response.ImageResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private OpenAIAPIimageClient openAIAPIimageClient;

    @Autowired
    private ImageEntityRepository imageEntityRepository;

    @Override
    public ResponseEntity<?> getImageLink(ImageRequest imageRequest) throws Exception {
        ImageResponse imageResponse = new ImageResponse();
        ImageEntity imageEntity = new ImageEntity();
        String url = openAIAPIimageClient.getImageFromPrompt(imageRequest.getPrompt());
        imageEntity.setImageId(new ObjectId());
        imageEntity.setImageUrl(url);
        imageEntity.setUserId(imageRequest.getUserId());
        imageEntity.setPromptSearched(imageRequest.getPrompt());
        imageResponse.setImageId(imageEntity.getImageId());
        imageResponse.setImageUrl(url);
        imageResponse.setUserId(imageRequest.getUserId());
        imageResponse.setPromptSearched(imageRequest.getPrompt());
        imageEntityRepository.save(imageEntity);
        return ResponseEntity.ok().body(imageResponse);
    }
}
