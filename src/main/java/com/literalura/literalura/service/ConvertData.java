package com.literalura.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertData implements  IConvertData{
    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public <T> T getData(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error deserializing JSON to " + clase.getSimpleName() + ": " + e.getMessage(), e);
        }
    }
}

