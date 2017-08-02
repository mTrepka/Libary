package mTrepka.libary.test;


import mTrepka.libary.service.BookService;
import mTrepka.libary.service.BorrowHistoryService;
import mTrepka.libary.service.UserService;
import mTrepka.libary.utility.NavigationBar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by Mario on 2017-07-20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private BorrowHistoryService borrowHistoryService;
    @Autowired
    NavigationBar defaultNavigationBar;
    @Autowired
    NavigationBar userNavigatonBar;
    @Autowired
    NavigationBar adminNavigationBar;
    @Test
    public void repositoryNotNull(){
        assertThat(userService).isNotNull();
        assertThat(bookService).isNotNull();
        assertThat(borrowHistoryService).isNotNull();
    }
}
