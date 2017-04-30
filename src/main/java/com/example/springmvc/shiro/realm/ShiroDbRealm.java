package com.example.springmvc.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.springmvc.front.model.User;
import com.example.springmvc.front.services.IUserService;

public class ShiroDbRealm extends AuthorizingRealm {

	@Autowired
	private IUserService userService;

	public ShiroDbRealm() {
		super();
	}

	/**
	 * authenticate user info
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user = userService.findUserByLoginName(token.getUsername());
		if (user != null) {
			return new SimpleAuthenticationInfo(user.getUsername(), DigestUtils.md5(user.getPassword()), getName());
		} else {
			throw new AuthenticationException();
		}
	}

	/**
	 * authorize user's rights
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Set<String> roleNames = new HashSet<String>();
		Set<String> permissions = new HashSet<String>();
		roleNames.add("admin");
		roleNames.add("user");
		permissions.add("user.do?myjsp");
		permissions.add("login.do?main");
		permissions.add("login.do?logout");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
		info.setStringPermissions(permissions);
		return info;
	}

	/**
	 * update user info in cache
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * clear user info in cache
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

}
