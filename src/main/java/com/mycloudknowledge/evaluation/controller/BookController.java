package com.mycloudknowledge.evaluation.controller;


import com.mycloudknowledge.evaluation.entity.Book;
import com.mycloudknowledge.evaluation.exception.ErrorMessage;
import com.mycloudknowledge.evaluation.exception.custom.BookNotFoundException;
import com.mycloudknowledge.evaluation.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class BookController {
	
	@Autowired
	private BookService service;

	@GetMapping("/")
	public ResponseEntity<?> health() {
		return new ResponseEntity<>("Running healthy instance",HttpStatus.OK);
	}

	@PostMapping("/book")
	public ResponseEntity<?> addBook(@RequestBody Book book){
		book = service.add(book);
		return new ResponseEntity<>(book, HttpStatus.CREATED);
	}

	@GetMapping("/books")
	public ResponseEntity<?> getAllBooks() {
		List<Book> books = service.findAll();
		if (books.isEmpty()) {
			throw new BookNotFoundException(ErrorMessage.NO_BOOK_NOT_FOUND);
		}
		return new ResponseEntity<>(books,HttpStatus.OK);
	}

	@GetMapping("/book/{id}")
	public ResponseEntity<?> getBook(@PathVariable int id) {
		Optional<Book> book = service.findById(id);
		if (book!=null) {
			throw new BookNotFoundException(ErrorMessage.BOOK_NOT_FOUND);
		}
		return new ResponseEntity<>(book,HttpStatus.OK);
	}

	@DeleteMapping("/book/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable int id) {
		service.delete(id);
		return new ResponseEntity<>("Book Deleted with "+id, HttpStatus.OK);
	}

	@PutMapping("/book")
	public ResponseEntity<?> updateBook(@RequestBody Book book) {
		book = service.update(book);
		return new ResponseEntity<>(book, HttpStatus.OK);
	}
}
