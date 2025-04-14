package com.edurmus.webflux;

import com.edurmus.webflux.metadata.SpringCourseMetadata;
import com.edurmus.webflux.model.Course;
import com.edurmus.webflux.model.Student;
import com.edurmus.webflux.repository.CourseRepository;
import com.edurmus.webflux.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@SpringBootApplication
public class WebfluxApplication implements CommandLineRunner {

	private final CourseRepository courseRepository;

	private final StudentRepository studentRepository;

	public WebfluxApplication(CourseRepository courseRepository, StudentRepository studentRepository) {
		this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

	public static void main(String[] args) {
		SpringApplication.run(WebfluxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Course course = Course.builder()
					.id(UUID.randomUUID())
				.name("Spring Boot")
				.description("Spring Boot Course")
				.duration(20)
				.teacher("Edurmus")
				.courseMetadata(
						SpringCourseMetadata.builder()
						.type("spring")
						.language("Java")
						.github("https://github.com/emredurrmus")
						.prerequisites(List.of("Java", "Spring"))
						.build())
				.isUpdated(false)
				.build();

		courseRepository.save(course)
				.doOnTerminate(() -> System.out.println("Course saved"))
				.subscribe();

		Student student = Student.builder()
				.id(UUID.randomUUID())
				.name("ED")
				.email("esd@gmail.com")
				.dateOfBirth(LocalDate.of(1998, 1, 1))
				.courses(Set.of(course.getId().toString()))
				.isUpdated(false)
				.build();

		studentRepository.save(student).doOnTerminate(() -> System.out.println("Student saved"))
				.subscribe();
	}
}
