package com.mycloudknowledge.evaluation.service;

import com.mycloudknowledge.evaluation.entity.Book;

import java.util.List;
import java.util.Optional;


public interface BookService {

    Book add(Book book);
    List<Book> findAll();
    Optional<Book> findById(int id);
    void delete(int id);

    Book update(Book book);
}
