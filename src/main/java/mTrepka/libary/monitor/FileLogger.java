package mTrepka.libary.monitor;

import mTrepka.libary.domain.Book;
import mTrepka.libary.domain.User;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

/**
 * Created by Mario on 2017-07-11.
 */
@Aspect
@Component
public class FileLogger {

    @Autowired
    private Logger logger;

    @AfterReturning(pointcut = "execution(* mTrepka.libary.controller.MainController.postAdminUsersEdit(..))",returning="retVal")
    public void userOperationLogger(Object retVal){
        ModelAndView modelAndView = (ModelAndView) retVal;
	    String user = ((User) modelAndView.getModel().get("user")).getCardNumber();
        logger.info("User '"+user+"', eddited by "+SecurityContextHolder.getContext().getAuthentication().getName());
    }
    @AfterReturning(pointcut = "execution(* mTrepka.libary.controller.MainController.postAdminBookEdit(..))",returning="retVal")
    public void bookOperationLogger(Object retVal){
        ModelAndView modelAndView = (ModelAndView) retVal;
        Long bookId = ((Book)modelAndView.getModel().get("book")).getId();
        logger.info("Book '"+bookId+"', eddited by "+SecurityContextHolder.getContext().getAuthentication().getName());
    }

}
