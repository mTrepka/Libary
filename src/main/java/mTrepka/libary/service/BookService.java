package mTrepka.libary.service;

import mTrepka.libary.domain.Book;
import mTrepka.libary.domain.User;

import java.util.List;

/**
 * Created by Mario on 2017-07-10.
 */
public interface BookService {
	List<Book> getAllBooks();

	Book getById(long id);
    List<Book> findByOwnerid(User owner);
    List<Book> findAllFreeBook();
    List<Book> findAllBorrowBook();

	String removeBook(Book book);

	void borrowBook(Long bookId);

	void addBook(Book book);

	Book editBookAndSave(Book BookData, long bookId);
}
