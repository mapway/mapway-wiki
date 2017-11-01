package cn.mapway.wiki.servlet;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.nutz.img.Images;
import org.nutz.lang.Strings;

/**
 * Mimetype
 * 
 * @author zhangjianshe
 *
 */
public class MimeType {

	private static final String OCTET = "application/octet-stream";

	private static MimeType INSTANCE;

	private Map<String, MimeTypeData> mapper;

	protected MimeType() {
		mapper = new HashMap<String, MimeTypeData>();
		mapper.put("001", new MimeTypeData("application/x-001", "001.png"));
		mapper.put("323", new MimeTypeData("text/h323", "323.png"));
		mapper.put("907", new MimeTypeData("drawing/907", "907.png"));
		mapper.put("acp", new MimeTypeData("audio/x-mei-aac", "acp.png"));
		mapper.put("aif", new MimeTypeData("audio/aiff", "aif.png"));
		mapper.put("aiff", new MimeTypeData("audio/aiff", "aiff.png"));
		mapper.put("asa", new MimeTypeData("text/asa", "asa.png"));
		mapper.put("asp", new MimeTypeData("text/asp", "asp.png"));
		mapper.put("au", new MimeTypeData("audio/basic", "au.png"));
		mapper.put("awf", new MimeTypeData("application/vnd.adobe.workflow", "awf.png"));
		mapper.put("bmp", new MimeTypeData("application/x-bmp", "bmp.png"));
		mapper.put("c4t", new MimeTypeData("application/x-c4t", "c4t.png"));
		mapper.put("cal", new MimeTypeData("application/x-cals", "cal.png"));
		mapper.put("cdf", new MimeTypeData("application/x-netcdf", "cdf.png"));
		mapper.put("cel", new MimeTypeData("application/x-cel", "cel.png"));
		mapper.put("cg4", new MimeTypeData("application/x-g4", "cg4.png"));
		mapper.put("cit", new MimeTypeData("application/x-cit", "cit.png"));
		mapper.put("cml", new MimeTypeData("text/xml", "cml.png"));
		mapper.put("cmx", new MimeTypeData("application/x-cmx", "cmx.png"));
		mapper.put("crl", new MimeTypeData("application/pkix-crl", "crl.png"));
		mapper.put("csi", new MimeTypeData("application/x-csi", "csi.png"));
		mapper.put("cut", new MimeTypeData("application/x-cut", "cut.png"));
		mapper.put("dbm", new MimeTypeData("application/x-dbm", "dbm.png"));
		mapper.put("dcd", new MimeTypeData("text/xml", "dcd.png"));
		mapper.put("der", new MimeTypeData("application/x-x509-ca-cert", "der.png"));
		mapper.put("dib", new MimeTypeData("application/x-dib", "dib.png"));
		mapper.put("doc", new MimeTypeData("application/msword", "doc.png"));
		mapper.put("docx", new MimeTypeData("application/msword", "docx.png"));
		mapper.put("drw", new MimeTypeData("application/x-drw", "drw.png"));
		mapper.put("dwf", new MimeTypeData("Model/vnd.dwf", "dwf.png"));
		mapper.put("dwg", new MimeTypeData("application/x-dwg", "dwg.png"));
		mapper.put("dxf", new MimeTypeData("application/x-dxf", "dxf.png"));
		mapper.put("emf", new MimeTypeData("application/x-emf", "emf.png"));
		mapper.put("ent", new MimeTypeData("text/xml", "ent.png"));
		mapper.put("eps", new MimeTypeData("application/x-ps", "eps.png"));
		mapper.put("etd", new MimeTypeData("application/x-ebx", "etd.png"));
		mapper.put("fax", new MimeTypeData("image/fax", "fax.png"));
		mapper.put("fif", new MimeTypeData("application/fractals", "fif.png"));
		mapper.put("frm", new MimeTypeData("application/x-frm", "frm.png"));
		mapper.put("gbr", new MimeTypeData("application/x-gbr", "gbr.png"));
		mapper.put("gif", new MimeTypeData("image/gif", "gif.png"));
		mapper.put("gp4", new MimeTypeData("application/x-gp4", "gp4.png"));
		mapper.put("hmr", new MimeTypeData("application/x-hmr", "hmr.png"));
		mapper.put("hpl", new MimeTypeData("application/x-hpl", "hpl.png"));
		mapper.put("hrf", new MimeTypeData("application/x-hrf", "hrf.png"));
		mapper.put("htc", new MimeTypeData("text/x-component", "htc.png"));
		mapper.put("html", new MimeTypeData("text/html", "html.png"));
		mapper.put("htx", new MimeTypeData("text/html", "htx.png"));
		mapper.put("ico", new MimeTypeData("image/x-icon", "ico.png"));
		mapper.put("iff", new MimeTypeData("application/x-iff", "iff.png"));
		mapper.put("igs", new MimeTypeData("application/x-igs", "igs.png"));
		mapper.put("img", new MimeTypeData("application/x-img", "img.png"));
		mapper.put("isp", new MimeTypeData("application/x-internet-signup", "isp.png"));
		mapper.put("java", new MimeTypeData("java/*", "java.png"));
		mapper.put("jpe", new MimeTypeData("image/jpeg", "jpe.png"));
		mapper.put("jpeg", new MimeTypeData("image/jpeg", "jpeg.png"));
		mapper.put("jpg", new MimeTypeData("application/x-jpg", "jpg.png"));
		mapper.put("jsp", new MimeTypeData("text/html", "jsp.png"));
		mapper.put("lar", new MimeTypeData("application/x-laplayer-reg", "lar.png"));
		mapper.put("lavs", new MimeTypeData("audio/x-liquid-secure", "lavs.png"));
		mapper.put("lmsff", new MimeTypeData("audio/x-la-lms", "lmsff.png"));
		mapper.put("ltr", new MimeTypeData("application/x-ltr", "ltr.png"));
		mapper.put("m2v", new MimeTypeData("video/x-mpeg", "m2v.png"));
		mapper.put("m4e", new MimeTypeData("video/mpeg4", "m4e.png"));
		mapper.put("man", new MimeTypeData("application/x-troff-man", "man.png"));
		mapper.put("mdb", new MimeTypeData("application/msaccess", "mdb.png"));
		mapper.put("mfp", new MimeTypeData("application/x-shockwave-flash", "mfp.png"));
		mapper.put("mhtml", new MimeTypeData("message/rfc822", "mhtml.png"));
		mapper.put("mid", new MimeTypeData("audio/mid", "mid.png"));
		mapper.put("mil", new MimeTypeData("application/x-mil", "mil.png"));
		mapper.put("mnd", new MimeTypeData("audio/x-musicnet-download", "mnd.png"));
		mapper.put("mocha", new MimeTypeData("application/x-javascript", "mocha.png"));
		mapper.put("mp1", new MimeTypeData("audio/mp1", "mp1.png"));
		mapper.put("mp2v", new MimeTypeData("video/mpeg", "mp2v.png"));
		mapper.put("mp4", new MimeTypeData("video/mpeg4", "mp4.png"));
		mapper.put("mpd", new MimeTypeData("application/vnd.ms-project", "mpd.png"));
		mapper.put("mpeg", new MimeTypeData("video/mpg", "mpeg.png"));
		mapper.put("mpga", new MimeTypeData("audio/rn-mpeg", "mpga.png"));
		mapper.put("mps", new MimeTypeData("video/x-mpeg", "mps.png"));
		mapper.put("mpv", new MimeTypeData("video/mpg", "mpv.png"));
		mapper.put("mpw", new MimeTypeData("application/vnd.ms-project", "mpw.png"));
		mapper.put("mtx", new MimeTypeData("text/xml", "mtx.png"));
		mapper.put("net", new MimeTypeData("image/pnetvue", "net.png"));
		mapper.put("nws", new MimeTypeData("message/rfc822", "nws.png"));
		mapper.put("out", new MimeTypeData("application/x-out", "out.png"));
		mapper.put("p12", new MimeTypeData("application/x-pkcs12", "p12.png"));
		mapper.put("p7c", new MimeTypeData("application/pkcs7-mime", "p7c.png"));
		mapper.put("p7r", new MimeTypeData("application/x-pkcs7-certreqresp", "p7r.png"));
		mapper.put("pc5", new MimeTypeData("application/x-pc5", "pc5.png"));
		mapper.put("pcl", new MimeTypeData("application/x-pcl", "pcl.png"));
		mapper.put("pdf", new MimeTypeData("application/pdf", "pdf.png"));
		mapper.put("pdx", new MimeTypeData("application/vnd.adobe.pdx", "pdx.png"));
		mapper.put("pgl", new MimeTypeData("application/x-pgl", "pgl.png"));
		mapper.put("pko", new MimeTypeData("application/vnd.ms-pki.pko", "pko.png"));
		mapper.put("plg", new MimeTypeData("text/html", "plg.png"));
		mapper.put("plt", new MimeTypeData("application/x-plt", "plt.png"));
		mapper.put("png", new MimeTypeData("application/x-png", "png.png"));
		mapper.put("ppa", new MimeTypeData("application/vnd.ms-powerpoint", "ppa.png"));
		mapper.put("pps", new MimeTypeData("application/vnd.ms-powerpoint", "pps.png"));
		mapper.put("ppt", new MimeTypeData("application/x-ppt", "ppt.png"));
		mapper.put("pptx", new MimeTypeData("application/x-ppt", "pptx.png"));
		mapper.put("prf", new MimeTypeData("application/pics-rules", "prf.png"));
		mapper.put("prt", new MimeTypeData("application/x-prt", "prt.png"));
		mapper.put("ps", new MimeTypeData("application/postscript", "ps.png"));
		mapper.put("pwz", new MimeTypeData("application/vnd.ms-powerpoint", "pwz.png"));
		mapper.put("ra", new MimeTypeData("audio/vnd.rn-realaudio", "ra.png"));
		mapper.put("ras", new MimeTypeData("application/x-ras", "ras.png"));
		mapper.put("rdf", new MimeTypeData("text/xml", "rdf.png"));
		mapper.put("red", new MimeTypeData("application/x-red", "red.png"));
		mapper.put("rjs", new MimeTypeData("application/vnd.rn-realsystem-rjs", "rjs.png"));
		mapper.put("rlc", new MimeTypeData("application/x-rlc", "rlc.png"));
		mapper.put("rm", new MimeTypeData("application/vnd.rn-realmedia", "rm.png"));
		mapper.put("rmi", new MimeTypeData("audio/mid", "rmi.png"));
		mapper.put("rmm", new MimeTypeData("audio/x-pn-realaudio", "rmm.png"));
		mapper.put("rms", new MimeTypeData("application/vnd.rn-realmedia-secure", "rms.png"));
		mapper.put("rmx", new MimeTypeData("application/vnd.rn-realsystem-rmx", "rmx.png"));
		mapper.put("rp", new MimeTypeData("image/vnd.rn-realpix", "rp.png"));
		mapper.put("rsml", new MimeTypeData("application/vnd.rn-rsml", "rsml.png"));
		mapper.put("rtf", new MimeTypeData("application/msword", "rtf.png"));
		mapper.put("rv", new MimeTypeData("video/vnd.rn-realvideo", "rv.png"));
		mapper.put("sat", new MimeTypeData("application/x-sat", "sat.png"));
		mapper.put("sdw", new MimeTypeData("application/x-sdw", "sdw.png"));
		mapper.put("slb", new MimeTypeData("application/x-slb", "slb.png"));
		mapper.put("slk", new MimeTypeData("drawing/x-slk", "slk.png"));
		mapper.put("smil", new MimeTypeData("application/smil", "smil.png"));
		mapper.put("snd", new MimeTypeData("audio/basic", "snd.png"));
		mapper.put("sor", new MimeTypeData("text/plain", "sor.png"));
		mapper.put("spl", new MimeTypeData("application/futuresplash", "spl.png"));
		mapper.put("ssm", new MimeTypeData("application/streamingmedia", "ssm.png"));
		mapper.put("stl", new MimeTypeData("application/vnd.ms-pki.stl", "stl.png"));
		mapper.put("sty", new MimeTypeData("application/x-sty", "sty.png"));
		mapper.put("swf", new MimeTypeData("application/x-shockwave-flash", "swf.png"));
		mapper.put("tg4", new MimeTypeData("application/x-tg4", "tg4.png"));
		mapper.put("tif", new MimeTypeData("image/tiff", "tif.png"));
		mapper.put("tiff", new MimeTypeData("image/tiff", "tiff.png"));
		mapper.put("top", new MimeTypeData("drawing/x-top", "top.png"));
		mapper.put("tsd", new MimeTypeData("text/xml", "tsd.png"));
		mapper.put("uin", new MimeTypeData("application/x-icq", "uin.png"));
		mapper.put("vcf", new MimeTypeData("text/x-vcard", "vcf.png"));
		mapper.put("vdx", new MimeTypeData("application/vnd.visio", "vdx.png"));
		mapper.put("vpg", new MimeTypeData("application/x-vpeg005", "vpg.png"));
		mapper.put("vsd", new MimeTypeData("application/x-vsd", "vsd.png"));
		mapper.put("vst", new MimeTypeData("application/vnd.visio", "vst.png"));
		mapper.put("vsw", new MimeTypeData("application/vnd.visio", "vsw.png"));
		mapper.put("vtx", new MimeTypeData("application/vnd.visio", "vtx.png"));
		mapper.put("wav", new MimeTypeData("audio/wav", "wav.png"));
		mapper.put("wb1", new MimeTypeData("application/x-wb1", "wb1.png"));
		mapper.put("wb3", new MimeTypeData("application/x-wb3", "wb3.png"));
		mapper.put("wiz", new MimeTypeData("application/msword", "wiz.png"));
		mapper.put("wk4", new MimeTypeData("application/x-wk4", "wk4.png"));
		mapper.put("wks", new MimeTypeData("application/x-wks", "wks.png"));
		mapper.put("wma", new MimeTypeData("audio/x-ms-wma", "wma.png"));
		mapper.put("wmf", new MimeTypeData("application/x-wmf", "wmf.png"));
		mapper.put("wmv", new MimeTypeData("video/x-ms-wmv", "wmv.png"));
		mapper.put("wmz", new MimeTypeData("application/x-ms-wmz", "wmz.png"));
		mapper.put("wpd", new MimeTypeData("application/x-wpd", "wpd.png"));
		mapper.put("wpl", new MimeTypeData("application/vnd.ms-wpl", "wpl.png"));
		mapper.put("wr1", new MimeTypeData("application/x-wr1", "wr1.png"));
		mapper.put("wrk", new MimeTypeData("application/x-wrk", "wrk.png"));
		mapper.put("ws2", new MimeTypeData("application/x-ws", "ws2.png"));
		mapper.put("wsdl", new MimeTypeData("text/xml", "wsdl.png"));
		mapper.put("xdp", new MimeTypeData("application/vnd.adobe.xdp", "xdp.png"));
		mapper.put("xfd", new MimeTypeData("application/vnd.adobe.xfd", "xfd.png"));
		mapper.put("xhtml", new MimeTypeData("text/html", "xhtml.png"));
		mapper.put("xls", new MimeTypeData("application/x-xls", "xls.png"));
		mapper.put("xml", new MimeTypeData("text/xml", "xml.png"));
		mapper.put("xq", new MimeTypeData("text/xml", "xq.png"));
		mapper.put("xquery", new MimeTypeData("text/xml", "xquery.png"));
		mapper.put("xsl", new MimeTypeData("text/xml", "xsl.png"));
		mapper.put("xwd", new MimeTypeData("application/x-xwd", "xwd.png"));
		mapper.put("sis", new MimeTypeData("application/vnd.symbian.install", "sis.png"));
		mapper.put("x_t", new MimeTypeData("application/x-x_t", "x_t.png"));
		mapper.put("apk", new MimeTypeData("application/vnd.android.package-archive", "apk.png"));
		mapper.put("301", new MimeTypeData("application/x-301", "301.png"));
		mapper.put("906", new MimeTypeData("application/x-906", "906.png"));
		mapper.put("a11", new MimeTypeData("application/x-a11", "a11.png"));
		mapper.put("ai", new MimeTypeData("application/postscript", "ai.png"));
		mapper.put("aifc", new MimeTypeData("audio/aiff", "aifc.png"));
		mapper.put("anv", new MimeTypeData("application/x-anv", "anv.png"));
		mapper.put("asf", new MimeTypeData("video/x-ms-asf", "asf.png"));
		mapper.put("asx", new MimeTypeData("video/x-ms-asf", "asx.png"));
		mapper.put("avi", new MimeTypeData("video/avi", "avi.png"));
		mapper.put("biz", new MimeTypeData("text/xml", "biz.png"));
		mapper.put("bot", new MimeTypeData("application/x-bot", "bot.png"));
		mapper.put("c90", new MimeTypeData("application/x-c90", "c90.png"));
		mapper.put("cat", new MimeTypeData("application/vnd.ms-pki.seccat", "cat.png"));
		mapper.put("cdr", new MimeTypeData("application/x-cdr", "cdr.png"));
		mapper.put("cer", new MimeTypeData("application/x-x509-ca-cert", "cer.png"));
		mapper.put("cgm", new MimeTypeData("application/x-cgm", "cgm.png"));
		mapper.put("class", new MimeTypeData("java/*", "class.png"));
		mapper.put("cmp", new MimeTypeData("application/x-cmp", "cmp.png"));
		mapper.put("cot", new MimeTypeData("application/x-cot", "cot.png"));
		mapper.put("crt", new MimeTypeData("application/x-x509-ca-cert", "crt.png"));
		mapper.put("css", new MimeTypeData("text/css", "css.png"));
		mapper.put("dbf", new MimeTypeData("application/x-dbf", "dbf.png"));
		mapper.put("dbx", new MimeTypeData("application/x-dbx", "dbx.png"));
		mapper.put("dcx", new MimeTypeData("application/x-dcx", "dcx.png"));
		mapper.put("dgn", new MimeTypeData("application/x-dgn", "dgn.png"));
		mapper.put("dll", new MimeTypeData("application/x-msdownload", "dll.png"));
		mapper.put("dot", new MimeTypeData("application/msword", "dot.png"));
		mapper.put("dtd", new MimeTypeData("text/xml", "dtd.png"));
		mapper.put("dwf", new MimeTypeData("application/x-dwf", "dwf.png"));
		mapper.put("dxb", new MimeTypeData("application/x-dxb", "dxb.png"));
		mapper.put("edn", new MimeTypeData("application/vnd.adobe.edn", "edn.png"));
		mapper.put("eml", new MimeTypeData("message/rfc822", "eml.png"));
		mapper.put("epi", new MimeTypeData("application/x-epi", "epi.png"));
		mapper.put("eps", new MimeTypeData("application/postscript", "eps.png"));
		mapper.put("exe", new MimeTypeData("application/x-msdownload", "exe.png"));
		mapper.put("fdf", new MimeTypeData("application/vnd.fdf", "fdf.png"));
		mapper.put("fo", new MimeTypeData("text/xml", "fo.png"));
		mapper.put("g4", new MimeTypeData("application/x-g4", "g4.png"));
		mapper.put("", new MimeTypeData("application/x-", ".png"));
		mapper.put("gl2", new MimeTypeData("application/x-gl2", "gl2.png"));
		mapper.put("hgl", new MimeTypeData("application/x-hgl", "hgl.png"));
		mapper.put("hpg", new MimeTypeData("application/x-hpgl", "hpg.png"));
		mapper.put("hqx", new MimeTypeData("application/mac-binhex40", "hqx.png"));
		mapper.put("hta", new MimeTypeData("application/hta", "hta.png"));
		mapper.put("htm", new MimeTypeData("text/html", "htm.png"));
		mapper.put("htt", new MimeTypeData("text/webviewhtml", "htt.png"));
		mapper.put("icb", new MimeTypeData("application/x-icb", "icb.png"));
		mapper.put("ico", new MimeTypeData("application/x-ico", "ico.png"));
		mapper.put("ig4", new MimeTypeData("application/x-g4", "ig4.png"));
		mapper.put("iii", new MimeTypeData("application/x-iphone", "iii.png"));
		mapper.put("ins", new MimeTypeData("application/x-internet-signup", "ins.png"));
		mapper.put("IVF", new MimeTypeData("video/x-ivf", "IVF.png"));
		mapper.put("jfif", new MimeTypeData("image/jpeg", "jfif.png"));
		mapper.put("jpe", new MimeTypeData("application/x-jpe", "jpe.png"));
		mapper.put("jpg", new MimeTypeData("image/jpeg", "jpg.png"));
		mapper.put("js", new MimeTypeData("application/x-javascript", "js.png"));
		mapper.put("la1", new MimeTypeData("audio/x-liquid-file", "la1.png"));
		mapper.put("latex", new MimeTypeData("application/x-latex", "latex.png"));
		mapper.put("lbm", new MimeTypeData("application/x-lbm", "lbm.png"));
		mapper.put("ls", new MimeTypeData("application/x-javascript", "ls.png"));
		mapper.put("m1v", new MimeTypeData("video/x-mpeg", "m1v.png"));
		mapper.put("m3u", new MimeTypeData("audio/mpegurl", "m3u.png"));
		mapper.put("mac", new MimeTypeData("application/x-mac", "mac.png"));
		mapper.put("math", new MimeTypeData("text/xml", "math.png"));
		mapper.put("mdb", new MimeTypeData("application/x-mdb", "mdb.png"));
		mapper.put("mht", new MimeTypeData("message/rfc822", "mht.png"));
		mapper.put("mi", new MimeTypeData("application/x-mi", "mi.png"));
		mapper.put("midi", new MimeTypeData("audio/mid", "midi.png"));
		mapper.put("mml", new MimeTypeData("text/xml", "mml.png"));
		mapper.put("mns", new MimeTypeData("audio/x-musicnet-stream", "mns.png"));
		mapper.put("movie", new MimeTypeData("video/x-sgi-movie", "movie.png"));
		mapper.put("mp2", new MimeTypeData("audio/mp2", "mp2.png"));
		mapper.put("mp3", new MimeTypeData("audio/mp3", "mp3.png"));
		mapper.put("mpa", new MimeTypeData("video/x-mpg", "mpa.png"));
		mapper.put("mpe", new MimeTypeData("video/x-mpeg", "mpe.png"));
		mapper.put("mpg", new MimeTypeData("video/mpg", "mpg.png"));
		mapper.put("mpp", new MimeTypeData("application/vnd.ms-project", "mpp.png"));
		mapper.put("mpt", new MimeTypeData("application/vnd.ms-project", "mpt.png"));
		mapper.put("mpv2", new MimeTypeData("video/mpeg", "mpv2.png"));
		mapper.put("mpx", new MimeTypeData("application/vnd.ms-project", "mpx.png"));
		mapper.put("mxp", new MimeTypeData("application/x-mmxp", "mxp.png"));
		mapper.put("nrf", new MimeTypeData("application/x-nrf", "nrf.png"));
		mapper.put("odc", new MimeTypeData("text/x-ms-odc", "odc.png"));
		mapper.put("p10", new MimeTypeData("application/pkcs10", "p10.png"));
		mapper.put("p7b", new MimeTypeData("application/x-pkcs7-certificates", "p7b.png"));
		mapper.put("p7m", new MimeTypeData("application/pkcs7-mime", "p7m.png"));
		mapper.put("p7s", new MimeTypeData("application/pkcs7-signature", "p7s.png"));
		mapper.put("pci", new MimeTypeData("application/x-pci", "pci.png"));
		mapper.put("pcx", new MimeTypeData("application/x-pcx", "pcx.png"));
		mapper.put("pdf", new MimeTypeData("application/pdf", "pdf.png"));
		mapper.put("pfx", new MimeTypeData("application/x-pkcs12", "pfx.png"));
		mapper.put("pic", new MimeTypeData("application/x-pic", "pic.png"));
		mapper.put("pl", new MimeTypeData("application/x-perl", "pl.png"));
		mapper.put("pls", new MimeTypeData("audio/scpls", "pls.png"));
		mapper.put("png", new MimeTypeData("image/png", "png.png"));
		mapper.put("pot", new MimeTypeData("application/vnd.ms-powerpoint", "pot.png"));
		mapper.put("ppm", new MimeTypeData("application/x-ppm", "ppm.png"));
		mapper.put("ppt", new MimeTypeData("application/vnd.ms-powerpoint", "ppt.png"));
		mapper.put("pr", new MimeTypeData("application/x-pr", "pr.png"));
		mapper.put("ps", new MimeTypeData("application/x-ps", "ps.png"));
		mapper.put("ptn", new MimeTypeData("application/x-ptn", "ptn.png"));
		mapper.put("r3t", new MimeTypeData("text/vnd.rn-realtext3d", "r3t.png"));
		mapper.put("ram", new MimeTypeData("audio/x-pn-realaudio", "ram.png"));
		mapper.put("rat", new MimeTypeData("application/rat-file", "rat.png"));
		mapper.put("rec", new MimeTypeData("application/vnd.rn-recording", "rec.png"));
		mapper.put("rgb", new MimeTypeData("application/x-rgb", "rgb.png"));
		mapper.put("rjt", new MimeTypeData("application/vnd.rn-realsystem-rjt", "rjt.png"));
		mapper.put("rle", new MimeTypeData("application/x-rle", "rle.png"));
		mapper.put("rmf", new MimeTypeData("application/vnd.adobe.rmf", "rmf.png"));
		mapper.put("rmj", new MimeTypeData("application/vnd.rn-realsystem-rmj", "rmj.png"));
		mapper.put("rmp", new MimeTypeData("application/vnd.rn-rn_music_package", "rmp.png"));
		mapper.put("rmvb", new MimeTypeData("application/vnd.rn-realmedia-vbr", "rmvb.png"));
		mapper.put("rnx", new MimeTypeData("application/vnd.rn-realplayer", "rnx.png"));
		mapper.put("rpm", new MimeTypeData("audio/x-pn-realaudio-plugin", "rpm.png"));
		mapper.put("rt", new MimeTypeData("text/vnd.rn-realtext", "rt.png"));
		mapper.put("rtf", new MimeTypeData("application/x-rtf", "rtf.png"));
		mapper.put("sam", new MimeTypeData("application/x-sam", "sam.png"));
		mapper.put("sdp", new MimeTypeData("application/sdp", "sdp.png"));
		mapper.put("sit", new MimeTypeData("application/x-stuffit", "sit.png"));
		mapper.put("sld", new MimeTypeData("application/x-sld", "sld.png"));
		mapper.put("smi", new MimeTypeData("application/smil", "smi.png"));
		mapper.put("smk", new MimeTypeData("application/x-smk", "smk.png"));
		mapper.put("sol", new MimeTypeData("text/plain", "sol.png"));
		mapper.put("spc", new MimeTypeData("application/x-pkcs7-certificates", "spc.png"));
		mapper.put("spp", new MimeTypeData("text/xml", "spp.png"));
		mapper.put("sst", new MimeTypeData("application/vnd.ms-pki.certstore", "sst.png"));
		mapper.put("stm", new MimeTypeData("text/html", "stm.png"));
		mapper.put("svg", new MimeTypeData("text/xml", "svg.png"));
		mapper.put("tdf", new MimeTypeData("application/x-tdf", "tdf.png"));
		mapper.put("tga", new MimeTypeData("application/x-tga", "tga.png"));
		mapper.put("tif", new MimeTypeData("application/x-tif", "tif.png"));
		mapper.put("tld", new MimeTypeData("text/xml", "tld.png"));
		mapper.put("torrent", new MimeTypeData("application/x-bittorrent", "torrent.png"));
		mapper.put("txt", new MimeTypeData("text/plain", "txt.png"));
		mapper.put("uls", new MimeTypeData("text/iuls", "uls.png"));
		mapper.put("vda", new MimeTypeData("application/x-vda", "vda.png"));
		mapper.put("vml", new MimeTypeData("text/xml", "vml.png"));
		mapper.put("vsd", new MimeTypeData("application/vnd.visio", "vsd.png"));
		mapper.put("vss", new MimeTypeData("application/vnd.visio", "vss.png"));
		mapper.put("vst", new MimeTypeData("application/x-vst", "vst.png"));
		mapper.put("vsx", new MimeTypeData("application/vnd.visio", "vsx.png"));
		mapper.put("vxml", new MimeTypeData("text/xml", "vxml.png"));
		mapper.put("wax", new MimeTypeData("audio/x-ms-wax", "wax.png"));
		mapper.put("wb2", new MimeTypeData("application/x-wb2", "wb2.png"));
		mapper.put("wbmp", new MimeTypeData("image/vnd.wap.wbmp", "wbmp.png"));
		mapper.put("wk3", new MimeTypeData("application/x-wk3", "wk3.png"));
		mapper.put("wkq", new MimeTypeData("application/x-wkq", "wkq.png"));
		mapper.put("wm", new MimeTypeData("video/x-ms-wm", "wm.png"));
		mapper.put("wmd", new MimeTypeData("application/x-ms-wmd", "wmd.png"));
		mapper.put("wml", new MimeTypeData("text/vnd.wap.wml", "wml.png"));
		mapper.put("wmx", new MimeTypeData("video/x-ms-wmx", "wmx.png"));
		mapper.put("wp6", new MimeTypeData("application/x-wp6", "wp6.png"));
		mapper.put("wpg", new MimeTypeData("application/x-wpg", "wpg.png"));
		mapper.put("wq1", new MimeTypeData("application/x-wq1", "wq1.png"));
		mapper.put("wri", new MimeTypeData("application/x-wri", "wri.png"));
		mapper.put("ws", new MimeTypeData("application/x-ws", "ws.png"));
		mapper.put("wsc", new MimeTypeData("text/scriptlet", "wsc.png"));
		mapper.put("wvx", new MimeTypeData("video/x-ms-wvx", "wvx.png"));
		mapper.put("xdr", new MimeTypeData("text/xml", "xdr.png"));
		mapper.put("xfdf", new MimeTypeData("application/vnd.adobe.xfdf", "xfdf.png"));
		mapper.put("xls", new MimeTypeData("application/vnd.ms-excel", "xls.png"));
		mapper.put("xlsx", new MimeTypeData("application/vnd.ms-excel", "xlsx.png"));
		mapper.put("xlw", new MimeTypeData("application/x-xlw", "xlw.png"));
		mapper.put("xpl", new MimeTypeData("audio/scpls", "xpl.png"));
		mapper.put("xql", new MimeTypeData("text/xml", "xql.png"));
		mapper.put("xsd", new MimeTypeData("text/xml", "xsd.png"));
		mapper.put("xslt", new MimeTypeData("text/xml", "xslt.png"));
		mapper.put("x_b", new MimeTypeData("application/x-x_b", "x_b.png"));
		mapper.put("sisx", new MimeTypeData("application/vnd.symbian.install", "sisx.png"));
		mapper.put("ipa", new MimeTypeData("application/vnd.iphone", "ipa.png"));
		mapper.put("xap", new MimeTypeData("application/x-silverlight-app", "xap.png"));
		mapper.put("tif", new MimeTypeData("image/tiff", "tif.png"));
		mapper.put("zip", new MimeTypeData(OCTET, "zip.png"));
		mapper.put("tar", new MimeTypeData(OCTET, "tar.png"));
		mapper.put("gz", new MimeTypeData(OCTET, "gz.png"));
	}

