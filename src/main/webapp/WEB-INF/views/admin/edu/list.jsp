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
	console.log("keyField: " + '${keyField}');
	
	//검색 후 select-option 유지
	$("#keyField").val("${keyField}").attr("selected","selected");
});
</script>
<!--**********************************
            Content body start
        ***********************************-->
<div class="content-body">
	<div class="container-fluid">
		<!-- 교육 히스토리 리스트 출력 -->
		<form id="search_form" action="/edu/list" method="post">
			<select name="keyField" id="keyField" size="1">
				<option value="">====</option>
				<option value="eduTitle">교육명</option>
				<option value="eduTarget">교육대상</option>
				<option value="eduProgress">진행상태</option>
			</select> <input type="text" id="kwd" name="keyword" value="${keyword}"> <input
				type="submit" value="찾기">
		</form>
		<br>
		<table class="table table-striped" border=1>
			<thead>
				<tr>
					<th>교육명</th>
					<th>교육인원</th>
					<th>교육대상</th>
					<th>교육시작일</th>
					<th>교육종료일</th>
					<th>진행상태</th>
					<th>교육배정</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty list}">
				<tr><td colspan=7>조회된 결과가 없습니다.</td></tr>
				</c:if>
				<c:forEach items="${list }" var="list">
					<tr>
						<td onClick="location.href='/edu/detail?edu_no=${list.eduNo }'">${list.eduTitle }</td>
						<td>${list.eduPeople }</td>
						<td>${list.eduTarget }</td>
						<td>${list.eduStart }</td>
						<td>${list.eduEnd }</td>
						<td><c:choose>
								<c:when test="${list.eduProgress eq 'pre'}">진행예정</c:when>
								<c:when test="${list.eduProgress eq 'ing'}">진행중</c:when>
								<c:otherwise>진행종료</c:otherwise>
							</c:choose></td>
						<td>
							<c:if test="${list.eduProgress eq 'pre'}">
								<button type="button" class="btn-primary" onclick="location.href='/edu/allocation/${list.eduNo }'">교육배정</button>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!-- 페이지네이션  -->
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<c:if test="${paging.hasPrev }">
					<li class="page-item"><a class="page-link"
						href="/edu/list/${paging.startPage-1}/${pageInfo.keyField }/${pageInfo.keyword}">이전</a></li>
				</c:if>

				<c:forEach var="p" begin="${paging.startPage }" end="${paging.endPage }" step="1">
					<c:choose>
						<c:when test="${p == tempPageNum }">
							<li class="page-item active"><a class="page-link" href="#">${p}</a>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${empty pageInfo.keyField}">
									<li class="page-item"><a class="page-link"
										href="/edu/list/${p}">${p}</a>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link"
										href="/edu/list/${p}/${pageInfo.keyField }/${pageInfo.keyword}">${p}</a>
								</c:otherwise>
							</c:choose>

						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${paging.hasNext }">
					<li class="page-item"><a class="page-link"
						href="/edu/list/${paging.endPage+1}/${pageInfo.keyField }/${pageInfo.keyword}">다음</a>
					</li>
				</c:if>
			</ul>
		</nav>
	</div>
</div>

<!--**********************************
            Content body end
        ***********************************-->
<c:import url="/WEB-INF/views/include/footer.jsp" />