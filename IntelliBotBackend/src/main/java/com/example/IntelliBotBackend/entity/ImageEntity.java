package com.example.IntelliBotBackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "image")
public class ImageEntity {
    @Id
    private ObjectId imageId;
    private String imageUrl;
    private ObjectId userId;
    private String promptSearched;
}
