package cn.mapway.wiki.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mapway.wiki.configure.shiro.AccountAccessToken;
import cn.mapway.wiki.repository.RESOURCEObj;
import cn.mapway.wiki.repository.ROLEObj;
import cn.mapway.wiki.repository.USERObj;

/**
 * 用户验证
 * 
 * @author zhangjianshe
 *
 */
@Service
public class ApplicationRealm extends AuthorizingRealm {
	private final static Log log = Logs.getLog(ApplicationRealm.class);

	@Autowired
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		List<?> data = principals.asList();

		for (Object o : data) {
			log.info(o.toString());
		}

		USERObj user = (USERObj) principals.getPrimaryPrincipal();

		List<String> userRoles = new ArrayList<String>();
		ArrayList<String> userPermissions = new ArrayList<String>();

		// 从数据库中获取当前登录用户的详细信息
		if (null != user) {
			// 获取当前用户下所有ACL权限列表 待续。。。
			// 获取当前用户下拥有的所有角色列表
			List<ROLEObj> roles = userService.findUserRoles(user.getId());
			for (int i = 0; i < roles.size(); i++) {
				userRoles.add(roles.get(i).getName());
				List<RESOURCEObj> res = userService.findRoleResource(roles.get(i).getId());
				for (RESOURCEObj r : res) {
					userPermissions.add(r.getCode());
				}
			}
		} else {
			throw new AuthorizationException();
		}

		// 为当前用户设置角色和权限
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addRoles(userRoles);
		authorizationInfo.addStringPermissions(userPermissions);
		System.out.println(Json.toJson(authorizationInfo));
		return authorizationInfo;

	}

	/**
	 * 验证当前登录的Subject LoginController.login()方法中执行Subject.login()时 执行此方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		log.info("开始认证" + SecurityUtils.getSubject().getSession().getId());
		AccountAccessToken accessToken = (AccountAccessToken) authcToken;

		USERObj user = userService.findUser(accessToken.getUsername(), accessToken.getAccountType());

		if (user == null) {
			throw new UnknownAccountException();// 没找到帐号
		}

		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, // 用户名
				user.getPwd(), // 密码
				getName() // realm name
		);
		return authenticationInfo;

	}
}
