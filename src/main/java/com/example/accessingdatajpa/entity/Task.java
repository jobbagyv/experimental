package com.example.accessingdatajpa.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="task")
public class Task extends CustomerSpecific{

	private String text;

	@Override
	public String toString() {
		return String.format("Task[id=%d, text='%s']", id, text);
	}


	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}


}