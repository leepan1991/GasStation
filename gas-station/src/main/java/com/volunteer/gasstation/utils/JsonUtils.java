package com.volunteer.gasstation.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * @author: huoyao
 */
public class JsonUtils {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    }

    /**
     * 反序列化
     *
     * @param jsonString
     * @param clazz
     * @param <T>
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static <T> T parseObject(String jsonString, Class<T> clazz) throws IOException {
        if (StringUtils.isBlank(jsonString)) {
            return null;
        }
        return objectMapper.readValue(jsonString, clazz);
    }

    public static <T> T parseObject(String jsonString, TypeReference<T> clazz) throws IOException {
        if (StringUtils.isBlank(jsonString)) {
            return null;
        }
        return (T) objectMapper.readValue(jsonString, clazz);
    }

    /**
     * 序列化
     *
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public static String toJSONString(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}
