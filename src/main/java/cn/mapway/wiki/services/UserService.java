package cn.mapway.wiki.services;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.random.R;
import org.springframework.stereotype.Service;

import cn.mapway.wiki.api.user.UserInfo;
import cn.mapway.wiki.api.user.UserLoginReq;
import cn.mapway.wiki.api.user.UserLoginResp;
import cn.mapway.wiki.exception.CodeException;
import cn.mapway.wiki.exception.ErrorCodes;
import cn.mapway.wiki.repository.TOKENObj;
import cn.mapway.wiki.repository.USERObj;
import cn.mapway.wiki.tools.Times;

/**
 * 用户服务
 * @author zhangjianshe
 *
 */
@Service
public class UserService extends BaseService{
	/**
	 * 初始化数据库
	 */
	public void initDatabse()
	{
		long count=dao.count(USERObj.class);
		if(count==0)
		{
			USERObj u=new USERObj();
			u.setAccount_type(0);
			u.setCt(Times.now());
			u.setName("admin");
			u.setPwd(Lang.md5("123456"));
			u.setUt(Times.now());
			dao.insert(u);
		}
	}

	public UserLoginResp login(UserLoginReq req, HttpServletRequest request) throws CodeException {
		UserLoginResp r=new UserLoginResp();
		Cnd where=Cnd.where(USERObj.FLD_NAME, "=", req.userName);
		where=where.and(USERObj.FLD_ACCOUNT_TYPE, "=", req.accountType);
		USERObj user = dao.fetch(USERObj.class, where);
		
		//检查验证码
		// String code=(String)
		// request.getSession(true).getAttribute(Tookits.LOGIN_CODE);
		// if(code==null || !code.equalsIgnoreCase(req.code))
		// {
		// throw new CodeException(ErrorCodes.ERROR_LOGIN_CODE);
		// }

		if(user==null)
		{
			throw new CodeException(ErrorCodes.ERROR_LOGIN_USER_NOT_EXIST.bind(req.userName));
		}
		
		if(!user.getPwd().equals(Lang.md5(req.pwd)))
		{
			throw new CodeException(ErrorCodes.ERROR_LOGIN_PASSWORD_NOT_MATCH);
		}
		
		//检查TOKEN
		String clientId=req.clientId;
		if(Strings.isBlank(clientId))
		{
			clientId=user.getId()+"";
		}
		
		TOKENObj token = dao.fetch(TOKENObj.class, Cnd.where(TOKENObj.FLD_USER_ID, "=", user.getId()).and(TOKENObj.FLD_CLIENT_ID, "=", clientId));
		
		if(token==null)
		{
			token=new TOKENObj();
			token.setClient_id(clientId);
			token.setUser_id(user.getId());
			token.setCt(System.currentTimeMillis());
			token.setUt(System.currentTimeMillis());
			token.setToken(R.UU16());
			dao.insert(token);
		}
		else
		{
			token.setToken(R.UU16());
			token.setUt(System.currentTimeMillis());
			dao.update(token);
		}
		
		r.user=toUserInfo(user,token);
		
		r.ok();
		return r;
	}

	private UserInfo toUserInfo(USERObj user,TOKENObj token) {
		UserInfo ui=new UserInfo();
		ui.createTime=Times.formatTime(user.getCt());
		ui.name=user.getName();
		ui.token=token.getToken();
		ui.updateTime=Times.formatTime(user.getUt());
		ui.avator="";
		return ui;
	}
}
