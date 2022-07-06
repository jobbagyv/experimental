package com.example.accessingdatajpa.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.querydsl.core.annotations.QueryInit;

@Entity
@Table(name = "note")
@NamedEntityGraph(name = "customer-eager", attributeNodes = { @NamedAttributeNode(value = "image"),
		@NamedAttributeNode(value = "owner"),
		@NamedAttributeNode(value = "newNote", subgraph = "newNoteSub") }, subgraphs = {
				@NamedSubgraph(name = "newNoteSub", attributeNodes = @NamedAttributeNode(value = "image")) })
public class Note extends CustomerSpecific {

	private String text;

	@OneToOne(fetch = FetchType.LAZY)
	@QueryInit("*.*")
	private Image image;

	@QueryInit("*.*")
	@OneToOne(fetch = FetchType.LAZY)
	private Note newNote;

	@Override
	public String toString() {
		return String.format("Note[id=%d, text='%s', image='%s', parent='%s', newNote='%s']", id, text, image, owner,
				newNote);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Note getNewNote() {
		return newNote;
	}

	public void setNewNote(Note newNote) {
		this.newNote = newNote;
	}

}