<%--
  Created by IntelliJ IDEA.
  User: 31190
  Date: 2020/5/9
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/VM_Secong_war_exploded/AddChefServlet">
    <table border="px">
        <tr>
            <th>账号</th>
            <th>密码</th>
            <th>姓名</th>
            <th>窗口</th>
            <th>邮箱</th>
            <th>电话号码</th>
        </tr>
        <tr>
            <td><input type="number" name="uno" min="1000000000" max="9999999999"></td>
            <td><input type="text" name="pwd"></td>
            <td><input type="text" name="name"></td>
            <td><input type="number" name="view" min="1" max="2"></td>
            <td><input type="text" name="emil"></td>
            <td><input type="number" name="phone_num" min="100" max="100000000000"></td>
            <td><input type="submit" value="增加"></td>
        </tr>
    </table>
    <a href="/VM_Secong_war_exploded/view/MangeChefView.jsp">返回</a>
    <a href="/VM_Secong_war_exploded/ExitServlet">退出</a>
</form>
</body>
</html>
