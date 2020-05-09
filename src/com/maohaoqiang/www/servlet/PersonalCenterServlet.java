package com.maohaoqiang.www.servlet;

import com.maohaoqiang.www.po.Record;
import com.maohaoqiang.www.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PersonalCenter",value = "/PersonalCenterServlet")
public class PersonalCenterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService=new UserService();
        String value=req.getParameter("value");
        HttpSession session=req.getSession();
        String uno=(String) session.getAttribute("uname");
        if(value.equals("查询余额")){
            int money= userService.selectMoney(uno);
            req.setAttribute("money",money);
            req.getRequestDispatcher("/view/ShowMoney.jsp").forward(req,resp);
        }else if(value.equals("查询历史记录")){
            List<Record> recordList=userService.selectRecord(uno);
            session.setAttribute("record",recordList);
            resp.sendRedirect("view/ShowRecord.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
