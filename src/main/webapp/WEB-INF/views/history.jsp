<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/views/include/header.jsp" />
<c:import url="/WEB-INF/views/include/sidebar.jsp" />

<!--**********************************
            Content body start
        ***********************************-->
<div class="content-body">
	<div class="container-fluid">
		<!-- 교육 히스토리 리스트 출력 -->
		<form id="search_form" action="history" method="post">
			<input type="hidden" name="a" value="list"> 
			<select name="keyField" size="1">
				<option value="eduTitle">교육명</option>
			</select> <input type="text" id="kwd" name="keyword" value=""> <input
				type="submit" value="찾기">
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
				<c:if test="${empty eduEmpHistoyList}">
				<tr><td colspan=5>조회된 결과가 없습니다.</td></tr>
				</c:if>
				<c:forEach items="${eduEmpHistoyList}" var="list">
					<tr>
						<td>${list.eduTitle }</td>
						<td>${list.attendance }%</td>
						<td>${list.score }</td>
					</tr>
				</c:forEach>
			<tbody>
		</table>
	</div>
</div>

<!--**********************************
            Content body end
        ***********************************-->
<c:import url="/WEB-INF/views/include/footer.jsp" />