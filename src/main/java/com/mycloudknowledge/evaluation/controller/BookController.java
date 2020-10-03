package com.mycloudknowledge.evaluation.controller;


import com.mycloudknowledge.evaluation.entity.Book;
import com.mycloudknowledge.evaluation.exception.ErrorMessage;
import com.mycloudknowledge.evaluation.exception.custom.BookNotFoundException;
import com.mycloudknowledge.evaluation.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static javax.servlet.http.HttpServletResponse.SC_OK;

@RestController
@CrossOrigin
@Api(tags = "BooK Collection API")
@RequestMapping("/api/v1")
public class BookController {
	
	@Autowired
	private BookService service;

	@ApiOperation(value = "Heath Check of the Book collection API")
	@ApiResponses(value = { @ApiResponse(code = SC_OK, message = "ok") })
	@GetMapping("/")
	public ResponseEntity<?> health() {
		return new ResponseEntity<>("Running healthy instance",HttpStatus.OK);
	}

	@ApiOperation(value = "Add a book information in the Book collection")
	@ApiResponses(value = { @ApiResponse(code = SC_OK, message = "ok") })
	@PostMapping("/book")
	public ResponseEntity<?> addBook(@RequestBody Book book){
		book = service.add(book);
		return new ResponseEntity<>(book, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Retrieves all the book information in the Book collection")
	@ApiResponses(value = { @ApiResponse(code = SC_OK, message = "ok") })
	@GetMapping("/books")
	public ResponseEntity<?> getAllBooks() {
		List<Book> books = service.findAll();
		if (books.isEmpty()) {
			throw new BookNotFoundException(ErrorMessage.NO_BOOK_NOT_FOUND);
		}
		return new ResponseEntity<>(books,HttpStatus.OK);
	}

	@ApiOperation(value = "Retrieves the book information by it's id in the Book collection")
	@ApiResponses(value = { @ApiResponse(code = SC_OK, message = "ok") })
	@GetMapping("/book/{id}")
	public ResponseEntity<?> getBook(@PathVariable int id) {
		Optional<Book> book = service.findById(id);
		if (!book.isPresent()) {
			throw new BookNotFoundException(ErrorMessage.BOOK_NOT_FOUND);
		}
		return new ResponseEntity<>(book,HttpStatus.OK);
	}

	@ApiOperation(value = "Delete the book by it's id in the Book collection")
	@ApiResponses(value = { @ApiResponse(code = SC_OK, message = "ok") })
	@DeleteMapping("/book/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable int id) {
		service.delete(id);
		return new ResponseEntity<>("Book Deleted with "+id, HttpStatus.OK);
	}

	@ApiOperation(value = "Update the book information in the Book collection")
	@ApiResponses(value = { @ApiResponse(code = SC_OK, message = "ok") })
	@PutMapping("/book")
	public ResponseEntity<?> updateBook(@RequestBody Book book) {
		book = service.update(book);
		return new ResponseEntity<>(book, HttpStatus.OK);
	}
}
