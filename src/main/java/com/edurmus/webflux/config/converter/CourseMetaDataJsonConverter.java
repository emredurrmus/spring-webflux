package com.edurmus.webflux.config.converter;

import com.edurmus.webflux.metadata.CourseMetadata;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.postgresql.codec.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;


@WritingConverter
@Slf4j
public class CourseMetaDataJsonConverter implements Converter<CourseMetadata, Json> {

    private final ObjectMapper objectMapper;

    public CourseMetaDataJsonConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    public Json convert(CourseMetadata source) {
        try {
            return Json.of(objectMapper.writeValueAsBytes(source));
        } catch (JsonProcessingException e) {
            log.error("Error converting CourseMetadata to JSON", e);
            throw new RuntimeException(e);
        }
    }
}
