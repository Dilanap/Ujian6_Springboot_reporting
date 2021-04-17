package com.kuliah.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.kuliah.main.entity.AdminUser;

import com.kuliah.main.repository.AdminUserRepository;


public class CustomUserDetailServices implements UserDetailsService {

	@Autowired
	private AdminUserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		AdminUser user = userRepo.findByUsername(username);		
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomUserDetail(user);
	}

}