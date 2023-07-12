package com.example.IntelliBotBackend.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageRequest {

    private ObjectId userId;
    private String prompt;


}
