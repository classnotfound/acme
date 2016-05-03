package net.classnotfound.pet.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
/**
 * This handler only is only for information logging purpose
 * @author Hervé Roussel (www.classnotfound.net)
 *
 */
@Component
public class RestLogoutSuccessHandler implements LogoutSuccessHandler{

	private static final Logger LOG = LoggerFactory.getLogger(RestLogoutSuccessHandler.class);
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		LOG.info("User has succesfully log out!!");
		
	}

}
