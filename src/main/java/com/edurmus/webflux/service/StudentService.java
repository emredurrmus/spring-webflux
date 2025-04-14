package com.edurmus.webflux.service;


import com.edurmus.webflux.dto.CourseDto;
import com.edurmus.webflux.dto.StudentDto;
import com.edurmus.webflux.dto.StudentListDto;
import com.edurmus.webflux.model.Student;
import com.edurmus.webflux.repository.StudentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final CourseService courseService;

    public StudentService(StudentRepository studentRepository, CourseService courseService) {
        this.studentRepository = studentRepository;
        this.courseService = courseService;
    }


    public Flux<Student> findAll() {
        return studentRepository.findAll();
    }

    public Mono<StudentListDto> findAllWithCourses() {
        return studentRepository.findAll()
                .flatMap(student -> {
                        List<Mono< CourseDto>> courseMonoList = student.getCourses().stream()
                                .map(course -> courseService.findById(UUID.fromString(course)))
                                .collect(Collectors.toList());

                        return Flux.combineLatest(courseMonoList, objects -> {
                            List<CourseDto> courses = Arrays.stream(objects)
                                    .map(CourseDto.class::cast)
                                    .collect(Collectors.toList());
                            return new StudentDto(student.getName(), student.getEmail(), courses);
                        });
                })
                .collectList()
                .map(StudentListDto::new);
    }


}
