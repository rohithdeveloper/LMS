package com.example.lms.service;

import com.example.lms.dtos.StudentRequestDto;
import com.example.lms.dtos.StudentResponseDto;

public interface StudentService {

	StudentResponseDto addStudent(StudentRequestDto studentRequestDto);

	StudentResponseDto updateEmail(StudentRequestDto studentUpdateEmailRequestDto);
//	StudentResponseDto updateEmailByName(String name, String newEmail);

}
