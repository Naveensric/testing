/*
package com.testing.config;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.testing.entities.Role;
import com.testing.entities.User;
import com.testing.repositories.UserRepository;

@Service
public class CommonUserDetailsService implements UserDetailsService{

	private UserRepository user_repo;

	public CommonUserDetailsService(UserRepository user_repo) {
		this.user_repo = user_repo;
	}

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		User user = user_repo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(
				()-> new UsernameNotFoundException("This "+usernameOrEmail+" Username or Email Not Found"));
		
		return new org.springframework.security.core.userdetails.User(
			user.getEmail(),user.getPassword(),
			mapRolesToAuthorities(user.getRoles())
			
			);
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
		return roles.stream().map( abcd -> new SimpleGrantedAuthority( abcd.getRoleName())).collect(Collectors.toList());
	}
	
	
	
}
*/