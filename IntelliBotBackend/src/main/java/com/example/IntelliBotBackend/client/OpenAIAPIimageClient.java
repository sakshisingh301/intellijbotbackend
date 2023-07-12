package com.example.IntelliBotBackend.client;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class OpenAIAPIimageClient {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Value("${chatgpt.image.endpoint}")
    private String endpoint;

    @Value("${chatgpt.api_key}")
    private String apiKey;

    public String getImageFromPrompt(String prompt) throws Exception {

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("prompt", prompt);

        // Set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        // Create the request entity with headers and body
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        // Create the RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Send the POST request
        ResponseEntity<String> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, requestEntity, String.class);

        // Get the response body
        String responseBody = responseEntity.getBody();

        // Process the response as needed
        System.out.println("Response Body: " + responseBody);
        String url=parseGptResponse(responseBody);
        return url;


    }

    private String parseGptResponse(String responseBody) throws IOException {
        try (JsonParser parser = mapper.getFactory().createParser(responseBody)) {
            JsonNode rootNode = parser.readValueAsTree();
            JsonNode choicesNode = rootNode.get("data").get(0);
            String url = choicesNode.get("url").asText();
            if (url.startsWith("\"") && url.endsWith("\"")) {
                url = url.substring(1, url.length() - 1);
            }
           return url;
        }

    }
}
