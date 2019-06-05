package mTrepka.libary.repository;

import mTrepka.libary.domain.Book;
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

	@Query(value = "SELECT * FROM book WHERE current_borrow_id is null", nativeQuery = true)
    List<Book> findFreeBook();

	@Query(value = "SELECT * FROM book WHERE current_borrow_id is not null", nativeQuery = true)
    List<Book> findBorrowBook();

	@Query(value = "SELECT * FROM book order by bookid desc limit 4", nativeQuery = true)
	List<Book> getLastFiveBooks();
}
