package com.example.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.dtos.AuthorRequestDto;
import com.example.lms.dtos.AuthorResponseDto;
import com.example.lms.service.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public AuthorResponseDto addAuthor(@RequestBody AuthorRequestDto author){
//        authorService.addAuthor(author);
//        return "Author Added";
        return authorService.addAuthor(author);
    }
    @GetMapping("/authors")
    public List<AuthorResponseDto> getAuthor(){
        return authorService.getAuthor();
    }
}