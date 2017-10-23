/*******************************************************************************
 数据库持久化层(For MySQL5.1 Java)
 创建日期  Fri Oct 20 13:10:58 SGT 2017
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
 *  Long user_id //<br/>
 *  Long role_id //<br/>
 */

@Table("user_role")
@Doc("user_role()")
@PK({"user_id","role_id"})
public class USER_ROLEObj{

  /**
  * 表名称. 
     */
  public static final String TBL_USER_ROLE="user_role";
  public USER_ROLEObj() {
  }
  /**
   *  user_id
 */
  public static final String FLD_USER_ID="user_id";

 /**
   * 字段user_id 
   */
	@Column("user_id")
  @ApiField(value="",example="")
  private Long user_id;

  /**
   * 返回字段user_id 的值.
   * @return user_id    bigint
  */
  public Long getUser_id() {
    return user_id;
  }

  /**
   * 设置字段user_id 的值.
   * @param user_id    bigint
   */
  public void setUser_id(Long user_id) {
    this.user_id=user_id;
  }

  /**
   *  role_id
 */
  public static final String FLD_ROLE_ID="role_id";

 /**
   * 字段role_id 
   */
	@Column("role_id")
  @ApiField(value="",example="")
  private Long role_id;

  /**
   * 返回字段role_id 的值.
   * @return role_id    bigint
  */
  public Long getRole_id() {
    return role_id;
  }

  /**
   * 设置字段role_id 的值.
   * @param role_id    bigint
   */
  public void setRole_id(Long role_id) {
    this.role_id=role_id;
  }

}
