package com.scrumretro.security.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.scrumretro.security.authentication.ScrumRetroUser;

/**
 * @author Ragil 
 */
@Component
public class SecurityContextUtil {
    

	/**
	 * This method shall return the current user profile. If SecurityContext doesn't have a user
	 * then an UNKNOWN user would be returned to client. 
	 * 
	 * @return
	 */
    public ScrumRetroUser getUserProfile() {
    	
    	ScrumRetroUser scrumRetroUser = null;
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {
            final Object currentPrincipal = authentication.getPrincipal();
            if (currentPrincipal instanceof UserDetails) {
            	scrumRetroUser = (ScrumRetroUser) currentPrincipal;
            }
        }
        return null == scrumRetroUser ?  ScrumRetroUser.UNKNOWN : scrumRetroUser; 
    }
}
