<%@ page import="java.util.List" %>
<%@ page import="com.maohaoqiang.www.po.Record" %><%--
  Created by IntelliJ IDEA.
  User: 31190
  Date: 2020/5/8
  Time: 2:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>点餐记录</title>
</head>
<body>
<table border="2px">
    <tr>
        <th>序号</th>
        <th>菜名</th>
    </tr>
    <%//获得存放记录的对象
        List<Record> recordList=(List<Record>) session.getAttribute("record");
        int number=1;
        for (Record record:
             recordList) {
            %>
    <tr>
        <td><%=number%></td>
        <td><%=record.getRecord()%></td>
    </tr>
    <%
        number++;
        }
    %>
</table>
<a href="/VM_Secong_war_exploded/view/PersonalCenterView.jsp">返回</a>
<a href="/VM_Secong_war_exploded/ExitServlet">退出</a>
</body>
</html>
