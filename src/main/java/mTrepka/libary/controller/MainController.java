package mTrepka.libary.controller;

import lombok.RequiredArgsConstructor;
import mTrepka.libary.domain.*;
import mTrepka.libary.service.BookService;
import mTrepka.libary.service.BorrowHistoryService;
import mTrepka.libary.service.UserService;
import mTrepka.libary.utility.NavigationBar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

/**
 * Created by Mario on 2017-07-10.
 */
@RestController
@RequiredArgsConstructor
public class MainController {
    private final BookService bookService;
    private final UserService userService;
    private final BorrowHistoryService borrowHistoryService;
    private final NavigationBar defaultNavigationBar;
    private final NavigationBar userNavigatonBar;
    private final NavigationBar adminNavigationBar;
    private final String LIBARY_NAME = "Sun - ";

    private ModelAndView quickModelAndView(String viewName, String title, int navigation) {
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("title", LIBARY_NAME + title);
        modelAndView.addObject("navigation", getNavigationWithRole().getNavigation(navigation));
	    modelAndView.addObject("form", userService.isUserAuthenticated());
        return modelAndView;
    }

    private NavigationBar getNavigationWithRole() {
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        if (role.equals("[ADMIN]")) {
            return adminNavigationBar;
        } else if (role.equals("[USER]")) {
            return userNavigatonBar;
        }
        return defaultNavigationBar;
    }

    @GetMapping(value = "/")
    public ModelAndView getIndex() {
        ModelAndView modelAndView = quickModelAndView("index", "Strona Glówna", 0);
	    modelAndView.addObject("form", userService.isUserAuthenticated());
        return modelAndView;
    }

