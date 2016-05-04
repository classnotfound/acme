package net.classnotfound.pet.security.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.classnotfound.pet.security.domain.User;
import net.classnotfound.pet.security.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public void disableUser(String userId) {
		User user = userRepository.findOne(userId);
		user.setEnabled(false);

	}
	
	@Override
	@Transactional
	public User findUser(String userId) {
		User user = userRepository.findOne(userId);
		return user;

	}
	

}
