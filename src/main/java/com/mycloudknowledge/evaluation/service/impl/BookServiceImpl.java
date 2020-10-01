package com.mycloudknowledge.evaluation.service.impl;

import com.mycloudknowledge.evaluation.entity.Book;
import com.mycloudknowledge.evaluation.repository.BookRepository;
import com.mycloudknowledge.evaluation.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;

    @Override
    public Book add(Book book) {
        return repository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Book> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public Book update(Book book) {
        return repository.save(book);
    }
}
