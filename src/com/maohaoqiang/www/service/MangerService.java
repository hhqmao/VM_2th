package com.maohaoqiang.www.service;

import com.maohaoqiang.www.dao.UserDaoImpl;
import com.maohaoqiang.www.po.User;

import java.util.List;

public class MangerService {
    UserDaoImpl userDao=new UserDaoImpl();
    //获得用户信息
    public List<User> getUser(){
        return userDao.selectUser();
    }
    //获得厨师信息
    public List<User> getChef(){
        return userDao.selectChef();
    }
    //删除厨师
    public boolean deleteChef(String uno){
        return userDao.deleteChef(uno);
    }
    //增加厨师
    public boolean addChef(User chef){
        return userDao.insertChef(chef);
    }
    //chefs同步数据
    public List<User> deleteChefs(List<User> chefs,String name){
        for (User chef:
             chefs) {
            if (name.equals(chef.getReal_name())){
                chefs.remove(chef);
                break;
            }
        }
        return chefs;
    }
    //将chef加入数组
    public List<User> addChefs(List<User> chefs,User chef){
        chefs.add(chef);
        return chefs;
    }

    public User selectUser(List<User> chefs,String name){
        User user=null;
        for (User chef:
                chefs) {
            if (name.equals(chef.getReal_name())){
                user= chef;
                break;
            }
        }
        return user;
    }
}
