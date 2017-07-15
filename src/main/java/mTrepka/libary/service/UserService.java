package mTrepka.libary.service;


import mTrepka.libary.domain.User;

import java.util.List;

public interface UserService {
	public User findUserByCardnumber(String cardnumber);
	public void saveUser(User user);
	public List<User> findAllUsers();
	public void removeUserByCardnumber(String cardnumber);
}
