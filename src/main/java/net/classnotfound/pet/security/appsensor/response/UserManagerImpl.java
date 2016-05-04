package net.classnotfound.pet.security.appsensor.response;

import javax.inject.Named;

import org.owasp.appsensor.core.User;
import org.owasp.appsensor.core.response.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import net.classnotfound.pet.security.service.UserService;

@Primary
@Named
public class UserManagerImpl implements UserManager{

	private Logger LOG = LoggerFactory.getLogger(UserManagerImpl.class);
	
	@Autowired
	private UserService userService;
	
	@Override
	public void logout(User user) {
		// TODO Auto-generated method stub
		LOG.info("Logout user: {}", user.getUsername());
	}

	@Override
	public void disable(User user) {
		LOG.info("Disabling user: {}", user.getUsername());
		userService.disableUser(user.getUsername());
		
	}

}
