package net.classnotfound.pet.security;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger LOG = LoggerFactory.getLogger(SecurityJavaConfig.class);
	
	@Autowired
	@Qualifier("ajaxAuthenticationFailureHandler")
	private AuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	private DataSource dataSource;
 
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().
//          withUser("admin").password("password").roles("ADMIN", "USER").and().
//          withUser("user").password("password").roles("USER");
        
        auth.jdbcAuthentication().dataSource(dataSource)
        	.usersByUsernameQuery("select username,password, enabled from users where username=?")
        	.authoritiesByUsernameQuery("select username, role from user_roles where username=?");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception { 
		
        http
        //.csrf().disable()
        .authorizeRequests()
        .antMatchers("/login").anonymous()
        .antMatchers(HttpMethod.GET, "/pet/**").access("hasRole('ROLE_USER')")
        .antMatchers(HttpMethod.POST, "/pet/**").access("hasRole('ROLE_ADMIN')");
        
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        FormLoginConfigurer<HttpSecurity> formLogin = http.formLogin();
		formLogin.loginProcessingUrl("/login");
		formLogin.usernameParameter("username");
		formLogin.passwordParameter("password");
        formLogin.successHandler(authenticationSuccessHandler);
        formLogin.failureHandler(authenticationFailureHandler);
        http.logout().logoutUrl("/logout").logoutSuccessHandler(new RestLogoutSuccessHandler());
        
    }

}