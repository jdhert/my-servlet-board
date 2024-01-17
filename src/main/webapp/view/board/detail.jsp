<%@ page import="com.kitri.myservletboard.data.Board" %>
<%@ page import="com.kitri.myservletboard.dao.MemberJdbcDao" %>
<%@ page import="com.kitri.myservletboard.dao.CommentJdbcDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.kitri.myservletboard.data.Comment" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<jsp:include page="/view/common/head.jsp">
    <jsp:param name="title" value="게시판 상세" />
</jsp:include>

<body class="sb-nav-fixed">
    <jsp:include page="/view/common/header.jsp"/>

<main class="mt-5 pt-5">
    <div class="container-fluid px-4 ">

        <div class="card mb-4 w-50 mx-auto">
            <div>
                <h2 class="mt-3" style="text-align: center;"><b>게시판 상세</b></h2>
                <hr class="mb-0">
            </div>
            <div class="d-flex flex-column" style="height: 500px;">
                <div class="p-2 border-bottom border-dark-subtle d-flex flex-row-reverse">
                    <div class="pd opacity-75 bg-info-subtle border border-dark rounded-pill"><small>조회수 : ${board.getViewCount()}</small></div>
                    &nbsp
                    <div class="pd opacity-75 bg-success-subtle border border-dark rounded-pill"><small>댓글수 : ${board.getCommentCount()}</small></div>
                    <div class="d-flex flex-row flex-fill">
                        <div class="pd opacity-75 border border-dark rounded-pill">#${board.getId()}</div>
                    </div>
                </div>
                <div class="p-2 border-bottom">
                    <b>${board.getTitle()}</b>
                </div>
                <div class="p-2 border-bottom">
                    <b>저자 :</b> ${board.getWriter()}
                </div>
                <div class="p-2 border-bottom">
                    <b>등록일시 :</b> ${board.getCreatedAt()}
                </div>
                <div class="m-3 h-75">
                    <textarea class="h-100 form-control bg-white" id="content" name="content"
                              disabled>${board.getContent()}</textarea>
                </div>
                <div class="d-flex flex-row-reverse mb-3 mr-3">
                    &nbsp
                    &nbsp
                    <a href="/board/delete?id=${board.getId()}" class="btn btn-secondary btn-sm" onclick="return confirm('삭제하시겠습니까?')" ${param.ide == board.getMember_id() ? "" : "hidden"}><small>삭제하기</small></a>
                    &nbsp
                    <a href="/board/updateForm?id=${board.getId()}&id2=${param.id2}&ide=${param.ide}" class="btn btn-secondary btn-sm" ${param.ide == board.getMember_id() ? "" : "hidden"}><small>수정하기</small></a>
                    &nbsp
                    <a href="/board/list" class="btn btn-secondary btn-sm"><small>목록으로</small></a>
                    &nbsp
                </div>
            </div>
        </div>
        <div class="card mb-4 w-50 mx-auto">
            <div>
                <h2 class="mt-3" style="text-align: center;"><b>댓글</b></h2>
                <hr class="mb-0">
            </div>
            <div class="d-flex flex-column" style="height: auto;">
                <%
                    ArrayList<Comment> comments = CommentJdbcDao.getInstance().getComment(Long.valueOf(request.getParameter("id"))) ;
                    if (comments != null) {
                    for(int i=0; i <= comments.size()-1; i++){%>
                <tr>
                    <td><h5 style="font-weight: bold; padding-left: 5px; text-align: left" > <%=comments.get(i).getName()%></h5></td>
                    <td><textarea class="h-100 form-control bg-white" name="content"
                              disabled><%=comments.get(i).getContent()%></textarea>
                    </td>
                    &nbsp
                    <td><%=comments.get(i).getCreatedAt().format(DateTimeFormatter.ofPattern("YYYY-MM-DD:HH:mm:ss"))%></td>
                </tr>
                <br>
                <a href="/comment/delete?id1=<%=comments.get(i).getId()%>&id=<%=request.getParameter("id")%>&id2=<%=request.getParameter("id2")%>&ide=<%=request.getParameter("ide")%>&author=<%=request.getParameter("author")%>"
                   class="btn btn-secondary btn-sm" onclick="return confirm('삭제하시겠습니까?')" <%= request.getParameter("ide") != null && Long.parseLong(request.getParameter("ide")) == comments.get(i).getMember_id() ? "" : "hidden"%>><small>삭제하기</small></a>
                <% } }%>
                &nbsp
            </div>
        </div>
        <% if(!Objects.equals(request.getParameter("id2"), "null")) {%>
        <form action="/comment/add">
            <input name="board_id" type="text" value="<%=request.getParameter("id")%>" hidden>
            <input name="id2" type="text" value="<%=request.getParameter("id2")%>" hidden>
            <input name="member_id" type="text" value="<%=request.getParameter("ide")%>" hidden>
            <input name="name" type="text" value="<%=request.getParameter("author")%>" hidden>
            <div class="mb-3">
                <label for="content" class="form-label">내용</label>
                <textarea name="content"  class="form-control" cols="30" rows="5" placeholder="내용을 입력해주세요"></textarea>
            </div>
            <div class="row">
                <div class="col-md-6 mb-3" style="width: 100%">
                    <button class="btn btn-secondary btn-block" type="submit">댓글 등록하기</button>
                </div>
            </div>
        </form>
        <% } %>
    </div>
</main>
</body>
<style>
    .pd {
        padding-left: 5px;
        padding-right: 5px;
    }
</style>
</html>