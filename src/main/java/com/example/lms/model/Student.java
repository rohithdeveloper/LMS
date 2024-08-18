package com.example.lms.model;

import com.example.lms.enums.Department;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="Student")
public class Student {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Student_Id")
	private int sId;
	
	@Column(name="Student_Name")
	private String sname;
	
	@Column(name="Student_Age")
	private int sage;

	@Enumerated(EnumType.STRING)
	@Column(name="Student_Department")
	private Department department;
	
	@Column(name="Student_Email")
	private String email;

	@OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    LibraryCard card;
}
