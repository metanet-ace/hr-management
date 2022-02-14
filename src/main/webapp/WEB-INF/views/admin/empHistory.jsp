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
				<div class="form-group row justify-content-start" style="margin-top: 4.2em;">
					<p>게시판 목록 갯수</p>
					<div class="w100" style="padding-right: 10px">
						<select id="listSize" class="form-control form-control-sm">
							<option value="10">10 개</option>
							<option value="15">15 개</option>
							<option value="20">20 개</option>
						</select>
					</div>
				</div>
			</div>
			<div class="col-lg-10 col-sm-11">
				<form class="form-inline d-flex justify-content-end" style="margin-top: 3.2em;"
					action="/admin/emp" method="get">
					<select name="field" id="field"
						class="form-control form-control-sm" onchange="changeForm();">
						<option value="empNo">사원번호로 검색</option>
						<option value="issuedDate" >변경일로 검색</option>
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
				<%-- 	<c:forEach items="${empHis.content }" var="list">
					<tr id="emplist">
						<td>${list.empNo }</td>
						<td>${list.posNo }</td>
						<td>${list.beforePos }</td>
						<td>${list.deptNo }</td>
						<td>${list.beforeDept }</td>
						<td>${list.issuedOrder}</td>
						<td>${list.issuedDate }</td>
					</tr>
				</c:forEach> --%>
			<tbody>
		</table>

		<!-- 페이지네이션  -->
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center" id="paginationBox">
				<%-- <c:choose>
					<c:when test="${empHis.first }">
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link" href="/admin/emp/history?page=0">처음</a></li>
						<li class="page-item"><a class="page-link" href="/admin/emp/history?page=${empHis.number-1}">이전</a></li>	
					</c:otherwise>
				</c:choose>
				
				<c:forEach begin="${startBlockPage }" end="${endBlockPage }" var="pageNum" >
					<c:choose>
						<c:when test="${pageNum == empHis.pageable.pageNumber+1 }">
							<li class="page-item active"><a class="page-link" href="/admin/emp/history?page=${pageNum-1}&field=${param.field}&word=${param.word}">${pageNum}</a>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="/admin/emp/history?page=${pageNum-1}&field=${param.field}&word=${param.word}">${pageNum}</a>
						</c:otherwise>		
					</c:choose>
				</c:forEach>

				<c:choose>
					<c:when test="${empHis.last ne true }">
						<li class="page-item"><a class="page-link" href="/admin/emp/history?page=${empHis.number+1}">다음</a></li>
						<li class="page-item"><a class="page-link" href="/admin/emp/history?page=${empHis.totalPages-1}">마지막</a></li>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose> --%>
			</ul>
		</nav>

		<%-- 

		<div class="row">
			<div class="col-12">
				<div class="card">
					<div class="card-header">
						<h4 class="card-title">Basic Datatable</h4>
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<table id="example" class="display" style="min-width: 845px">
								<thead>
									<tr>
										<th>이름</th>
										<th>직급</th>
										<th>부서</th>
										<th>이메일</th>
										<th>입사일</th>
										<th>연봉</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${emplist }" var="list">
										<tr>
											<td>${list.empName }</td>
											<td>${list.pos.posName }</td>
											<td>${list.dept.deptName }</td>
											<td>${list.empEmail }</td>
											<td>${list.empHiredate }</td>
											<td>${list.empSal }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div class="col-12">
				<div class="card">
					<div class="card-header">
						<h4 class="card-title">Datatable</h4>
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<table id="example2" class="display" style="width: 100%">
								<thead>
									<tr>
										<th>Name</th>
										<th>Position</th>
										<th>Office</th>
										<th>Age</th>
										<th>Start date</th>
										<th>Salary</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>Tiger Nixon</td>
										<td>System Architect</td>
										<td>Edinburgh</td>
										<td>61</td>
										<td>2011/04/25</td>
										<td>$320,800</td>
									</tr>
									<tr>
										<td>Garrett Winters</td>
										<td>Accountant</td>
										<td>Tokyo</td>
										<td>63</td>
										<td>2011/07/25</td>
										<td>$170,750</td>
									</tr>
									<tr>
										<td>Ashton Cox</td>
										<td>Junior Technical Author</td>
										<td>San Francisco</td>
										<td>66</td>
										<td>2009/01/12</td>
										<td>$86,000</td>
									</tr>
									<tr>
										<td>Michael Bruce</td>
										<td>Javascript Developer</td>
										<td>Singapore</td>
										<td>29</td>
										<td>2011/06/27</td>
										<td>$183,000</td>
									</tr>
									<tr>
										<td>Donna Snider</td>
										<td>Customer Support</td>
										<td>New York</td>
										<td>27</td>
										<td>2011/01/25</td>
										<td>$112,000</td>
									</tr>
								</tbody>
								<tfoot>
									<tr>
										<th>Name</th>
										<th>Position</th>
										<th>Office</th>
										<th>Age</th>
										<th>Start date</th>
										<th>Salary</th>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</div>
			</div>

		</div> --%>

	</div>
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
				data += "<td>" + list[i]['posNo'] + "</td>";
				data += "<td>" + list[i]['beforePos'] + "</td>";
				data += "<td>" + list[i]['deptNo'] + "</td>";
				data += "<td>" + list[i]['beforeDept'] + "</td>";
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

function changeForm(){
	
}
</script>
<c:import url="/WEB-INF/views/include/footer.jsp" />
