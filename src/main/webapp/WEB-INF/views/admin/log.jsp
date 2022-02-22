<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/views/include/header.jsp" />
<c:import url="/WEB-INF/views/include/sidebar.jsp" />

<style type="text/css">
h1 {
	text-align: center;
}

table {
	width: 80%;
	height: 100px;
	text-align: center;
	margin: auto;
}

table th {
	height: 10px;
}

form {
	text-align: center;
}
</style>

<script type="text/javascript">
$(document).ready(function(){
	console.log("ready");
	console.log("keyField: " + '${pageInfo.keyField}');
	
	//검색 후 select-option 유지
	$("#keyField").val("${pageInfo.keyField}").attr("selected","selected");
});
</script>


<!--**********************************
            Content body start
        ***********************************-->
<div class="content-body">
	<div class="container-fluid">
		<!-- 공지사항 전체 리스트 출력 -->
		<div class="row page-titles mx-0">
         <div class="col-sm-6 p-md-0">
            <div class="welcome-text">
               <h4>로그 기록</h4>
            </div>
         </div>
      </div>
		<form id="search_form" action="/edu/log" method="post" class="form-inline d-flex justify-content-end">
			<input type="hidden" name="empNo" value="${sessionEmp.empNo }">
			<select name="keyField" size="1" id="keyField" class="form-control form-control-sm">
				<option value="">====</option>
				<option value="logIp">아이피</option>
				<option value="empNo">사원번호</option>
				<option value="logDate">기록일(yyyy-mm-dd)</option>
				<option value="logTarget">로그장소</option>
			</select> <input type="text" id="kwd" name="keyword" value="${pageInfo.keyword}" class="form-control form-cotrol-sm" style="margin: 10px"> 
			<input type="submit" class="btn btn-outline-info btn-sm" value="찾기">
		</form>
		<br>
		<table class="table table-striped" border=1>
			<thead>
				<tr>
					<th>로그번호</th>
					<th>아이피</th>
					<th>사원번호</th>
					<th>기록일</th>
					<th>로그장소</th>
					<th>로그내용</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty list }">
				<tr><td colspan=7>조회된 결과가 없습니다.</td></tr>
				</c:if>
				<c:forEach items="${list }" var="list">
					<tr>
						<td>${list.logNo }</td>
						<td>${list.logIp }</td>
						<td><a href="/emp/detail?empNo=${list.empNo }">${list.empNo }</a></td>
						<td>${list.logDate}</td>
						<td>${list.logTarget}</td>
						<td>${list.logDesc }</td>
					</tr>
				</c:forEach>
			<tbody>
		</table>
		<!-- 페이지네이션  -->
			<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<c:if test="${paging.hasPrev }">
					<li class="page-item"><a class="page-link" href="/edu/log?pageNum=${paging.startPage-1}&keyField=${pageInfo.keyField }&keyword=${pageInfo.keyword}">이전</a></li>
				</c:if>
				
				<c:forEach var="p" begin="${paging.startPage }" end="${paging.endPage }" step="1">
					<c:choose>
						<c:when test="${p == tempPageNum }" >
							<li class="page-item active"><a class="page-link" href="#">${p}</a>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${empty pageInfo.keyField}">
									<li class="page-item"><a class="page-link" href="/edu/log?pageNum=${p}">${p}</a>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link" href="/edu/log?pageNum=${p}&keyField=${pageInfo.keyField }&keyword=${pageInfo.keyword}">${p}</a>
								</c:otherwise>
							</c:choose>
							
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${paging.hasNext }">
					<li class="page-item"><a class="page-link" href="/edu/log?pageNum=${paging.endPage+1}&keyField=${pageInfo.keyField }&keyword=${pageInfo.keyword}">다음</a></li>
				</c:if>
			</ul>
			</nav>
	</div>
</div>

<!--**********************************
            Content body end
        ***********************************-->
<c:import url="/WEB-INF/views/include/footer.jsp" />