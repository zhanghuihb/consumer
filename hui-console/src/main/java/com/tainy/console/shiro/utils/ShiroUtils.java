package com.tainy.console.shiro.utils;

import com.tainy.common.domain.console.ConsoleUser;
import com.tainy.common.util.constant.Constants;
import com.tainy.console.shiro.auth.MyRealm;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.RealmSecurityManager;

import java.util.Collection;

public class ShiroUtils {

	/**
	 * @功能: 获取用户拥有的权限
	 * @作者: yangc
	 * @创建日期: 2014年6月13日 下午12:53:20
	 * @return
	 */
	public static Collection<String> getUserPermission() {
		RealmSecurityManager realmSecurityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		MyRealm myRealm = (MyRealm) realmSecurityManager.getRealms().iterator().next();
		return myRealm.getUserPermission(SecurityUtils.getSubject().getPrincipals());
	}

	/**
	 * @功能: 清除用户认证缓存信息
	 * @作者: yangc
	 * @创建日期: 2014年5月21日 上午10:24:18
	 * @param username
	 */
	public static void clearCachedAuthenticationInfo(String username) {
		if (StringUtils.isNotBlank(username)) {
			RealmSecurityManager realmSecurityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
			MyRealm myRealm = (MyRealm) realmSecurityManager.getRealms().iterator().next();
			myRealm.clearCachedAuthenticationInfo(username);
		}
	}

	/**
	 * @功能: 清除用户权限缓存信息
	 * @作者: yangc
	 * @创建日期: 2014年5月21日 上午10:24:18
	 * @param username
	 */
	public static void clearCachedAuthorizationInfo(String username) {
		if (StringUtils.isNotBlank(username)) {
			RealmSecurityManager realmSecurityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
			MyRealm myRealm = (MyRealm) realmSecurityManager.getRealms().iterator().next();
			myRealm.clearCachedAuthorizationInfo(username);
		}
	}

	/**
	 * @功能: 清除所有权限缓存信息
	 * @作者: yangc
	 * @创建日期: 2014年5月21日 下午7:54:41
	 */
	public static void clearAllCachedAuthorizationInfo() {
		RealmSecurityManager realmSecurityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		MyRealm myRealm = (MyRealm) realmSecurityManager.getRealms().iterator().next();
		myRealm.clearAllCachedAuthorizationInfo();
	}

	/**
	 * @功能: 获取当前登录用户
	 * @作者: yangc
	 * @创建日期: 2014年5月21日 下午7:54:41
	 */
	public static ConsoleUser getCurrentUser() {
		return (ConsoleUser) SecurityUtils.getSubject().getSession().getAttribute(Constants.CURRENT_USER);
	}

	/**
	 * 生成私盐
	 *
	 * @return
	 */
	public static String genSalt(){
		return new SecureRandomNumberGenerator().nextBytes().toHex();
	}

	/**
	 * 生成加密密码
	 *
	 * @param algorithmName
	 * @param username
	 * @param password
	 * @param salt
	 * @param hashIterations
	 * @return
	 */
	public static String pwdEncryption(String algorithmName, String username, String password, String salt, int hashIterations){
		return new SimpleHash(algorithmName, password, username + salt, hashIterations).toHex();
	}
}
