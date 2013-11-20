package com.scrumretro.security.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author Ragil 
 */
@Component
public class SecurityContextUtil {
    

    public UserDetails getPrincipal() {
        UserDetails principal = null;

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (null != authentication) {
            final Object currentPrincipal = authentication.getPrincipal();
            if (currentPrincipal instanceof UserDetails) {
                principal = (UserDetails) currentPrincipal;
            }
        }

        return principal;
    }
}
