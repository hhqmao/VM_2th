package com.maohaoqiang.www.dao;

import com.maohaoqiang.www.po.User;
import com.maohaoqiang.www.util.LoginUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {
    public static int login(User user) {
        int loginSuccess=0;
        String loginname=user.getUser_no();
        String loginpaw=user.getUser_paw();
        Connection conn=null;
        PreparedStatement stat=null;
        ResultSet rs=null;
        try {
            conn= LoginUtil.getoCnnetion();
            String sql="select * from user where user_no=? and user_paw=? ";
            stat=conn.prepareStatement(sql);
            stat.setString(1,loginname);
            stat.setString(2,loginpaw);
            rs=stat.executeQuery();
            if(rs.next()){
                if(rs.getInt("power")==1)loginSuccess=1;
                if(rs.getInt("power")==2)loginSuccess=2;
                if(rs.getInt("power")==3)loginSuccess=3;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }finally {
            LoginUtil.close(rs,stat,conn);
        }
        return loginSuccess;
    }
}