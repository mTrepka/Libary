package mTrepka.libary.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Mario on 2017-07-10.
 */
@Entity
public class Book {
    @Id
    @GeneratedValue
    @Column(name = "bookid")
    private long id;
    private String name;
    private String author;
    private String description;
    private long year;
    @ManyToOne
    @JoinColumn(name = "ownerid")
    private User ownerid;
    @OneToMany(mappedBy = "bookborrow")
    private List<BorrowHistory> bookborrow;
    private String currentBorrowId;
    public User getOwnerid() {
        return ownerid;
    }

    public String getCurrentBorrowId() {
        return currentBorrowId;
    }

    public void setCurrentBorrowId(String currentBorrowId) {
        this.currentBorrowId = currentBorrowId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year +
                ", ownerid=" + ownerid.getCardnumber() +
                '}';
    }

    public List<BorrowHistory> getBookborrow() {
        return bookborrow;
    }

    public void setBookborrow(List<BorrowHistory> bookborrow) {
        this.bookborrow = bookborrow;
    }

    public void setOwnerid(User owner) {
        this.ownerid = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }
}
