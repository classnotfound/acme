package net.classnotfound.pet.security.appsensor.response;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void logout(User user) {
		LOG.info("Invalidate user session: {}", user.getUsername());
		HttpSession session = request.getSession(false);
		if(session!=null)
			session.invalidate();
		else
			LOG.warn("User not logged in, cannot invalidate session: {}", user!=null?user.getUsername():null);
	}

	@Override
	public void disable(User user) {
		LOG.info("Disabling user: {}", user.getUsername());
		userService.disableUser(user.getUsername());
		
	}

}
