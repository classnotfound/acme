package net.classnotfound.pet.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component("ajaxAuthenticationFailureHandler")
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler{
	
	private static final Map<Class<? extends Exception>, String> MESSAGE_MAP = new HashMap<>();
	static {
		MESSAGE_MAP.put(LockedException.class, "Sorry, your account has been locked");
		MESSAGE_MAP.put(DisabledException.class, "Sorry, your account has been disabled");
		MESSAGE_MAP.put(BadCredentialsException.class, "Wrong username or password");
	}

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
		String message = MESSAGE_MAP.get(authException.getClass());
		response.sendError(HttpServletResponse.SC_FORBIDDEN, message!=null?message:"Unknown error");
		
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