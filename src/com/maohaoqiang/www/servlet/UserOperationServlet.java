package com.maohaoqiang.www.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UesrOperation",value = "/UserOperationServlet")
public class UserOperationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //通过用户界面而非通过搜索界面进入菜单，partmenus为null
        HttpSession session=req.getSession();
        session.setAttribute("partmenus",null);
        resp.sendRedirect("/VM_Secong_war_exploded/view/ShowMenus.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
