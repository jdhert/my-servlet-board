<%@ page import="java.util.ArrayList" %>
<%@ page import="com.kitri.myservletboard.data.Board" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.kitri.myservletboard.data.Pagination" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  ArrayList<Board> boards = (ArrayList<Board>) request.getAttribute("boards");
  Pagination pagination = (Pagination) request.getAttribute("pagination");
  pagination.calcPagination();
  if(pagination.getType()==null){
    pagination.setType("");
  }
  if(pagination.getKeyword()==null){
    pagination.setKeyword("");
  }
%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="/view/common/head.jsp">
  <jsp:param name="title" value="게시판 목록"/>
</jsp:include>

<body>
  <jsp:include page="/view/common/header.jsp">
    <jsp:param name="page" value="<%=pagination.getCurrentPage()%>"/>
    <jsp:param name="type" value="<%=pagination.getType()%>"/>
    <jsp:param name="term" value="<%=pagination.getTerm()%>"/>
    <jsp:param name="keyword" value="<%=pagination.getKeyword()%>"/>
  </jsp:include>

  <div>
    <h2 style="text-align: center; margin-top: 100px;"><b>게시판 목록</b></h2>
  </div>
  <div class="container class=d-flex justify-content-center">
    <div class="p-2 border-primary mb-3">
      <table class="table align-middle table-hover">
        <thead class="table-dark">
          <tr>
            <th scope="col">번호</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">날짜</th>
            <th scope="col">조회수</th>
            <th scope="col">댓글수</th>
          </tr>
        </thead>
        <tbody class="table-group-divider">
        <% for(int i=0; i <= boards.size()-1; i++){%>
          <tr>
            <th scope="row"><%=(pagination.getCurrentPage()-1) * pagination.getRecordsPerPage() + i + 1 %></th>
            <td><a href="/board/detail?id=<%=boards.get(i).getId()%>" style="color: black; text-decoration: none">
              <%=boards.get(i).getTitle()%></a></td>
            <td><%=boards.get(i).getWriter()%></td>
            <td><%=boards.get(i).getCreatedAt().format(DateTimeFormatter.ofPattern("YYYY-MM-DD:HH:mm:ss"))%></td>
            <td><%=boards.get(i).getViewCount()%></td>
            <td><%=boards.get(i).getCommentCount()%></td>
          </tr>
        <% } %>
        </tbody>
      </table>
      <div>
        <a href="/board/createForm" role="button" class="btn btn-outline-dark">글쓰기</a>
      </div>

      <div class="d-flex justify-content-center">
      <nav aria-label="Page navigation example">
        <ul class="pagination pagination-sm">
          <% if(pagination.isHasPrev()) {%>
          <li class="page-item">
            <a class="page-link" href="/board/list?page=<%=pagination.getStartPageOnScreen()-1%>&type=<%=pagination.getType()%>&keyword=<%=pagination.getKeyword()
            %>&term=<%=pagination.getTerm()%>">Previous</a>
          </li>
          <% }  else {%>
          <li class="page-item disabled">
            <a class="page-link" href="#">Previous</a>
          </li>
          <% } %>
          <%
            for(int i = pagination.getStartPageOnScreen(); i <= pagination.getEndPageOnScreen(); i++) {
              if(pagination.getCurrentPage() == i ) {
          %>
          <li class="page-item"><a class="page-link active" href="/board/list?page=<%=i%>&type=<%=pagination.getType()%>&keyword=<%=pagination.getKeyword()%>&term=<%=pagination.getTerm()%>"><%=i%></a></li>
          <%} else {%>
          <li class="page-item"><a class="page-link" href="/board/list?page=<%=i%>&type=<%=pagination.getType()%>&keyword=<%=pagination.getKeyword()%>&term=<%=pagination.getTerm()%>"><%=i%></a></li>
          <%}}%>
          <% if(pagination.isHasNext()) {%>
          <li class="page-item">
            <a class="page-link" href="/board/list?page=<%=pagination.getEndPageOnScreen() + 1%>&type=<%=pagination.getType()%>&keyword=<%=pagination.getKeyword()%>&term=<%=pagination.getTerm()%>" >Next</a>
          </li>
          <% }  else {%>
          <li class="page-item disabled">
            <a class="page-link" href="#"  aria-disabled="true">Next</a>
          </li>
          <% }%>
        </ul>
      </nav>
    </div>

    </div>
  </div>

  <div class="p-2">
    <div class="container d-flex justify-content-center">
      <footer>
        <span class="text-muted">&copy; Company's Bootstrap-board</span>
      </footer>
    </div>
  </div>


</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
  integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

</html>