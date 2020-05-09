package com.maohaoqiang.www.servlet;

import com.maohaoqiang.www.po.User;
import com.maohaoqiang.www.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet",value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uno=req.getParameter("uno");
        String pwd=req.getParameter("pwd");
        String name=req.getParameter("name");
        String emil=req.getParameter("emil");
        String phone_num=req.getParameter("phone_num");
        User user=new User(uno,pwd,name,emil,phone_num);
        UserService userService=new UserService();
        if (userService.addUser(user)){
            req.setAttribute("success","success");
        }
        req.getRequestDispatcher("/view/Welcome.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
