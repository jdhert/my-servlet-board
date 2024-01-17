package com.kitri.myservletboard.controller;

import com.kitri.myservletboard.dao.CommentJdbcDao;
import com.kitri.myservletboard.dao.MemberJdbcDao;
import com.kitri.myservletboard.data.Comment;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/comment/*")
public class CommentController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        String requestURI =req.getRequestURI();
        String contextPath = req.getContextPath();
        String command = requestURI.substring(contextPath.length());

        String view = "/board/detail";

        CommentJdbcDao commentJdbcDao = CommentJdbcDao.getInstance();




        if (command.equals("/comment/add")) {
            long board_id = Long.parseLong(req.getParameter("board_id"));
            long member_id = Long.parseLong(req.getParameter("member_id"));
            String id2 = req.getParameter("id2");
            String name = req.getParameter("name");
            String content = req.getParameter("content");

            commentJdbcDao.insertComment(new Comment(1L, content, LocalDateTime.now(),member_id, name,board_id));

            view += "?id=" + board_id + "&id2=" + id2 + "&ide=" + member_id + "&author=" + name;
        } else if (command.equals("/comment/delete")){
            long id = Long.parseLong(req.getParameter("id1"));
            commentJdbcDao.deleteComment(id);

            long board_id = Long.parseLong(req.getParameter("id"));
            long member_id = Long.parseLong(req.getParameter("ide"));
            String id2 = req.getParameter("id2");
            String name = req.getParameter("author");


            view += "?id=" + board_id + "&id2=" + id2 + "&ide=" + member_id + "&author=" + name;
        }



        RequestDispatcher dispatcher = req.getRequestDispatcher(view);
        dispatcher.forward(req, resp);
    }
}
