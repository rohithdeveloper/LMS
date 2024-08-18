package com.example.lms.serviceimpl;

import java.util.Optional;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.dtos.IssueBookRequestDto;
import com.example.lms.dtos.IssueBookResponseDto;
import com.example.lms.enums.CardStatus;
import com.example.lms.enums.TransactionStatus;
import com.example.lms.exceptions.BookIssueException;
import com.example.lms.exceptions.BookNotFoundException;
import com.example.lms.exceptions.CardNotActivatedException;
import com.example.lms.exceptions.CardNotPresentException;
import com.example.lms.model.Book;
import com.example.lms.model.LibraryCard;
import com.example.lms.model.Transaction;
import com.example.lms.repository.BookRepository;
import com.example.lms.repository.CardRepository;
import com.example.lms.repository.TransactionRepository;
import com.example.lms.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private CardRepository cardRepo;

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private BookRepository bookRepo;

    @Override
    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto) {
        // Create a new Transaction entity
        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(UUID.randomUUID().toString()); // Set a unique transaction number
        transaction.setIsissuedOperation(true); // Indicate that this operation involves issuing a book

        // Retrieve and validate the LibraryCard using the provided card ID
        Optional<LibraryCard> libraryCardOptional = cardRepo.findById(issueBookRequestDto.getCardId());
        if (libraryCardOptional.isPresent()) {
            LibraryCard libraryCard = libraryCardOptional.get();

            // Retrieve and validate the Book using the provided book ID
            Optional<Book> bookOptional = bookRepo.findById(issueBookRequestDto.getBookId());
            if (bookOptional.isPresent()) {
                Book book = bookOptional.get();

                // Check if the LibraryCard is activated
                if (libraryCard.getStatus() == CardStatus.ACTIVATED) {

                    // Check if the Book is not already issued
                    if (!book.isIssued()) {

                        // Proceed with issuing the book
                        transaction.setTransactionStatus(TransactionStatus.SUCCESS); // Set transaction status to SUCCESS
                        transaction.setMessage("Transaction was successful"); // Set a success message
                        book.setIssued(true); // Mark the book as issued
                        book.setCard(libraryCard); // Associate the book with the library card
                        book.getTransaction().add(transaction); // Add the transaction to the book's list of transactions
                        libraryCard.getTransaction().add(transaction); // Add the transaction to the library card's list of transactions
                        libraryCard.getBookIssued().add(book); // Add the issued book to the library card's list of issued books

                        // Save the updated entities to the database
                        cardRepo.save(libraryCard);
                        bookRepo.save(book);
                        transactionRepo.save(transaction);

                        // Prepare and return the response DTO
                        IssueBookResponseDto issueBookResponseDto = new IssueBookResponseDto();
                        issueBookResponseDto.setTransactionId(transaction.getTransactionNumber()); // Set transaction number
                        issueBookResponseDto.setTransactionStatus(TransactionStatus.SUCCESS); // Set transaction status
                        issueBookResponseDto.setBookName(book.getTitle()); // Set book title for response

                        return issueBookResponseDto;

                    } else {
                        // Handle the case where the book is already issued
                        transaction.setTransactionStatus(TransactionStatus.FAILED); // Set transaction status to FAILED
                        transaction.setMessage("Sorry, the book is already issued"); // Set error message
                        transactionRepo.save(transaction); // Save the failed transaction
                        throw new BookIssueException("Sorry, the book is already issued"); // Throw custom exception
                    }

                } else {
                    // Handle the case where the library card is not activated
                    transaction.setTransactionStatus(TransactionStatus.FAILED); // Set transaction status to FAILED
                    transaction.setMessage("Your card is not activated"); // Set error message
                    transactionRepo.save(transaction); // Save the failed transaction
                    throw new CardNotActivatedException("Your card is not activated"); // Throw custom exception
                }

            } else {
                // Handle the case where the book is not found
                transaction.setTransactionStatus(TransactionStatus.FAILED); // Set transaction status to FAILED
                transaction.setMessage("Book not found"); // Set error message
                transactionRepo.save(transaction); // Save the failed transaction
                throw new BookNotFoundException("Book not found"); // Throw custom exception
            }

        } else {
            // Handle the case where the library card is not found
            transaction.setTransactionStatus(TransactionStatus.FAILED); // Set transaction status to FAILED
            transaction.setMessage("Card not found"); // Set error message
            transactionRepo.save(transaction); // Save the failed transaction
            throw new CardNotPresentException("Card not present"); // Throw custom exception
        }
    }
}
