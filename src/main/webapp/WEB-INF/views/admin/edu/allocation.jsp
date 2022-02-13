<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/views/include/header.jsp" />
<c:import url="/WEB-INF/views/include/sidebar.jsp" />

<div class="content-body">
	<div class="container-fluid">
		<form id="search_form" action="./allocation" method="post">
			<select name="keyword" size="1">
				<option value="deptName">부서</option>
				<option value="posName">직급</option>
			</select> <input type="text" id="kwd" name="searchContent" value=""> <input
				type="submit" value="찾기">
		</form>
		<br>
		<div style='width: 80px; float: right;'>
			<input type='button' class='btn' name='btn' value='발령처리'>
		</div>
		<table class="table table-striped" border=1>
			<thead>
				<tr>
					<th>선택</th>
					<th>사원번호</th>
					<th>성명</th>
					<th>직급</th>
					<th>부서</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list }" var="list">
					<tr>
						<td><input type="checkbox" name="checkbox" value="${list.empNo }"></td>
						<td>${list.empNo }</td>
						<td>${list.empName }</td>
						<td>${list.posName }</td>
						<td>${list.deptName }</td>
					</tr>
				</c:forEach>
			<tbody>
		</table>
		
		<!-- 페이지네이션  -->
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<c:if test="${paging.hasPrev }">
					<li class="page-item"><a class="page-link" href="./allocation?pageNum=${paging.startPage-1}&keyword=${pageInfo.keyword }&searchContent=${pageInfo.searchContent}">이전</a></li>
				</c:if>

				<c:forEach var="p" begin="${paging.startPage }" end="${paging.endPage }" step="1">
					<c:choose>
						<c:when test="${p == tempPageNum }" >
							<li class="page-item active"><a class="page-link" href="./allocation?pageNum=${p}&keyword=${pageInfo.keyword }&searchContent=${pageInfo.searchContent}">${p}</a>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="./allocation?pageNum=${p}&keyword=${pageInfo.keyword }&searchContent=${pageInfo.searchContent}">${p}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${paging.hasNext }">
					<li class="page-item"><a class="page-link" href="./allocation?pageNum=${paging.endPage+1}&keyword=${pageInfo.keyword }&searchContent=${pageInfo.searchContent}">다음</a></li>
				</c:if>
			</ul>
			</nav>
		</div>
	</div>
<c:import url="/WEB-INF/views/include/footer.jsp" />