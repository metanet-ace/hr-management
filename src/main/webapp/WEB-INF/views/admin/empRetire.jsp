<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/views/include/header.jsp" />
<c:import url="/WEB-INF/views/include/sidebar.jsp" />
<script>
$(document).ready(function() {
	getPage(1);
	
	$("#searchBtn1").on("click", function(e){
		e.preventDefault();
	
		const keyword = $("#empName").val();
		$("#searchEmp").val(keyword);
		getPage(1);
	})
	
	$("#searchBtn2").on("click", function(e){
		e.preventDefault();
	
		const begin = $("input[name=begin]").val();
		const end = $("input[name=end]").val();
		
		$("#searchStart").val(begin);
		$("#searchEnd").val(end);
		
		getPage(1);
	})
	
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
					style="margin-top: 3.2em; margin-bottom: 0.2em">
					<p style="margin-top: 1.3em;">게시판 목록 갯수</p>
					<div class="w100" style="padding-right: 10px; margin-top: 1.3em;">
						<select id="listSize" class="form-control form-control-sm"
							onchange="getPage(1)">
							<option value="5">5 개</option>
							<option value="15">10 개</option>
							<option value="20">20 개</option>
						</select>
					</div>
				</div>
			</div>
			<div class="col-lg-10 col-sm-11">
				<form class="form-inline d-flex justify-content-end"
					style="margin-top: 3.2em;" action="" method="get">
					<select name="field" id="field" style=" margin-top: 2em"
						class="form-control form-control-sm" onchange="changeForm(this);">
						<option value="empName">사원이름로 검색</option>
						<option value="issuedDate">퇴사일로 검색</option>
					</select> 
					
					<div id="empNameDiv" >
					<input type="text" id="empName" name="empName" value=""
						class="form-control form-cotrol-sm" style="margin-top: 2em">
					<input type="submit" class="btn btn-outline-info btn-sm" id="searchBtn1" style=" margin-top: 2em" value="검색">
					</div>	
					
					<div id="issuedDateDiv"  style="display:none; margin-top: 2em">
					 <input class="form-control form-cotrol-sm" type="date" name="begin"> ~ <input class="form-control form-cotrol-sm" type="date" name="end">
						
					<input type="submit" class="btn btn-outline-info btn-sm" id="searchBtn2" value="검색">
					</div>	
								
				</form>
				
				<!-- 검색어를 저장하기 위한 input -->
				<input type="hidden" id="searchEmp" value="" />
				<input type="hidden" id="searchStart" value="" />
				<input type="hidden" id="searchEnd" value="" />
			</div>
		</div>
		
		<br>
		<table class="table table-striped" border=1>
			<thead>
				<tr align='center'>
					<th>사원이름</th>
					<th>부서</th>
					<th>퇴사일</th>
					<th>퇴사사유</th>
					<th>출력</th>
					<th>취소</th>
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
	
	<!-- Modal -->
	<div class="modal" id="reasonModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">변동 상세 사유</h5>
				</div>
				<div class="modal-body">...</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" onClick='closeBtn();'
						data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</div>

<!--**********************************
            Content body end
        ***********************************-->
<script>
function reasonModal(reason){
	$(".modal-body").html(reason);
	$("#reasonModal").modal("show");
}

function closeBtn(){
	$("#reasonModal").modal("hide");
}

function getPage(pageNum){
	// 페이지 사이즈 정보 
	var pageSize = $("#listSize option:selected").val();
	
	// 검색 필드
	var field = $("#field").val();
	
	// 검색타입
	var empName = $("#searchEmp").val();
	var start = $("#searchStart").val();
	var end = $("#searchEnd").val();
	
	// 날짜 검색
	if ( field == 'issuedDate'){
		if(start == '' || end == ''){
			alert('시작날짜와 종료날짜를 모두 입력해주세요');
			return;
		}
		var data = {pageSize: pageSize, 
					startDate: start,
					endDate: end};
	} else {
		var data = {pageSize: pageSize, 
					empName: empName};
	}
	
	$.ajax({
		url: "/admin/emp/retire/" + pageNum,
		data: JSON.stringify(data),
		type: "POST",
		contentType : 'application/json',
		success: function(result){
			const list = result['content'];
			console.log(list);
			var data = "";
			var block = "";
			
			// 테이블 정보
			
			if(list == ''){
				data += "<td colspan='6' align='center'>검색 결과가 없습니다.</td>";
			} else {
			for(var i = 0; i < list.length; i++) {
				data += "<tr id='empRetireList'>";
				data += "<td align='center'>" + list[i][0]['empName'] + "</td>";
				data += "<td align='center'>" + list[i][0]['dept']['deptName'] + "</td>"
				data += "<td align='center'>" + list[i][0]['empRetdate'] + "</td>";
				data += "<td align='center'>"  + list[i][1] + "</td>";
				data += "<td width= '20%' align='center'><button class='btn btn-primary' href='#' onclick=download(" + list[i][0]['empNo'] + ");>경력증명서 출력</td>";
				data += "<td width='10%' align='center'><button class='btn btn-primary' href='#' onclick=reasonModal('" + "취소" + "');>취소</td>";
			}
			
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

// PDF 다운로드 API 호출
function download(empNo){
	
	$.ajax({
		url: "/admin/pdf?empNo=" + empNo,
		type: "GET",
		contentType : 'application/json',
		success: function(result){
			window.open("/admin/pdf?empNo=" + empNo);
		}
	});
}

function changeForm(item){
	if($(item).val() == "empName"){
		$("#empNameDiv").css("display", "inline-block");
		$("#issuedDateDiv").css("display", "none");
	}
	
	if($(item).val() == "issuedDate"){
		$("#empNameDiv").css("display", "none");
		$("#issuedDateDiv").css("display", "inline-block");
	}
}

</script>
<c:import url="/WEB-INF/views/include/footer.jsp" />
