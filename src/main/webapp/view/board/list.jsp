<%@ page import="java.util.ArrayList" %>
<%@ page import="com.kitri.myservletboard.data.Board" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.kitri.myservletboard.data.Pagination" %>
<%@ page import="java.util.Objects" %>
<%@ page import="com.kitri.myservletboard.data.Member" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  ArrayList<Board> boards = (ArrayList<Board>) request.getAttribute("boards");
  Pagination pagination = (Pagination) request.getAttribute("pagination");
  pagination.calcPagination();
  String searchParam;
  if(pagination.getKeyword()==null || Objects.equals(pagination.getKeyword(), "")) {
    pagination.setType("");
    pagination.setKeyword("");
  }
//    searchParam = "";
//  } else {
  searchParam = "&term=" + pagination.getTerm() +"&type=" + pagination.getType() + "&keyword=" + pagination.getKeyword() +
          "&orderBy=" + pagination.getOrderBy() + "&maxRecordsPerPage="+ pagination.getRecordsPerPage();
//  }
  String id2 = (String) session.getAttribute("id");
  Member mem = (Member) session.getAttribute("mem");
  String parama = "";
  if(mem != null){
    parama = "&ide="+ mem.getId() + "&author=" + mem.getName();
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
    <jsp:param name="id2" value="<%=id2%>"/>
  </jsp:include>

  <div>
    <div class="d-flex justify-content-around position-relative" style="text-align: center; margin-top: 80px; margin-right: 15%; margin-left: 47%">
    <h2><b>게시판 목록</b></h2>
      <form class="form-inline my-2 my-lg-0 ml-auto pr-5 mt-5" action="/board/list">
        <input hidden type="text" name="type" value="<%=pagination.getType()%>">
        <input hidden type="text" name="keyword" value="<%=pagination.getKeyword()%>">
        <input hidden type="text" name="term" value="<%=pagination.getTerm()%>">
        <select name="orderBy" onchange="this.form.submit()">
          <option value="created_at" <%=Objects.equals(pagination.getOrderBy(), "created_at") ? "selected" : ""%>>최신순</option>
          <option value="view_count" <%=Objects.equals(pagination.getOrderBy(), "view_count") ? "selected" : ""%>>조회순</option>
          <option value="accuracy" <%=Objects.equals(pagination.getOrderBy(), "accuracy") ? "selected" : ""%>>정확도순</option>
        </select>
        &nbsp;
        <select name="maxRecordsPerPage" onchange="this.form.submit()">
          <option value="5" <%=pagination.getRecordsPerPage() == 5 ? "selected" : ""%>>5개씩 보기</option>
          <option value="10" <%=pagination.getRecordsPerPage() == 10 ? "selected" : ""%>>10개씩 보기</option>
          <option value="15" <%=pagination.getRecordsPerPage() == 15 ? "selected" : ""%>>15개씩 보기</option>
          <option value="20" <%=pagination.getRecordsPerPage() == 20 ? "selected" : ""%>>20개씩 보기</option>
          <option value="30" <%=pagination.getRecordsPerPage() == 30 ? "selected" : ""%>>30개씩 보기</option>
          <option value="40" <%=pagination.getRecordsPerPage() == 40 ? "selected" : ""%>>40개씩 보기</option>
          <option value="50" <%=pagination.getRecordsPerPage() == 50 ? "selected" : ""%>>50개씩 보기</option>
        </select>
      </form>
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
            <td><a href="/board/detail?id=<%=boards.get(i).getId()%>&id2=<%=id2%><%=parama%>" style="color: black; text-decoration: none">
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
        <a href="/board/createForm?id2=<%=id2%><%=parama%>" role="button" class="btn btn-outline-dark" <%=id2 == null ? "hidden" : ""%>>글쓰기</a>
      </div>

      <div class="d-flex justify-content-center">
      <nav aria-label="Page navigation example">
        <ul class="pagination pagination-sm">
          <% if(pagination.isHasPrev()) {%>
          <li class="page-item">
            <a class="page-link" href="/board/list?page=<%=pagination.getStartPageOnScreen()-1%><%=searchParam%>">Previous</a>
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
          <li class="page-item"><a class="page-link active" href="/board/list?page=<%=i%><%=searchParam%>"><%=i%></a></li>
          <%} else {%>
          <li class="page-item"><a class="page-link" href="/board/list?page=<%=i%><%=searchParam%>"><%=i%></a></li>
          <%}}%>
          <% if(pagination.isHasNext()) {%>
          <li class="page-item">
            <a class="page-link" href="/board/list?page=<%=pagination.getEndPageOnScreen() + 1%><%=searchParam%>" >Next</a>
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

  </div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
  integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

</html>