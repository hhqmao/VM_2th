package com.maohaoqiang.www.servlet;

import com.maohaoqiang.www.po.User;
import com.maohaoqiang.www.service.MangerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddChefServlet",value = "/AddChefServlet")
public class AddChefServlet extends HttpServlet {
    @Override
    //增加厨师
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uno=req.getParameter("uno");
        String pwd=req.getParameter("pwd");
        String name=req.getParameter("name");
        int view=Integer.parseInt(req.getParameter("view"));
        String emil=req.getParameter("emil");
        String phone_num=req.getParameter("phone_num");
        MangerService mangerService=new MangerService();
        User chef=new User(uno,pwd,name,view,emil,phone_num);
        HttpSession session=req.getSession();
        List<User> chefs=(List<User>) session.getAttribute("chefs");
        if (mangerService.addChef(chef)){
            chefs=mangerService.addChefs(chefs,chef);
        }
        session.setAttribute("chefs",chefs);
        resp.sendRedirect("/VM_Secong_war_exploded/view/MangeChefView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
