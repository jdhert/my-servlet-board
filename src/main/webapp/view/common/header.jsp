<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: kitri
  Date: 2024-01-09
  Time: 오후 4:25
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <header class="d-flex flex-row">
    <a class="logo" href="/board/list"><span class="pb-5 material-symbols-outlined">
    location_city
    </span></a>
        <nav>
            <ul class="nav-items">
                <li><a href="/board/list">게시글목록</a></li>
                <li><a href="/view/member/join.jsp">회원가입</a></li>
                <li><a href="/view/member/registration.jsp">회원정보수정</a></li>
                <li><a href=${param.id2 == "null" || param.id2 == null ? "/view/member/login.jsp" : "/login/Logout"}>${param.id2 == "null" || param.id2 == null ? "로그인" : "로그아웃"}</a></li>
            </ul>
        </nav>
        <form class="form-inline my-2 my-lg-0 ml-auto pr-5" action="/board/list" method="get">
            <input name="page"type="text" value="1" hidden>
            <select name="term">
                <option value="all" ${param.term == 'all' || param.term == '' ? 'selected' : ''}>전체기간</option>
                <option value="day" ${param.term == 'day' ? 'selected' : ''}>1일</option>
                <option value="week" ${param.term == 'week' ? 'selected' : ''}>1주</option>
                <option value="month" ${param.term == 'month' ? 'selected' : ''}>1개월</option>
                <option value="half_mon" ${param.term == 'half_mon' ? 'selected' : ''}>6개월</option>
                <option value="year" ${param.term == 'year' ? 'selected' : ''}>1년</option>
            </select>
            &nbsp;
            <select name="type">
                <option value="title" ${param.type == 'title' || param.type == '' ? 'selected' : ''}>제목</option>
                <option value="writer" ${param.type == 'writer' ? 'selected' : ''}>작성자</option>
            </select>
            &nbsp;
            <input class="form-control mr-sm-2" name="keyword" type="text" value="${param.keyword}">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </header>
</body>
<style>
    select {
        height: 60%;
        border-radius: 5px;
    }
</style>
</html>
