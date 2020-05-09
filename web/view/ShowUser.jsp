<%@ page import="java.util.List" %>
<%@ page import="com.maohaoqiang.www.po.User" %><%--
  Created by IntelliJ IDEA.
  User: 31190
  Date: 2020/5/9
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form>
    <table border="2px">
        <tr>
            <th>姓名</th>
            <th>邮箱</th>
            <th>电话</th>
        </tr>
        <%
            //获得存放学生信息的对象
            List<User> users=(List<User>) request.getAttribute("users");
            for (User user:
                 users) {
                %>
        <tr>
            <td><%=user.getReal_name()%></td>
            <td><%=user.getEmil()%></td>
            <td><%=user.getPhone_num()%></td>
        </tr>
        <%
            }
        %>
    </table>
    <a href="/VM_Secong_war_exploded/view/MangerView.jsp">返回</a>
    <a href="/VM_Secong_war_exploded/ExitServlet">退出</a>
</form>
</body>
</html>
