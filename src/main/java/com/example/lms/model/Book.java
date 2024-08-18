package com.example.lms.model;

import java.util.ArrayList;
import java.util.List;

import com.example.lms.enums.Genre;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Book")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Book_Id")
	private int id;

	@Column(name = "Book_Title")
	private String title;

	@Column(name = "Book_Price")
	private int price;

	@Enumerated(EnumType.STRING)
	private Genre genre;
	 @Column(name = "Is_Issued", nullable = false)
	private boolean isIssued;

	@ManyToOne
	@JoinColumn(name = "author_id")
	Author author;

	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	List<Transaction> transaction = new ArrayList<>();

	@ManyToOne
	@JoinColumn
	LibraryCard card;
}
