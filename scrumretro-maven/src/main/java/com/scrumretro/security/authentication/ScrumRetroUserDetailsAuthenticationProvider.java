package com.scrumretro.security.authentication;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
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
 * If you want to replace the UserDetailsAuthenticationProvider  authentication provider 
 * you may write another authentication provider like an LDAP authentication provider.
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
		
		if (null == authentication.getCredentials()) {
			logger.debug("No password is provided for "+userDetails.getUsername());
			throw new BadCredentialsException("No password is provided for "+userDetails.getUsername());
		}
		
		if(!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), userDetails.getPassword())){
			 logger.debug("Authentication failed for user "+userDetails.getUsername());
			 throw new BadCredentialsException("Incorrect password is provided for "+userDetails.getUsername());
		}
		
		logger.info("Authentication successfull for user "+userDetails.getUsername());
	}

	@Override
	protected UserDetails retrieveUser(final String username, 
			final UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		
		User user = null; 
		try{		
			user = userRepository.findByUserId(username);
		}catch(final Exception e){
			logger.error(e);
			throw new RuntimeException("Unknown error occurred while finding the user "+username, e);
		}
		
		if(null == user){
			logger.debug(user + "is not found in the scrumretro.com");
			throw new UsernameNotFoundException(username + "is not registered with scrumretro.com");
		}
		
		logger.info(username + " is found in scrumretro.com");
		final Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		final SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
		grantedAuthorities.add(simpleGrantedAuthority);
		
		return new ScrumRetroUser(user.getUserId(), user.getPassword(),
				user.getActive(), user.getUserDetail().getFirstName(), user.getUserDetail().getLastName(), 
				user.getUserDetail().getOrganization(), grantedAuthorities);
	}

}
