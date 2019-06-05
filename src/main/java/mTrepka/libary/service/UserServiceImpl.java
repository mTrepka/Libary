package mTrepka.libary.service;

import lombok.RequiredArgsConstructor;
import mTrepka.libary.domain.User;
import mTrepka.libary.repository.RoleRepository;
import mTrepka.libary.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.beans.Transient;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	private final DataSource dataSource;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User findUserByCardnumber(String cardnumber) {
		return userRepository.findByCardNumber(cardnumber);
	}

	@Override
	public void removeUserByCardnumber(String cardnumber) {
		try {
			Connection connection = dataSource.getConnection();
			connection.prepareStatement("DELETE FROM `user_role` WHERE `user_role`.`user_id` = "+cardnumber).execute();
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
		userRepository.delete(userRepository.findByCardNumber(cardnumber));
	}


	@Override
	public String getCurrentUserRole() {
		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		if (role.equals("[ADMIN]")) {
			return "ADMIN";
		} else if (role.equals("[USER]")) {
			return "USER";
		}
		return null;
	}

	@Override
	public boolean isUserAuthenticated() {
		return !(this.getCurrentUserRole() == null);
	}

	@Override
	public User editUserByCardNumber(String cardNumber, User newUserData) {
		User currentUser = userRepository.findByCardNumber(cardNumber);
		System.out.println(currentUser);
		changeUserByUserData(currentUser, newUserData);
		return userRepository.save(currentUser);
	}

	@Transient
	private void changeUserByUserData(User user, User userData) {
		if (!user.getPassword().equals(userData.getPassword()))
			user.setPassword(userData.getPassword());
		if (!user.getName().equals(userData.getName()))
			user.setName(userData.getName());
		if (!user.getLastName().equals(userData.getLastName()))
			user.setLastName(userData.getLastName());
	}

	@Override
	public User editCurrentUser(User userData) {
		return editUserByCardNumber(getCurrentUser().getCardNumber(), userData);
	}

	@Override
	public User getCurrentUser() {
		return userRepository.findByCardNumber(SecurityContextHolder.getContext().getAuthentication().getName());
	}

	@Override
	public String activeUser(String userId) {
		User user = findUserByCardnumber(userId);
		if (user.getActive() == 1) {
			user.setActive(0);
			userRepository.save(user);
			return "User is deactivated";
		} else {
			user.setActive(1);
			userRepository.save(user);
			return "User is activated";
		}
	}

	@Override
	public void createNewUser(User user) {
		user.setPassword(user.getCardNumber());
		user.setBorrowHistory(new ArrayList<>());
		user.setRoles(new HashSet<>());
		user.getRoles().add(roleRepository.findByRole("USER"));
		user.setActive(0);
		userRepository.save(user);
	}
}
