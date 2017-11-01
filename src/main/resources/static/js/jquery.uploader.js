/**
 * 文件上传JQUERY plugin.
 */
(function($) {

    $.fn.fu = function(options) {
        var settings = $.extend({}, $.fn.fu.defaults, options || {});
        var native_css = "cursor: pointer;position: absolute;right: 0px;top: 0px;left: 0px;bottom: 0px;filter: alpha(opacity = 0);opacity: 0;"
        var input_source = "<input type='file'   style='" + native_css + "'/>";
        var btn_css = "position:relative;display:inline-block;padding:3px;background-color:darkblue;color:white;";
        function debug(obj) {
            if (window.console && window.console.log) {
                window.console.log(obj);
            }
        }
        // 停止传输
        function _stop() {

        }
        // 用户选择文件
        function _change_file() {
            var file = $(this);
            var fp = file.parent();
            var tag = (Math.random() + "").replace("\.", "").substring(1);
            var loading = new $.fn.fu.loading(file, tag, settings);

            fp.parent().parent().find("._pcon").append(loading);
            var input = $(input_source).attr("name", settings.fieldName)
                    .change(_change_file);
            fp.append(input);

        }
        function _init_obj(elem) {
            var html = "<div style='box-shadow: 0px 1px 2px 0 rgba(34, 36, 38, 0.15);border:solid 1px #f0f0f0;border-radius:6px;width:450px;'>"
                    + "     <div style='height:50px;line-height:50px;padding:0px 5px;border-radius:6px 6px 0px 0px;font-weight:bold;color:white;background-color:#00B5FA;'>文件上传 " +
                    		"<img src='"+settings.uploadUrl+"/icon?ext=upload' style='float:right;margin-top:5px;margin-right:5px;' width='40px'/></div>"
                    + "     <div class='_pcon' style='height:300px;overflow:auto;'/>"
                    + "     <div style='padding:8px 5px;background-color:#f0f0f0;border-radius:0px 0px 6px 6px;'>"
                    + "         <div class='_btnupload' style='"
                    + btn_css
                    + "'>上传文件</div></div>"
                    + "</div>";
            elem.html(html);
            var input = $(input_source).attr("name", settings.fieldName)
                    .change(_change_file);
            elem.find("._btnupload").append(input);
        }

        return this.each(function() {
            var elem = $(this);
            _init_obj(elem);
        });
    }
    $.fn.fu.defaults = {
        fieldName : "file",
        uploadUrl : "fileserver",
        tagName : "tag",
        onSuccess : function(d) {

        }
    };

    /**
     * 加载文件组件
     */
    $.fn.fu.loading = function(file, tag, settings) {
        this.settings = settings;

        this.file = file;
        this.checkurl = settings.uploadUrl + "/state?" + settings.tagName + "="
                + tag;
        this.iframe = $('<iframe style="display:none;" name="' + tag + '" />');
        this.form = $('<form method="post" style="display:none;" enctype="multipart/form-data" />');
        this.form.attr("target", tag).attr("name", "form_" + tag).attr(
                "action",
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

        var html = "<div style='border-bottom:solid 1px #f0f0f0;padding:4px 5px;'><table width='100%'><tr><td width='90px' rowspan='2'><img class='_pic' width='80px' height='80px' /></td>"
                + "<td><div class='_title' style='display:none;'></div>"
                + "<div class='_progress' style='display:inline-block;width:100%;height:22px;border:solid 1px skyblue;position:relative;'>"
                + "    <div class='_dom' style='text-align:center;line-height:18px;height:18px;overflow:hidden;position:absolute;left:1px;top:1px;width:0px;background-color:skyblue;color:yellow;'></div>"
                + "</div>"
                + "<div class='_sizePan' style='display:none;'></div>"
                + "</td>"
                + "<td align='right' width='80px' rowspan='2'><img width='30px' src='"+this.settings.uploadUrl+"/icon?ext=delete' class='_del' /></td>"
                + "</tr></table>";
        var $html = $(html);
        this.progress = $html.find("._progress");
        this.dom = $html.find("._dom");
        this.dele = $html.find("._del");
        this.pic = $html.find("._pic");
        this.title = $html.find("._title");
        this.sizePan = $html.find("._sizePan");
        this.dele.click(this.del.bind(this));

        this.html = $html;

        this.timer = setInterval(this.check.bind(this), 200);
        return this.html;
    }
    $.fn.fu.loading.prototype.finish = function(d) {
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

            this.pic.attr("src", this.settings.uploadUrl + "/icon?ext="
                    + data.icon);

        }
    }
    $.fn.fu.loading.prototype.toReadableSize = function($fileSize) {
        $unit = new Array(' 字节', ' KB', ' MB', ' GB', ' TB', ' PB', ' EB',
                ' ZB', ' YB');
        $i = 0;
        $inv = 1 / 1024;

        while ($fileSize >= 1024 && $i < 8) {
            $fileSize *= $inv;
            ++$i;
        }

        $fileSizeTmp = Math.floor($fileSize);
        return $fileSizeTmp + $unit[$i];

    }
    $.fn.fu.loading.prototype.check = function() {

        $.get(this.checkurl, this.ondata.bind(this));
    }
    $.fn.fu.loading.prototype.del = function() {
        this.iframe.attr("src", "");
        this.stop();
        this.html.remove();
    }
    $.fn.fu.loading.prototype.stop = function() {
        console.log("stop timer " + this.timer);
        clearInterval(this.timer);
        this.timer = -1;
    }
    $.fn.fu.loading.prototype.ondata = function(d) {

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
}(jQuery));