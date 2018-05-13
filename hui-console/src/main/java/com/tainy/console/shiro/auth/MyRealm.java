package com.tainy.console.shiro.auth;

import com.alibaba.fastjson.JSON;
import com.tainy.common.domain.console.ConsoleResource;
import com.tainy.common.domain.console.ConsoleRole;
import com.tainy.common.domain.console.ConsoleUser;
import com.tainy.common.util.StringUtil;
import com.tainy.console.mapper.ConsoleResourceMapper;
import com.tainy.console.mapper.ConsoleRoleMapper;
import com.tainy.console.service.ConsoleUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyRealm extends AuthorizingRealm {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyRealm.class);

	@Autowired
	private ConsoleUserService consoleUserService;

	@Autowired
	private ConsoleRoleMapper consoleRoleMapper;

	@Autowired
	private ConsoleResourceMapper consoleResourceMapper;

	/**
	 * 授权
	 * @param principalCollection
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		String currentUsername = (String)super.getAvailablePrincipal(principalCollection);
		LOGGER.info("当前用户名为currentUsername=" + currentUsername);
		//角色集合
		List<String> roleList = new ArrayList<>();
		//权限集合
		List<String> permissionList = new ArrayList<>();
		//从数据库中获取当前用户的详细信息
		ConsoleUser consoleUser = consoleUserService.findUserByUsername(currentUsername);
		if(consoleUser != null){
			//实体类UpmsUser中包含角色信息
			if(!StringUtil.isEmpty(consoleUser.getRoleIds())){
				//获取当前登录用户的角色
				String[] roleIdArr = consoleUser.getRoleIds().split(",");
				ConsoleRole role = null;
				for (int i = 0; i < roleIdArr.length; i++) {
					role = consoleRoleMapper.selectByPrimaryKey(Integer.parseInt(roleIdArr[i]));
					if(role != null){
						roleList.add(role.getRole());
						if(!StringUtil.isEmpty(role.getResourceIds())){
							//获取权限
							if(!StringUtil.isEmpty(role.getResourceIds())){
								String[] resourceIdArr = role.getResourceIds().split(",");
								ConsoleResource resource = null;
								for (int j = 0; j < resourceIdArr.length; j++) {
									resource = consoleResourceMapper.selectByPrimaryKey(Integer.parseInt(resourceIdArr[j]));
									if(resource != null){
										if(!StringUtil.isEmpty(resource.getPermission())){
											permissionList.add(resource.getPermission());
										}
									}
								}
							}
						}
					}
				}
			}
		}else{
			LOGGER.info("用户信息为空, 用户名为username=" + currentUsername);
			throw new AuthorizationException();
		}

		//为当前用户设置角色和权限
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addRoles(roleList);
		simpleAuthorizationInfo.addStringPermissions(permissionList);

		return simpleAuthorizationInfo;
	}

	/**
	 * 认证
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		//获取基于用户名和密码的令牌
		//实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		LOGGER.info("基于用户名和密码的令牌token:" + JSON.toJSONString(usernamePasswordToken));
		ConsoleUser consoleUser = consoleUserService.findUserByUsername(usernamePasswordToken.getUsername());
		LOGGER.info("用户{}" + JSON.toJSONString(consoleUser));
		if(consoleUser != null){
			if(consoleUser.getStatus() == 2){
				LOGGER.info("用户被锁定" + JSON.toJSONString(consoleUser));
				throw new LockedAccountException("用户被锁定");
			}
			SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(consoleUser.getUsername(), consoleUser.getPassword(),ByteSource.Util.bytes(consoleUser.getUsername() + consoleUser.getSalt()), getName());
			simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(consoleUser.getUsername() + consoleUser.getSalt()));

			SecurityUtils.getSubject().getSession().setAttribute("currentUser", consoleUser);
			return simpleAuthenticationInfo;
		}else{
			LOGGER.info("用户不存在" + JSON.toJSONString(consoleUser));
			throw new UnknownAccountException("用户不存在");
		}
	}

	/**
	 * @功能: 获取用户拥有的权限
	 * @作者: yangc
	 * @创建日期: 2014年6月13日 下午12:50:22
	 * @param principals
	 * @return
	 */
	public Collection<String> getUserPermission(PrincipalCollection principals) {
		if (!CollectionUtils.isEmpty(principals)) {
			return this.getAuthorizationInfo(principals).getStringPermissions();
		}
		return null;
	}

	/**
	 * @功能: 清除用户认证缓存信息
	 * @作者: yangc
	 * @创建日期: 2014年5月21日 上午10:24:18
	 * @param principal username
	 */
	public void clearCachedAuthenticationInfo(Object principal) {
		if (principal != null) {
			SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, this.getName());
			this.clearCachedAuthenticationInfo(principals);
		}
	}

	/**
	 * @功能: 清除用户权限缓存信息
	 * @作者: yangc
	 * @创建日期: 2014年5月21日 上午10:24:18
	 * @param principal username
	 */
	public void clearCachedAuthorizationInfo(Object principal) {
		if (principal != null) {
			SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, this.getName());
			this.clearCachedAuthorizationInfo(principals);
		}
	}

	/**
	 * @功能: 清除所有认证缓存信息
	 * @作者: yangc
	 * @创建日期: 2014年5月21日 下午7:54:41
	 */
	public void clearAllCachedAuthenticationInfo() {
		this.getAuthenticationCache().clear();
	}

	/**
	 * @功能: 清除所有权限缓存信息
	 * @作者: yangc
	 * @创建日期: 2014年5月21日 下午7:54:41
	 */
	public void clearAllCachedAuthorizationInfo() {
		this.getAuthorizationCache().clear();
	}

	/**
	 * @功能: 清除所有认证和权限缓存信息
	 * @作者: yangc
	 * @创建日期: 2014年5月21日 下午7:54:41
	 */
	public void clearAllCached() {
		this.clearAllCachedAuthenticationInfo();
		this.clearAllCachedAuthorizationInfo();
	}
}
