package mTrepka.libary.service;

import lombok.RequiredArgsConstructor;
import mTrepka.libary.domain.Book;
import mTrepka.libary.domain.User;
import mTrepka.libary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Mario on 2017-07-10.
 */
@Service("bookService")
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
	private final BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findByOwnerid(User ownerid) {
        return bookRepository.findByOwnerid(ownerid);
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<Book> findAllFreeBook() {
        return bookRepository.findFreeBook();
    }

    @Override
    public List<Book> findAllBorrowBook() {
        return bookRepository.findBorrowBook();
    }

    @Override
    public void removeBook(Book book) {
        bookRepository.delete(book);
    }
}
