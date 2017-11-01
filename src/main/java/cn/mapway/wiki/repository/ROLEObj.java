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
 *  Long id //角色ID<br/>
 *  String name //角色名称<br/>
 *  String summary //角色说明<br/>
 *  Integer is_sys //是否是系统角色<br/>
 */

@Table("role")
@Doc("role()")
public class ROLEObj{

  /**
  * 表名称. 
     */
  public static final String TBL_ROLE="role";
  public ROLEObj() {
  }
  /**
   * 角色ID id
 */
  public static final String FLD_ID="id";

 /**
   * 字段id 角色ID
   */
	@Id
  @ApiField(value="角色ID",example="")
  private Long id;

  /**
   * 返回字段id 角色ID的值.
   * @return id  角色ID  bigint
  */
  public Long getId() {
    return id;
  }

  /**
   * 设置字段id 角色ID的值.
   * @param id  角色ID  bigint
   */
  public void setId(Long id) {
    this.id=id;
  }

  /**
   * 角色名称 name
 */
  public static final String FLD_NAME="name";

 /**
   * 字段name 角色名称
   */
	@Column("name")
  @ApiField(value="角色名称",example="")
  private String name;

  /**
   * 返回字段name 角色名称的值.
   * @return name  角色名称  varchar
  */
  public String getName() {
    return name;
  }

  /**
   * 设置字段name 角色名称的值.
   * @param name  角色名称  varchar
   */
  public void setName(String name) {
    this.name=name;
  }

  /**
   * 角色说明 summary
 */
  public static final String FLD_SUMMARY="summary";

 /**
   * 字段summary 角色说明
   */
	@Column("summary")
  @ApiField(value="角色说明",example="")
  private String summary;

  /**
   * 返回字段summary 角色说明的值.
   * @return summary  角色说明  varchar
  */
  public String getSummary() {
    return summary;
  }

  /**
   * 设置字段summary 角色说明的值.
   * @param summary  角色说明  varchar
   */
  public void setSummary(String summary) {
    this.summary=summary;
  }

  /**
   * 是否是系统角色 is_sys
 */
  public static final String FLD_IS_SYS="is_sys";

 /**
   * 字段is_sys 是否是系统角色
   */
	@Column("is_sys")
  @ApiField(value="是否是系统角色",example="")
  private Integer is_sys;

  /**
   * 返回字段is_sys 是否是系统角色的值.
   * @return is_sys  是否是系统角色  int
  */
  public Integer getIs_sys() {
    return is_sys;
  }

  /**
   * 设置字段is_sys 是否是系统角色的值.
   * @param is_sys  是否是系统角色  int
   */
  public void setIs_sys(Integer is_sys) {
    this.is_sys=is_sys;
  }

}
