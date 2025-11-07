package com.library.repository;

import com.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Book Repository Interface
 * Extends JpaRepository to provide CRUD operations for Book entity
 * Spring Data JPA automatically implements this interface
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    // Custom query methods
    Optional<Book> findByIsbn(String isbn);
    
    List<Book> findByAuthor(String author);
    
    List<Book> findByTitleContainingIgnoreCase(String title);
}

