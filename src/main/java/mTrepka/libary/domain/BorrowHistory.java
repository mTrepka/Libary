package mTrepka.libary.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Mario on 2017-07-16.
 */
@Entity(name = "borrow_history")
@Data
public class BorrowHistory {
    @Id
    @GeneratedValue
    private long id;
    private Date borrow_date;
    private Date return_date;
    @ManyToOne
    @JoinColumn(name = "userborrow")
    private User userborrow;
    @ManyToOne
    @JoinColumn(name = "bookborrow")
    private Book bookborrow;
}
