package com.danny.book.controllers;

import com.danny.book.exceptions.BookException;
import com.danny.book.model.Book;
import com.danny.book.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;





//    git init
//    git add README.md
//    git commit -m "first commit"
//    git branch -M main
//    git remote add origin https://github.com/michaeldaniel51/book.git
//    git push -u origin main







    @PostMapping
    public Book addBooks(@RequestBody Book book){
    return bookService.addBook(book);
    }

    @GetMapping
    public List<Book> getAllBooks() throws BookException {
    return bookService.userCanViewBooksInLibrary();
    }

    @GetMapping("/{bookId}")
    public Book addBorrowedBook(@PathVariable int bookId){
    return bookService.addToBorrowedBook(bookId);
    }

    @GetMapping("/borrowedBooks")
    public List<Book> getBorrowedBook(){
    return bookService.getAllBorrowedBooks();
    }

    @GetMapping("/copyBooks")
    public Book copyOfBorrowedBook(@RequestParam int id,@RequestParam String name){
    return bookService.moreThanOneCopyOfBook(id,name);
    }

    @GetMapping("/returnBook/{id}")
    public List<Book> returnLibraryBook(@PathVariable int id){
        return bookService.returnBookToTheLibrary(id);
    }
}
