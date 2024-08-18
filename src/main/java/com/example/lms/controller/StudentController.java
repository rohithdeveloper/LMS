package com.example.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.dtos.StudentRequestDto;
import com.example.lms.dtos.StudentResponseDto;
import com.example.lms.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService studentService;

	@PostMapping("/add")
	public StudentResponseDto addStudent(@RequestBody StudentRequestDto studentRequestDto) {

		return studentService.addStudent(studentRequestDto);
	}

	@PutMapping("/update_email")
	public String updateEmail(@RequestBody StudentRequestDto studentUpdateEmailRequestDto) {
		studentService.updateEmail(studentUpdateEmailRequestDto);
		return "Email updated successfully";
	}
}
