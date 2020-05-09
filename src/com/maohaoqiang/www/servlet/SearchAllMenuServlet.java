package com.maohaoqiang.www.servlet;

import com.maohaoqiang.www.po.Menu;
import com.maohaoqiang.www.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchAllMenu",value = "/SearchAllMenuServlet")
public class SearchAllMenuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService=new UserService();
        List<Menu> menus=userService.selectAllMenu();
        HttpSession session=req.getSession();
        session.setAttribute("menus",menus);
        req.getRequestDispatcher("/view/ShowMenus.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
