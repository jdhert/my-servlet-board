package com.kitri.myservletboard.controller;


import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Pagination;
import com.kitri.myservletboard.service.BoardService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {

    BoardService boardService = BoardService.getInstance();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h1> 요청을 잘 응답 받았습니다</h1>");
        //URL을 파싱해서 어떤 요청인지 파악
        out.println(request.getRequestURI());

        String requestURI =request.getRequestURI(); // /board/ist
        String contextPath = request.getContextPath(); // /거나, 아무 것도 없거나
        String command = requestURI.substring(contextPath.length()); // /board/list

        //뷰(페이지)를 응답하는 방법 2가지
        //1.리다이렉트 = 클라이언트에게 url을 넘겨서, 클라이언트가 직접 해당 url로 접속하도록 하는 것
        //리다이렉트 방식도 또 여러개 있나봄
        //1. response.sendRedirect("/view/board/해당하는html파일명.html");
        //2. response.addHeader("Refresh", "2; url = " + "view");
        //2.포워드 =
        String view = "/view/board/";

        if (command.equals("/board/list")) {

            // 요청: 조회 게시글 리스트 좀 보여주라는 뜻
            // 응답: 그러면 우리는 게시글 리스트 패이지로 응답해줘야 한다. by 리다이렉트 or 포워드
            //1.리다이렉트 방식
//            response.sendRedirect("/view/board/list.jsp");
            //사실상 이 SendRedirect나 바로 아래 addHeader나 동일해서 하나만 써줘도 됨
//            response.addHeader("Refresh", "2; url = " + "view");
            ArrayList<Board> boards;
            Pagination pagination = new Pagination(1);
            String page = request.getParameter("page");
            String type = request.getParameter("type");
            String keyword = request.getParameter("keyword");
            String  term = request.getParameter("term");
            String orderBy = request.getParameter("orderBy");
            String maxRecordsPerPage = request.getParameter("maxRecordsPerPage");

            if(maxRecordsPerPage != null) pagination.setRecordsPerPage(Integer.parseInt(maxRecordsPerPage));

            if (term == null || term.isEmpty()) pagination.setTerm("all");
            else pagination.setTerm(term);

            if(type == null || type.isEmpty()) pagination.setType("title");
            else pagination.setType(type);

            if (page != null) pagination.setCurrentPage(Integer.parseInt(page));

            if(keyword == null) pagination.setKeyword("");
            else pagination.setKeyword(keyword);
//            if(keyword != null) {
//                pagination.setKeyword(keyword);
//            }

            if (orderBy == null) pagination.setOrderBy("created_at");
            else pagination.setOrderBy(orderBy);

            boards = boardService.getBoards(pagination);
            request.setAttribute("pagination", pagination);
            request.setAttribute("boards", boards);
            view += "list.jsp";

        } else if (command.equals("/board/createForm")) {
            // 요청: 게시글 등록하게 등록폼 좀 주라는 뜻
            // 응답: 등록폼으로 응답 by 리다이렉트 or 포워드
            //1.리다이렉트 방식
//            response.sendRedirect("/view/board/createForm.jsp");
            view += "createForm.jsp";
        } else if (command.equals("/board/create")) {
            // 요청: 게시글 등록하게 게시판 이렇게 좀 만들어 주라는 뜻
            // 응답: by 리다이렉트 or 포워드
            boardService.addBoard(new Board(null, request.getParameter("title"),request.getParameter("content"),
                    request.getParameter("writer"), LocalDateTime.now(), 0, 0, Long.parseLong(request.getParameter("member_id"))));
            response.sendRedirect("/board/list");
            return;
        } else if (command.equals("/board/updateForm")) {
            // 요청: 게시글 등록하게 게시판 이렇게 좀 만들어 주라는 뜻
            // 응답: by 리다이렉트 or 포워드
            //1.리다이렉트 방식
//            response.sendRedirect("/view/board/updateForm.jsp");
            Board board = boardService.getBoard(Long.valueOf(request.getParameter("id")));
            request.setAttribute("board", board);
            view += "updateForm.jsp";
        } else if (command.equals("/board/update")) {
            // 요청: 이 번호의 게시판 이렇게 수정해주라는 뜻
            // 응답: by 리다이렉트 or 포워드
            boardService.updateBoard((new Board(Long.parseLong(request.getParameter("id")), request.getParameter("title"),request.getParameter("content"),
                    request.getParameter("writer"), LocalDateTime.now(),0, 0,Long.parseLong(request.getParameter("member_id")))));
            response.sendRedirect("/board/list");
            return;
        } else if (command.equals("/board/delete")) {
            // 요청: 이 번호의 게시판 삭제해주라는 뜻
            // 응답: by 리다이렉트 or 포워드
            Board board = boardService.getBoard(Long.valueOf(request.getParameter("id")));
            boardService.deleteBoard(board);
            response.sendRedirect("/board/list");
            return;
        } else if (command.equals("/board/detail")) {
            // /board/detail?id=1
            Board board = boardService.getBoard(Long.valueOf(request.getParameter("id")));
            request.setAttribute("board", board);
            view += "detail.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);


    }
}