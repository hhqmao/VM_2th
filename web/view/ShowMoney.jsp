<%--
  Created by IntelliJ IDEA.
  User: 31190
  Date: 2020/5/8
  Time: 2:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>余额</title>
</head>
<body>
<table border="2px">
    <%
        Integer money=(Integer) request.getAttribute("money");
        Integer control=(Integer)session.getAttribute("power");
        int a=1;
        String adss=null;
        //从权限来区分路径
        if (control.equals(1)){
            adss="/VM_Secong_war_exploded/view/PersonalCenterView.jsp";
        }else {
            adss="/VM_Secong_war_exploded/view/MangerView.jsp";
        }
    %>
    <tr>
        <td>余额</td>
        <td><%=money%></td>
    </tr>
</table>
<a href="<%=adss%>">返回</a>
<a href="/VM_Secong_war_exploded/ExitServlet">退出</a>

</body>
</html>
