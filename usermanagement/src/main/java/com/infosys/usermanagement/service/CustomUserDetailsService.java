package com.infosys.usermanagement.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.infosys.usermanagement.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class.getName());
	
	@Autowired
    private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		logger.info("Entering requestinto " +this.getClass() + "# loadUserByUsername() method");
		try {
			User appUser=userRepository.findByUsername(userName);
			
			boolean enabled = true;
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;
			long userId =-999;
			String password = "";
			int roleId =-999;
			
			if(appUser !=null){
				// load user details from database through userid
				//userDetails=iUserDetailsDAO.getUserDetails(appUser.getUserId());
				//userId = appUser.getUserId;
				password = appUser.getPassword();
			}
			///UserRole userRole = iUserRoleDAO.getUserRoles(userId);
			/*if(userRole !=null){
				roleId = (int)userRole.getRoleId();
				logger.info("roleId : "+roleId);
			}*/
			
			return new User(	
					userName, 
					password,
					enabled,
					accountNonExpired,
					credentialsNonExpired,
					accountNonLocked,
					getAuthorities(roleId));
		}catch (Exception e) {
			e.printStackTrace();
			//throw new RuntimeException(e);
		}//end try-catch
		
		return new User("kishore", "kishore", new ArrayList<>());
	}

	/**
	 * Retrieves a collection of {@link GrantedAuthority} based on a numerical role
	 * @param role the numerical role
	 * @return a collection of {@link GrantedAuthority
	 */
	public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}// end
	
	/**
	 * Converts a numerical role to an equivalent list of roles
	 * @param role the numerical role
	 * @return list of roles as as a list of {@link String}
	 */
	public List<String> getRoles(Integer role) {
		List<String> roles = new ArrayList<String>();
		
		if (role.intValue() == 1) {
			roles.add("ROLE_USER");
			roles.add("ROLE_ADMIN");
		} else if (role.intValue() == 2) {
			roles.add("ROLE_USER");
		} else if(role.intValue()==-999){
			roles.add("ROLE_GUEST");
		}//end if-else-if
		return roles;
	}// end
	
	/**
	 * Wraps {@link String} roles to {@link SimpleGrantedAuthority} objects
	 * @param roles {@link String} of roles
	 * @return list of granted authorities
	 */
	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}// end for  loop
		return authorities;
	}// end
}
