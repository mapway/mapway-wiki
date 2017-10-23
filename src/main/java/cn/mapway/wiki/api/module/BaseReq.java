package cn.mapway.wiki.api.module;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;

@Doc("接口请求基类信息")
public class BaseReq {
	@ApiField(value = "客户端标识", example = "HW_SW20932")
	public String clientId;
}
