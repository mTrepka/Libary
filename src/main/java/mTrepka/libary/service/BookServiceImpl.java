package mTrepka.libary.service;

import mTrepka.libary.domain.Book;
import mTrepka.libary.domain.User;
import mTrepka.libary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mario on 2017-07-10.
 */
@Service("bookService")
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;

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
}
