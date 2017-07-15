package mTrepka.libary.service;

import java.sql.Connection;
import java.util.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import mTrepka.libary.domain.Role;
import mTrepka.libary.domain.User;
import mTrepka.libary.repository.RoleRepository;
import mTrepka.libary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;


@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	DataSource dataSource;

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User findUserByCardnumber(String cardnumber) {
		return userRepository.findByCardnumber(cardnumber);
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
		userRepository.delete(userRepository.findByCardnumber(cardnumber));
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(user.getPassword());
        user.setActive(1);
        user.setPassword(user.getName().hashCode()+"12"+new Date().hashCode());
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

}
