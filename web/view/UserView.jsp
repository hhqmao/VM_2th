<%--
  Created by IntelliJ IDEA.
  User: 31190
  Date: 2020/5/3
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎使用WE外卖系统</title>
</head>
<body>
<form action="/VM_Secong_war_exploded/SearchMenuServlet">
    <%
        //判断下单是否成功并显示出来
        String success=(String)request.getAttribute("success");
        if(success!=null){
            out.print("下单成功");
        }
    %>
    <br>
    <input type="text" name="menuname">
    <input type="submit" value="搜索菜品"><br>
    <a href="/VM_Secong_war_exploded/UserOperationServlet?">菜单</a><br>
    <a href="/VM_Secong_war_exploded/view/PersonalCenterView.jsp">个人中心</a><br>
    <a href="/VM_Secong_war_exploded/ExitServlet">退出</a>
</form>

</body>
</html>
