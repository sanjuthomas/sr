package com.scrumretro.security.authentication;

import static junit.framework.Assert.assertEquals;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;

/**
 * 
 * @author Ragil
 *
 */
public class TestRestAuthenticationEntryPoint {

	private RestAuthenticationEntryPoint authenticationEntryPoint;

    @Before
    public void setUp() {
        authenticationEntryPoint = new RestAuthenticationEntryPoint();
    }

    @Test
    public void commence() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        AuthenticationException ex = new AuthenticationCredentialsNotFoundException("");
        authenticationEntryPoint.commence(request, response, ex);
        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
    }
}
