package mTrepka.libary.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue
	private String cardnumber;
	@Transient
	private String password;
	@NotEmpty
	private String name;
	@NotEmpty
	private String lastName;
	private int active;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	@OneToMany(mappedBy = "ownerid")
	private List<Book> books;
	@OneToMany(mappedBy = "userborrow")
	private List<BorrowHistory> borrowHistory;
}
