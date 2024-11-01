<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="servlet.*" import="model.*" import="java.net.URLEncoder"%>
<% 
Page newPage = (Page)session.getAttribute("page");
String encoded = URLEncoder.encode(newPage.getTitle(), "UTF-8");
String titleUrl = "https://ja.wikipedia.org/wiki/" + encoded;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ランダムWikipedia</title>
</head>
<body>

<p><a href = "Main">取得ボタン</a></p>
<p><a href = "<%= titleUrl %>" target="_blank"><%= newPage.getTitle() %></a>(<%=newPage.getTimestamp() %>)</p>
</body>
</html>