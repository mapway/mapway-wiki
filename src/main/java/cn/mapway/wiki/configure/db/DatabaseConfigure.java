package cn.mapway.wiki.configure.db;

import javax.sql.DataSource;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.json.Json;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ejb.access.AbstractSlsbInvokerInterceptor;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;

import cn.mapway.wiki.configure.DbProperties;

/**
 * 数据源配置
 * 
 * @author zhangjianshe
 *
 */
@Configuration
public class DatabaseConfigure {
	private Log log = Logs.getLog(DatabaseConfigure.class);

	@Autowired
	DbProperties dbProperties;

	@Autowired
	@Bean
	public DataSource dataSource() {

		
		log.info("\r\n" + Json.toJson(dbProperties));

		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(dbProperties.getUrl());
		dataSource.setPassword(dbProperties.getPassword());
		dataSource.setUsername(dbProperties.getUser());
		dataSource.setDriverClassName(dbProperties.getDriver());
		dataSource.setMaxActive(30);
		dataSource.setMaxWait(5 * 1000);
		dataSource.setTestOnBorrow(true);
		dataSource.setTestWhileIdle(true);
		dataSource.setTestOnReturn(true);

		StatFilter statFilter = new StatFilter();
		dataSource.getProxyFilters().add(statFilter);
		return dataSource;

	}

	@Autowired
	@Bean
	public Dao dao(DataSource ds) {
		return new NutDao(ds);
	}
}
