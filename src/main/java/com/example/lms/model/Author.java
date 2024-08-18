package com.example.lms.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Author")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
 public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Author_Id")
	private int id;

	@Column(name = "Author_Name")
	private String name;

	@Column(name = "Author_Age", nullable = false) // Ensure `nullable = false` is set
	private int age;

	@Column(name = "Author_ph")
	private String mobno;

	@Column(name = "Author_Email")
	private String email;

	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	List<Book> book = new ArrayList<>();

	
	
	

}
