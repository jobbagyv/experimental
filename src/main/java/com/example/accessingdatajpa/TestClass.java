package com.example.accessingdatajpa;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.JoinType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.accessingdatajpa.entity.Customer;
import com.example.accessingdatajpa.entity.Image;
import com.example.accessingdatajpa.entity.Note;
import com.example.accessingdatajpa.entity.QCustomerSpecific;
import com.example.accessingdatajpa.entity.QNote;
import com.example.accessingdatajpa.entity.Rectangle;
import com.example.accessingdatajpa.entity.Task;
import com.example.accessingdatajpa.entity.repo.CustomerRepository;
import com.example.accessingdatajpa.entity.repo.ImageRepo;
import com.example.accessingdatajpa.entity.repo.NoteRepo;
import com.example.accessingdatajpa.entity.repo.RectangleRepo;
import com.example.accessingdatajpa.entity.repo.TaskRepo;
import com.example.accessingdatajpa.querydsl.repo.GenericRepository;
import com.example.accessingdatajpa.querydsl.repo.NoteQuerydslRepo;
import com.example.accessingdatajpa.specification.repo.CustomerSpecificationRepo;
import com.querydsl.core.types.dsl.BooleanExpression;

@Component
public class TestClass {
    @Autowired
    CustomerRepository customerRepo;
    @Autowired
    CustomerSpecificationRepo specCustomerRepo;
    @Autowired
    NoteRepo noteRepo;
    @Autowired
    TaskRepo taskRepo;
    @Autowired
    ImageRepo imageRepo;
    @Autowired
    RectangleRepo rectangleRepo;
    @Autowired
    NoteQuerydslRepo queryDslCustomerRepo;
    @Autowired
    GenericRepository genericRepo;

    private void setUpBasics() {
        System.err.println("testing");
        Customer toSave = new Customer();
        Note toAdd = new Note();
        toAdd.setText("text");
        Image image = new Image();
        Rectangle rect = new Rectangle();
        rect.setW(4d);
        rect.setH(2d);
        rectangleRepo.save(rect);
        image.setRectangle(rect);
        image.setText("image");
        Task task = new Task();
        task.setText("task");
        taskRepo.save(task);
        imageRepo.save(image);
        toAdd.setImage(image);
        toSave.setNote(toAdd);
        toSave.setFirstName("a");
        toSave.setLastName("B");
        Customer toSave2 = new Customer();
        toSave2.setNote(task);
        toSave2.setFirstName("e");
        customerRepo.save(toSave2);
        Note newNote = new Note();
        Image newImage = new Image();
        rect = new Rectangle();
        rect.setW(2d);
        rect.setH(1d);
        rectangleRepo.save(rect);
        newImage.setRectangle(rect);
        imageRepo.save(newImage);
        newNote.setImage(newImage);
        newNote.setText("new note");
        noteRepo.save(newNote);
        toAdd.setNewNote(newNote);
        noteRepo.save(toAdd);
        customerRepo.save(toSave);
    }

    private void exampleFun() {
        Customer search = new Customer();
        search.setFirstName(null);
        System.err.println("pimpin");
//		System.err.println(customerRepo.findAll(Example.of(search), EntityGraphs.named(Customer.GRAPH_FETCH)));
//		System.err.println(noteRepo.findAll(EntityGraphs.named(Customer.GRAPH_FETCH)));
        System.err.println("a");
    }

    private List<Customer> specificationFun() {
        return specCustomerRepo.findAll((r, q, c) -> {
            return c.equal(r.join("note", JoinType.LEFT).get("deleted"), true);
        });
    }

    private BooleanExpression createBase(QCustomerSpecific _super) {
        return _super.deleted.eq(false);
    }

    private Iterable<Note> queryDslFun() {
        return genericRepo.findAll(QNote.note, QNote.note.deleted.eq(false).and(QNote.note.text.eq("text")),
                List.of(QNote.note.image, QNote.note.owner, QNote.note.newNote.owner, QNote.note.newNote, QNote.note.newNote.image, QNote.note.image.rectangle, QNote.note.newNote.image.rectangle));
//		return genericRepo.findAll(QNote.note, createBase(QNote.note._super).and(QNote.note.text.eq("text")), "customer-eager");
    }

    @PostConstruct
    public void start() {
        setUpBasics();
        System.err.println(queryDslFun());
    }

}
