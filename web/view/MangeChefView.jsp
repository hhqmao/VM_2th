<%@ page import="java.util.List" %>
<%@ page import="com.maohaoqiang.www.po.User" %><%--
  Created by IntelliJ IDEA.
  User: 31190
  Date: 2020/5/9
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理厨师</title>
</head>
<body>
<form action="/VM_Secong_war_exploded/MangeChefServlet">
<table border="2px">
    <tr>
        <th>姓名</th>
        <th>窗口</th>
    </tr>
    <%
        List<User> chefs=(List<User>)session.getAttribute("chefs");
        for (User chef:
             chefs) {
            %>
    <tr>
        <td><input type="checkbox" name="name" value="<%=chef.getReal_name()%>"><%=chef.getReal_name()%></td>
        <td><%=chef.getView()%></td>
        <td><input type="submit" name="control" value="删除"></td>
    </tr>
    <%
        }
    %>
</table>
    <a href="/VM_Secong_war_exploded/MangeChefServlet?control=增加">增加</a>
    <a href="/VM_Secong_war_exploded/view/MangerView.jsp">返回</a>
    <a href="/VM_Secong_war_exploded/ExitServlet">退出</a>
</form>
</body>
</html>
