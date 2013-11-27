package com.scrumretro.repository.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import com.scrumretro.security.util.SecurityContextUtil;

/**
 * 
 * Auditor class for scrumretro.com
 * 
 * @author Sanju Thomas
 *
 */
public class ScrumRetroAuditor implements AuditorAware<String> {
	
	@Autowired
	private SecurityContextUtil securityContextUtil;

	public void setSecurityContextUtil(final SecurityContextUtil securityContextUtil) {
		this.securityContextUtil = securityContextUtil;
	}

	@Override
	public String getCurrentAuditor() {
		return securityContextUtil.getUserProfile().getUsername();
	}
}
