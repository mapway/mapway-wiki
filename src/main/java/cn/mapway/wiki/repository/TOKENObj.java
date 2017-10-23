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
 * 数据库表 用户TOKEN信息表<br/>
 * @author zhangjsf@enn.cn
 * <b>字段列表</b><br/>
 *  Long id //tokenID<br/>
 *  Long user_id //用户ID<br/>
 *  String client_id //客户端标识<br/>
 *  String token //token<br/>
 *  Long ct //创建时间<br/>
 *  Long ut //更新时间<br/>
 */

@Table("token")
@Doc("token(用户TOKEN信息表)")
public class TOKENObj{

  /**
  * 表用户TOKEN信息表名称. 
     */
  public static final String TBL_TOKEN="token";
  public TOKENObj() {
  }
  /**
   * tokenID id
 */
  public static final String FLD_ID="id";

 /**
   * 字段id tokenID
   */
	@Id
  @ApiField(value="tokenID",example="")
  private Long id;

  /**
   * 返回字段id tokenID的值.
   * @return id  tokenID  bigint
  */
  public Long getId() {
    return id;
  }

  /**
   * 设置字段id tokenID的值.
   * @param id  tokenID  bigint
   */
  public void setId(Long id) {
    this.id=id;
  }

  /**
   * 用户ID user_id
 */
  public static final String FLD_USER_ID="user_id";

 /**
   * 字段user_id 用户ID
   */
	@Column("user_id")
  @ApiField(value="用户ID",example="")
  private Long user_id;

  /**
   * 返回字段user_id 用户ID的值.
   * @return user_id  用户ID  bigint
  */
  public Long getUser_id() {
    return user_id;
  }

  /**
   * 设置字段user_id 用户ID的值.
   * @param user_id  用户ID  bigint
   */
  public void setUser_id(Long user_id) {
    this.user_id=user_id;
  }

  /**
   * 客户端标识 client_id
 */
  public static final String FLD_CLIENT_ID="client_id";

 /**
   * 字段client_id 客户端标识
   */
	@Column("client_id")
  @ApiField(value="客户端标识",example="")
  private String client_id;

  /**
   * 返回字段client_id 客户端标识的值.
   * @return client_id  客户端标识  varchar
  */
  public String getClient_id() {
    return client_id;
  }

  /**
   * 设置字段client_id 客户端标识的值.
   * @param client_id  客户端标识  varchar
   */
  public void setClient_id(String client_id) {
    this.client_id=client_id;
  }

  /**
   * token token
 */
  public static final String FLD_TOKEN="token";

 /**
   * 字段token token
   */
	@Column("token")
  @ApiField(value="token",example="")
  private String token;

  /**
   * 返回字段token token的值.
   * @return token  token  varchar
  */
  public String getToken() {
    return token;
  }

  /**
   * 设置字段token token的值.
   * @param token  token  varchar
   */
  public void setToken(String token) {
    this.token=token;
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
  private Long ct;

  /**
   * 返回字段ct 创建时间的值.
   * @return ct  创建时间  bigint
  */
  public Long getCt() {
    return ct;
  }

  /**
   * 设置字段ct 创建时间的值.
   * @param ct  创建时间  bigint
   */
  public void setCt(Long ct) {
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
  private Long ut;

  /**
   * 返回字段ut 更新时间的值.
   * @return ut  更新时间  bigint
  */
  public Long getUt() {
    return ut;
  }

  /**
   * 设置字段ut 更新时间的值.
   * @param ut  更新时间  bigint
   */
  public void setUt(Long ut) {
    this.ut=ut;
  }

}
