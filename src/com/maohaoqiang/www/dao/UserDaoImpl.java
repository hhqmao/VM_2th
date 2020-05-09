package com.maohaoqiang.www.dao;


import com.maohaoqiang.www.po.Record;
import com.maohaoqiang.www.po.User;
import com.maohaoqiang.www.util.LoginUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    //查用户
    public List<User> selectUser() {
        List<User> users=new ArrayList<>();
        Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        String sql="select real_name,emil,phone_num from user where power=1";
        try {
            conn=LoginUtil.getoCnnetion();
            stat=conn.createStatement();
            rs=stat.executeQuery(sql);
            while (rs.next()){
               String name=rs.getString("real_name");
               String emil=rs.getNString("emil");
               String phone_num=rs.getNString("phone_num");
               User user=new User(name,emil,phone_num);
               users.add(user);
            }return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            LoginUtil.close(rs,stat,conn);
        }
    }

    @Override
    //查厨师
    public List<User> selectChef() {
        List<User> chefs=new ArrayList<>();
        Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        String sql="select user_no,real_name,view '窗口' from user where power=2";
        try {
            conn=LoginUtil.getoCnnetion();
            stat=conn.createStatement();
            rs=stat.executeQuery(sql);
            while (rs.next()){
                String user_no=rs.getNString("user_no");
               String name= rs.getString("real_name");
               int view=rs.getInt("窗口");
                User chef=new User(user_no,name,view);
               chefs.add(chef);
            }return chefs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            LoginUtil.close(rs,stat,conn);
        }
    }

    @Override
    public boolean insertUser(User user) {
        boolean succ=false;
        Connection conn=null;
        PreparedStatement stat=null;
        String sql="insert into user (user_no,user_paw,real_name,power,emil,phone_num) values (?,?,?,1,?,?)";
        try {
            conn=LoginUtil.getoCnnetion();
            stat=conn.prepareStatement(sql);
            stat.setString(1,user.getUser_no());
            stat.setString(2,user.getUser_paw());
            stat.setString(3,user.getReal_name());
            stat.setString(4,user.getEmil());
            stat.setString(5,user.getPhone_num());
            int count=stat.executeUpdate();
            if (count>0)succ=true;
        } catch (Exception e) {
            return succ;
        }finally {
            LoginUtil.close(null,stat,conn);
        }
        return succ;
    }

    @Override
    //增加厨师
    public boolean insertChef(User chef) {
        boolean succ=false;
        Connection conn=null;
        PreparedStatement stat=null;
        String sql="insert into user (user_no,user_paw,real_name,power,view,emil,phone_num) values (?,?,?,2,?,?,?)";
        try {
            conn=LoginUtil.getoCnnetion();
            stat=conn.prepareStatement(sql);
            User user=chef;
            stat.setString(1,user.getUser_no());
            stat.setString(2,user.getUser_paw());
            stat.setString(3,user.getReal_name());
            stat.setInt(4,user.getView());
            stat.setString(5,user.getEmil());
            stat.setString(6,user.getPhone_num());
            int count=stat.executeUpdate();
            if (count>0)succ=true;
        } catch (Exception e) {
            return succ;
        }finally {
            LoginUtil.close(null,stat,conn);
        }
        return succ;
    }

    @Override
    //删除厨师
    public boolean deleteChef(String uno) {
        boolean succ=false;
        Connection conn=null;
        PreparedStatement stat=null;
        String sql="delete from user where user_no=? and power=2";
        try {
            conn=LoginUtil.getoCnnetion();
            stat=conn.prepareStatement(sql);
            stat.setString(1,uno);
            int count=stat.executeUpdate();
            if (count>0)succ=true;
        } catch (Exception e) {
            return succ;
        }finally {
            LoginUtil.close(null,stat,conn);
        }
        return succ;
    }

    @Override
    //查余额
    public int selectMoney(String uno) {
        Connection conn=null;
        PreparedStatement stat=null;
        ResultSet rs=null;
        int money=0;
        try {
            String sql="select mony from user where user_no = ?";
            conn= LoginUtil.getoCnnetion();
            stat=conn.prepareStatement(sql);
            stat.setString(1,uno);
            rs=stat.executeQuery();
            if (rs.next()){
                money= rs.getInt("mony");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            LoginUtil.close(rs,stat,conn);
        }
        return money;
    }

    @Override
    //查记录
    public List<Record> selectRecord(String uno) {
        List<Record> recordList=new ArrayList<>();
        Connection conn=null;
        PreparedStatement stat=null;
        ResultSet rs=null;
        try {
            conn= LoginUtil.getoCnnetion();
            String sql="select record from record where user_no= ?";
            stat=conn.prepareStatement(sql);
            stat.setString(1,uno);
            rs=stat.executeQuery();
            while (rs.next()){
               Record record=new Record(rs.getString("record"));
               recordList.add(record);
            }return recordList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            LoginUtil.close(rs,stat,conn);
        }
    }
}