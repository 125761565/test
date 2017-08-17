<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=path%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- umeditor -->
<link href="<%=path%>/resources/themes/default/css/umeditor.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript"
	src="<%=path%>/resources/third-party/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/third-party/template.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=path%>/resources/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=path%>/resources/umeditor.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/lang/zh-cn/zh-cn.js"></script>
<script src="<%=path%>/resources/js/fileupload.js" type="text/javascript"></script>
<style type="text/css">
h1 {
	font-family: "微软雅黑";
	font-weight: normal;
}
</style>
</head>
<body>
	<h1>欢迎${user.username}</h1>
	<form id="myForm" action="" method="post"   enctype="application/x-www-form-urlencoded">
		
		书名：<input type="text" id="name" name="name"></input><br />
		<br />
		<br /> 价格：<input type="text" id="price" name="price"></input>￥[shift+4]<br />
		<br />
		<br />
		封面：<br/>
		 <div class="uploadBox">
							<div class="big-photo">
								<div id="preview">
								
									<img id="imghead" border="0"  src="<%=path%>/resources/img/upload.jpg" width="270"
										height="100" onclick="$('#previewImg').click();"></img>
										<input type="text" id="coverUrl"  name="coverUrl" style="display: none">	
								</div>
								<input type="file" onchange="previewImage(this)" 
									style="display: none;" id="previewImg"  name="file"></input>
							</div>
						</div>
		<br />
		<br /> 简介：<br />
		<textarea cols="100" rows="3" id="myEditor" name="content">
   
</textarea>
		<br /> <input type="submit" value="提交"></input>
	</form>
	<script type="text/javascript">
		//实例化编辑器
		var um = UM.getEditor('myEditor',{initialFrameHeight:100,initialFrameWidth:400 });
		um.addListener('blur', function() {
			$('#focush2').html('编辑器失去焦点了')
		});
		um.addListener('focus', function() {
			$('#focush2').html('')
		});
		//按钮的操作
		function insertHtml() {
			var value = prompt('插入html代码', '');
			um.execCommand('insertHtml', value)
		}
		function isFocus() {
			alert(um.isFocus())
		}
		function doBlur() {
			um.blur()
		}
		function createEditor() {
			enableBtn();
			um = UM.getEditor('myEditor');
		}
		function getAllHtml() {
			alert(UM.getEditor('myEditor').getAllHtml())
		}
		function getContent() {
			var arr = [];
			arr.push("使用editor.getContent()方法可以获得编辑器的内容");
			arr.push("内容为：");
			arr.push(UM.getEditor('myEditor').getContent());
			alert(arr.join("\n"));
		}
		function getPlainTxt() {
			var arr = [];
			arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
			arr.push("内容为：");
			arr.push(UM.getEditor('myEditor').getPlainTxt());
			alert(arr.join('\n'))
		}
		function setContent(isAppendTo) {
			var arr = [];
			arr.push("使用editor.setContent('欢迎使用umeditor')方法可以设置编辑器的内容");
			UM.getEditor('myEditor').setContent('欢迎使用umeditor', isAppendTo);
			alert(arr.join("\n"));
		}
		function setDisabled() {
			UM.getEditor('myEditor').setDisabled('fullscreen');
			disableBtn("enable");
		}

		function setEnabled() {
			UM.getEditor('myEditor').setEnabled();
			enableBtn();
		}

		function getText() {
			//当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
			var range = UM.getEditor('myEditor').selection.getRange();
			range.select();
			var txt = UM.getEditor('myEditor').selection.getText();
			alert(txt)
		}

		function getContentTxt() {
			var arr = [];
			arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
			arr.push("编辑器的纯文本内容为：");
			arr.push(UM.getEditor('myEditor').getContentTxt());
			alert(arr.join("\n"));
		}
		function hasContent() {
			var arr = [];
			arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
			arr.push("判断结果为：");
			arr.push(UM.getEditor('myEditor').hasContents());
			alert(arr.join("\n"));
		}
		function setFocus() {
			UM.getEditor('myEditor').focus();
		}
		function deleteEditor() {
			disableBtn();
			UM.getEditor('myEditor').destroy();
		}
	</script>

</body>
</html>