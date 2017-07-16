package mTrepka.libary.domain;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Mario on 2017-07-16.
 */
@Entity(name = "borrow_history")
public class BorrowHistory {
    @Id
    @GeneratedValue
    private long id;
    private Date borrow_date;
    private Date return_date;
    @ManyToOne
    @JoinColumn(name = "userborrow")
    private User userborrow;

    @Override
    public String toString() {
        return "BorrowHistory{" +
                "id=" + id +
                ", borrow_date=" + borrow_date +
                ", return_date=" + return_date +
                ", userborrow=" + userborrow +
                ", bookborrow=" + bookborrow +
                '}';
    }

    public Book getBookborrow() {
        return bookborrow;
    }

    public void setBookborrow(Book bookborrow) {
        this.bookborrow = bookborrow;
    }

    @ManyToOne
    @JoinColumn(name = "bookborrow")
    private Book bookborrow;

    public User getUserborrow() {
        return userborrow;
    }

    public void setUserborrow(User userborrow) {
        this.userborrow = userborrow;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(Date borrow_date) {
        this.borrow_date = borrow_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

}
