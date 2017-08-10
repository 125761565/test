<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=path%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>验证码实例</title>

<script src="<%=path%>/resources/js/jquery-3.2.1.min.js" type="text/javascript"></script>
<style type="text/css">
input.error {
	border: 1px dotted red;
}

label.error {
	background-image: url('../backui/jquery-validation/pic/error.png');
	background-repeat: no-repeat;
	padding-left: 18px;
	color: red;
}

.align-center1 {
	margin: 0 auto; /* 居中 这个是必须的，，其它的属性非必须 */
	width: 400px;
	background-color: #9AC6FF;
	text-align: left; /* 文字等内容居中 */
}
</style>
</head>
<body>
	
	<form action="<%=path %>/index" method="POST">
		
				<div style="height: 50px;">用户名:<input type="text" name="username"  value="zhang"/></div>
			
				<div style="height: 50px;">密码:<input type="password" name="password"  value="123"/></div>
			
			
				<div >
				验证码：<input type="text" style="width: 150px;height: 20px;" class="authod" name="authcode" id="authcode"></input>				
					 <img id="validationCode" alt="验证码图片" title="验证码图片" src="<%=path %>/pcrimg" onclick="refreshCode(this)" />  

    					<a id="aRecode" href="javascript:void(0);" onclick="refreshCode()">看不清，换一张</a>  
				</div>
		
			<div><input type="submit" value="登录"></div>
	</form>
	<script type="text/javascript">
	function refreshCode(imgObj) {  

        if (!imgObj) {  

            imgObj = document.getElementById("validationCode");  

        }  

        var index = imgObj.src.indexOf("?");  

        if(index != -1) {  

            var url = imgObj.src.substring(0,index + 1);  

            imgObj.src = url + Math.random();  

        } else {  

            imgObj.src = imgObj.src + "?" + Math.random();  

        }  

    }  
	</script>
	
</body>
</html>