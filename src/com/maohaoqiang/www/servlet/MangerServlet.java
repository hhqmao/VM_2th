package com.maohaoqiang.www.servlet;

import com.maohaoqiang.www.po.User;
import com.maohaoqiang.www.service.MangerService;
import com.maohaoqiang.www.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MangerServlet",value = "/MangerServlet")
public class MangerServlet extends HttpServlet {
    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String control=req.getParameter("control");
        HttpSession session=req.getSession();
        String uno=(String) session.getAttribute("uname");
        MangerService mangerService=new MangerService();
        UserService userService=new UserService();
        //使不同选择走向不同方向
        switch (control){
            case "查看":
                List<User> users=mangerService.getUser();
                req.setAttribute("users",users);
                req.getRequestDispatcher("/view/ShowUser.jsp").forward(req,resp);
                break;
            case "管理":
                List<User> chefs=mangerService.getChef();
                session.setAttribute("chefs",chefs);
                resp.sendRedirect("/VM_Secong_war_exploded/view/MangeChefView.jsp");
                break;
            case "收入":
                int money=userService.selectMoney(uno);
                req.setAttribute("money",money);
                req.getRequestDispatcher("/view/ShowMoney.jsp").forward(req,resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
