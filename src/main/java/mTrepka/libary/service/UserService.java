package mTrepka.libary.service;


import mTrepka.libary.domain.User;

import java.util.List;

public interface UserService {
	User findUserByCardnumber(String cardnumber);

	List<User> findAllUsers();

	void removeUserByCardnumber(String cardnumber);

	User editCurrentUser(User userData);

	String getCurrentUserRole();

	boolean isUserAuthenticated();

	User editUserByCardNumber(String cardNumber, User newUserData);

	User getCurrentUser();

	void createNewUser(User user);

	String activeUser(String userId);
}