	public static synchronized MimeType get() {
		if (INSTANCE == null) {
			INSTANCE = new MimeType();
		}
		return INSTANCE;
	}

	/**
	 * 根据文件扩展名 查找文件的MIME类型.
	 * 
	 * @param fileExtension
	 * @return
	 */
	public String find(String fileExtension) {
		if (Strings.isBlank(fileExtension)) {
			return OCTET;
		}
		MimeTypeData v = mapper.get(fileExtension);

		if (v == null) {
			return OCTET;
		}
		return v.mime;
	}

	public static void main(String[] args) {

		File d = new File("d:\\res");
		if (d.exists()) {
			d.delete();
		}

		d.mkdirs();

		MimeType t = MimeType.get();
		for (String key : t.mapper.keySet()) {
			String txt = key;
			if (txt.length() > 3) {
				txt = txt.substring(0, 2);
			}
			BufferedImage image = Images.read("d:\\pdf.png");
			Graphics2D s2d = image.createGraphics();
			s2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			Font f = new Font("Impact", Font.PLAIN, 22);
			s2d.setFont(f);

			s2d.drawString(txt, 2, 40);
			try {
				Images.write(image, new File("C:\\Users\\zhangjianshe\\git\\mapway-wiki\\src\\main\\java\\cn\\mapway\\wiki\\servlet\\icons\\" + key + ".png"));
			} catch (Exception e) {
				System.out.println(key + " " + e.getMessage());
			}
		}
	}
}
