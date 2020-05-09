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

@WebServlet(name = "MangeChefServlet",value = "/MangeChefServlet")
public class MangeChefServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String control=req.getParameter("control");
        if (control.equals("删除")){
            String name=req.getParameter("name");
            HttpSession session=req.getSession();
            List<User> chefs=(List<User>)session.getAttribute("chefs");
            MangerService mangerService=new MangerService();
            User chef=mangerService.selectUser(chefs,name);
            if (mangerService.deleteChef(chef.getUser_no())){
                chefs=mangerService.deleteChefs(chefs,name);
            }
            session.setAttribute("chefs",chefs);
            resp.sendRedirect("/VM_Secong_war_exploded/view/MangeChefView.jsp");
        }
        if (control.equals("增加")){
            resp.sendRedirect("/VM_Secong_war_exploded/view/AddChef.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
