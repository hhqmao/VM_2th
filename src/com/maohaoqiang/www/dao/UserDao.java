package com.maohaoqiang.www.dao;

import com.maohaoqiang.www.po.Record;
import com.maohaoqiang.www.po.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    public List<User> selectUser();//查看用户
    public List<User> selectChef();//查看厨师
    public boolean insertUser(User user);
    public boolean insertChef(User chef);
    public boolean deleteChef(String name);
    public int selectMoney(String uno);
    public List<Record> selectRecord(String uno);
}