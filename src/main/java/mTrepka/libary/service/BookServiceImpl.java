package mTrepka.libary.service;

import lombok.RequiredArgsConstructor;
import mTrepka.libary.domain.Book;
import mTrepka.libary.domain.BorrowHistory;
import mTrepka.libary.domain.User;
import mTrepka.libary.repository.BookRepository;
import mTrepka.libary.repository.BorrowHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Mario on 2017-07-10.
 */
@Service("bookService")
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
	private final BookRepository bookRepository;
	private final BorrowHistoryRepository borrowHistoryRepository;
    private final UserService userService;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getById(long id) {
        return bookRepository.findById(id);
    }



    @Override
    public List<Book> findAllFreeBook() {
        return bookRepository.findFreeBook();
    }

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void borrowBook(Long bookId) {
        Book book = bookRepository.findById(bookId);
	    if (book.getCurrentBorrow() == null) {
            User user = userService.getCurrentUser();
            BorrowHistory borrowHistory = new BorrowHistory();
            borrowHistory.setBorrow_date(LocalDate.now());
		    borrowHistory.setBookId(book.getId());
		    borrowHistory.setUserCardNumber(user.getCardNumber());
		    borrowHistory.setStatus("lend");
            book.setCurrentBorrow(borrowHistory);
		    user.getBooks().add(book);
		    borrowHistoryRepository.save(borrowHistory);
        }
    }

    @Override
    public List<Book> findAllBorrowBook() {
	    return bookRepository.findBorrowBook();
    }

    @Override
    public String removeBook(Book book) {
	    if (book.getCurrentBorrow() == null) {
            bookRepository.delete(book);
            return "Book has been removed";
        } else {
            return "You can't remove book becouse is borrowed";
        }

    }

    @Override
    public Book editBookAndSave(Book bookData, long bookId) {
        Book currentBook = bookRepository.findById(bookId);
        editBookFromTemplate(currentBook, bookData);
	    bookRepository.save(currentBook);
        return currentBook;
    }

    private void editBookFromTemplate(Book currentBook, Book bookData) {
        currentBook.setAuthor(bookData.getAuthor());
        currentBook.setName(bookData.getName());
        currentBook.setIsbn(bookData.getIsbn());
        currentBook.setDescription(bookData.getDescription());
        currentBook.setPageAmount(bookData.getPageAmount());
        currentBook.setReleaseDate(bookData.getReleaseDate());
    }

	public List<Book> getLastFiveBooks() {
		return bookRepository.getLastFiveBooks();
	}
}
