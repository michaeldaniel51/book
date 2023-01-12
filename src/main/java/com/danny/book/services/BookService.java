package com.danny.book.services;

import com.danny.book.exceptions.BookException;
import com.danny.book.BookInterface;
import com.danny.book.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class BookService {


    Book book1 = new Book(1, "obi goes to school");
    Book book2 = new Book(2, "edet lives in calabar");
    Book book3 = new Book(3, "joys of motherhood");
    Book book4 = new Book(3, "joys of motherhood");
    Book book5 = new Book(4, "kunle and wakama");
    List<Book> bookList = new ArrayList<>(Arrays.asList(book1, book2, book3, book4, book5));

    List<Book> borrowedBooks = new ArrayList<>();

    public Book addBook(Book book) {

        bookList.add(book);
        return book;
    }

    public List<Book> userCanViewBooksInLibrary() throws BookException {

        if (bookList.size() < 1) {
            return new ArrayList<>();

        }
        return bookList;
    }

    public List<Book> getAllBorrowedBooks() {
        return borrowedBooks;
    }

    public Book addToBorrowedBook(int id) {

        BookInterface num = ((n)-> {
            int w = n;
            return w;
        });

        Book book = bookList
                .stream()
                .filter(bid -> id == bid.getId())
                .findAny()
                .get();


       if (borrowedBooks.size() < num.size(2)){
           borrowedBooks.add(book);
           bookList.remove(book);

       }else {
           throw new RuntimeException();
       }
        System.out.println(borrowedBooks);
        return book;
    }

    public Book moreThanOneCopyOfBook(int id,String name) {
        Book book = bookList
                .stream()
                .filter(bid -> id == bid.getId() && bid.getName().equals(name))
                .findAny()
                .get();

        if (Objects.nonNull(book) && borrowedBooks.size() < 1) {
            borrowedBooks.add(book);
            bookList.remove(book);
        } else if (borrowedBooks.size() > 1) {
            borrowedBooks.remove(book);
        } else {
            throw new RuntimeException("cannot add this book");
        }
        return book;
    }

    public List<Book> returnBookToTheLibrary(int id) {
        System.out.println(borrowedBooks);
        Book book = borrowedBooks
                .stream()
                .filter(bid -> id == bid.getId())
                .findAny()
                .orElseThrow();

            bookList.add(book);
            borrowedBooks.remove(book);

        return borrowedBooks;

    }
}