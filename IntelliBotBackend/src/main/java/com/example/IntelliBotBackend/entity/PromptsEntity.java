package com.example.IntelliBotBackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "prompts")
public class PromptsEntity {

    @Id
    private ObjectId promptId;
    @JsonIgnore
    private String useCase;
    private ObjectId userId;
    private String prompt;
    private String[] tags;
    private String category;
    private String subCategory;
    private Boolean fromGPT;
    private Date addedDate;
    private String lang;

}
