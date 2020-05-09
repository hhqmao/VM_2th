package com.maohaoqiang.www.servlet;

import com.maohaoqiang.www.dao.LoginDao;
import com.maohaoqiang.www.po.Menu;
import com.maohaoqiang.www.po.User;
import com.maohaoqiang.www.service.ChefService;
import com.maohaoqiang.www.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "checkServlet" ,value = "/CheckServlet")
public class CheckServlet extends HttpServlet {
    @Override
    //实现登陆
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        int power=0;
        String name=req.getParameter("uname");
        String pwd=req.getParameter("upwd");
        User user=new User(name,pwd);
        UserService userService=new UserService();
        power=userService.login(user);
        HttpSession session=req.getSession();
        if(power!=0){
            //登陆成功，将必要信息放入session对象中
            session.setAttribute("uname",name);
            session.setAttribute("power",power);
        }
        switch (power){
            case 0: resp.sendRedirect("/VM_Secong_war_exploded/view/Welcome.jsp");break;
            case 1:
                List<Menu> menus=userService.selectAllMenu();
                //获得所有菜品的信息
                session.setAttribute("menus",menus);
                session.setAttribute("partmenus",null);
                resp.sendRedirect("/VM_Secong_war_exploded/view/UserView.jsp");
                break;

            case 2:
                ChefService chefService=new ChefService();
                List<Menu> chefmenus=chefService.selectChefMenus(name);
                session.setAttribute("chefmenus",chefmenus);
                resp.sendRedirect("/VM_Secong_war_exploded/view/ChefView.jsp");
                break;
            case 3: resp.sendRedirect("/VM_Secong_war_exploded/view/MangerView.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
