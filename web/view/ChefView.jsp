<%@ page import="java.util.List" %>
<%@ page import="com.maohaoqiang.www.po.Menu" %><%--
  Created by IntelliJ IDEA.
  User: 31190
  Date: 2020/5/3
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>厨师您好</title>
</head>
<body>
<form action="/VM_Secong_war_exploded/ChefOperationServlet" >
    <table border="2px">
        <tr>
            <th>选择</th>
            <th>菜名</th>
            <th>菜系</th>
            <th>价格</th>
            <th>数量</th>
        </tr>
        <%
            List<Menu> chefmenus=(List<Menu>) session.getAttribute("chefmenus");
            for (Menu menu:
                 chefmenus) {
                %>
        <tr>
            <td><input type="checkbox" name="localname" value="<%=menu.getMenuname()%>"></td>
            <td> <input type="text" name="<%=menu.getMenuname()+"new"%>" value="<%=menu.getMenuname()%>"></td>
            <td><input type="text" name="<%=menu.getMenuname()+"from"%>" value="<%=menu.getFrom()%>"></td>
            <td><input type="number" name="<%=menu.getMenuname()+"cash"%>"
                       value="<%=menu.getCash()%>" max="10000" min="0"></td>
            <td><input type="number" name="<%=menu.getMenuname()+"number"%>"
                       value="<%=menu.getNumber()%>" max="10000" min="0"></td>
            <td><input type="submit" name="control" value="更新">
                <input type="submit" name="control" value="删除">
            </td>
        </tr>
        <%
            }
        %>
        <tr>
            <td> </td>
            <td><input type="text" name="addname"></td>
            <td><input type="text" name="addfrom"></td>
            <td><input type="number" name="addcash" max="10000" min="0"></td>
            <td><input type="number" name="addnumber" max="10000" min="0"></td>
            <td><input type="submit" name="control" value=新增></td>
        </tr>
    </table>
</form>
<table></table>
<a href="/VM_Secong_war_exploded/ExitServlet">退出</a>
</body>
</html>
