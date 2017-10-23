package cn.mapway.wiki.services;

import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基础服务.
 * @author zhangjianshe
 *
 */
public class BaseService {

	@Autowired
	public Dao dao;

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}
}
