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

<!--**********************************
            Content body start
        ***********************************-->
<div class="content-body">
	<div class="container-fluid">
		<!-- 교육 히스토리 리스트 출력 -->
		<form id="search_form" action="/edu/history" method="post" class="form-inline d-flex justify-content-end">
			<input type="hidden" name="empNo" value="${sessionEmp.empNo }">
			<select name="keyField" size="1" class="form-control form-control-sm">
				<option value="eduTitle">교육명</option>
			</select> <input type="text" id="kwd" name="keyword" class="form-control form-cotrol-sm" style="margin: 10px"> 
			<input type="submit" class="btn btn-outline-info btn-sm" value="찾기">
		</form>
		<br>
		
		<table class="table table-striped" border=1>
			<thead>
				<tr>
					<th>교육명</th>
					<th>출석률</th>
					<th>점수</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty list }">
				<tr><td colspan=5>조회된 결과가 없습니다.</td></tr>
				</c:if>
				<c:forEach items="${list }" var="list">
					<tr>
						<td><a href="./eduEmpDetail?edu_no=${list.eduNo }">${list.eduTitle }</a></td>
						<td>${list.attendance }%</td>
						<td>${list.score }</td>
					</tr>
				</c:forEach>
			<tbody>
		</table>
		<!-- 페이지네이션  -->
			<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<c:if test="${paging.hasPrev }">
					<li class="page-item"><a class="page-link" href="/edu/history?empNo=${sessionEmp.empNo}&pageNum=${paging.startPage-1}&keyField=${pageInfo.keyField }&keyword=${pageInfo.keyword}">이전</a></li>
				</c:if>
				
				<c:forEach var="p" begin="${paging.startPage }" end="${paging.endPage }" step="1">
					<c:choose>
						<c:when test="${p == tempPageNum }" >
							<li class="page-item active"><a class="page-link" href="#">${p}</a>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${empty pageInfo.keyField}">
									<li class="page-item"><a class="page-link" href="/edu/history?empNo=${sessionEmp.empNo}&pageNum=${p}">${p}</a>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link" href="/edu/history?empNo=${sessionEmp.empNo}&pageNum=${p}&keyField=${pageInfo.keyField }&keyword=${pageInfo.keyword}">${p}</a>
								</c:otherwise>
							</c:choose>
							
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${paging.hasNext }">
					<li class="page-item"><a class="page-link" href="/edu/history?empNo=${eduNo}?pageNum=${paging.endPage+1}&keyField=${pageInfo.keyField }&keyword=${pageInfo.keyword}">다음</a></li>
				</c:if>
			</ul>
			</nav>
	</div>
</div>

<!--**********************************
            Content body end
        ***********************************-->
<c:import url="/WEB-INF/views/include/footer.jsp" />