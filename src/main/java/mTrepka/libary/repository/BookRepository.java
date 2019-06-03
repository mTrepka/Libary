package mTrepka.libary.repository;

import mTrepka.libary.domain.Book;
import mTrepka.libary.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mario on 2017-07-10.
 */
@Repository("bookRepository")
public interface BookRepository extends JpaRepository<Book,Long>{
    List<Book> findAll();
    Book findById(long id);

	List<Book> findByOwner(User owner);

	@Query(value = "SELECT * FROM book WHERE current_borrow = null", nativeQuery = true)
    List<Book> findFreeBook();

	@Query(value = "SELECT * FROM book WHERE current_borrow != null", nativeQuery = true)
    List<Book> findBorrowBook();
}
