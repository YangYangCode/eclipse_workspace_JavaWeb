package com.example.tx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tx.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
