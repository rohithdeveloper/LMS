
package com.example.lms.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.dtos.AuthorRequestDto;
import com.example.lms.dtos.AuthorResponseDto;
import com.example.lms.dtos.BookRequestDto;
import com.example.lms.model.Author;
import com.example.lms.model.Book;
import com.example.lms.repository.AuthorRepository;
import com.example.lms.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepo;

	@Autowired
	private ModelMapper modelMapper;

//	@Override
//	public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto) {
//		// Create Author entity from DTO
//		Author author = new Author();
//		author.setId(authorRequestDto.getId());
//		author.setName(authorRequestDto.getName());
//		author.setAge(authorRequestDto.getAge());
//		author.setMobno(authorRequestDto.getMobno());
//		author.setEmail(authorRequestDto.getEmail());
//
//		// Handle books
//		List<BookRequestDto> bookRequestDtos = authorRequestDto.getBooks();
//		if (bookRequestDtos != null && !bookRequestDtos.isEmpty()) {
//			List<Book> books = bookRequestDtos.stream().map(bookRequestDto -> {
//				Book book = new Book();
//				book.setId(bookRequestDto.getId());
//				book.setTitle(bookRequestDto.getTitle());
//				book.setPrice(bookRequestDto.getPrice());
//				book.setGenre(bookRequestDto.getGenre());
//				book.setAuthor(author); // Set the author for the book
//				return book;
//			}).collect(Collectors.toList());
//			author.setBook(books); // Associate books with the author
//		}
//
//		// Save the author (which includes saving the books due to CascadeType.ALL)
//		Author savedAuthor = authorRepo.save(author);
//
//		// Map saved Author entity to AuthorResponseDto
//		AuthorResponseDto authorResponseDto = modelMapper.map(savedAuthor, AuthorResponseDto.class);
//
//		// Convert list of Book entities to list of BookRequestDto using ModelMapper
//		List<BookRequestDto> bookResponseDtos = savedAuthor.getBook().stream()
//				.map(book -> modelMapper.map(book, BookRequestDto.class)).collect(Collectors.toList());
//
//		authorResponseDto.setBooks(bookResponseDtos);
//
//		return authorResponseDto;
//	}
//
//	@Override
//	public List<AuthorResponseDto> getAuthor() {
//		// Retrieve all Author entities from the repository
//		List<Author> authors = authorRepo.findAll();
//
//		// Convert the list of Author entities to a list of AuthorResponseDto using
//		// ModelMapper
//		return authors.stream().map(author -> {
//			// Map Author to AuthorResponseDto
//			AuthorResponseDto authorResponseDto = modelMapper.map(author, AuthorResponseDto.class);
//
//			// Convert list of Book entities to list of BookRequestDto using ModelMapper
//			List<BookRequestDto> bookDtos = author.getBook().stream()
//					.map(book -> modelMapper.map(book, BookRequestDto.class)).collect(Collectors.toList());
//
//			authorResponseDto.setBooks(bookDtos); // Set the list of books in the AuthorResponseDto
//			return authorResponseDto;
//		}).collect(Collectors.toList());
//	}
	@Override
	public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto) {
		// Create Author entity from DTO
		Author author = new Author();
		author.setId(authorRequestDto.getId());
		author.setName(authorRequestDto.getName());
		author.setAge(authorRequestDto.getAge());
		author.setMobno(authorRequestDto.getMobno());
		author.setEmail(authorRequestDto.getEmail());

		
		// Save the author (which includes saving the books due to CascadeType.ALL)
		Author savedAuthor = authorRepo.save(author);

		// Map saved Author entity to AuthorResponseDto
		AuthorResponseDto authorResponseDto = modelMapper.map(savedAuthor, AuthorResponseDto.class);

		return authorResponseDto;
	}

	@Override
	public List<AuthorResponseDto> getAuthor() {
	    // Retrieve all Author entities from the repository
	    List<Author> authors = authorRepo.findAll();

	    // Convert each Author entity to AuthorResponseDto
	    List<AuthorResponseDto> authorDtos = authors.stream()
	        .map(author -> modelMapper.map(author, AuthorResponseDto.class))
	        .collect(Collectors.toList());

	    return authorDtos;
	}
	
}
