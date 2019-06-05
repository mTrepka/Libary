package mTrepka.libary.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate releaseDate;
    private String author;
	@Column(columnDefinition = "varchar(512)")
	private String description;
	@OneToMany
	private List<BorrowHistory> bookBorrow;
	@OneToOne
	private BorrowHistory currentBorrow;
}
