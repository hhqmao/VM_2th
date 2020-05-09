<%@ page import="com.maohaoqiang.www.po.Menu" %>
<%@ page import="java.util.List" %>
<%@ page import="com.maohaoqiang.www.po.MenuItem" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 31190
  Date: 2020/5/3
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>菜品信息</title>
</head>
<body>
<form action="/VM_Secong_war_exploded/CartServlet">
        <%
        String error=(String)request.getAttribute("error");
        if (error!=null){
            out.print("请勾选菜名");
        }
    %>
    <br>
    <table border="2px">
        <tr>
            <th>菜名</th>
            <th>菜系</th>
            <th>餐厅</th>
            <th>价格</th>
            <th>库存</th>
        </tr>
        <%
            List<Menu> menus=(List<Menu>) session.getAttribute("partmenus");
            if (menus==null){
                 menus=(List<Menu>) session.getAttribute("menus");
            }
            if (menus!=null){
            for (Menu menu:menus){
        %>
        <tr>
            <td><input type="checkbox" name="menuname" value="<%=menu.getMenuname()%>"><%=menu.getMenuname()%></td>
            <td><%=menu.getFrom()%></td>
            <td><%=menu.getView()%></td>
            <td><%=menu.getCash()%></td>
            <td><%=menu.getNumber()%></td>
            <%
                if(menu.getNumber()==0){
                    %>
            <td>暂时无库存</td>
            <%
                }else {
                    %>
            <td><input type="submit" name="control" value="加入购物车"></td>
            <%
                }
            %>
            <%
                }
                }
            %>
        </tr>
    </table>
    <br>
    <a href="/VM_Secong_war_exploded/OderServlet">下单</a>
    <a href="/VM_Secong_war_exploded/view/UserView.jsp">返回</a><br>
    -----------------购物车------------------

        <%!
        long allcash=0;
        %>
    <table border="2px">
        <tr>
            <th>菜名</th>
            <th>价格</th>
            <th>数量</th>
            <th>金额</th>
        </tr>
        <%
            allcash=0;
            List<MenuItem> menuItemList=(List<MenuItem>) session.getAttribute("menusItem");
            if (menuItemList!=null){
                for (MenuItem menuItem:menuItemList) {
                    Menu menu=menuItem.getMenu();
                    int count=menuItem.getCount();
                    allcash+=(count*menu.getCash());
        %>
        <tr>
            <td><input type="checkbox" name="menuname" value="<%=menu.getMenuname()%>"><%=menu.getMenuname()%></td>
            <td><%=menu.getCash()%></td>
            <td><%=count%></td>
            <td><%=count*menu.getCash()%></td>
            <td><input type="submit" name="control" value="删除"></td>
            <%
                    }
                }
            %>

        </tr>
    </table>
    总金额：<%=allcash%>
</body>
</html>
