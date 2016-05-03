package net.classnotfound.pet.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.appsensor.core.AppSensorClient;
import org.owasp.appsensor.core.DetectionPoint;
import org.owasp.appsensor.core.DetectionSystem;
import org.owasp.appsensor.core.Event;
import org.owasp.appsensor.core.IPAddress;
import org.owasp.appsensor.core.User;
import org.owasp.appsensor.core.event.EventManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component("ajaxAuthenticationFailureHandler")
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler{

	@Autowired
	private AppSensorClient client;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		EventManager eventManager = client.getEventManager();
		User user = extractUser();
		
		DetectionSystem detectionSystem = new DetectionSystem("Acme petstore");
		DetectionPoint detectionPoint = new DetectionPoint("Authentication", "AE2");
		//use this constructor as it includes the timestamp initialization
		Event event = new Event(user, detectionPoint, detectionSystem);
		eventManager.addEvent(event);
		//response.sendError(HttpServletResponse.SC_FORBIDDEN, "Wrong username or password");
		
	}

	private User extractUser() {
		User user = new User();
		ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest req = sra.getRequest();
		String remoteAddr = req.getRemoteAddr();
		user.setIPAddress(new IPAddress(remoteAddr, null ));

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth !=null) { //trying to get username from Spring Security context
			user.setUsername(auth.getName());
		} else {//trying to get it from the http request parameters
			String username = req.getParameter("username");
			if(username!=null)
				user.setUsername(username);
			else
				user.setUsername("no username found");
			
		}
		return user;
	}
}