package mTrepka.libary.service;


import mTrepka.libary.domain.User;

import java.util.List;

public interface UserService {
	 User findUserByCardnumber(String cardnumber);
	 void saveUser(User user);
	 List<User> findAllUsers();
	 void removeUserByCardnumber(String cardnumber);
	 void editAndSave(User user);
}
