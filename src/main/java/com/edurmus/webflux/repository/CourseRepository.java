package com.edurmus.webflux.repository;

import com.edurmus.webflux.model.Course;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface CourseRepository extends ReactiveCrudRepository<Course, UUID> {
}
