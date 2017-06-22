<%@page import="search.pojo.DocObject"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<div id="nav">
					<ul>
						<li><a href="http://www.cnki.net/">主页</a></li>
						<li><a href="http://www.cnki.net/">搜索</a></li>
						<li><a href="http://www.cnki.net/">其他链接</a></li>
						<li><a href="http://www.cnki.net/">关于</a></li>
					</ul>
				</div>
				<div id="search">
					<form action="/search/GetKeyWordServlet" method="get">
						<input type="text" name="q" size="30" class="text" value="${q}" />
						<input type="submit" value="搜索" class="srchbutton" />
					</form>
					<div id="stats">
						<p></p>
					</div>
				</div>
			</div>
			<%
				//将关键字标红
				List<DocObject> list = (List<DocObject>) request.getAttribute("docList");
				if (list != null && list.size() != 0) {
					List<String> keys = (List<String>) request.getAttribute("keys");
					for (DocObject docObj : list) {
						String abs = docObj.getDocAbstract();
						String newAbs = abs;
						for (String key : keys) {
							newAbs = newAbs.replaceAll(key, "<span style='color: red;'>" + key + "</span>");
						}
						docObj.setDocAbstract(newAbs);
					}
				}
			%>
			<div id="left">
				<c:choose>
					<c:when test="${empty docList}">
						<span style="color: red; font-size: 15px; text-align: left;">无记录！</span>
					</c:when>
					<c:otherwise>
						<ul>
							<c:forEach items="${docList }" var="doc">
								<li>
									<h3>${doc.name }</h3>
									<h5>作者：${doc.authors }</h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<%-- <h5>关键字：${doc.keywords }</h5> --%> <br />
									<p>摘要：${doc.docAbstract }</p>
								</li>
								<%-- <tr>
									<td>${doc.id }</td>
									<td>${doc.name }</td>
									<td>${doc.dir }</td>
									<td>${doc.keywords }</td>
								</tr> --%>
							</c:forEach>
						</ul>
					</c:otherwise>
				</c:choose>
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
