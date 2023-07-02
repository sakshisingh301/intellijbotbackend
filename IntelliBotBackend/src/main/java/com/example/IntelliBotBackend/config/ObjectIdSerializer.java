package com.example.IntelliBotBackend.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.bson.types.ObjectId;

import java.io.IOException;

public class ObjectIdSerializer extends StdSerializer<ObjectId> {
    private static final long serialVersionUID = 123456789L;

    public ObjectIdSerializer() {
        super(ObjectId.class);
    }

    @Override
    public void serialize(ObjectId objectId, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(objectId.toHexString());
    }
}
