<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/views/include/header.jsp" />
<c:import url="/WEB-INF/views/include/sidebar.jsp" />
<!DOCTYPE html>
<html>s
<head>
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
<script>
$(document).ready(function(){
	//검색 후 select-option 유지
	$("#keyField").val("${keyField}").attr("selected","selected");
});

function eduAllocation(){
	var empNo = [];
	$("input[name='empNo']:checked").each(function(i){
		empNo.push($(this).val());
	});
	var eduNo = ${eduNo}
	var data ={empNo: empNo,
			eduNo: eduNo}

	$.ajax({
		type: "POST",
		url: "/edu/allocation",
		data: JSON.stringify(data),
		contentType: "application/json; charset=UTF-8",
		success: function(){
			alert("교육배정이 완료되었습니다.");
		},
		error: function(e) {
			alert("교육 배정 과정에서 문제가 발생했습니다. 다시 시도해주세요"+e);
		}
	});
}
</script>
</head>
<body>
<div class="content-body">
	<div class="container-fluid">
			<div class="row page-titles mx-0">
				<div class="col-sm-6 p-md-0">
					<div class="welcome-text">
						<h4>교육 인원 배정 - [${eduTitle}]</h4>
					</div>
				</div>
			</div>
			<form id="search_form" action="/edu/allocation/${eduNo}/${eduTitle}" method="post" class="form-inline de-flex justify-content-end">
			<select name="keyField" id="keyField" size="1" class="form-control form-cotrol-sm">
				<option value="">==</option>
				<option value="deptName">부서</option>
				<option value="posName">직급</option>
			</select> 
			<input type="text" id="kwd" name="keyword" value="${keyword}" class="form-control form-cotrol-sm"> 
			<input type="submit" class="btn btn-outline-info btn-sm" value="찾기">
		</form>
		<br>
		<div style='width: 80px; float: right; margin-bottom: 0.5em;'>
			<input type='button' class="btn btn-primary" name='btn' value='교육배정' onclick="eduAllocation();">
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
				<c:if test="${empty list}">
				<tr><td colspan=5>조회된 결과가 없습니다.</td></tr>
				</c:if>
				<c:forEach items="${list }" var="list">
					<tr>
						<td><input type="checkbox" name="empNo" value="${list.empNo }"></td>
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
					<c:choose>
						<c:when test="${empty pageInfo.keyField}">
							<li class="page-item"><a class="page-link" href="/edu/allocation/${eduNo}/${eduTitle}/${paging.startPage-1}">이전</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="/edu/allocation/${eduNo}/${eduTitle}/${paging.startPage-1}/${pageInfo.keyField }/${pageInfo.keyword}">이전</a></li>
						</c:otherwise>
					</c:choose>
				</c:if>

				<c:forEach var="p" begin="${paging.startPage }" end="${paging.endPage }" step="1">
					<c:choose>
						<c:when test="${p == tempPageNum }" >
							<li class="page-item active"><a class="page-link" href="#">${p}</a>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${empty pageInfo.keyField}">
									<li class="page-item"><a class="page-link" href="/edu/allocation/${eduNo}/${eduTitle}/${p}">${p}</a>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link" href="/edu/allocation/${eduNo}/${eduTitle}/${p}/${pageInfo.keyField }/${pageInfo.keyword}">${p}</a>
								</c:otherwise>
							</c:choose>
							
						</c:otherwise>
					</c:choose>
				</c:forEach>		
				<c:if test="${paging.hasNext }">
					<c:choose>
						<c:when test="${empty pageInfo.keyField}">
							<li class="page-item"><a class="page-link" href="/edu/allocation/${eduNo}/${eduTitle}/${paging.endPage+1}">다음</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="/edu/allocation/${eduNo}/${eduTitle}/${paging.endPage+1}/${pageInfo.keyField }/${pageInfo.keyword}">다음</a></li>
						</c:otherwise>
					</c:choose>
					
				</c:if>
			</ul>
			</nav>
		</div>
	</div>
	<c:import url="/WEB-INF/views/include/footer.jsp" />
</body>
</html>