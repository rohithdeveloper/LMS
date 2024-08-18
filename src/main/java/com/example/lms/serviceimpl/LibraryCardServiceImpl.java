//package com.example.lms.serviceimpl;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.lms.dtos.LibraryCardRequestDto;
//import com.example.lms.dtos.LibraryCardResponseDto;
//import com.example.lms.exceptions.StudentNotfoundException;
//import com.example.lms.model.LibraryCard;
//import com.example.lms.model.Student;
//import com.example.lms.repository.CardRepository;
//import com.example.lms.repository.StudentRepository;
//import com.example.lms.service.LibraryCardService;
//
//@Service
//public class LibraryCardServiceImpl implements LibraryCardService{
//
//	@Autowired
//	private CardRepository cardRepo;
//	
//	@Autowired
//	private StudentRepository studentRepo;
//
//	@Autowired
//	private ModelMapper modelMapper;
//	
//	@Override
//	public LibraryCardResponseDto addCard(LibraryCardRequestDto cardRequestDto) {
//		// TODO Auto-generated method stub
//		Student student=studentRepo.findById(cardRequestDto.getStudentId())
//				.orElseThrow(() -> new StudentNotfoundException("Student not found"));
//		
//		LibraryCard card=new LibraryCard();
//		card.setCardno(cardRequestDto.getCardno());
//		card.setStudent(student);
//		LibraryCard savedCard=cardRepo.save(card);
//		
//		LibraryCardResponseDto libResponseDto = modelMapper.map(savedCard, LibraryCardResponseDto.class);
//		return libResponseDto;
//	}
//	
//
//}
