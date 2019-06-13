<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%
// セッションスコープからユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
User loginUserName = (User) session.getAttribute("loginUserName");
%>
<?$changeup = $_POST['changeup'] + 1;?>
<?$changedown = $_POST['changedown'] + 1;?>
<%--aaaaaaaaa --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" type="text/css" href="Stayle.css">

<title>パスワード再設定</title>
</head>
<body>
<h1>座席管理システム</h1>
<form action="/Bteam/SettingUesr" method="post">
	新しいパスワード:<input type="password" name="pass"><br>
	<input type="submit" value="再設定">
</form>

<div>
    <div id="nav" class="nav">
         <div class="icon">
         	<ul>
                 <li><a href="/Bteam/Menu"><i class="fa fa-home "></i></a></li>
                 <li><a href="/Bteam/SettingUser"><i class="fa fa-cog "></i></a></li>
             </ul>
         </div>
             <div class="text">
             </div>
         <div class="info">
             <div class="logo"><img src="Test_icon.png" alt="写真" width="100px" height="100px"></div><div class="name"><c:out value="${loginUserName.emp_name}" /></div>
             <div class="quote"></div>
             <div class="social">
             </div>

                 <a href="index.jsp" onclick="return confirm('ログアウトします')" value="Logout">Logout</a>

           </div>
       </div>
</div>
</body>
</html>