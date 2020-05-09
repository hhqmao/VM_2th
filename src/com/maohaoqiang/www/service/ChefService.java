package com.maohaoqiang.www.service;

import com.maohaoqiang.www.dao.MenuDao;
import com.maohaoqiang.www.dao.MenuDaoImpl;
import com.maohaoqiang.www.po.Menu;

import java.util.List;

public class ChefService {
    MenuDaoImpl menuDao=new MenuDaoImpl();
    public List<Menu> selectChefMenus(String uno){
        return menuDao.selectChefMenu(uno);
    }
    public boolean updateMenu(Menu menu,String localname,String uno){
        return menuDao.updateMenu(menu,localname,uno);
    }
    public boolean deleteMenu(String localname,String uno){
        return menuDao.delete(localname,uno);
    }
    public boolean insertMenu(Menu menu,String uno){
        return menuDao.insert(menu,uno);
    }
}
