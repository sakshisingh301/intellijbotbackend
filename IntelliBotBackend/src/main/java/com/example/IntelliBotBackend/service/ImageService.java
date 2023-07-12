package com.example.IntelliBotBackend.service;

import com.example.IntelliBotBackend.request.ImageRequest;
import org.springframework.http.ResponseEntity;

public interface ImageService {

    ResponseEntity<?> getImageLink(ImageRequest imageRequest) throws Exception;
}
