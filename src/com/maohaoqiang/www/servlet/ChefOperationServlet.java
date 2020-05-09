package com.maohaoqiang.www.servlet;

import com.maohaoqiang.www.po.Menu;
import com.maohaoqiang.www.service.ChefService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ChefOperation",value = "/ChefOperationServlet")
public class ChefOperationServlet extends HttpServlet {
    @Override
    //厨师的操作
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String control=req.getParameter("control");
        ChefService chefService=new ChefService();
        HttpSession session=req.getSession();
        List<Menu> chefmenus=(List<Menu>) session.getAttribute("chefmenus");
        String uno=(String)session.getAttribute("uname");
        String localname=req.getParameter("localname");
        switch (control){
            case "更新":
                String changename=req.getParameter(localname+"new");
                String from=req.getParameter(localname+"from");
                int cash=Integer.parseInt(req.getParameter(localname+"cash"));
                int number=Integer.parseInt(req.getParameter(localname+"number"));
                Menu newmenu=new Menu(changename,from,cash,number);
                if (chefService.updateMenu(newmenu,localname,uno)){
                    //更新session中的数据
                    for (Menu menu:
                         chefmenus) {
                        if (menu.getMenuname().equals(localname)){
                            chefmenus.remove(menu);
                            chefmenus.add(newmenu);
                            break;
                        }
                    }
                }
                break;
            case  "删除":
                if(chefService.deleteMenu(localname,uno)){
                    for (Menu menu:
                         chefmenus) {
                        if (menu.getMenuname().equals(localname)){
                            chefmenus.remove(menu);
                            break;
                        }
                    }
                }
                break;
            case "新增":
                String menuname =req.getParameter("addname");
                String addfrom =req.getParameter("addfrom");
                int addcash=Integer.parseInt(req.getParameter("addcash"));
                int addnumber=Integer.parseInt(req.getParameter("addnumber"));
                Menu addmenu=new Menu(menuname,addfrom,addcash,addnumber);
                if (chefService.insertMenu(addmenu,uno)){
                    chefmenus.add(addmenu);
                }
        }
        session.setAttribute("chefmenus",chefmenus);
        resp.sendRedirect("/VM_Secong_war_exploded/view/ChefView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
