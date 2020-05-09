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
    //缓存全部菜品的信息
    public List<Menu> selectAllMenu(){
        return menuDao.selectAll();
    }
    //实现登陆
    public int login(User user){
        return LoginDao.login(user);
    }
    //模糊查询菜品
    public List<Menu> selectMenu(String menuname){
        return menuDao.select(menuname);
    }
    //获取消费记录
    public List<Record> selectRecord(String uno){
        return userDao.selectRecord(uno);
    }
    //获得账号中的钱的数目
    public int selectMoney(String uno){
       return userDao.selectMoney(uno);
    }
    public boolean cashControl(List<MenuItem> menuItemList, String uno){ ;
        return menuDao.setMenu(menuItemList,uno);
    }
    //增加用户
    public boolean addUser(User user){
        return userDao.insertUser(user);
    }
}
