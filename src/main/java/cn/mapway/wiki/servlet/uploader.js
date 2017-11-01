var cn = cn || {};
cn.mapway = cn.mapway || {};
cn.mapway.ui = cn.mapway.ui || {};
cn.mapway.ui.Uploader = function(options) {
    var defaults = {
        fieldName : "file",
        uploadUrl : "fileserver",
        tagName : "tag",
        max : 5,
        min : 0,
        onSuccess : function(d) {

        }
    };
    this.settings = $.extend({}, defaults, options || {});
    this.input_source = "<input type='file'  class='_uploadinput'/>";
    var _this = this;
    this._change_file = function() {

        var file = $(this);
        var fp = file.parent();
        var tag = (Math.random() + "").replace("\.", "").substring(1);
        var loading = new cn.mapway.ui.Uploader.FileItem(file, tag,
                _this.settings);

        _this.pcon.append(loading);
        var input = $(_this.input_source)
                .attr("name", _this.settings.fieldName).change(
                        _this._change_file);
        fp.append(input);
        _this.updateInfo();
    }
    var html = "<div >"
            + "<div class='_uploadinfo'></div><div class='_btnuploader' >选择文件</div>"
            + "     <div class='_uploadcon' />"
            + "</div>";
    this.obj = $(html);
    this.info = this.obj.find("._uploadinfo");
    this.pcon = this.obj.find("._uploadcon");
    var input = $(this.input_source).attr("name", this.settings.fieldName)
            .change(this._change_file);
    this.obj.find("._btnuploader").append(input);
    this.updateInfo();
};
cn.mapway.ui.Uploader.prototype.get = function() {
    return this.obj;
}
cn.mapway.ui.Uploader.prototype.show = function() {
    this.obj.show();
    return this;
}
cn.mapway.ui.Uploader.prototype.hide = function() {
    this.obj.hide();
    return this;
}
cn.mapway.ui.Uploader.prototype.clear = function() {
    this.pcon.html("");
    this.updateInfo();
    return this;
}
cn.mapway.ui.Uploader.prototype.configure = function(options) {
    this.settings = $.extend(this.settings, options || {});
    this.updateInfo();
    return this;
}

