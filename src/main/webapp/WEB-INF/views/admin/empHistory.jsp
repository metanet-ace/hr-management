<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/views/include/header.jsp" />
<c:import url="/WEB-INF/views/include/sidebar.jsp" />
<script>
$(document).ready(function() {
	getPage(1);
});
</script>

<!--**********************************
            Content body start
        ***********************************-->
<div class="content-body">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-2 col-sm-1">
				<div class="form-group row justify-content-start"
					style="margin-top: 4.2em;">
					<p>게시판 목록 갯수</p>
					<div class="w100" style="padding-right: 10px">
						<select id="listSize" class="form-control form-control-sm">
							<option value="10">5 개</option>
							<option value="15">10 개</option>
							<option value="20">20 개</option>
						</select>
					</div>
				</div>
			</div>
			<div class="col-lg-10 col-sm-11">
				<form class="form-inline d-flex justify-content-end"
					style="margin-top: 3.2em;" action="/admin/emp" method="get">
					<select name="field" id="field"
						class="form-control form-control-sm" onchange="changeForm();">
						<option value="empNo">사원번호로 검색</option>
						<option value="issuedDate">변경일로 검색</option>
					</select> <input type="text" id="word" name="word" value=""
						class="form-control form-cotrol-sm" style="margin: 10px">
					<input type="submit" class="btn btn-outline-info btn-sm" value="검색">
				</form>
			</div>
		</div>
	</div>
	<br>
	<table class="table table-striped" border=1>
		<thead>
			<tr>
				<th>사원번호</th>
				<th>변경후직급</th>
				<th>변경전직급</th>
				<th>변경후부서</th>
				<th>변경전부서</th>
				<th>비고</th>
				<th>변경일</th>
			</tr>
		</thead>
		<tbody id="dataSection">
		<tbody>
	</table>

	<!-- 페이지네이션  -->
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center" id="paginationBox">





		</ul>
	</nav>
</div>

<!--**********************************
            Content body end
        ***********************************-->
<script>

function getPage(pageNum){
	
	$.ajax({
		url: "/admin/emp/history/" + pageNum,
		data: {},
		success: function(result){
			const list = result['content'];
			
			var data = "";
			var block = "";
			
			// 테이블 정보
			for(var i = 0; i < list.length; i++) {
				data += "<tr id='empHistoryList'>";
				data += "<td>" + list[i]['empNo'] + "</td>";
				data += "<td>" + posView(list[i]['posNo']) + "</td>";
				data += "<td>" + posView(list[i]['beforePos']) + "</td>";
				data += "<td>" + deptView(list[i]['deptNo']) + "</td>";
				data += "<td>" + deptView(list[i]['beforeDept']) + "</td>";
				data += "<td>" + list[i]['issuedOrder'] + "</td>";
				data += "<td>" + list[i]['issuedDate'] + "</td>";
			}
			
			// 이전 버튼 활성화
			if(!result['first']){
				block += "<li class='page-item'><a class='page-link' href='javascript:getPage(" + 1 + ")'>처음</a></li>";
				block += "<li class='page-item'><a class='page-link' href='javascript:getPage(" + result['number'] + ")'>이전</a></li>";	
			}else{
				
			}
			
			// 한 블록에 몇 페이지까지 보여줄지
			var blockSize = 5;
			var startBlockPage = ((Math.floor((result['pageable'].pageNumber / blockSize)) * blockSize) + 1);
			var endBlockPage = startBlockPage + blockSize - 1;
			endBlockPage = endBlockPage > result['totalPages'] ? result['totalPages'] : endBlockPage;
			console.log("시작블록" + startBlockPage);
			console.log("끝블록" + endBlockPage);
			console.log("현재페이지" + result['number']);
			// 번호 표시 
			for(var i = startBlockPage; i <= endBlockPage; i++){
				if(pageNum !== i) {
					block += "<li class='page-item ' ><a class='page-link' href='javascript:getPage(" + i + ")'>" + i + "</a></li>";
				} else {
					block += "<li class='page-item active'><a class='page-link'>" +  i  + "</a></li>";
				}
			}
	
			// 다음 버튼 활성화 
			if(!result['last']){
				block += "<li class='page-item'><a class='page-link' href='javascript:getPage(" + (result['number']+2) + ")'>다음</a></li>";
				block += "<li class='page-item'><a class='page-link' href='javascript:getPage(" + (result['totalPages']) + ")'>끝</a></li>";
			} else{
				
			}
			
			$("#dataSection").html(data);
			$("#paginationBox").html(block);
		},
		error: function(err){
			alert(err);
		}
	})
}

function deptView(no){
	switch(no){
	case 1:
		return "인사팀"
	case 2:
		return "마케팅팀"
	case 3: 
		return "경영팀"
	case 4:
		return "개발팀"
	case 5:
		return "기획팀"
	case 6:
		return "법무팀"
	}
}

function posView(no){
	switch(no){
	case 1:
		return "사원"
	case 2:
		return "대리"
	case 3: 
		return "과장"
	case 4:
		return "차장"
	case 5:
		return "부장"
	case 6:
		return "사장"
	}
}

function changeForm(){
	
}
</script>
<c:import url="/WEB-INF/views/include/footer.jsp" />
