package com.sj1688.ultlon.config;

import org.apache.shiro.SecurityUtils;
import org.springframework.data.domain.AuditorAware;
public class AuditorAwareImpl implements AuditorAware<Object> {

	@Override
	public Object getCurrentAuditor() {
		return SecurityUtils.getSubject().getPrincipal();
	}
	
}
