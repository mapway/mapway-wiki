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
 *  Long id //<br/>
 *  String name //<br/>
 *  Long pid //<br/>
 *  String code //<br/>
 *  String summary //<br/>
 *  String icon //<br/>
 *  String param //<br/>
 *  String css //<br/>
 *  Long rootid //根ID<br/>
 */

@Table("resource")
@Doc("resource()")
public class RESOURCEObj{

  /**
  * 表名称. 
     */
  public static final String TBL_RESOURCE="resource";
  public RESOURCEObj() {
  }
  /**
   *  id
 */
  public static final String FLD_ID="id";

 /**
   * 字段id 
   */
	@Id
  @ApiField(value="",example="")
  private Long id;

  /**
   * 返回字段id 的值.
   * @return id    bigint
  */
  public Long getId() {
    return id;
  }

  /**
   * 设置字段id 的值.
   * @param id    bigint
   */
  public void setId(Long id) {
    this.id=id;
  }

  /**
   *  name
 */
  public static final String FLD_NAME="name";

 /**
   * 字段name 
   */
	@Column("name")
  @ApiField(value="",example="")
  private String name;

  /**
   * 返回字段name 的值.
   * @return name    varchar
  */
  public String getName() {
    return name;
  }

  /**
   * 设置字段name 的值.
   * @param name    varchar
   */
  public void setName(String name) {
    this.name=name;
  }

  /**
   *  pid
 */
  public static final String FLD_PID="pid";

 /**
   * 字段pid 
   */
	@Column("pid")
  @ApiField(value="",example="")
  private Long pid;

  /**
   * 返回字段pid 的值.
   * @return pid    bigint
  */
  public Long getPid() {
    return pid;
  }

  /**
   * 设置字段pid 的值.
   * @param pid    bigint
   */
  public void setPid(Long pid) {
    this.pid=pid;
  }

  /**
   *  code
 */
  public static final String FLD_CODE="code";

 /**
   * 字段code 
   */
	@Column("code")
  @ApiField(value="",example="")
  private String code;

  /**
   * 返回字段code 的值.
   * @return code    varchar
  */
  public String getCode() {
    return code;
  }

  /**
   * 设置字段code 的值.
   * @param code    varchar
   */
  public void setCode(String code) {
    this.code=code;
  }

  /**
   *  summary
 */
  public static final String FLD_SUMMARY="summary";

 /**
   * 字段summary 
   */
	@Column("summary")
  @ApiField(value="",example="")
  private String summary;

  /**
   * 返回字段summary 的值.
   * @return summary    varchar
  */
  public String getSummary() {
    return summary;
  }

  /**
   * 设置字段summary 的值.
   * @param summary    varchar
   */
  public void setSummary(String summary) {
    this.summary=summary;
  }

  /**
   *  icon
 */
  public static final String FLD_ICON="icon";

 /**
   * 字段icon 
   */
	@Column("icon")
  @ApiField(value="",example="")
  private String icon;

  /**
   * 返回字段icon 的值.
   * @return icon    varchar
  */
  public String getIcon() {
    return icon;
  }

  /**
   * 设置字段icon 的值.
   * @param icon    varchar
   */
  public void setIcon(String icon) {
    this.icon=icon;
  }

  /**
   *  param
 */
  public static final String FLD_PARAM="param";

 /**
   * 字段param 
   */
	@Column("param")
  @ApiField(value="",example="")
  private String param;

  /**
   * 返回字段param 的值.
   * @return param    varchar
  */
  public String getParam() {
    return param;
  }

  /**
   * 设置字段param 的值.
   * @param param    varchar
   */
  public void setParam(String param) {
    this.param=param;
  }

  /**
   *  css
 */
  public static final String FLD_CSS="css";

 /**
   * 字段css 
   */
	@Column("css")
  @ApiField(value="",example="")
  private String css;

  /**
   * 返回字段css 的值.
   * @return css    varchar
  */
  public String getCss() {
    return css;
  }

  /**
   * 设置字段css 的值.
   * @param css    varchar
   */
  public void setCss(String css) {
    this.css=css;
  }

  /**
   * 根ID rootid
 */
  public static final String FLD_ROOTID="rootid";

 /**
   * 字段rootid 根ID
   */
	@Column("rootid")
  @ApiField(value="根ID",example="")
  private Long rootid;

  /**
   * 返回字段rootid 根ID的值.
   * @return rootid  根ID  bigint
  */
  public Long getRootid() {
    return rootid;
  }

  /**
   * 设置字段rootid 根ID的值.
   * @param rootid  根ID  bigint
   */
  public void setRootid(Long rootid) {
    this.rootid=rootid;
  }

}
