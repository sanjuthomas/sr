package com.scrumretro.security.authentication;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.scrumretro.repository.UserRepository;
import com.scrumretro.repository.model.User;

/**
 * 
 * @author Ragil
 *
 */
public class ScrumRetroUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {
		final User user = userRepository.findByUserId(username);
		final Collection<GrantedAuthority> gaCol = new ArrayList<GrantedAuthority>();
		final SimpleGrantedAuthority ga = new SimpleGrantedAuthority("ROLE_USER");
		gaCol.add(ga);
		final ScrumRetroUser scrumretroUser = new ScrumRetroUser(user.getUserId(),user.getPassword(),user.getUserDetail().getFirstName(),user.getUserDetail().getLastName(),
				user.getUserDetail().getOrganization(),gaCol);
		return scrumretroUser;
	}

	
	public void setUserRepository(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}
