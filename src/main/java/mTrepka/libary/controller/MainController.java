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
    private BookService bookService;
    @Autowired
    private UserService userService;
//    @RequestMapping(value = "/",method = RequestMethod.GET)
//    public  ModelAndView getIndex(){
//        ModelAndView modelAndView = new ModelAndView("");
//        return modelAndView;
//    }
    private static String role(){
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        if(role.equals("[ADMIN]")){
            return"ADMIN";
        }else if(role.equals("[USER]")){
            return "USER";
        }
        return null;
    }
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public  ModelAndView getIndex(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("userRole",role());
        Set<Role> roles = userService.findAllUsers().get(1).getRoles();
        return modelAndView;
    }
    @RequestMapping(value = "/book/",method = RequestMethod.GET)
    public ModelAndView book(){
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        ModelAndView modelAndView = new ModelAndView("book");
        modelAndView.addObject("bookList",bookService.getAllBooks());
        modelAndView.addObject("user",user);
        modelAndView.addObject("userRole",role());
        return modelAndView;
    }
    @RequestMapping(value = "/book/{bookId}",method = RequestMethod.GET)
    public ModelAndView getBookById(@PathVariable("bookId")long bookId){
        System.out.println(bookId);
        ModelAndView modelAndView = new ModelAndView("selectbook");
        modelAndView.addObject("currentBook",bookService.getById(bookId));
        modelAndView.addObject("userRole",role());
        return modelAndView;
    }
    @RequestMapping(value = "contact",method = RequestMethod.GET)
    public ModelAndView getContact(){
        ModelAndView modelAndView = new ModelAndView("contact");
        modelAndView.addObject("userRole",role());
        return modelAndView;
    }
    @RequestMapping(value = "/admin/users/",method = RequestMethod.GET)
        public  ModelAndView getAdminUsers(){
        ModelAndView modelAndView = new ModelAndView("adminUsers");
        modelAndView.addObject("userRole",role());
        modelAndView.addObject("userList",userService.findAllUsers());
        return modelAndView;
    }
    @RequestMapping(value = "/admin/users/{cardnumber}",method = RequestMethod.GET)
    public  ModelAndView removeAdminUsers(@PathVariable("cardnumber") String cardnumber){
        userService.removeUserByCardnumber(cardnumber);
        ModelAndView modelAndView = new ModelAndView("adminUsers");
        modelAndView.addObject("userRole",role());
        modelAndView.addObject("userList",userService.findAllUsers());
        return modelAndView;
    }
        @RequestMapping(value = "/admin/users/add",method = RequestMethod.GET)
    public  ModelAndView getAdminUsersAdd(){
        ModelAndView modelAndView = new ModelAndView("adminUsersAdd");
            modelAndView.addObject("userRole",role());
        User user = new User();
        modelAndView.addObject("user",user);
            modelAndView.addObject("number",String.class);
        return modelAndView;
    }
    @RequestMapping(value = "/admin/users/edit/{user}",method = RequestMethod.GET)
    public  ModelAndView getAdminUsersEdit(@PathVariable("user") String cardnumber){
        ModelAndView modelAndView = new ModelAndView("adminUsersEdit");
        modelAndView.addObject("userRole",role());
        User user = userService.findUserByCardnumber(cardnumber);
        if(user==null){
            modelAndView.addObject("exist",false);
        }else {
            modelAndView.addObject("exist",true);
            modelAndView.addObject("user",user);
            System.out.println(user.toString());
        }
        return modelAndView;
    }
    @RequestMapping(value = "/admin/users/edit/{path}",method = RequestMethod.POST)
    public  ModelAndView postAdminUsersEdit(@Valid User user,@PathVariable("path") String path){
        ModelAndView modelAndView = new ModelAndView("adminUsersEdit");
        modelAndView.addObject("userRole",role());
        User existingUser = userService.findUserByCardnumber(path);
        existingUser.setName(user.getName());
        existingUser.setLastName(user.getLastName());
        existingUser.setActive(user.getActive());
        userService.editAndSave(existingUser);
        // SET CORRECT MODEL AND VIEW
        return modelAndView;
    }
        @RequestMapping(value = "/admin/book/",method = RequestMethod.GET)
    public  ModelAndView getAdminallBook(){
        ModelAndView modelAndView = new ModelAndView("adminAllBook");
        modelAndView.addObject("bookList",bookService.getAllBooks());
        return modelAndView;
    }
        @RequestMapping(value = "/user/settings",method = RequestMethod.GET)
    public  ModelAndView getUserSettings(){
        ModelAndView modelAndView = new ModelAndView("userSettings");
        User user = userService.findUserByCardnumber(SecurityContextHolder.getContext().getAuthentication().getName());
            System.out.println(user.toString());
        modelAndView.addObject("user",user);
        return modelAndView;
    }
    @RequestMapping(value = "/user/settings",method = RequestMethod.POST)
    public  ModelAndView postUserSettings(@Valid User user){
        ModelAndView modelAndView = new ModelAndView("userSettings");
        User existingUser = userService.findUserByCardnumber(SecurityContextHolder.getContext().getAuthentication().getName());
        user.setActive(1);
        user.setCardnumber(existingUser.getCardnumber());
        user.setRoles(existingUser.getRoles());
        if(user.getPassword()==""){
            user.setPassword(existingUser.getPassword());
        }
        userService.editAndSave(user);
        System.out.println(user);
        modelAndView.addObject("user",user);
        return modelAndView;
    }
}
