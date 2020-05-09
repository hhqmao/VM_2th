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

@WebServlet(name = "SearchMenu",value = "/SearchMenuServlet")
public class SearchMenuServlet extends HttpServlet {
    @Override
    //查询菜品
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String menuname=req.getParameter("menuname");
        UserService userService=new UserService();
        List<Menu> partmenus=userService.selectMenu(menuname);
        HttpSession session=req.getSession();
        List<Menu> menus=(List<Menu>) session.getAttribute("menus");
        //将刚刚充数据库中得到的查询结果中的数据和session中的同步
        if (menus!=null){
            for (Menu menu:
                    partmenus) {
                for (Menu menu1:
                        menus) {
                    if (menu.getMenuname().equals(menu1.getMenuname())){
                        menu.setNumber(menu1.getNumber());break;
                    }
                }
            }
            session.setAttribute("partmenus",partmenus);
        }
        resp.sendRedirect("/VM_Secong_war_exploded/view/ShowMenus.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
