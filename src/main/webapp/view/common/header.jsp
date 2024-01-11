<%--
  Created by IntelliJ IDEA.
  User: kitri
  Date: 2024-01-09
  Time: 오후 4:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <header>
    <a class="logo" href="/board/list"><span class="material-symbols-outlined">
    location_city
    </span></a>
    <nav>
        <ul class="nav-items">
            <li><a href="/board/list">게시글목록</a></li>
            <li><a href="/view/member/join.jsp">회원가입</a></li>
            <li><a href="/view/member/registration.jsp">회원정보수정</a></li>
            <li><a href="/view/member/login.jsp">로그인</a></li>
        </ul>
        <form action="/board/list" method="get">
            <input name="page"type="text" value="1" hidden>
            <select name="term">
                <option value="all">전체기간</option>
                <option value="day">1일</option>
                <option value="week">1주</option>
                <option value="month">1개월</option>
                <option value="half_mon">6개월</option>
                <option value="year">1년</option>
            </select>
            <select name="type">
<%--                <c:choose>  <!-- if, else의 시작임을 정의 -->--%>
<%--                    <c:when test="${param.type eq 'title' || param.type eq ''}"> <!-- if와 동일 -->--%>
<%--                        <option id ="title" value="title" selected>제목</option>--%>
<%--                        <option id= "writer" value="writer">작성자</option>--%>
<%--                    </c:when> <!-- if 종료 -->--%>
<%--                    <c:otherwise> <!-- else와 동일 -->--%>
<%--                        <option id ="title" value="title">제목</option>--%>
<%--                        <option id= "writer" value="writer" selected>작성자</option>--%>
<%--                    </c:otherwise> <!-- else 종료 -->--%>
<%--                </c:choose>  <!-- if, else의 종료임을 정의-->--%>
                <option id ="title" value="title">제목</option>
                <option id= "writer" value="writer">작성자</option>
            </select>
            <input name="keyword" type="text" value="${param.keyword}">
            <button type="submit">Search</button>
        </form>
    </nav>
    </header>
</body>
</html>
