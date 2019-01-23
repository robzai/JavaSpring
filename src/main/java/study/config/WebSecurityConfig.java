package study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;

// Username:ben Password:benspassword. 
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()	// Spring security was waiting for csrf token for POST requests because CSRF protection is enabled by default in spring security.
			.authorizeRequests()
				.antMatchers("/people/**").permitAll() // So in order to make this line work, you must provide the csrf token in POST request 
													   // OR you can temporarily turn CSRF protection off (but you should enable it again before going to production
													   // as this is a serious attack)
				.anyRequest().fullyAuthenticated()
				.and()
			.formLogin();
	}

	//You need an LDAP server. Spring Boot’s provides autoconfiguration for an embedded server written in pure Java
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.ldapAuthentication()	//ldapAuthentication() configures things where the username at the login form is plugged into {0} such that it searches uid={0},ou=people,dc=springframework,dc=org in the LDAP server. 
				.userDnPatterns("uid={0},ou=people")
				.groupSearchBase("ou=groups")
				.contextSource()
					.url("ldap://localhost:8389/dc=springframework,dc=org")
					.and()
				.passwordCompare()	//passwordCompare() configures the encoder and the name of the password’s attribute.
					.passwordEncoder(new LdapShaPasswordEncoder())
					.passwordAttribute("userPassword");
	}

}
