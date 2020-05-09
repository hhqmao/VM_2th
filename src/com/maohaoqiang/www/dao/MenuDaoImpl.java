package com.maohaoqiang.www.dao;

import com.maohaoqiang.www.po.Menu;
import com.maohaoqiang.www.po.MenuItem;
import com.maohaoqiang.www.po.User;
import com.maohaoqiang.www.util.LoginUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MenuDaoImpl implements MenuDao {
    @Override
    //更新菜品信息
    public boolean updateMenu(Menu menu, String localname,String uno) {
        String sql ="update menu set menu=?,from1=?,cash=?,number=? where menu=?" +
                "and view=(select view from user where user_no=?)";
        Connection conn = null;
        int count = 0;
        PreparedStatement stat = null;
        boolean result=false;
        try {
            conn=LoginUtil.getoCnnetion();
            stat=conn.prepareStatement(sql);
            stat.setString(1,menu.getMenuname());
            stat.setString(2,menu.getFrom());
            stat.setInt(3,menu.getCash());
            stat.setInt(4,menu.getNumber());
            stat.setString(5,localname);
            stat.setString(6,uno);
            count=stat.executeUpdate();
            if(count>0){
                result=true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }finally {
            LoginUtil.close(null, stat, conn);
        }
        return result;
    }

    @Override
    //添加菜品
    public boolean insert(Menu menu,String uno) {
        String sql = "insert into menu (view,menu,from1,cash,number) values ((select view from user where user_no=?),?,?,?,?)";
        Connection conn = null;
        int count = 0;
        PreparedStatement stat = null;
        boolean result = true;
        try {
            conn = LoginUtil.getoCnnetion();
            stat = conn.prepareStatement(sql);
            stat.setString(1, uno);
            stat.setString(2, menu.getMenuname());
            stat.setString(3, menu.getFrom());
            stat.setInt(4, menu.getCash());
            stat.setInt(5, menu.getNumber());
            count = stat.executeUpdate();
            if (count > 0) result=true;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        } finally {
            LoginUtil.close(null, stat, conn);
        }
        return result;
    }

    @Override
    //删除菜品
    public boolean delete(String localname,String uno) {
        String sql = "delete from menu where view=(select view from user where user_no=?) and menu=?";
        boolean result = false;
        Connection conn = null;
        PreparedStatement stat = null;
        int count=0;
        try {
            conn = LoginUtil.getoCnnetion();
            stat = conn.prepareStatement(sql);
            stat.setString(1, uno);
            stat.setString(2, localname);
            count=stat.executeUpdate();
            if (count>0){
                result=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        } finally {
            LoginUtil.close(null, stat, conn);
        }
        return result;
    }

    @Override
    //模糊查寻菜品
    public List<Menu> select(String menu) {
        String sql = "select * from (select menu.menu '菜名',menu.from1 '菜系',view.view_name '店名',menu.number '库存' " +
                ",menu.cash '价格' from menu join view on menu.view=view.id) a where 菜名 like ?";
        List<Menu> menus=new ArrayList<>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            //实现模糊查询
            conn = LoginUtil.getoCnnetion();
            stat = conn.prepareStatement(sql);
            stat.setString(1, '%' + menu + '%');
            rs = stat.executeQuery();
            while (rs.next()){
                String menuname=rs.getNString("菜名");
                String from=rs.getNString("菜系");
                String view_name=rs.getNString("店名");
                int number=rs.getInt("库存");
                int cash=rs.getInt("价格");
                Menu menu1=new Menu(menuname,from,cash,number,view_name);
                menus.add(menu1);
            }return menus;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            LoginUtil.close(rs, stat, conn);
        }
    }

    //查看所有菜品
    public List<Menu> selectAll() {
        List<Menu> menus=new ArrayList<>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            //注册驱动，获取连接
            conn = LoginUtil.getoCnnetion();
            String sql = " select menu.menu '菜名',menu.from1 '菜系',view.view_name '店名',menu.number '库存' ,menu.cash '价格'" +
                    "from menu join view on menu.view=view.id";
            //预处理sql语句
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();
            //若sql语句执行成功，就遍历菜品反之则返回初始界面
            while (rs.next()){
                String menu=rs.getNString("菜名");
                String from=rs.getNString("菜系");
                String view_name=rs.getNString("店名");
                int number=rs.getInt("库存");
                int cash=rs.getInt("价格");
                Menu menu1=new Menu(menu,from,cash,number,view_name);
                menus.add(menu1);
            }return menus;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            LoginUtil.close(rs, stat, conn);
        }
    }



    @Override
    //查看厨师窗口的菜品
    public List<Menu> selectChefMenu(String uno) {
       List<Menu> menus=new ArrayList<>();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String sql = "select menu,from1,cash,number from menu where view =(select view from user where user_no=?)";
        try {
            conn = LoginUtil.getoCnnetion();
            stat = conn.prepareStatement(sql);
            stat.setString(1, uno);
            rs = stat.executeQuery();
            while (rs.next()) {
                String menuname=rs.getNString("menu");
                String from=rs.getNString("from1");
                int cash=rs.getInt("cash");
                int number=rs.getInt("number");
                Menu menu=new Menu(menuname,from,cash,number);
                menus.add(menu);
            }return menus;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            LoginUtil.close(rs, stat, conn);
        }
    }

    @Override
    public boolean setMenu(List<MenuItem> menuItemList, String uno) {
        boolean result=false;
        Connection conn=null;
        PreparedStatement stat=null;
        int count=0;
        try {
            conn=LoginUtil.getoCnnetion();
            conn.setAutoCommit(false);//关闭自动提交
            for(MenuItem menuItem:menuItemList){
                //库存操作
                String sql ="update menu set number=number-1 where menu=?";
                stat=conn.prepareStatement(sql);
                stat.setString(1,menuItem.getMenu().getMenuname());
                count+=stat.executeUpdate();
                //资金操作
                sql="update user set mony=mony-(select cash from menu where menu=?) where user_no=?";
                stat=conn.prepareStatement(sql);
                stat.setString(1,menuItem.getMenu().getMenuname());
                stat.setString(2,uno);
                count+=stat.executeUpdate();
                //资金加到经理账号上
                sql="update user set mony=mony+(select cash from menu where menu=?) where power=3";
                stat=conn.prepareStatement(sql);
                stat.setString(1,menuItem.getMenu().getMenuname());
                count+=stat.executeUpdate();
                //记录消费记录
                sql="insert into record (user_no,record) values (?,?)";
                stat=conn.prepareStatement(sql);
                stat.setString(1,uno);
                stat.setString(2,menuItem.getMenu().getMenuname());
                count+=stat.executeUpdate();
        }
            if(count==(menuItemList.size()*4)){
                //若以上都实现，就提交事务
                conn.commit();
                result=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            LoginUtil.close(null,stat,conn);
        }
        return result;
    }
}