package com.maohaoqiang.www.service;

import com.maohaoqiang.www.dao.MenuDaoImpl;
import com.maohaoqiang.www.po.Menu;

import java.util.List;

public class ChefService {
    MenuDaoImpl menuDao=new MenuDaoImpl();
    //查询用户对应窗口的信息
    public List<Menu> selectChefMenus(String uno){
        return menuDao.selectChefMenu(uno);
    }
    //更新菜品
    public boolean updateMenu(Menu menu,String localname,String uno){
        return menuDao.updateMenu(menu,localname,uno);
    }
    //删除菜品
    public boolean deleteMenu(String localname,String uno){
        return menuDao.delete(localname,uno);
    }
    //增加菜品
    public boolean insertMenu(Menu menu,String uno){
        return menuDao.insert(menu,uno);
    }
}
