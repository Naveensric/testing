/*

package com.testing.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@Service
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{

	@Autowired
	private CommonUserDetailsService userDetailsService;
	
	private BCryptPasswordEncoder encoder;
	
	public SecurityConfigurer(BCryptPasswordEncoder encoder) {
		this.encoder = encoder;
	}

	@Override
	protected  void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.GET,"/api/**").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
	}
}	

*/	
	
	
/*	
	@Override
	protected UserDetailsService userDetailsService() {
		UserDetails user =User.builder().username("chan")
										.password(encoder.encode("chan"))
										.roles("USER").build();
		UserDetails admin =User.builder().username("admin")
										.password(encoder.encode("admin"))
										.roles("ADMIN").build();
		return new InMemoryUserDetailsManager(user,admin);
	}
*/
	


