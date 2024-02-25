package org.blockchain.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.blockchain.model.Blockchain;

public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Serialize Java object into JSON string
    public static String toJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    // Deserialize JSON string into Java object
    public static <T> T fromJson(String json, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(json, clazz);
    }

    // Specific method for deserializing the given JSON string to a Blockchain object
    public static Blockchain fromJsonToBlockchain(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, Blockchain.class);
    }
}
