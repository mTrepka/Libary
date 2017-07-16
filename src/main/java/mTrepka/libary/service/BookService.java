package mTrepka.libary.service;

import mTrepka.libary.domain.Book;
import mTrepka.libary.domain.User;

import java.util.List;

/**
 * Created by Mario on 2017-07-10.
 */
public interface BookService {
    public List<Book> getAllBooks();
    public Book getById(long id);
    List<Book> findByOwnerid(User owner);
}
