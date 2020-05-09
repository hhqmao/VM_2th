package com.maohaoqiang.www.service;


import com.maohaoqiang.www.dao.LoginDao;
import com.maohaoqiang.www.dao.MenuDaoImpl;
import com.maohaoqiang.www.dao.UserDaoImpl;
import com.maohaoqiang.www.po.Menu;
import com.maohaoqiang.www.po.MenuItem;
import com.maohaoqiang.www.po.Record;
import com.maohaoqiang.www.po.User;

import java.util.List;

public class UserService {
    UserDaoImpl userDao=new UserDaoImpl();
    MenuDaoImpl menuDao=new MenuDaoImpl();
    public List<Menu> selectAllMenu(){
        return menuDao.selectAll();
    }
    public int login(User user){
        return LoginDao.login(user);
    }
    public List<Menu> selectMenu(String menuname){
        return menuDao.select(menuname);
    }
    public List<Record> selectRecord(String uno){
        return userDao.selectRecord(uno);
    }
    public int selectMoney(String uno){
       return userDao.selectMoney(uno);
    }
    public boolean cashControl(List<MenuItem> menuItemList, String uno){ ;
        return menuDao.setMenu(menuItemList,uno);
    }
    public boolean addUser(User user){
        return userDao.insertUser(user);
    }
}
