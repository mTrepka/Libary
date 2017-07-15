package mTrepka.libary.controller;

import com.sun.org.apache.regexp.internal.RE;
import mTrepka.libary.domain.Role;
import mTrepka.libary.domain.User;
import mTrepka.libary.service.BookService;
import mTrepka.libary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Set;

/**
 * Created by Mario on 2017-07-10.
 */
@Controller
public class MainController {
    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;
//    @RequestMapping(value = "/",method = RequestMethod.GET)
//    public  ModelAndView getIndex(){
//        ModelAndView modelAndView = new ModelAndView("");
//        return modelAndView;
//    }
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public  ModelAndView getIndex(){
        ModelAndView modelAndView = new ModelAndView("index");
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        if(role.equals("[ADMIN]")){
            modelAndView.addObject("userRole","ADMIN");
        }else if(role.equals("[USER]")){
            modelAndView.addObject("userRole","USER");
        }
        Set<Role> roles = userService.findAllUsers().get(1).getRoles();
        System.out.println();
        return modelAndView;
    }
    @RequestMapping(value = "/book/",method = RequestMethod.GET)
    public ModelAndView book(){
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        ModelAndView modelAndView = new ModelAndView("book");
        modelAndView.addObject("bookList",bookService.getAllBooks());
        modelAndView.addObject("user",user);
        return modelAndView;
    }
    @RequestMapping(value = "/book/{bookId}",method = RequestMethod.GET)
    public ModelAndView getBookById(@PathVariable("bookId")long bookId){
        System.out.println(bookId);
        ModelAndView modelAndView = new ModelAndView("selectbook");
        modelAndView.addObject("currentBook",bookService.getById(bookId));
        return modelAndView;
    }
    @RequestMapping(value = "contact",method = RequestMethod.GET)
    public ModelAndView getContact(){
        ModelAndView modelAndView = new ModelAndView("contact");
        return modelAndView;
    }
    @RequestMapping(value = "/admin/users/",method = RequestMethod.GET)
        public  ModelAndView getAdminUsers(){
        ModelAndView modelAndView = new ModelAndView("adminUsers");
        modelAndView.addObject("userList",userService.findAllUsers());
        return modelAndView;
    }
    @RequestMapping(value = "/admin/users/{cardnumber}",method = RequestMethod.GET)
    public  ModelAndView removeAdminUsers(@PathVariable("cardnumber") String cardnumber){
        userService.removeUserByCardnumber(cardnumber);
        ModelAndView modelAndView = new ModelAndView("adminUsers");
        modelAndView.addObject("userList",userService.findAllUsers());
        return modelAndView;
    }
        @RequestMapping(value = "/admin/users/add",method = RequestMethod.GET)
    public  ModelAndView getAdminUsersAdd(){
        ModelAndView modelAndView = new ModelAndView("adminUsersAdd");
        User user = new User();
        modelAndView.addObject("user",user);
            modelAndView.addObject("number",String.class);
        return modelAndView;
    }
    @RequestMapping(value = "/admin/users/edit",method = RequestMethod.POST)
    public  ModelAndView getAdminUsersEdit(@Valid User user){
        ModelAndView modelAndView = new ModelAndView("adminUsersEdit");
        User userExist= userService.findUserByCardnumber(user.getCardnumber());
        if(userExist==null){
            userService.saveUser(user);
        }else {
            System.out.println("brak");
        }
        modelAndView.addObject("user",new User());
        return modelAndView;
    }
}
