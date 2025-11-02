package com.learning.spring_web_jpa.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import lombok.SneakyThrows;

public class GenericUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private GenericUtils(){}

    @SneakyThrows
    public static <T> T applyMergePatch(JsonMergePatch patch, T target, Class<T> type) {
        try{
            var patchedNode = patch.apply(objectMapper.convertValue(target, JsonNode.class));
            return objectMapper.treeToValue(patchedNode, type);
        } catch (UnrecognizedPropertyException unrecognizedPropertyException){
            throw new IllegalArgumentException(unrecognizedPropertyException.getMessage());
        }
    }

}
