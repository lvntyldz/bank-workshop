package com.ba.controller;

import com.ba.domain.Book;
import com.ba.domain.Page;
import com.ba.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/add")
    public String addBook() {

        Page page1 = new Page(1, "chapter1 contents", "chapter1:why java");
        Set<Page> pages = new HashSet<>();
        pages.add(page1);

        Book book = new Book("Java 101", "Ali ALİOĞLU", "isbn12345", pages);

        bookRepository.save(book);

        return book.toString();
    }

    @GetMapping("/list")
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (!optionalBook.isPresent()) {
            return "sonuç bulunamadı!";
        }

        Book book = optionalBook.get();

        book.getPages().forEach(p -> {
            System.out.println("page chapter : " + p.getChapter());
        });

        return book.toString();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "ID : " + id + " olan kitap ve sayfaları silindi";
    }
}
