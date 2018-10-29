package com.janani.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.janani.spring.dao.CourseDAO;
import com.janani.spring.model.Course;

@CrossOrigin
@RestController
public class CourseRestController {

	@Autowired
	private CourseDAO courseDAO;

	@GetMapping("/course")
	public List<Course> getCustomers() {

		return courseDAO.findAll();

	}

	@GetMapping("/course/{code}")
	public ResponseEntity<?> getCourse(@PathVariable("code") String code) {

		Course course = courseDAO.findByCode(code);
		if (course == null) {
			return new ResponseEntity<>("No course found for code " + code, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(course, HttpStatus.OK);

	}

	@PostMapping(value = "/course")
	public ResponseEntity<?> createCourse(@RequestBody Course course) {
		// System.out.println(course);

		courseDAO.create(course);
		return new ResponseEntity<>(course, HttpStatus.OK);

	}

	@DeleteMapping("/course/{code}")
	public ResponseEntity<?> deleteCourse(@PathVariable String code) {

		courseDAO.delete(code);
		return new ResponseEntity<>(code, HttpStatus.OK);

	}

	@PutMapping(value = "/course/{code}")
	public ResponseEntity<?> updateCustomer(@PathVariable String code, @RequestBody Course course) {
		// System.out.println("Updating");
		courseDAO.update(course);
		return new ResponseEntity<>(course, HttpStatus.OK);

	}

}
