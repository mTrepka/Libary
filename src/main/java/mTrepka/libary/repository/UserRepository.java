package mTrepka.libary.repository;

import mTrepka.libary.domain.Book;
import mTrepka.libary.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	 User findByCardnumber(String login);
	 void deleteRoleByCardnumber(String cardnumber);
}