cn.mapway.ui.Uploader.prototype.updateInfo = function() {
    // 更新信息
    var count = this.pcon.children().length;
    this.info.text("已上传" + count + "个,允许上传 最少" + this.settings.min + "个,最多"
            + this.settings.max + "个");
}
cn.mapway.ui.Uploader.prototype.getValue = function() {
    // 更新信息
    var v = [];
    this.pcon.children().each(function(index) {
        var t = $(this).data("data");
        if (t != null) {
            var d = {};
            d.fileName = t.fileName;
            d.relPath = t.relPath;
            d.extra = t.extra;
            v.push(d);
        }
    });
    return v;
}
cn.mapway.ui.Uploader.FileItem = function(file, tag, settings) {
    this.settings = settings;

    this.file = file;
    this.checkurl = settings.uploadUrl + "/state?" + settings.tagName + "="
            + tag;
    this.iframe = $('<iframe style="display:none;" name="' + tag + '" />');
    this.form = $('<form method="post" style="display:none;" enctype="multipart/form-data" />');
    this.form.attr("target", tag).attr("name", "form_" + tag).attr("action",
            settings.uploadUrl + "?" + settings.tagName + "=" + tag);
    this.file.appendTo(this.form);
    this.form.appendTo(this.iframe);
    $(document.body).append(this.iframe);

    var _this = this;
    this.iframe.load(function() {
        var data = $(this).contents().find('body').text();
        _this.iframe.remove();
        settings.onSuccess(data);
        _this.finish(eval("(" + data + ")"));
    });
    this.form.submit();

    var html = "<div class='_uploaditem'><table width='100%'>" +
    		"<tr><td width='70px' rowspan='2'><img class='_upload_pic'  /></td>"
            + "<td><div class='_title' title='点击编辑名称' style='display:none;'></div>" +
            		"<input type='text' class='_uploadedit'  />"
            + "<div class='_uploadprogress' >"
            + "    <div class='_uploadprogress_text' ></div>"
            + "</div>"
            + "<div class='_sizePan' style='display:none;'></div>"
            + "</td>"
            + "<td align='right' width='80px' rowspan='2'><img width='30px' src='"
            + this.settings.uploadUrl
            + "/icon?ext=delete' class='_del' /></td>" + "</tr></table>";
    var $html = $(html);
    this.progress = $html.find("._uploadprogress");
    this.dom = $html.find("._uploadprogress_text");
    this.dele = $html.find("._del");
    this.pic = $html.find("._upload_pic");
    this.title = $html.find("._title");
    this.title.click(this.titleClick.bind(this));
    this.sizePan = $html.find("._sizePan");
    this.dele.click(this.del.bind(this));
    this.editor=$html.find("._uploadedit");
    this.editor.on("blur",this.blur.bind(this));
    this.html = $html;
    this.timer = setInterval(this.check.bind(this), 200);
    return this.html;
};
cn.mapway.ui.Uploader.FileItem.prototype.titleClick=function()
{
   this.editor.val(this.title.text()).show().focus();
   this.title.hide();
}
cn.mapway.ui.Uploader.FileItem.prototype.blur=function()
{
    this.editor.hide();
    this.title.show();
    if(this.editor.val().length>0)
        {
          this.title.text(this.editor.val());
          this.html.data("data").fileName=this.editor.val();
        }
    
}
cn.mapway.ui.Uploader.FileItem.prototype.finish = function(d) {
    var data = d.files[0];
    this.progress.hide();
    this.title.show();
    this.sizePan.show();
    this.sizePan.text("文件长度约：" + this.toReadableSize(data.size));
    this.title.text(data.fileName);

    if (data.isPicture) {
        this.pic.attr("src", this.settings.uploadUrl + "/query?path="
                + data.relPath);
    } else {
        this.pic
                .attr("src", this.settings.uploadUrl + "/icon?ext=" + data.icon);
    }
    this.html.data("data", data);
}
cn.mapway.ui.Uploader.FileItem.prototype.toReadableSize = function($fileSize) {
    $unit = new Array(' 字节', ' KB', ' MB', ' GB', ' TB', ' PB', ' EB', ' ZB',
            ' YB');
    $i = 0;
    $inv = 1 / 1024;

    while ($fileSize >= 1024 && $i < 8) {
        $fileSize *= $inv;
        ++$i;
    }

    $fileSizeTmp = Math.floor($fileSize);
    return $fileSizeTmp + $unit[$i];

}
cn.mapway.ui.Uploader.FileItem.prototype.check = function() {

    $.get(this.checkurl, this.ondata.bind(this));
}
cn.mapway.ui.Uploader.FileItem.prototype.del = function() {
    this.iframe.attr("src", "");
    this.stop();
    this.html.remove();
}
cn.mapway.ui.Uploader.FileItem.prototype.stop = function() {
    console.log("stop timer " + this.timer);
    clearInterval(this.timer);
    this.timer = -1;
}
cn.mapway.ui.Uploader.FileItem.prototype.ondata = function(d) {

    if (d.retCode == 1) {
        this.stop();
    } else {
        if (d.files != null && d.files.length > 0) {
            var f = d.files[0];
            var info = "";
            console.log(f.contentLength);
            if (f.contentLength > 0) {
                var pc = Math.floor((f.byteRead * 100 / f.contentLength));
                info = pc + "%";
                var w = this.progress.width() * pc / 100 - 2;
                this.dom.css('width', w + "px");
                if (Math.abs(pc = 100) < 0.0001) {
                    this.stop();
                }
            } else {
                info = "未知大小";
            }
            this.dom.text(info);
        }
    }
}
