package com.example.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.dtos.BookRequestDto;
import com.example.lms.dtos.BookResponseDto;
import com.example.lms.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public BookResponseDto addBook(@RequestBody BookRequestDto  bookRequestDto){
        
         return  bookService.addBook(bookRequestDto);
    }
}
