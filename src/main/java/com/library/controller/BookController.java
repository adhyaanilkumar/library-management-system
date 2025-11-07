package com.library.controller;

import com.library.model.Book;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

/**
 * Book Controller Class
 * Handles HTTP requests and responses
 * Demonstrates Spring MVC and REST API
 */
@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * Display all books (Web View)
     */
    @GetMapping
    public String getAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    /**
     * Display add book form
     */
    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    /**
     * Handle book submission
     */
    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book, RedirectAttributes redirectAttributes) {
        try {
            bookService.addBook(book);
            redirectAttributes.addFlashAttribute("successMessage", "Book added successfully!");
            return "redirect:/books";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/books/add";
        }
    }

    /**
     * Display edit book form
     */
    @GetMapping("/edit/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Book> book = bookService.getBookById(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "edit-book";
        }
        redirectAttributes.addFlashAttribute("errorMessage", "Book not found");
        return "redirect:/books";
    }

    /**
     * Handle book update
     */
    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book, RedirectAttributes redirectAttributes) {
        try {
            bookService.updateBook(id, book);
            redirectAttributes.addFlashAttribute("successMessage", "Book updated successfully!");
            return "redirect:/books";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/books/edit/" + id;
        }
    }

    /**
     * Delete a book
     */
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            bookService.deleteBook(id);
            redirectAttributes.addFlashAttribute("successMessage", "Book deleted successfully!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/books";
    }

    // REST API Endpoints

    /**
     * REST API: Get all books
     */
    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<Book>> getAllBooksAPI() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    /**
     * REST API: Get book by ID
     */
    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Book> getBookByIdAPI(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * REST API: Add a new book
     */
    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<Book> addBookAPI(@RequestBody Book book) {
        try {
            Book savedBook = bookService.addBook(book);
            return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * REST API: Update a book
     */
    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Book> updateBookAPI(@PathVariable Long id, @RequestBody Book book) {
        try {
            Book updatedBook = bookService.updateBook(id, book);
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * REST API: Delete a book
     */
    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteBookAPI(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
