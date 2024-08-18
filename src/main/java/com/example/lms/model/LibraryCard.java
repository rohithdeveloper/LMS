package com.example.lms.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.lms.enums.CardStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="Book")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LibraryCard {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Card_No")
    private int cardno;

	@Column(name="Card_Status")
    @Enumerated(EnumType.STRING)
    private CardStatus status;

	@Column(name="Card_CreationDate")
    @CreationTimestamp
    private Date creationDate;
	
	@Column(name="Card_UpdationDate")
    @UpdateTimestamp
    private Date updationDate;
	
	@OneToOne
    @JoinColumn
    Student student;
	
	@OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    List<Transaction> transaction = new ArrayList<>();
	
    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    List<Book> bookIssued = new ArrayList<>();

}