    @GetMapping(value = "/book/")
    public ModelAndView book() {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        ModelAndView modelAndView = quickModelAndView("book", "Książki", 1);
        modelAndView.addObject("bookList", bookService.findAllFreeBook());
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping(value = "/book/{bookId}")
    public ModelAndView getBookById(@PathVariable("bookId") long bookId) {
        Book book = bookService.getById(bookId);
        ModelAndView modelAndView = quickModelAndView("selectbook", book.getName(), 1);
        modelAndView.addObject("currentBook", book);
        return modelAndView;
    }

    @GetMapping(value = "/contact")
    public ModelAndView getContact() {
	    return quickModelAndView("contact", "Kontakt", 0);
    }

    @GetMapping(value = "/admin/users/")
    public ModelAndView getAdminUsers() {
        ModelAndView modelAndView = quickModelAndView("adminUsers", "Uzytkownicy", 2);
        modelAndView.addObject("userList", userService.findAllUsers());
        return modelAndView;
    }

    @GetMapping(value = "/admin/users/{cardnumber}")
    public ModelAndView removeAdminUsers(@PathVariable("cardnumber") String cardnumber) {
        userService.removeUserByCardnumber(cardnumber);
        ModelAndView modelAndView = quickModelAndView("adminUsers", "Usuń", 2);
        modelAndView.addObject("userList", userService.findAllUsers());
        return modelAndView;
    }

    @GetMapping(value = "/admin/users/add")
    public ModelAndView getAdminUsersAdd() {
        ModelAndView modelAndView = quickModelAndView("adminUsersAdd", "Dodaj użytkownika", 2);
        User user = new User();
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping(value = "/admin/users/edit/{user}")
    public ModelAndView getAdminUsersEdit(@PathVariable("user") String cardnumber) {
        ModelAndView modelAndView = quickModelAndView("adminUsersEdit", "Edytuj użytkownika, ", 3);
        User user = userService.findUserByCardnumber(cardnumber);
        if (user == null) {
            modelAndView.addObject("exist", false);
        } else {
            modelAndView.addObject("exist", true);
            modelAndView.addObject("user", user);
        }
        return modelAndView;
    }


    @PostMapping(value = "/admin/users/edit/{path}")
    public ModelAndView postAdminUsersEdit(@Valid User user, @PathVariable("path") String path) {
        ModelAndView modelAndView = quickModelAndView("adminUsersEdit", "Edytuj użytkownika, ", 3);
	    User existingUser = userService.editUserByCardNumber(path, user);
        modelAndView.addObject("user", existingUser);
        modelAndView.addObject("exist", true);
        return modelAndView;
    }

    @GetMapping(value = "/admin/book/")
    public ModelAndView getAdminallBook() {
        ModelAndView modelAndView = quickModelAndView("adminAllBook", "Wszystkie ksiązki", 2);
        modelAndView.addObject("bookList", bookService.getAllBooks());
        return modelAndView;
    }

    @GetMapping(value = "/user/settings")
    public ModelAndView getUserSettings() {
        ModelAndView modelAndView = quickModelAndView("userSettings", "Ustawienia", 1);
        User user = userService.findUserByCardnumber(SecurityContextHolder.getContext().getAuthentication().getName());
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping(value = "/user/settings")
    public ModelAndView postUserSettings(@Valid User user) {
        ModelAndView modelAndView = quickModelAndView("userSettings", "Ustawienia", 1);
	    User existingUser = userService.editCurrentUser(user);
	    modelAndView.addObject("user", existingUser);
        return modelAndView;
    }

    @GetMapping(value = "/user/books")
    public ModelAndView getUserBooks() {
        ModelAndView modelAndView = quickModelAndView("userBook", "Książki", 1);
        User user = userService.findUserByCardnumber(SecurityContextHolder.getContext().getAuthentication().getName());
	    modelAndView.addObject("bookList", user.getBooks());
        return modelAndView;
    }

    @GetMapping(value = "/user/history")
    public ModelAndView getUserHistory() {
        ModelAndView modelAndView = quickModelAndView("userHistory", "Historia wypożyczeń", 1);
        User user = userService.findUserByCardnumber(SecurityContextHolder.getContext().getAuthentication().getName());
	    modelAndView.addObject("borrowHistoryList", user.getBorrowHistory());
        return modelAndView;
    }

    @GetMapping(value = "/borrow/{bookId}")
    public ModelAndView getBorrowBook(@PathVariable("bookId") Long bookId) {
        ModelAndView modelAndView = quickModelAndView("borrowBook", "Wypożycz", 1);
        modelAndView.addObject("navigation", getNavigationWithRole().getNavigation(1));
	    bookService.borrowBook(bookId);
        return modelAndView;
    }

    @GetMapping(value = "/admin/book/lend")
    public ModelAndView getAdminBookLend() {
        ModelAndView modelAndView = quickModelAndView("adminBookLend", "Wypożyczone ksiązki", 2);
        modelAndView.addObject("bookList", bookService.findAllBorrowBook());
        return modelAndView;
    }

    @GetMapping(value = "/admin/book/add")
    public ModelAndView getAdminBookAdd() {
        ModelAndView modelAndView = quickModelAndView("adminBookAdd", "Dodaj ksiązke", 2);
        modelAndView.addObject("book", new Book());
        return modelAndView;
    }


    @PostMapping(value = "/admin/book/add")
    public ModelAndView postAdminBookAdd(@Valid Book book) {
        ModelAndView modelAndView = quickModelAndView("adminBookAddPost", "Dodaj ksiązke", 2);
	    bookService.addBook(book);
        return modelAndView;
    }

    @GetMapping(value = "/admin/book/{bookid}")
    public ModelAndView removeAdminBook(@PathVariable("bookid") long bookid) {
        ModelAndView modelAndView = quickModelAndView("adminBookRemove", "Usuń ksiązke", 2);
        Book book = bookService.getById(bookid);
	    String result = bookService.removeBook(book);
        modelAndView.addObject("userList", userService.findAllUsers());
        return modelAndView;
    }

    @GetMapping(value = "/admin/book/edit/{bookid}")
    public ModelAndView getAdminBookEdit(@PathVariable("bookid") long bookid) {
        Book book = bookService.getById(bookid);
        ModelAndView modelAndView = quickModelAndView("adminBookEdit", "Edytuj ksiązke '" + book.getName() + "'", 3);
        modelAndView.addObject("book", book);
        return modelAndView;
    }


    @PostMapping(value = "/admin/book/edit/{bookid}")
    public ModelAndView postAdminBookEdit(@Valid Book bookSave, @PathVariable("bookid") long bookid) {
        ModelAndView modelAndView = quickModelAndView("adminBookEdit", "Edytuj ksiązke '" + bookSave.getName() + "'", 3);
	    Book book = bookService.editBookAndSave(bookSave, bookid);
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    @GetMapping(value = "/admin/book/edit/")
    public ModelAndView getAdminBookEditFind() {
	    return quickModelAndView("adminBookEditFind", "Szukaj ksiązke", 3);
    }

    @PostMapping(value = "/admin/book/edit/")
    public ModelAndView postAdminBookEditFind(@Valid String string) {
        if (bookService.getById(Long.parseLong(string)) != null) {
            return new ModelAndView("redirect:" + string);
        }
        ModelAndView modelAndView = quickModelAndView("adminBookEditFind", "Szukaj ksiązke", 3);
        modelAndView.addObject("error", "Brak książki o podanym id!");
        return modelAndView;
    }

    @GetMapping(value = "/admin/users/edit/")
    public ModelAndView getUsersBookEditFind() {
	    return quickModelAndView("adminBookEditFind", "Szukaj użytkownika", 3);
    }

    @PostMapping(value = "/admin/users/edit/")
    public ModelAndView postUsersBookEditFind(@Valid String string) {
        if (userService.findUserByCardnumber(string) != null) {
            return new ModelAndView("redirect:" + string);
        }
        ModelAndView modelAndView = quickModelAndView("adminBookEditFind", "Szukaj użytkownika", 3);
        modelAndView.addObject("error", "Brak Użytkownika o podanym id!");
        return modelAndView;
    }

	//ToDo
	@GetMapping(value = "/admin/users/active/{path}")
	public ModelAndView postAdminUserActiveEdit(@PathVariable("path") String userId) {
		ModelAndView modelAndView = quickModelAndView("adminUserActive", "Edycja użytkownika", 3);
		String result = userService.activeUser(userId);
		return modelAndView;
	}
}