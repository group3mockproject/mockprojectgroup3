package org.o7planning.project.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("sa".equals(username)) {
			return User.withUsername("sa").password("{noop}sa").roles("USER").build();
		} else {
			throw new UsernameNotFoundException("User not found");
		}
	}
}
