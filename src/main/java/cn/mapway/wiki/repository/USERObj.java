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
 *  Long id //用户ID<br/>
 *  String name //用户名称<br/>
 *  String pwd //密码<br/>
 *  Integer account_type //0 注册用户 1LDAP<br/>
 *  java.sql.Timestamp ct //创建时间<br/>
 *  java.sql.Timestamp ut //更新时间<br/>
 *  String avator //头像地址<br/>
 */

@Table("user")
@Doc("user()")
public class USERObj{

  /**
  * 表名称. 
     */
  public static final String TBL_USER="user";
  public USERObj() {
  }
  /**
   * 用户ID id
 */
  public static final String FLD_ID="id";

 /**
   * 字段id 用户ID
   */
	@Id
  @ApiField(value="用户ID",example="")
  private Long id;

  /**
   * 返回字段id 用户ID的值.
   * @return id  用户ID  bigint
  */
  public Long getId() {
    return id;
  }

  /**
   * 设置字段id 用户ID的值.
   * @param id  用户ID  bigint
   */
  public void setId(Long id) {
    this.id=id;
  }

  /**
   * 用户名称 name
 */
  public static final String FLD_NAME="name";

 /**
   * 字段name 用户名称
   */
	@Column("name")
  @ApiField(value="用户名称",example="")
  private String name;

  /**
   * 返回字段name 用户名称的值.
   * @return name  用户名称  varchar
  */
  public String getName() {
    return name;
  }

  /**
   * 设置字段name 用户名称的值.
   * @param name  用户名称  varchar
   */
  public void setName(String name) {
    this.name=name;
  }

  /**
   * 密码 pwd
 */
  public static final String FLD_PWD="pwd";

 /**
   * 字段pwd 密码
   */
	@Column("pwd")
  @ApiField(value="密码",example="")
  private String pwd;

  /**
   * 返回字段pwd 密码的值.
   * @return pwd  密码  varchar
  */
  public String getPwd() {
    return pwd;
  }

  /**
   * 设置字段pwd 密码的值.
   * @param pwd  密码  varchar
   */
  public void setPwd(String pwd) {
    this.pwd=pwd;
  }

  /**
   * 0 注册用户 1LDAP account_type
 */
  public static final String FLD_ACCOUNT_TYPE="account_type";

 /**
   * 字段account_type 0 注册用户 1LDAP
   */
	@Column("account_type")
  @ApiField(value="0 注册用户 1LDAP",example="")
  private Integer account_type;

  /**
   * 返回字段account_type 0 注册用户 1LDAP的值.
   * @return account_type  0 注册用户 1LDAP  int
  */
  public Integer getAccount_type() {
    return account_type;
  }

  /**
   * 设置字段account_type 0 注册用户 1LDAP的值.
   * @param account_type  0 注册用户 1LDAP  int
   */
  public void setAccount_type(Integer account_type) {
    this.account_type=account_type;
  }

  /**
   * 创建时间 ct
 */
  public static final String FLD_CT="ct";

 /**
   * 字段ct 创建时间
   */
	@Column("ct")
  @ApiField(value="创建时间",example="")
  private java.sql.Timestamp ct;

  /**
   * 返回字段ct 创建时间的值.
   * @return ct  创建时间  timestamp
  */
  public java.sql.Timestamp getCt() {
    return ct;
  }

  /**
   * 设置字段ct 创建时间的值.
   * @param ct  创建时间  timestamp
   */
  public void setCt(java.sql.Timestamp ct) {
    this.ct=ct;
  }

  /**
   * 更新时间 ut
 */
  public static final String FLD_UT="ut";

 /**
   * 字段ut 更新时间
   */
	@Column("ut")
  @ApiField(value="更新时间",example="")
  private java.sql.Timestamp ut;

  /**
   * 返回字段ut 更新时间的值.
   * @return ut  更新时间  timestamp
  */
  public java.sql.Timestamp getUt() {
    return ut;
  }

  /**
   * 设置字段ut 更新时间的值.
   * @param ut  更新时间  timestamp
   */
  public void setUt(java.sql.Timestamp ut) {
    this.ut=ut;
  }

  /**
   * 头像地址 avator
 */
  public static final String FLD_AVATOR="avator";

 /**
   * 字段avator 头像地址
   */
	@Column("avator")
  @ApiField(value="头像地址",example="")
  private String avator;

  /**
   * 返回字段avator 头像地址的值.
   * @return avator  头像地址  varchar
  */
  public String getAvator() {
    return avator;
  }

  /**
   * 设置字段avator 头像地址的值.
   * @param avator  头像地址  varchar
   */
  public void setAvator(String avator) {
    this.avator=avator;
  }

}
