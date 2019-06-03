package mTrepka.libary.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Mario on 2017-07-10.
 */
@Entity(name = "book")
@Data
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
}
