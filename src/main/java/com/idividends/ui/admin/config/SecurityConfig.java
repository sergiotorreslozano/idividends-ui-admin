package com.idividends.ui.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.vaadin.spring.http.HttpService;
import org.vaadin.spring.security.annotation.EnableVaadinSharedSecurity;
import org.vaadin.spring.security.config.VaadinSharedSecurityConfiguration;
import org.vaadin.spring.security.shared.VaadinAuthenticationSuccessHandler;
import org.vaadin.spring.security.shared.VaadinSessionClosingLogoutHandler;
import org.vaadin.spring.security.shared.VaadinUrlAuthenticationSuccessHandler;
import org.vaadin.spring.security.web.VaadinRedirectStrategy;

@Configuration
@EnableWebSecurity
@EnableVaadinSharedSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, proxyTargetClass = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final static String AUTH_USER = "AUTH_USER";
	private final static String AUTH_PASSWORD = "AUTH_PASSWORD";
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().
				// withUser("user").password("password1").roles("USER")
				withUser(System.getenv(AUTH_USER)).password(System.getenv(AUTH_PASSWORD)).roles("USER")
			;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); // Use Vaadin's built-in CSRF protection instead
		http.authorizeRequests().antMatchers("/login/**").anonymous().antMatchers("/vaadinServlet/UIDL/**").permitAll()
				.antMatchers("/vaadinServlet/HEARTBEAT/**").permitAll().anyRequest().authenticated();
		http.httpBasic().disable();
		http.formLogin().disable();
		// Remember to add the VaadinSessionClosingLogoutHandler
		http.logout().addLogoutHandler(new VaadinSessionClosingLogoutHandler()).logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout").permitAll();
		http.exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));
		// Instruct Spring Security to use the same RememberMeServices as
		// Vaadin4Spring. Also remember the key.
		http.rememberMe().rememberMeServices(rememberMeServices()).key("myAppKey");
		// Instruct Spring Security to use the same authentication strategy as
		// Vaadin4Spring
		http.sessionManagement().sessionAuthenticationStrategy(sessionAuthenticationStrategy());
	}

	/**
	 * The {@link AuthenticationManager} must be available as a Spring bean for
	 * Vaadin4Spring.
	 */
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * The {@link RememberMeServices} must be available as a Spring bean for
	 * Vaadin4Spring.
	 */
	@Bean
	public RememberMeServices rememberMeServices() {
		return new TokenBasedRememberMeServices("myAppKey", userDetailsService());
	}

	/**
	 * The {@link SessionAuthenticationStrategy} must be available as a Spring
	 * bean for Vaadin4Spring.
	 */
	@Bean
	public SessionAuthenticationStrategy sessionAuthenticationStrategy() {
		return new SessionFixationProtectionStrategy();
	}

	@Bean(name = VaadinSharedSecurityConfiguration.VAADIN_AUTHENTICATION_SUCCESS_HANDLER_BEAN)
	VaadinAuthenticationSuccessHandler vaadinAuthenticationSuccessHandler(HttpService httpService,
			VaadinRedirectStrategy vaadinRedirectStrategy) {
		return new VaadinUrlAuthenticationSuccessHandler(httpService, vaadinRedirectStrategy, "/admin");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/VAADIN/**");
	}


}
