package net.classnotfound.pet.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CsrfInjectorFilter extends OncePerRequestFilter {

	private static final String XSRF_TOKEN = "XSRF-TOKEN";
	private static final Logger LOG = LoggerFactory.getLogger(CsrfInjectorFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
		if (csrfToken != null) {
			Cookie cookie = new Cookie(XSRF_TOKEN, csrfToken.getToken());
			//mandatory to avoid duplicate cookies in browser (at least FF)
			cookie.setPath("/");
			response.addCookie(cookie);
		}else {
			LOG.debug("No csrf token found!!");
		}
		chain.doFilter(request, response);
	}



}
