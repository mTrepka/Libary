package mTrepka.libary.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "cardnumber")
	private String cardnumber;
	@Column(name = "password")
	@Transient
	private String password;
	@Column(name = "name")
	@NotEmpty
	private String name;
	@Column(name = "last_name")
	@NotEmpty
	private String lastName;
	@Column(name = "active")
	private int active;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	@OneToMany(mappedBy = "ownerid")
	private List<Book> books;
	@OneToMany(mappedBy = "userborrow")
	private List<BorrowHistory> borrowHistory;

	@Override
	public String toString() {
		return "User{" +
				"cardnumber='" + cardnumber + '\'' +
				", password='" + password + '\'' +
				", name='" + name + '\'' +
				", lastName='" + lastName + '\'' +
				", active=" + active +
				", roles=" + roles +
				", books=" + books +
				", borrowHistory=" + borrowHistory +
				'}';
	}

	public List<BorrowHistory> getBorrowHistory() {
		return borrowHistory;
	}

	public void setBorrowHistory(List<BorrowHistory> borrowHistory) {
		this.borrowHistory = borrowHistory;
	}


	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public String getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
