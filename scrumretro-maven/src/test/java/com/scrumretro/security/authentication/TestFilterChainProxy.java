package com.scrumretro.security.authentication;

import java.util.List;

import javax.servlet.Filter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author Sanju Thomas
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml", "classpath:test-applicationSecurity.xml"})
public class TestFilterChainProxy {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private FilterChainProxy filterChainProxy;
	
	@Before
	public void setUp(){}
	
	@Test
	public void shouldNotGoViaProxy(){
		final  List<Filter> filters = filterChainProxy.getFilters("/resources/**");
		Assert.assertTrue(filters.size() == 0);
	}

}
