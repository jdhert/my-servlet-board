package com.kitri.myservletboard.login;

import com.kitri.myservletboard.dao.BoardJdbcDao;
import com.kitri.myservletboard.dao.MemberJdbcDao;
import com.kitri.myservletboard.data.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/login/Regist")
public class Regist extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        MemberJdbcDao memberJdbcDao= MemberJdbcDao.getInstance();
        RequestDispatcher dispatcher;

        String name = req.getParameter("name");
        String id = req.getParameter("userId");
        String pw = req.getParameter("password");
        String email = req.getParameter("email");

        boolean isRegistFailed;

        ArrayList<String> userIds  = memberJdbcDao.getUserIds();
        for(int i=0; i <= userIds.size()-1; i++){
            if(userIds.get(i).equals(id)){
                isRegistFailed = true;
                req.setAttribute("isRegistFailed", isRegistFailed);
                dispatcher = req.getRequestDispatcher("/view/member/join.jsp");
                dispatcher.forward(req,resp);
                break;
            }
        }
        memberJdbcDao.regist(new Member(1L,id,pw,name,email));


        dispatcher = req.getRequestDispatcher("/board/list");
        dispatcher.forward(req,resp);

    }
}
