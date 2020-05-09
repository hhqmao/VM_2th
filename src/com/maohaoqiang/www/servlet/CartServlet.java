package com.maohaoqiang.www.servlet;

import com.maohaoqiang.www.po.Menu;
import com.maohaoqiang.www.po.MenuItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Cart",value = "/CartServlet")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String menuname=null;
        try {
            menuname=req.getParameter("menuname");
        }catch (Exception e){

        }
        String control=req.getParameter("control");
        HttpSession session=req.getSession();
        Menu menu=null;
        List<Menu> menus=(List<Menu>) session.getAttribute("menus");
        List<Menu> partmenus=(List<Menu>) session.getAttribute("partmenus");
        MenuItem menuItem=null;
        List<MenuItem> menuItemList=null;
        //获取购物车；
        menuItemList=(List<MenuItem>)session.getAttribute("menusItem");
        //获得与mununame对应的数据
        if (menuname!=null){
            for(Menu menu1:menus){
                if(menuname.equals(menu1.getMenuname())){
                    menu=menu1;break;
                }
            }
            if (control.equals("加入购物车")){
                if (menuItemList==null){
                    //判断购物车是否为空，若为空则创建一个购物车
                    menuItemList=new ArrayList<>();
                    menuItem=new MenuItem(menu,1);
                    menuItemList.add(menuItem);
                }else{
                    if(searchequal(menuItemList,menuname)){
                        //判断购物车中是否有mununame对应的菜品
                        for (MenuItem menuItem1:menuItemList){
                            if (menuItem1.getMenu().getMenuname().equals(menuname))
                                menuItem1.setCount(menuItem1.getCount()+1);
                        }
                    }else {
                        menuItem=new MenuItem(menu,1);
                        menuItemList.add(menuItem);
                    }
                }
                session.setAttribute("menusItem",menuItemList);
                menu.setNumber(menu.getNumber()-1);

            }else if (control.equals("删除")){
                int index=0;
                for (MenuItem menuItem1: menuItemList) {
                    //在购物车中找出要删除的菜品信息
                    if(menuItem1.getMenu().getMenuname().equals(menuname)){
                        menu.setNumber(menu.getNumber()+menuItem1.getCount());
                        menuItemList.remove(index);
                        break;
                    }
                    index++;
                }
                session.setAttribute("menusItem",menuItemList);
            }
            if(partmenus!=null){//将partmenus的数据和menus的数据同步
                for (Menu menu2:
                        partmenus) {
                    for (Menu menu1:
                            menus) {
                        if (menu2.getMenuname().equals(menu1.getMenuname())){
                            menu2.setNumber(menu1.getNumber());break;
                        }
                    }
                }
                session.setAttribute("partmenus",partmenus);
            }
        }
        resp.sendRedirect("/VM_Secong_war_exploded/view/ShowMenus.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
    public boolean searchequal(List<MenuItem> menuItemList,String menuname) {
        boolean result=false;
        for (MenuItem menuItem1 : menuItemList) {
            if (menuItem1.getMenu().getMenuname().equals(menuname)) {
                result=true;
            }
        }
        return result;
    }
}
