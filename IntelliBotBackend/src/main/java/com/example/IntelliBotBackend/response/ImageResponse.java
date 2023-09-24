package com.example.IntelliBotBackend.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponse {

    private ObjectId imageId;
    private String imageUrl;
    private ObjectId userId;
    private String promptSearched;
    private String description;
}
