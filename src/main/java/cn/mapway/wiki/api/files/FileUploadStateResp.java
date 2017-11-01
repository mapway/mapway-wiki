package cn.mapway.wiki.api.files;

import java.util.ArrayList;
import java.util.List;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;
import cn.mapway.wiki.api.main.BaseResp;
import cn.mapway.wiki.services.files.FileUploadState;

@Doc("文件上传状态信息")
public class FileUploadStateResp extends BaseResp {
	@ApiField("文件状态列表")
	public List<FileUploadState> files;

	public FileUploadStateResp() {
		files = new ArrayList<FileUploadState>();
	}
}
