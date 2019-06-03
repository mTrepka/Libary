package mTrepka.libary.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
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
	private long isbn;
	private long pageAmount;
	private LocalDate releaseDate;
    private String author;
    private String description;
    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;
	@OneToMany(mappedBy = "bookBorrow")
	private List<BorrowHistory> bookBorrow;
	@OneToOne
	private BorrowHistory currentBorrow;
}
