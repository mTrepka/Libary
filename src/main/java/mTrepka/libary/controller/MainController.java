package mTrepka.libary.controller;

import mTrepka.libary.domain.*;
import mTrepka.libary.service.BookService;
import mTrepka.libary.service.BorrowHistoryService;
import mTrepka.libary.service.UserService;
import mTrepka.libary.utility.NavigationBar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.method.P;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Mario on 2017-07-10.
 */
@Controller
public class MainController {
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private BorrowHistoryService borrowHistoryService;
    @Autowired
    NavigationBar defaultNavigationBar;
    @Autowired
    NavigationBar userNavigatonBar;
    @Autowired
    NavigationBar adminNavigationBar;
//    @RequestMapping(value = "/",method = RequestMethod.GET)
//    public  ModelAndView getIndex(){
//        ModelAndView modelAndView = new ModelAndView("");
//        return modelAndView;
//    }
private final String LIBARY_NAME = "Sun - ";

    private NavigationBar getNavigationWithRole() {
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        if (role.equals("[ADMIN]")) {
            return adminNavigationBar;
        } else if (role.equals("[USER]")) {
            return userNavigatonBar;
        }
        return defaultNavigationBar;
    }
    private static String role(){
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        if(role.equals("[ADMIN]")){
            return"ADMIN";
        }else if(role.equals("[USER]")){
            return "USER";
        }
        return null;
    }

