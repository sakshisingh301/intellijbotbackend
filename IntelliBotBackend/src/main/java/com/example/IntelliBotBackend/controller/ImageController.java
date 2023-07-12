package com.example.IntelliBotBackend.controller;

import com.example.IntelliBotBackend.request.ImageRequest;
import com.example.IntelliBotBackend.service.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageServiceImpl imageService;


    @PostMapping("/getImageFromText")
    public ResponseEntity<?> getImage(@RequestBody ImageRequest imageRequest) throws Exception {
        return imageService.getImageLink(imageRequest);
    }
}
