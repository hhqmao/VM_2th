package com.maohaoqiang.www.servlet;

import com.maohaoqiang.www.po.MenuItem;
import com.maohaoqiang.www.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Oder",value = "/OderServlet")
public class OderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        List<MenuItem> menuItemList =(List<MenuItem>) session.getAttribute("menusItem");
        String uname=(String) session.getAttribute("uname");
        UserService userService=new UserService();
        //将数据传入service，实现点餐功能
        if(userService.cashControl(menuItemList,uname)){
            session.setAttribute("menusItem",null);
            req.setAttribute("success","success");
        }

        req.getRequestDispatcher("/view/UserView.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
