package com.mycloudknowledge.evaluation.repository;

import com.mycloudknowledge.evaluation.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, Integer> {

}
