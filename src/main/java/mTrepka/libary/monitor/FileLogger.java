package mTrepka.libary.monitor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by Mario on 2017-07-11.
 */
@Aspect
@Component
public class FileLogger {

    @Before("execution(* mTrepka.libary.service.BookService.*(..))")
    public void bookervice() {
        System.out.println("Aktywny BookService przez: "+ SecurityContextHolder.getContext().getAuthentication().getName());
    }



}
