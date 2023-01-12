package com.danny.book;

import com.danny.book.model.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class BookApplicationTests {


	Book book1 = new Book(1,"a nice book");
	Book book2 = new Book(2,"a mystery book");
	Book book3 = new Book(3,"a mystery book");

	List<Book> library = new ArrayList<>(Arrays.asList(book1,book2,book3));
	List<Book> borrowedBooks = new ArrayList<>();


	@Test()
	public void checkIfLibraryIsEmptyOrNot(){

		Assertions.assertThat(book1.getId()).isEqualTo(1);
		Assertions.assertThat(library.get(1).getId()).isEqualTo(2);
		// checking if library has some books in it
		Assertions.assertThat(library).doesNotContainNull();
		System.out.println(library);

	}


	@Test
	public void addBooksToBorrowedList(){

		System.out.println(borrowedBooks);

		Book b = library
				.stream()
				.filter(id -> id.getId() == 1)
				.findFirst()
				.get();
		// 	borrowed a book from the library
		borrowedBooks.add(b);
		System.out.println(borrowedBooks);

		// checking if the book was boorrowed successfully
		Assertions.assertThat(borrowedBooks).isNotNull();

		// removing he borrowed book from the library and checking if it was removed

		Assertions.assertThat(library.remove(b)).isTrue();
		System.out.println(library);
	}

	@Test
	public void eachUserHasBorrowingLimitOfTwoBooks(){

		List<Book> borrowedBook = new ArrayList<>(1);

		Book b1 = library.get(0);
		Book b2 = library.get(1);

		borrowedBook.add(b1);
	    borrowedBook.add(b2);

		// check to see if borrowing limit is greater than 2
		Assertions.assertThat(borrowedBook.size()).isGreaterThan(2);

	    System.out.println(borrowedBook.get(1));
		System.out.println(borrowedBook);
	}


	@Test
	public void moreThanOneCopyOfABook(){

		Book b = library.stream()
				.filter(book -> book.getName()
				.equals("a nice book") && book.getId() == 1)
				.findFirst().get();
		Assertions.assertThat(borrowedBooks).isEmpty();
		borrowedBooks.add(b);
		Assertions.assertThat(borrowedBooks).isNotNull();
		System.out.println(library);
		Assertions.assertThat(library.remove(b)).isTrue();
		System.out.println(library);
	}

	@Test
	public void returnBookToTheLibrary(){

		List<Book> borrowed = new ArrayList<>(Arrays.asList(book1,book2));
		List<Book> lib = new ArrayList<>();

		// assert that library has no book
		Assertions.assertThat(lib).isEmpty();
		Book b0 = borrowed.get(0);
		Book b1 = borrowed.get(1);

		// books has been added to the library
		lib.add(b0);
		lib.add(b1);
		Assertions.assertThat(lib).isNotNull();

		// when a book is returned to the library it is deleted from the borrowedList

		Assertions.assertThat(borrowed.remove(b0)).isTrue();
		Assertions.assertThat(borrowed.remove(b1)).isTrue();

		// and displays an emptyList borrowedList
		System.out.println(lib);
		System.out.println(borrowed);

	}

}
