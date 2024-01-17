package com.kitri.myservletboard.login;

import com.kitri.myservletboard.dao.MemberJdbcDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/login/Login")
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("userId");
        String pw = req.getParameter("userPassword");

        RequestDispatcher dispatcher;

        boolean isLoginFailed = false;

        if (id.isEmpty() || pw.isEmpty()) {
            isLoginFailed = true;
        }

        HashMap<String ,String> userInfo = MemberJdbcDao.getInstance().getUserPass();

        if(userInfo.get(id) == null) {
            isLoginFailed = true;
        } else {
            if(!userInfo.get(id).equals(pw)) {
                isLoginFailed = true;
            }
        }

        if(isLoginFailed){
            req.setAttribute("loginFailed", isLoginFailed);
            dispatcher = req.getRequestDispatcher("/view/member/login.jsp");
            dispatcher.forward(req,resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("id", id);
            session.setAttribute("mem", MemberJdbcDao.getInstance().getInfo(id));
        }


        dispatcher = req.getRequestDispatcher("/board/list");
        dispatcher.forward(req,resp);


    }
}
