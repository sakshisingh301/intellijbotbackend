package com.example.IntelliBotBackend.utilities;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class Utils {

    public void logInfo(Logger logger, String message) {
        if (logger.isInfoEnabled()) {
            logger.info(message);
        }
    }
}
