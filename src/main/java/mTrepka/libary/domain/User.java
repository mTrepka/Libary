package mTrepka.libary.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class User {
	@Id
	private String cardNumber;
	private String password;
	@NotEmpty
	private String name;
	@NotEmpty
	private String lastName;
	private int active;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	@OneToMany
	private List<Book> books;
	@OneToMany
	private List<BorrowHistory> borrowHistory;
}
