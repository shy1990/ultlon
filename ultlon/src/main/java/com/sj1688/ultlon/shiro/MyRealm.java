package com.sj1688.ultlon.shiro;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.sj1688.ultlon.dao.mysql.UserRepository;
import com.sj1688.ultlon.domain.User;

public class MyRealm extends AuthorizingRealm {

	@Autowired
	private UserRepository userRepository;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			final AuthenticationToken token) throws AuthenticationException {
		final UsernamePasswordToken credentials = (UsernamePasswordToken) token;
		final String username = credentials.getUsername();
		if (username == null) {
			throw new UnknownAccountException("username not provided");
		}
		final User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UnknownAccountException("Account does not exist");
		}
		return new SimpleAuthenticationInfo(user, user.getPassword()
				.toCharArray(), ByteSource.Util.bytes(username), getName());
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			final PrincipalCollection principals) {
		final User user = (User) principals.getPrimaryPrincipal();
		if (user == null) {
			throw new UnknownAccountException("Account does not exist");
		}
		final Set<String> roleNames = new LinkedHashSet<String>();
		final Set<String> permissionNames = new LinkedHashSet<String>();
		roleNames.add(user.getRoler());
		final SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(
				roleNames);
		info.setStringPermissions(permissionNames);
		return info;
	}

}
