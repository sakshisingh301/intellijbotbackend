package com.example.IntelliBotBackend.service;

import java.io.IOException;

public interface ModerationService {

    boolean checkModeration(String inputText) throws IOException;
}
