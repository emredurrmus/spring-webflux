package com.edurmus.webflux.dto;

import com.edurmus.webflux.metadata.CourseMetadata;

public record CourseDto(String name, String description, Integer duration, String teacher, CourseMetadata courseMetaData) {
}
