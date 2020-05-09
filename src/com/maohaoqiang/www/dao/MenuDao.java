package com.maohaoqiang.www.dao;

import com.maohaoqiang.www.po.Menu;
import com.maohaoqiang.www.po.MenuItem;
import com.maohaoqiang.www.po.User;

import java.util.List;
import java.util.Map;

public interface MenuDao {
    public boolean insert(Menu menu,String uno);//添加菜品
    public boolean updateMenu(Menu menu,String localname,String uno);//更新菜品信息
    public boolean delete(String localname,String uno);//删除菜品*/
    public List<Menu> select(String menu);//查询菜品
    public List<Menu> selectAll();
    public List<Menu> selectChefMenu(String uno);
    public boolean setMenu(List<MenuItem> menuItemList, String uno);//下单
}
