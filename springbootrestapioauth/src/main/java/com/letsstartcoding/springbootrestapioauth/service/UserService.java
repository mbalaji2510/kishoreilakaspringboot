package com.letsstartcoding.springbootrestapioauth.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserService<T> implements UserDetailsService {
	
	/*@Autowired
	private UserRepository userRepository;*/

	/*@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//return userRepository.findOneByUsername(username);
		return new User("kishore", "kishore", new ArrayList<Object>());
	}*/
	
	
	
	@SuppressWarnings("unchecked")
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		List<T> list = new ArrayList<T>();
		
		return new User("kishore", "kishore", (Collection<? extends GrantedAuthority>) list);
	}
}