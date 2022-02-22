<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/views/include/header.jsp" />
<c:import url="/WEB-INF/views/include/sidebar.jsp" />
<style>
.eduScore{
	border: none;
	background: transparent;
}

.eduHisno{
	display:none;
}
</style>
<script type="text/javascript">

$(document).ready(function(){
	console.log("ready");
	console.log("keyField: " + '${keyField}');
	
	//검색 후 select-option 유지
	$("#keyField").val("${keyField}").attr("selected","selected");

	$(".eduScore").keyup(function(){
		console.log("keyup");
		var vaildScore = /^[PNpn]$|^(100|[1-9]?\d)$/;
		if(!vaildScore.test($(this).val())){
			alert("잘못된 형식입니다.\n점수는 0 ~ 100 사이의 숫자 또는 P/N 만 입력 가능합니다.");
			$(this).val("");
		} 
	});
});

function activeInput(){
	$("input[name='score2']").attr("readonly",false);
	$("input[name='score']").attr("readonly",false);
	$("input[name='score']").attr("value","");
	$("input[name='btnScore']").attr("hidden",false);
}

function modifyScore(){
	console.log("modifyScore()");
	var eduHisno = [];
	var score = [];
	$("input[name='eduHisno']").each(function(i){
		eduHisno.push($(this).val());	
	});
	$(".eduScore").each(function(i){
		score.push($(this).val());	
	});

	var data = {eduHisno: eduHisno, score: score}
	console.log(data);
	
	$.ajax({
		type: "POST",
		url: "/edu/score",
		data: JSON.stringify(data),
		contentType: "application/json; charset=UTF-8",
		success: function(){
			alert("점수 반영이 완료되었습니다.");
			$("input[name='score']").attr("readonly",true);
			$("input[name='score2']").attr("readonly",true);
			$("input[name='btnScore']").attr("hidden",true);
		},
		error: function(e) {
			alert("점수 반영 과정에서 문제가 발생했습니다. 다시 시도해주세요");
		}
	});
}

</script>

<!--**********************************
            Content body start
        ***********************************-->
<div class="content-body">
	<div class="container-fluid">
		<!-- 교육 히스토리 리스트 출력 -->
			<form id="search_form" action="/edu/admin/history" method="post" class="form-inline de-flex justify-content-end">
				<select name="keyField" id="field" class="form-control form-control-sm" >
					<option value="">=====</option>
					<option value="eduTitle">교육명</option>
					<option value="empNo">사원번호</option>
					<option value="empName">사원이름</option>
				</select>
				<input type="text" class="form-control form-cotrol-sm" id="kwd" name="keyword" value="${pageInfo.keyword}"  > 
				<input	type="submit" class="btn btn-outline-info btn-sm" value="찾기">
			</form>
		<br>
		<form action="/edu/score" method="post">
		<table class="table table-striped" border=1>
			<thead>
				<tr>
					<th>교육명</th>
					<th>사원번호</th>
					<th>성명</th>
					<th>출석률</th>
					<th>점수<span class="scoreMsg"></span></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty eduHistoryList}">
				<tr><td colspan=5>조회된 결과가 없습니다.</td></tr>
				</c:if>
				<c:forEach items="${eduHistoryList}" var="list">
					<tr>
						<td>${list.eduTitle }</td>
						<td>${list.empNo }</td>
						<td>${list.empName }</td>
						<td>${list.attendance }%</td>
						<td>
						<c:choose>
							<c:when test="${empty list.score and list.attendance eq 0}">
								<input type="text" name="score3" class="eduScore" value="-" readonly="readonly" size="1">
							</c:when>
							<c:when test="${empty list.score and list.attendance ne 0}">
								<input type="text" name="score" class="eduScore" value="-" readonly="readonly" size="1">
							</c:when>
							<c:otherwise>
								<input type="text" name="score2" class="eduScore" value="${list.score }" readonly="readonly" size="1">
							</c:otherwise>
						</c:choose>
						</td>
						<td class="eduHisno"><input type="text" name="eduHisno" value="${list.eduHisno }"></td>
					</tr>
				</c:forEach>
			<tbody>
		</table>
		<div>
			<input type="button" class="btn btn-light" value="점수입력" onclick="activeInput();">
			<input type="button" class="btn btn-primary" name="btnScore" value="점수반영" hidden="hidden" onclick="modifyScore();">
		</div>
		</form>
		<!-- 페이지네이션  -->
			<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<c:if test="${paging.hasPrev }">
					<c:choose>
						<c:when test="${empty pageInfo.keyField}">
							<li class="page-item"><a class="page-link" href="/edu/admin/history/${paging.startPage-1}">이전</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="/edu/admin/history/${paging.startPage-1}/${pageInfo.keyField }/${pageInfo.keyword}">이전</a></li>
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
									<li class="page-item"><a class="page-link" href="/edu/admin/history/${p}">${p}</a>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link" href="/edu/admin/history/${p}/${pageInfo.keyField }/${pageInfo.keyword}">${p}</a>
								</c:otherwise>
							</c:choose>
							
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${paging.hasNext }">
					<c:choose>
						<c:when test="${empty pageInfo.keyField}">
							<li class="page-item"><a class="page-link" href="/edu/admin/history/${paging.endPage+1}">다음</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="/edu/admin/history/${paging.endPage+1}/${pageInfo.keyField }/${pageInfo.keyword}">다음</a></li>
						</c:otherwise>
					</c:choose>
				</c:if>
			</ul>
			</nav>
	</div>
</div>

<!--**********************************
            Content body end
        ***********************************-->
<c:import url="/WEB-INF/views/include/footer.jsp" />