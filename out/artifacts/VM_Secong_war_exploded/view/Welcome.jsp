<%--
  Created by IntelliJ IDEA.
  User: 31190
  Date: 2020/4/28
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎光临VM外卖系统</title>
    <style>
        body{
            height: 100%;
            width: 100%;
            background: url("/VM_Secong_war_exploded/bbp.jpg") no-repeat;
            background-size: cover;
            position: absolute;
            overflow: hidden;
        }
    </style>
</head>
<body>
<form action="/VM_Secong_war_exploded/CheckServlet" method="post">
    <%
        String success=(String)request.getAttribute("success");
        if(success!=null){
            out.print("注册成功");
        }
    %>
    <br>
    账号：<input type="text" name="uname"/><br>
    密码：<input type="password" name="upwd"/><br>
    <input type="submit" value="登陆"/>
    <a href="/VM_Secong_war_exploded/view/Register.jsp">注册</a>
</form>
</body>
</html>
