package com.example.IntelliBotBackend.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class DateSerializer extends JsonSerializer<Date> {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final ZoneId UTC_ZONE_ID = ZoneId.of("UTC");
    private static final ZoneId IST_ZONE_ID = ZoneId.of("Asia/Kolkata");

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        LocalDateTime utcDateTime = LocalDateTime.ofInstant(date.toInstant(), UTC_ZONE_ID);
        LocalDateTime istDateTime = utcDateTime.atZone(UTC_ZONE_ID).withZoneSameInstant(IST_ZONE_ID).toLocalDateTime();

        String formattedDate = istDateTime.format(DATE_TIME_FORMATTER);
        jsonGenerator.writeString(formattedDate);
    }
}

