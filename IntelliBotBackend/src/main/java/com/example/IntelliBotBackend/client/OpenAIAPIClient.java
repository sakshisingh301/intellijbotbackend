package com.example.IntelliBotBackend.client;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.connector.Request;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.http.MediaType.*;


@Service
public class OpenAIAPIClient {
    @Value("${chatgpt.endpoint}")
    private String endpoint;

    @Value("${chatgpt.api_key}")
    private String apiKey;

    @Value("${chatgpt.temperature}")
    private int temperature;

    @Value("${chatgpt.topP}")
    private int topP;

    @Value("${chatgpt.maxTokens}")
    private int max_token;

    @Value("${chatgpt.model}")
    private String model;
    private static final ObjectMapper mapper = new ObjectMapper();

    public String getPromptOrTag(String SystemMessage, String InputTextFromUser) throws Exception {

        JSONObject item = new JSONObject();
        item.put("model", model);
        item.put("temperature", temperature);
        item.put("max_tokens", max_token);
        item.put("top_p", topP);
        JSONObject systemMsg = new JSONObject();
        JSONArray messages = new JSONArray();
        systemMsg.put("role", "system");
        systemMsg.put("content", SystemMessage);
        messages.put(systemMsg);
        JSONObject userMsg = new JSONObject();
        userMsg.put("role", "user");
        userMsg.put("content", InputTextFromUser);
        messages.put(userMsg);
        item.put("messages", messages);
        String requestBody = item.toString();

        RequestEntity<String> requestEntity;
        requestEntity = RequestEntity
                .post(new URI(endpoint))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .body(requestBody);

        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Send the POST request
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        // Read the response body as a string
        String responseBody = responseEntity.getBody();
        String result = parseGptResponse(responseBody);

        // Handle the response as per your requirements
        return result;


    }

    private String parseGptResponse(String responseBody) throws Exception {
        if (responseBody == null || responseBody.trim().isBlank()) {
            throw new Exception("Exception in getting response");
        }
        try (JsonParser parser = mapper.getFactory().createParser(responseBody)) {
            JsonNode rootNode = parser.readValueAsTree();
            JsonNode choicesNode = rootNode.get("choices");

            if (!rootNode.has("choices")) {
                throw new Exception("Response is missing 'choices' field: " + responseBody);
            }

            if (choicesNode.size() < 1) {
                throw new Exception("Response is missing 'choices' array: " + responseBody);
            }

            String tagsOrPrompt = choicesNode.get(0).get("message").get("content").asText();

            if (tagsOrPrompt.startsWith("Prompt:")) {
                tagsOrPrompt = tagsOrPrompt.substring("Prompt:".length()).trim();
            }

            if (tagsOrPrompt.startsWith("\"") && tagsOrPrompt.endsWith("\"")) {
                tagsOrPrompt = tagsOrPrompt.substring(1, tagsOrPrompt.length() - 1);
            }

            if (Character.isLowerCase(tagsOrPrompt.charAt(0))) {
                tagsOrPrompt = Character.toUpperCase(tagsOrPrompt.charAt(0)) + tagsOrPrompt.substring(1);
            }

            return tagsOrPrompt;
        }
    }


}
