package com.project.dropeditions.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.dropeditions.entities.AppUser;
import com.project.dropeditions.services.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		AppUser user = userService.findUserByEmail(email);
		if(user == null) throw new UsernameNotFoundException(email);
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(r->{
			
			authorities.add(new SimpleGrantedAuthority(r.toString()));//toString converts enum to string
		});
			
		return new User(user.getEmail(),user.getPassword(),authorities);
	}

}
