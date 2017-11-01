package cn.mapway.wiki.api.files;

import java.util.ArrayList;
import java.util.List;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;
import cn.mapway.wiki.api.main.BaseResp;

@Doc("文件上传返回值")
public class UploadResp extends BaseResp {

	@ApiField("文件信息列表")
	public List<FileUploadResp> files;

	public UploadResp() {
		files = new ArrayList<FileUploadResp>();
	}
}
