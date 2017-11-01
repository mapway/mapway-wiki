/*******************************************************************************
 数据库持久化层(For MySQL5.1 Java)
 创建日期  Wed Oct 25 11:55:13 SGT 2017
 创建程序 Program 版本(3.0)
 程序设计 张建设 EMAIL:zhangjianshe@gmail.com PHONE:15910868680

<pre>

           =============================================================
           -   ____ _  _ ____ _  _ ____  _ _ ____ _  _ ____ _  _ ____  -
           -    __] |__| |__| |\ | | __  | | |__| |\ | [__  |__| |___  -
           -   [___ |  | |  | | \| |__| _| | |  | | \| ___] |  | |___  -
           -           http://hi.baidu.com/zhangjianshe                -
           =============================================================

</pre>
/******************************************************************************/

package cn.mapway.wiki.repository;

import java.util.Date;

import org.nutz.dao.entity.annotation.*;
import java.math.BigDecimal;
import cn.mapway.document.annotation.*;

/**
 * 数据库表 <br/>
 * @author zhangjsf@enn.cn
 * <b>字段列表</b><br/>
 *  Long role_id //角色ID<br/>
 *  Long resource_id //资源ID<br/>
 */

@Table("role_resource")
@Doc("role_resource()")
@PK({"role_id","resource_id"})
public class ROLE_RESOURCEObj{

  /**
  * 表名称. 
     */
  public static final String TBL_ROLE_RESOURCE="role_resource";
  public ROLE_RESOURCEObj() {
  }
  /**
   * 角色ID role_id
 */
  public static final String FLD_ROLE_ID="role_id";

 /**
   * 字段role_id 角色ID
   */
	@Column("role_id")
  @ApiField(value="角色ID",example="")
  private Long role_id;

  /**
   * 返回字段role_id 角色ID的值.
   * @return role_id  角色ID  bigint
  */
  public Long getRole_id() {
    return role_id;
  }

  /**
   * 设置字段role_id 角色ID的值.
   * @param role_id  角色ID  bigint
   */
  public void setRole_id(Long role_id) {
    this.role_id=role_id;
  }

  /**
   * 资源ID resource_id
 */
  public static final String FLD_RESOURCE_ID="resource_id";

 /**
   * 字段resource_id 资源ID
   */
	@Column("resource_id")
  @ApiField(value="资源ID",example="")
  private Long resource_id;

  /**
   * 返回字段resource_id 资源ID的值.
   * @return resource_id  资源ID  bigint
  */
  public Long getResource_id() {
    return resource_id;
  }

  /**
   * 设置字段resource_id 资源ID的值.
   * @param resource_id  资源ID  bigint
   */
  public void setResource_id(Long resource_id) {
    this.resource_id=resource_id;
  }

}
