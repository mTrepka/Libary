package mTrepka.libary.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * Created by Mario on 2017-07-16.
 */
@Entity(name = "borrow_history")
@Data
public class BorrowHistory {
    @Id
    @GeneratedValue
    private long id;
	private LocalDate borrow_date;
	private LocalDate return_date;
	private String status;
	private String userCardNumber;
	private long bookId;
}
