<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的搜索</title>
<link href="css.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="wrap1">
		<div id="wrap">
			<div id="topdiv">
				<a class="logo" href="#"></a>
				<div id="nav"></div>
				<br />
				<div id="init">
					<form action="/search/InitServlet" method="get">
						<!-- 请输入文档所在的路径:<input type="text" name="dirPath" size="30"
							class="text" value="" />  -->
						<input type="submit" value="初始化" class="srchbutton" /><span
							style="color: red;">${message }</span>
					</form>
					<br /> <span
						style="color: red; font-size: 15px; text-align: left;">先将文章的相关要素导入到数据库中（直接导入数据库），点击初始化，系统将会对搜索引擎初始化,初始化比较慢，请稍等
					</span><br />

				</div>
			</div>
			<div id="left">
				<p></p>
				<p></p>
				<p></p>
				<p></p>
			</div>
			<div id="footer">
				<p>
					Copyright: <a href="#">Search</a>
				</p>
			</div>
			<div id="footer2">
				Design by <a href="#">My Search</a>
			</div>
		</div>
	</div>
</body>
</html>
