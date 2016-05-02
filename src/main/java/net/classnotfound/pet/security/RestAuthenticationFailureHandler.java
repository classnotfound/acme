package net.classnotfound.pet.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component("ajaxAuthenticationFailureHandler")
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler{

//	@Autowired
//	private AppSensorClient client;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
//		EventManager eventManager = client.getEventManager();
//		Event event = new Event();
//		DetectionPoint detectionPoint = new DetectionPoint("login_err", "Wrong username or password");
//		event.setDetectionPoint(detectionPoint);
//		eventManager.addEvent(event);
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "Wrong username or password");
		
	}
}