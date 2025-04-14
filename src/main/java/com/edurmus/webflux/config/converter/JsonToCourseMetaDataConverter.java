package com.edurmus.webflux.config.converter;

import com.edurmus.webflux.metadata.CourseMetadata;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.postgresql.codec.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.io.IOException;


@ReadingConverter
@Slf4j
public class JsonToCourseMetaDataConverter implements Converter<Json, CourseMetadata> {

    private final ObjectMapper objectMapper;

    public JsonToCourseMetaDataConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    public CourseMetadata convert(Json source) {
        try {
            return objectMapper.readValue(source.asString(), CourseMetadata.class);
        } catch (IOException e) {
            log.error("Error converting JSON to CourseMetadata", e);
            throw new RuntimeException(e);
        }
    }
}
