package cn.mapway.wiki.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mapway.wiki.repository.USERObj;

/**
 * 用户验证
 * @author zhangjianshe
 *
 */
@Service
public class ApplicationRealm extends AuthorizingRealm{
	@Autowired
    private UserService userService;
	@Autowired
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        String currentLoginName = (String)principals.getPrimaryPrincipal();
//       
//        List<String> userRoles = new ArrayList<String>();  
//        List<String> userPermissions = new ArrayList<String>();  
//        //从数据库中获取当前登录用户的详细信息  
//        USERObj user = userService.findByLoginName(currentLoginName);
//        if(null != user){  
//            //获取当前用户下所有ACL权限列表  待续。。。
//            //获取当前用户下拥有的所有角色列表
//            List<Role> roles = roleService.findByUserId(user.getId());
//            for (int i = 0; i < roles.size(); i++) {
//                userRoles.add(roles.get(i).getCode());
//            }
//        }else{  
//            throw new AuthorizationException();  
//        }  
//         System.out.println("#######获取角色："+userRoles);
//         System.out.println("#######获取权限："+userPermissions);
//        //为当前用户设置角色和权限  
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        authorizationInfo.addRoles(userRoles);
//        authorizationInfo.addStringPermissions(userPermissions); 
//        return authorizationInfo;
    	return null;
    }

    /** 
     * 验证当前登录的Subject
     * LoginController.login()方法中执行Subject.login()时 执行此方法 
     */ 
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
//        System.out.println("###【开始认证[SessionId]】"+SecurityUtils.getSubject().getSession().getId());
//        String loginName = (String)authcToken.getPrincipal();
//        User user = userService.findByLoginName(loginName);
//        if(user == null) {
//            throw new UnknownAccountException();//没找到帐号
//        }
//        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//                user.getUserName(), //用户名
//                user.getPassword(), //密码
//                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt,采用明文访问时，不需要此句
//                getName()  //realm name
//        );
//        return authenticationInfo;
    	return null;
    }
}
