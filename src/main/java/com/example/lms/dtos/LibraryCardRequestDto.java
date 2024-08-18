package com.example.lms.dtos;

import java.util.Date;
import java.util.List;

import com.example.lms.enums.CardStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryCardRequestDto {
	private int cardno;
    private CardStatus status;
    private Date creationDate;
    private Date updationDate;
    private int studentId; // Assuming you have student ID instead of full Student object in DTO
    private List<Integer> transactionIds; // Assuming you want to pass transaction IDs
    private List<Integer> bookIssuedIds;
}
