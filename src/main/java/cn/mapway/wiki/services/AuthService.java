package cn.mapway.wiki.services;

import java.util.ArrayList;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;
import org.springframework.stereotype.Service;

import cn.mapway.wiki.repository.RESOURCEObj;
import cn.mapway.wiki.repository.ROLEObj;
import cn.mapway.wiki.repository.ROLE_RESOURCEObj;
import cn.mapway.wiki.repository.USER_ROLEObj;

/**
 * 认证服务.
 * 
 * @author zhangjianshe
 *
 */
@Service
public class AuthService extends BaseService {
	/**
	 * 列出系统中的所有资源
	 * 
	 * @param rootid
	 *            资源的根节点
	 * @return
	 */
	public List<RESOURCEObj> listResource(Long rootid) {
		return dao.query(RESOURCEObj.class, Cnd.where(RESOURCEObj.FLD_ROOTID, "=", rootid));
	}

	/**
	 * 更新或新建一个资源
	 * 
	 * @param obj
	 * @return
	 */
	public RESOURCEObj saveOrUpdateResource(RESOURCEObj obj) {
		if (obj == null) {
			return obj;
		}
		if (obj.getId() == null) {
			dao.insert(obj);
		} else {
			dao.update(obj);
		}
		return obj;
	}

	/**
	 * 更新或新建一个角色
	 * 
	 * @param obj
	 * @return
	 */
	public ROLEObj saveOrUpdateRole(ROLEObj obj) {
		if (obj == null) {
			return obj;
		}
		if (obj.getId() == null) {
			dao.insert(obj);
		} else {
			dao.update(obj);
		}
		return obj;
	}

	/**
	 * 删除一个角色
	 * 
	 * @param roleId
	 * @return
	 */
	public boolean deleteRole(Long roleId) {
		ROLEObj role = dao.fetch(ROLEObj.class, roleId);
		if (role == null || role.getIs_sys() > 0) {
			return false;
		}
		Trans.exec(new Atom() {
			@Override
			public void run() {
				// 删除角色对应的资源
				dao.clear(ROLE_RESOURCEObj.class, Cnd.where(ROLE_RESOURCEObj.FLD_ROLE_ID, "=", roleId));
				// 删除用户对应的角色
				dao.clear(USER_ROLEObj.class, Cnd.where(USER_ROLEObj.FLD_ROLE_ID, "=", roleId));
				// 删除角色
				dao.delete(ROLEObj.class, roleId);
			}
		});
		return true;
	}

	/**
	 * 删除一个资源
	 * 
	 * @param resId
	 * @return
	 */
	public boolean deleteResource(Long resId) {
		Trans.exec(new Atom() {
			@Override
			public void run() {
				// 删除角色对应的资源
				dao.clear(ROLE_RESOURCEObj.class, Cnd.where(ROLE_RESOURCEObj.FLD_RESOURCE_ID, "=", resId));
				// 删除资源
				dao.delete(RESOURCEObj.class, resId);
			}
		});
		return true;
	}

	/**
	 * 更新角色对应的资源ID
	 * 
	 * @param roleId
	 * @param resIds
	 * @return
	 */
	public boolean updateRoleResource(Long roleId, List<Long> resIds) {

		Trans.exec(new Atom() {
			@Override
			public void run() {
				// 删除角色对应的资源
				dao.clear(ROLE_RESOURCEObj.class, Cnd.where(ROLE_RESOURCEObj.FLD_ROLE_ID, "=", roleId));
				// 更新角色对应的资源
				if (resIds.size() > 0) {
					List<ROLE_RESOURCEObj> roleResourceList = new ArrayList<ROLE_RESOURCEObj>();
					for (Long resId : resIds) {
						ROLE_RESOURCEObj rr = new ROLE_RESOURCEObj();
						rr.setResource_id(resId);
						rr.setRole_id(roleId);
						roleResourceList.add(rr);
					}
					dao.fastInsert(roleResourceList);
				}
			}
		});
		return true;
	}

	/**
	 * 查询角色对应的资源列表
	 * 
	 * @param roleId
	 * @return
	 */
	public List<RESOURCEObj> listRoleResources(Long roleId) {
		String where = String.format("where %s in (select %s from %s where %s =%d) order by %s asc", RESOURCEObj.FLD_ID,
				ROLE_RESOURCEObj.FLD_RESOURCE_ID, ROLE_RESOURCEObj.TBL_ROLE_RESOURCE, ROLE_RESOURCEObj.FLD_ROLE_ID,
				roleId, RESOURCEObj.FLD_ID);
		return dao.query(RESOURCEObj.class, Cnd.wrap(where));
	}

	/**
	 * 列出素所有的角色
	 * 
	 * @return
	 */
	public List<ROLEObj> listRoles() {
		return dao.query(ROLEObj.class, Cnd.orderBy().asc(ROLEObj.FLD_ID));
	}

	/**
	 * 用户添加角色
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public boolean userAddRole(Long userId, Long roleId) {
		USER_ROLEObj userRole = new USER_ROLEObj();
		userRole.setRole_id(roleId);
		userRole.setUser_id(userId);
		dao.insert(userRole);
		return true;
	}

	/**
	 * 用户添加角色
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public boolean userRemoveRole(Long userId, Long roleId) {
		dao.clear(USER_ROLEObj.class,
				Cnd.where(USER_ROLEObj.FLD_ROLE_ID, "=", roleId).and(USER_ROLEObj.FLD_USER_ID, "=", userId));
		return true;
	}
}
