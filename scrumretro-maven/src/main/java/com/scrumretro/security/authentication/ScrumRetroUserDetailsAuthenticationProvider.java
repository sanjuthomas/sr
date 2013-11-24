package com.scrumretro.security.authentication;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.scrumretro.repository.UserRepository;
import com.scrumretro.repository.model.User;

/**
 * Custom UserDetailsAuthenticationProvider for ScrumRetro.com. 
 * This UserDetailsAuthenticationProvider connect to mongodb and do authentication.
 * 
 * If you want to replace the default authentication provider you may write another 
 * authentication provider like an LDAP authentication provider.
 * 
 * @author Sanju Thomas
 *
 */
public class ScrumRetroUserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void setUserRepository(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void setbCryptPasswordEncoder(final BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void additionalAuthenticationChecks(final UserDetails userDetails, 
			final UsernamePasswordAuthenticationToken authentication)throws AuthenticationException {
		
	}

	@Override
	protected UserDetails retrieveUser(final String username, 
			final UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		
		User user = null; 
		try{		
			user = userRepository.findByUserId(username);
		}catch(Exception e){
			throw new RuntimeException("Unknown error occurred while accessing finding the user "+username);
		}
		
		if(null == user){
			throw new UsernameNotFoundException(username + "is not registered with scrumretro.com");
		}
		
		final Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		final SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
		grantedAuthorities.add(simpleGrantedAuthority);
		
		return new ScrumRetroUser(user.getUserId(), user.getPassword(),
				user.getActive(), user.getUserDetail().getFirstName(), user.getUserDetail().getLastName(), 
				user.getUserDetail().getOrganization(), grantedAuthorities);
	}

}
