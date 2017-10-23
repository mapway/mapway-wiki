package cn.mapway.wiki.services;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring辅助类获取Bean
 * 
 * @author enn
 *
 */
@Component
public class SpringService implements ApplicationContextAware {

  private static SpringService INSTANCE=null;
  private static ApplicationContext applicationContext = null;

  public SpringService() {
	  INSTANCE =this;
  }
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    if (SpringService.applicationContext == null) {

      SpringService.applicationContext = applicationContext;

    }

  }

  /**
   * 获取服务实例
   * @return
   */
  public synchronized static SpringService get()
  {
	  return INSTANCE;
  }
  // 获取applicationContext

  public static ApplicationContext getApplicationContext() {

    return applicationContext;

  }

  // 通过name获取 Bean.

  public static Object getBean(String name) {

    return getApplicationContext().getBean(name);

  }

  // 通过class获取Bean.

  public static <T> T getBean(Class<T> clazz) {

    return getApplicationContext().getBean(clazz);

  }

  // 通过name,以及Clazz返回指定的Bean

  public static <T> T getBean(String name, Class<T> clazz) {

    return getApplicationContext().getBean(name, clazz);

  }

}
