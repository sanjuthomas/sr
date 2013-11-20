package com.scrumretro.security.util;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Ragil 
 */
public class TestSecurityContextUtil {

    private SecurityContextUtil securityContextUtil;

    @Before
    public void setUp() {
        securityContextUtil = new SecurityContextUtil();
    }

    @Test
    public void getPrincipal() {
        SecurityContext securityContextMock = mock(SecurityContext.class);
        Authentication authenticationMock = mock(Authentication.class);
        SecurityContextHolder.setContext(securityContextMock);
        when(securityContextMock.getAuthentication()).thenReturn(authenticationMock);
        UserDetails expectedPrincipal = new User("info@scrumretro.com", "password", new ArrayList<GrantedAuthority>());
        when(authenticationMock.getPrincipal()).thenReturn(expectedPrincipal);
        UserDetails actualPrincipal = securityContextUtil.getPrincipal();
        assertEquals(expectedPrincipal, actualPrincipal);
    }

    @Test
    public void getPrincipalWhenAuthenticationIsNull() {
        SecurityContext securityContextMock = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContextMock);
        when(securityContextMock.getAuthentication()).thenReturn(null);
        UserDetails principal = securityContextUtil.getPrincipal();
        assertNull(principal);
    }

    @Test
    public void getPrincipalWhenAuthenticationDoesNotImplementUserDetails() {
    	SecurityContext securityContextMock = mock(SecurityContext.class);
        Authentication authenticationMock = mock(Authentication.class);
        SecurityContextHolder.setContext(securityContextMock);
        when(securityContextMock.getAuthentication()).thenReturn(authenticationMock);
        Object obj = new Object();
        when(authenticationMock.getPrincipal()).thenReturn(obj);
        UserDetails principal = securityContextUtil.getPrincipal();
        assertNull(principal);
    }
}
