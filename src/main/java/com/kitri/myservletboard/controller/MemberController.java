package com.kitri.myservletboard.controller;

import com.kitri.myservletboard.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
    MemberService memberService = MemberService.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");


        String requestURI =req.getRequestURI(); // /board/ist
        String contextPath = req.getContextPath(); // /거나, 아무 것도 없거나
        String command = requestURI.substring(contextPath.length()); // /board/list

        //뷰(페이지)를 응답하는 방법 2가지
        //1.리다이렉트 = 클라이언트에게 url을 넘겨서, 클라이언트가 직접 해당 url로 접속하도록 하는 것
        //리다이렉트 방식도 또 여러개 있나봄
        //1. response.sendRedirect("/view/board/해당하는html파일명.html");
        //2. response.addHeader("Refresh", "2; url = " + "view");
        //2.포워드 =
        String view = "/view/member/";

        if (command.equals("/memeber/join")) {
            String name = req.getParameter("name");
            String id = req.getParameter("userId");
            String pw = req.getParameter("password");
            String email = req.getParameter("email");
        }
    }
}
