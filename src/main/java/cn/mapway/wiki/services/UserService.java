package cn.mapway.wiki.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.nutz.dao.Cnd;
import org.nutz.dao.util.Daos;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.random.R;
import org.springframework.stereotype.Service;

import cn.mapway.wiki.api.main.BaseResp;
import cn.mapway.wiki.api.module.BaseReq;
import cn.mapway.wiki.api.user.UserInfo;
import cn.mapway.wiki.api.user.UserLoginReq;
import cn.mapway.wiki.api.user.UserLoginResp;
import cn.mapway.wiki.api.user.UserLogoutResp;
import cn.mapway.wiki.configure.shiro.AccountAccessToken;
import cn.mapway.wiki.exception.CodeException;
import cn.mapway.wiki.exception.ErrorCodes;
import cn.mapway.wiki.repository.RESOURCEObj;
import cn.mapway.wiki.repository.ROLEObj;
import cn.mapway.wiki.repository.ROLE_RESOURCEObj;
import cn.mapway.wiki.repository.TOKENObj;
import cn.mapway.wiki.repository.USERObj;
import cn.mapway.wiki.repository.USER_ROLEObj;
import cn.mapway.wiki.tools.Times;

/**
 * 用户服务
 * 
 * @author zhangjianshe
 *
 */
@Service
public class UserService extends BaseService {
	/**
	 * 初始化数据库
	 */
	public void initDatabse() {

		boolean isExist = dao.exists(USERObj.class);
		if (!isExist) {
			Daos.createTablesInPackage(dao, USERObj.class, true);
		}
		long count = dao.count(USERObj.class);
		if (count == 0) {
			USERObj u = new USERObj();
			u.setAccount_type(0);
			u.setCt(Times.now());
			u.setName("admin");
			u.setPwd(Lang.md5("123456"));
			u.setUt(Times.now());
			dao.insert(u);
		}
	}

	/**
	 * 根据用户名和账户类型查询用户
	 * 
	 * @param userName
	 *            用户名
	 * @param accountType
	 *            账户类型
	 * @return
	 */
	public USERObj findUser(String userName, Integer accountType) {
		Cnd where = Cnd.where(USERObj.FLD_NAME, "=", userName);
		where = where.and(USERObj.FLD_ACCOUNT_TYPE, "=", accountType);
		USERObj user = dao.fetch(USERObj.class, where);
		return user;
	}

	public UserLoginResp login(UserLoginReq req, HttpServletRequest request) throws CodeException {
		UserLoginResp r = new UserLoginResp();

		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(new AccountAccessToken(req.userName, req.pwd, req.accountType));
		} catch (AuthorizationException e) {
			throw new CodeException(ErrorCodes.ERROR_LOGIN_ERROR.bind(e.getMessage()));
		} catch (UnknownAccountException e) {
			throw new CodeException(ErrorCodes.ERROR_LOGIN_ERROR.bind(e.getMessage()));
		} catch (IncorrectCredentialsException e) {
			throw new CodeException(ErrorCodes.ERROR_LOGIN_ERROR.bind("密码不匹配"));
		}

		USERObj user = (USERObj) subject.getPrincipal();
		TOKENObj token = getToken(user, req.clientId);
		r.user = toUserInfo(user, token);
		r.ok();
		return r;
	}

	/**
	 * 退出登录.
	 * 
	 * @param req
	 * @param request
	 * @return
	 */
	public UserLogoutResp logout(@Valid BaseReq req, HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		UserLogoutResp r = new UserLogoutResp();
		r.ok();
		return r;
	}

	/**
	 * 获取用户TOKEN
	 * 
	 * @param user
	 * @param clientId
	 * @return
	 */
	private TOKENObj getToken(USERObj user, String clientId) {
		if (Strings.isBlank(clientId)) {
			clientId = user.getId().toString();
		}
		TOKENObj t = dao.fetch(TOKENObj.class,
				Cnd.where(TOKENObj.FLD_USER_ID, "=", user.getId()).and(TOKENObj.FLD_CLIENT_ID, "=", clientId));
		if (t == null) {
			t = new TOKENObj();
			t.setClient_id(clientId);
			t.setCt(Times.now().getTime());
			t.setUt(Times.now().getTime());
			t.setToken(R.UU16());
			t.setUser_id(user.getId());
			dao.insert(t);

		} else {
			t.setUt(Times.now().getTime());
			t.setToken(R.UU16());
		}

		return t;
	}

	private UserInfo toUserInfo(USERObj user, TOKENObj token) {
		UserInfo ui = new UserInfo();
		ui.createTime = Times.formatTime(user.getCt());
		ui.name = user.getName();
		ui.token = token.getToken();
		ui.updateTime = Times.formatTime(user.getUt());
		ui.avator = "";
		return ui;
	}

	/**
	 * 查询用户的角色
	 * 
	 * @param id
	 * @return
	 */
	public List<ROLEObj> findUserRoles(Long userId) {
		String sqlUserRole = " select " + USER_ROLEObj.FLD_ROLE_ID + " from " + USER_ROLEObj.TBL_USER_ROLE + " where "
				+ USER_ROLEObj.FLD_USER_ID + " = " + userId;

		List<ROLEObj> roles = dao.query(ROLEObj.class,
				Cnd.wrap("where " + ROLEObj.FLD_ID + " in ( " + sqlUserRole + ")"));
		return roles;
	}

	/**
	 * 根据用户ID 获取用户信息F
	 * 
	 * @param userId
	 * @return
	 */
	public USERObj findUserById(Integer userId) {
		return dao.fetch(USERObj.class, userId);
	}

	/**
	 * 查询角色的资源信息
	 * 
	 * @param roleId
	 * @return
	 */
	public List<RESOURCEObj> findRoleResource(Long roleId) {
		String sqlUserRole = " select " + ROLE_RESOURCEObj.FLD_RESOURCE_ID + " from "
				+ ROLE_RESOURCEObj.TBL_ROLE_RESOURCE + " where " + ROLE_RESOURCEObj.FLD_ROLE_ID + " = " + roleId;

		List<RESOURCEObj> ress = dao.query(RESOURCEObj.class,
				Cnd.wrap("where " + RESOURCEObj.FLD_ID + " in ( " + sqlUserRole + ")"));
		return ress;
	}

}
