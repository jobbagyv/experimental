package com.example.accessingdatajpa.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToOne;

@Entity
public class Customer {

	public static final String GRAPH_FETCH = "customer-eager";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;

	@JoinColumn(name = "note")
	@OneToOne(fetch = FetchType.LAZY)
	private CustomerSpecific note;

	@Override
	public String toString() {
		return String.format("Customer[id=%d, firstName='%s', lastName='%s', noteId='%s']", id, firstName, lastName, note.getId()
				);
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public CustomerSpecific getNote() {
		return note;
	}

	public void setNote(CustomerSpecific note) {
		this.note = note;
	}

}