package net.classnotfound.pet.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
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
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

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
 
//    @Autowired
//    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
// 
//    @Autowired
//    private MySavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler;
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().
          withUser("admin").password("password").roles("ADMIN", "USER").and().
          withUser("user").password("password").roles("USER");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception { 
		
        http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/login").anonymous()
        .antMatchers(HttpMethod.GET, "/pet/**").access("hasRole('USER')")
        .antMatchers(HttpMethod.DELETE, "/pet/**").access("hasRole('ADMIN')")
        .antMatchers(HttpMethod.POST, "/pet/**").access("hasRole('ADMIN')");
        
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        FormLoginConfigurer<HttpSecurity> formLogin = http.formLogin();
		formLogin.loginProcessingUrl("/login");
		formLogin.usernameParameter("username");
		formLogin.passwordParameter("password");
        formLogin.successHandler(authenticationSuccessHandler);
        formLogin.failureHandler(authenticationFailureHandler);
        //Allow access to HS console
//        http.headers().frameOptions().disable().and().authorizeRequests().antMatchers("/h2-console/**").permitAll();
    }
 
//    @Bean
//    public MySavedRequestAwareAuthenticationSuccessHandler mySuccessHandler(){
//        return new MySavedRequestAwareAuthenticationSuccessHandler();
//    }
    @Bean
    public SimpleUrlAuthenticationFailureHandler myFailureHandler(){
        return new SimpleUrlAuthenticationFailureHandler();
    }
}