    private boolean loginFormRole() {
        if (role() == null) {
            return true;
        } else {
            return false;
        }
    }
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public  ModelAndView getIndex(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("title", LIBARY_NAME + "Strona Glówna");
        modelAndView.addObject("navigation", getNavigationWithRole().getNavigation(0));
        System.out.println(this.loginFormRole());
        modelAndView.addObject("form", this.loginFormRole());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        return modelAndView;
    }
    @RequestMapping(value = "/book/",method = RequestMethod.GET)
    public ModelAndView book(){
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        ModelAndView modelAndView = new ModelAndView("book");
        modelAndView.addObject("bookList", bookService.findAllFreeBook());
        modelAndView.addObject("form", loginFormRole());
        modelAndView.addObject("title", LIBARY_NAME + "Książki");
        modelAndView.addObject("user", user);
        modelAndView.addObject("navigation", getNavigationWithRole().getNavigation(1));
        return modelAndView;
    }
    @RequestMapping(value = "/book/{bookId}",method = RequestMethod.GET)
    public ModelAndView getBookById(@PathVariable("bookId")long bookId){
        ModelAndView modelAndView = new ModelAndView("selectbook");
        modelAndView.addObject("form", loginFormRole());
        Book book = bookService.getById(bookId);
        modelAndView.addObject("title", LIBARY_NAME + book.getName());
        modelAndView.addObject("currentBook", book);
        modelAndView.addObject("navigation", getNavigationWithRole().getNavigation(1));
        return modelAndView;
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public ModelAndView getContact(){
        ModelAndView modelAndView = new ModelAndView("contact");
        modelAndView.addObject("form", loginFormRole());
        modelAndView.addObject("title", LIBARY_NAME + "Kontakt");
        modelAndView.addObject("navigation", getNavigationWithRole().getNavigation(0));
        return modelAndView;
    }
    @RequestMapping(value = "/admin/users/",method = RequestMethod.GET)
        public  ModelAndView getAdminUsers(){
        ModelAndView modelAndView = new ModelAndView("adminUsers");
        modelAndView.addObject("form", loginFormRole());
        modelAndView.addObject("title", LIBARY_NAME + "Uzytkownicy");
        modelAndView.addObject("navigation", getNavigationWithRole().getNavigation(2));
        modelAndView.addObject("userList", userService.findAllUsers());
        return modelAndView;
    }
    @RequestMapping(value = "/admin/users/{cardnumber}",method = RequestMethod.GET)
    public  ModelAndView removeAdminUsers(@PathVariable("cardnumber") String cardnumber){
        userService.removeUserByCardnumber(cardnumber);
        ModelAndView modelAndView = new ModelAndView("adminUsers");
        modelAndView.addObject("form", loginFormRole());
        modelAndView.addObject("title", LIBARY_NAME + "Usuń");
        modelAndView.addObject("navigation", getNavigationWithRole().getNavigation(2));
        modelAndView.addObject("userList", userService.findAllUsers());
        return modelAndView;
    }
        @RequestMapping(value = "/admin/users/add",method = RequestMethod.GET)
    public  ModelAndView getAdminUsersAdd(){
        ModelAndView modelAndView = new ModelAndView("adminUsersAdd");
            modelAndView.addObject("form", loginFormRole());
            modelAndView.addObject("title", LIBARY_NAME + "Dodaj użytkownika");
            modelAndView.addObject("navigation", getNavigationWithRole().getNavigation(2));
        User user = new User();
        modelAndView.addObject("user",user);
            modelAndView.addObject("number",String.class);
        return modelAndView;
    }
    @RequestMapping(value = "/admin/users/edit/{user}",method = RequestMethod.GET)
    public  ModelAndView getAdminUsersEdit(@PathVariable("user") String cardnumber){
        ModelAndView modelAndView = new ModelAndView("adminUsersEdit");
        modelAndView.addObject("form", loginFormRole());
        modelAndView.addObject("title", LIBARY_NAME + "Edytuj użytkownika, " + cardnumber);
        modelAndView.addObject("navigation", getNavigationWithRole().getNavigation(3));
        User user = userService.findUserByCardnumber(cardnumber);
        if(user==null){
            modelAndView.addObject("exist",false);
        }else {
            modelAndView.addObject("exist",true);
            modelAndView.addObject("user",user);
        }
        return modelAndView;
    }
    @RequestMapping(value = "/admin/users/edit/{path}",method = RequestMethod.POST)
    public  ModelAndView postAdminUsersEdit(@Valid User user,@PathVariable("path") String path){
        ModelAndView modelAndView = new ModelAndView("adminUsersEdit");
        modelAndView.addObject("form", loginFormRole());
        modelAndView.addObject("title", LIBARY_NAME + "Edytuj użytkownika, " + path);
        modelAndView.addObject("navigation", getNavigationWithRole().getNavigation(3));
        User existingUser = userService.findUserByCardnumber(path);
        existingUser.setName(user.getName());
        existingUser.setLastName(user.getLastName());
        existingUser.setActive(user.getActive());
        userService.editAndSave(existingUser);
        modelAndView.addObject("user", existingUser);
        modelAndView.addObject("exist", true);
        return modelAndView;
    }
        @RequestMapping(value = "/admin/book/",method = RequestMethod.GET)
    public  ModelAndView getAdminallBook(){
        ModelAndView modelAndView = new ModelAndView("adminAllBook");
            modelAndView.addObject("form", loginFormRole());
            modelAndView.addObject("title", LIBARY_NAME + "Wszystkie ksiązki");
            modelAndView.addObject("navigation", getNavigationWithRole().getNavigation(2));
            modelAndView.addObject("bookList", bookService.getAllBooks());
        return modelAndView;
    }
        @RequestMapping(value = "/user/settings",method = RequestMethod.GET)
    public  ModelAndView getUserSettings(){
        ModelAndView modelAndView = new ModelAndView("userSettings");
            modelAndView.addObject("form", loginFormRole());
            modelAndView.addObject("title", LIBARY_NAME + "Ustawienia");
            modelAndView.addObject("navigation", getNavigationWithRole().getNavigation(1));
        User user = userService.findUserByCardnumber(SecurityContextHolder.getContext().getAuthentication().getName());
            modelAndView.addObject("user", user);
        return modelAndView;
    }
    @RequestMapping(value = "/user/settings",method = RequestMethod.POST)
    public  ModelAndView postUserSettings(@Valid User user){
        ModelAndView modelAndView = new ModelAndView("userSettings");
        modelAndView.addObject("form", loginFormRole());
        modelAndView.addObject("title", LIBARY_NAME + "Ustawienia");
        modelAndView.addObject("navigation", getNavigationWithRole().getNavigation(1));
        User existingUser = userService.findUserByCardnumber(SecurityContextHolder.getContext().getAuthentication().getName());
        user.setActive(1);
        user.setCardnumber(existingUser.getCardnumber());
        user.setRoles(existingUser.getRoles());
        if(user.getPassword()==""){
            user.setPassword(existingUser.getPassword());
        }
        userService.editAndSave(user);
        modelAndView.addObject("user", user);
        return modelAndView;
    }
    @RequestMapping(value = "/user/books",method = RequestMethod.GET)
    public  ModelAndView getUserBooks(){
        ModelAndView modelAndView = new ModelAndView("userBook");
        modelAndView.addObject("form", loginFormRole());
        modelAndView.addObject("title", LIBARY_NAME + "Książki");
        modelAndView.addObject("navigation", getNavigationWithRole().getNavigation(1));
        User user = userService.findUserByCardnumber(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Book> bookList = user.getBooks();
        modelAndView.addObject("bookList", bookList);
        return modelAndView;
    }
    @RequestMapping(value = "/user/history",method = RequestMethod.GET)
    public  ModelAndView getUserHistory(){
        ModelAndView modelAndView = new ModelAndView("userHistory");
        modelAndView.addObject("form", loginFormRole());
        modelAndView.addObject("title", LIBARY_NAME + "Historia wypożyczeń");
        modelAndView.addObject("navigation", getNavigationWithRole().getNavigation(1));
        User user = userService.findUserByCardnumber(SecurityContextHolder.getContext().getAuthentication().getName());
        List<BorrowHistory> borrowHistories = user.getBorrowHistory();
        modelAndView.addObject("borrowHistoryList", borrowHistories);
        return modelAndView;
    }
        @RequestMapping(value = "/borrow/{bookId}",method = RequestMethod.GET)
    public  ModelAndView getBorrowBook(@PathVariable("bookId") Long bookId){
        ModelAndView modelAndView = new ModelAndView("borrowBook");
            modelAndView.addObject("form", loginFormRole());
            modelAndView.addObject("title", LIBARY_NAME + "Wypożycz");
            modelAndView.addObject("navigation", getNavigationWithRole().getNavigation(1));
        String role = this.role();
        if(role.equals("USER")){
            Book book = bookService.getById(bookId);
            if(book.getOwnerid()==null){
                User user = userService.findUserByCardnumber(SecurityContextHolder.getContext().getAuthentication().getName());
                BorrowHistory borrowHistory = new BorrowHistory();
                java.util.Date date = new java.util.Date();
                borrowHistory.setBorrow_date(new Date(date.getTime()));
                borrowHistory.setBookborrow(book);
                borrowHistory.setUserborrow(user);
                borrowHistory.setId(borrowHistoryService.countHistories()+1);
                borrowHistoryService.save(borrowHistory);
                book.setCurrentBorrowId(Long.toString(borrowHistory.getId()));
                book.setOwnerid(user);
                userService.editAndSave(user);
                bookService.saveBook(book);
            }
        }
        return modelAndView;
    }
        @RequestMapping(value = "/admin/book/lend",method = RequestMethod.GET)
    public  ModelAndView getAdminBookLend(){
        ModelAndView modelAndView = new ModelAndView("adminBookLend");
            modelAndView.addObject("form", loginFormRole());
            modelAndView.addObject("title", LIBARY_NAME + "Wypożyczone ksiązki");
            modelAndView.addObject("navigation", getNavigationWithRole().getNavigation(2));
        modelAndView.addObject("bookList",bookService.findAllBorrowBook());
        return modelAndView;
    }
        @RequestMapping(value = "/admin/book/add",method = RequestMethod.GET)
    public  ModelAndView getAdminBookAdd(){
        ModelAndView modelAndView = new ModelAndView("adminBookAdd");
            modelAndView.addObject("form", loginFormRole());
            modelAndView.addObject("navigation", getNavigationWithRole().getNavigation(2));
            modelAndView.addObject("title", LIBARY_NAME + "Dodaj ksiązke");
        modelAndView.addObject("book",new Book());
        return modelAndView;
    }
        @RequestMapping(value = "/admin/book/add",method = RequestMethod.POST)
    public  ModelAndView postAdminBookAdd(@Valid Book book){
        ModelAndView modelAndView = new ModelAndView("adminBookAddPost");
            modelAndView.addObject("form", loginFormRole());
            modelAndView.addObject("navigation", getNavigationWithRole().getNavigation(2));
            modelAndView.addObject("title", LIBARY_NAME + "Dodaj ksiazke");
        Book existingBook = bookService.getById(book.getId());
        if(existingBook==null){
            bookService.saveBook(book);
        }
        return modelAndView;
    }
    @RequestMapping(value = "/admin/book/{bookid}",method = RequestMethod.GET)
    public  ModelAndView removeAdminBook(@PathVariable("bookid") long bookid){
        ModelAndView modelAndView = new ModelAndView("adminBookRemove");
        modelAndView.addObject("form", loginFormRole());
        modelAndView.addObject("navigation", getNavigationWithRole().getNavigation(2));
        modelAndView.addObject("title", LIBARY_NAME + "Usuń ksiązke");
        Book book = bookService.getById(bookid);
        if(book.getOwnerid()==null){
            bookService.removeBook(book);
            modelAndView.addObject("tru","Ksiązke usunięto pomyślni");
        }else{
            modelAndView.addObject("error","Ksiązka jest wypożyczona i nie można jej usunąć!");
        }
        modelAndView.addObject("userList", userService.findAllUsers());
        return modelAndView;
    }
        @RequestMapping(value = "/admin/book/edit/{bookid}",method = RequestMethod.GET)
    public  ModelAndView getAdminBookEdit(@PathVariable("bookid") long bookid){
        ModelAndView modelAndView = new ModelAndView("adminBookEdit");
            modelAndView.addObject("form", loginFormRole());
            modelAndView.addObject("navigation", getNavigationWithRole().getNavigation(2));
            Book book = bookService.getById(bookid);
            modelAndView.addObject("title", LIBARY_NAME + "Edytuj ksiązke '" + book.getName() + "'");
            modelAndView.addObject("book", book);
        return modelAndView;
    }
    @RequestMapping(value = "/admin/book/edit/{bookid}",method = RequestMethod.POST)
    public  ModelAndView postAdminBookEdit(@Valid Book bookSave,@PathVariable("bookid") long bookid){
        ModelAndView modelAndView = new ModelAndView("adminBookEdit");
        modelAndView.addObject("form", loginFormRole());
        modelAndView.addObject("title", LIBARY_NAME + "Edytuj ksiązke '" + bookSave.getName() + "'");
        modelAndView.addObject("navigation", getNavigationWithRole().getNavigation(3));
        bookSave.setId(bookid);
        bookService.saveBook(bookSave);
        Book book = bookService.getById(bookid);
        modelAndView.addObject("book", book);
        return modelAndView;
    }
    @RequestMapping(value = "/admin/book/edit/",method = RequestMethod.GET)
    public  ModelAndView getAdminBookEditFind(){
        ModelAndView modelAndView = new ModelAndView("adminBookEditFind");
        modelAndView.addObject("form", loginFormRole());
        modelAndView.addObject("title", LIBARY_NAME + "Szukaj ksiązke");
        modelAndView.addObject("navigation", getNavigationWithRole().getNavigation(3));
        modelAndView.addObject("string", new SerString());
        return modelAndView;
    }
    @RequestMapping(value = "/admin/book/edit/",method = RequestMethod.POST)
    public  ModelAndView postAdminBookEditFind(@Valid SerString string){
        if(bookService.getById(Long.parseLong(string.getString()))!=null) {
            return new ModelAndView("redirect:" + string.getString());
        }
        ModelAndView modelAndView = new ModelAndView("adminBookEditFind");
        modelAndView.addObject("form", loginFormRole());
        modelAndView.addObject("title", LIBARY_NAME + "Szukaj ksiązke");
        modelAndView.addObject("navigation", getNavigationWithRole().getNavigation(3));
        modelAndView.addObject("string", new SerString());
        modelAndView.addObject("error", "Brak książki o podanym id!");
        return modelAndView;
    }
    @RequestMapping(value = "/admin/users/edit/",method = RequestMethod.GET)
    public  ModelAndView getUsersBookEditFind(){
        ModelAndView modelAndView = new ModelAndView("adminBookEditFind");
        modelAndView.addObject("form", loginFormRole());
        modelAndView.addObject("title", LIBARY_NAME + "Szukaj użytkownika");
        modelAndView.addObject("navigation", getNavigationWithRole().getNavigation(3));
        modelAndView.addObject("string", new SerString());
        return modelAndView;
    }
    @RequestMapping(value = "/admin/users/edit/",method = RequestMethod.POST)
    public  ModelAndView postUsersBookEditFind(@Valid SerString string){
        if(userService.findUserByCardnumber(string.getString())!=null) {
            return new ModelAndView("redirect:" + string.getString());
        }
        ModelAndView modelAndView = new ModelAndView("adminBookEditFind");
        modelAndView.addObject("form", loginFormRole());
        modelAndView.addObject("title", LIBARY_NAME + "Szukaj użytkownika");
        modelAndView.addObject("navigation", getNavigationWithRole().getNavigation(3));
        modelAndView.addObject("string", new SerString());
        modelAndView.addObject("error", "Brak Użytkownika o podanym id!");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/users/active/{path}", method = RequestMethod.GET)
    public ModelAndView postAdminUserActiveEdit(@PathVariable("path") String path) {
        ModelAndView modelAndView = new ModelAndView("adminUserActive");
        modelAndView.addObject("form", loginFormRole());
        modelAndView.addObject("navigation", getNavigationWithRole().getNavigation(3));
        User user = userService.findUserByCardnumber(path);
        if (user.getActive() == 1) {
            user.setActive(0);
            modelAndView.addObject("active", "Użytkownik został wyłączony");
        } else {
            user.setActive(1);
            modelAndView.addObject("active", "Użytkownik został włączony");
        }
        userService.editAndSave(user);
        return modelAndView;
    }
}