<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/views/include/header.jsp" />
<c:import url="/WEB-INF/views/include/sidebar.jsp" />
<script>
	$(document).ready(function(){

		// 사원 인사이동 처리 버튼 클릭시
		$("#updateHumanResource").on("click", function(e){
			
			 if(confirm("정말로 변경하시겠습니까?")==true){
	e.preventDefault();	
			
			var array = [];
			
			$('input:checkbox[name="checkbox"]').each(function(){
				if($(this).is(":checked")){
					array.push(this.value);
				}
			});	
			
			if(array.length === 0){
				alert("선택된 인원이 없습니다.");
				return;
			}
			
			var deptData = $("#changeDept").val();
			var posData = $("#changePos").val();
			
			if(deptData === "" && posData === ""){
				alert("변경사항이 없습니다.");
				return;
			}
			
			var data = {targetEmps: array, deptData: deptData, posData: posData};
			
			$.ajax({
				url: '/admin/hr',
				data: JSON.stringify(data),
				contentType: 'application/json',
				type: 'POST',
				dataType: 'text',
				success: function(result){
					console.log(result);
					alert("성공적으로 처리되었습니다.");
					window.location.reload();
				},
				error: function(error){
					console.log(error);
				}
			});	
			}else{
				
			} 
		});
	});
</script>

<!--**********************************
            Content body start
        ***********************************-->
<div class="content-body">
	<div class="container-fluid">
		<!-- 사원 리스트 출력 -->
		<form class="form-inline d-flex justify-content-end" action="/admin/emp" method="get">
			 <select name="field" id="field" class="form-control form-control-sm">
				<option value="deptName">부서</option>
				<option value="posName">직급</option>
				<option value="empName">이름</option>
			</select> 
			<input type="text" id="word" name="word" value="" class="form-control form-cotrol-sm" style="margin: 10px">
		    <input type="submit" class="btn btn-outline-info btn-sm" value="검색">
		</form>
		<br>
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
				<c:forEach items="${emplist.content }" var="list">
					<tr id="emplist">
					<c:url value="/admin/emp/detail" var="detail" >
						<c:param name="empNo" value="${list.empNo }" />
					</c:url>
						<td id="empNo"><input type="checkbox" name="checkbox" value="${list.empNo }"></td>
						<td><a href="${detail}">${list.empNo }</a></td>
						<td><a href="${detail}">${list.empName }</a></td>
						<td><a href="${detail}">${list.pos.posName }</a></td>
						<td><a href="${detail}">${list.dept.deptName }</a></td>
					</tr>
				</c:forEach>
			<tbody>
		</table>

		<!-- 페이지네이션  -->
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<c:choose>
					<c:when test="${emplist.first }">
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link" href="/admin/emp?page=0">처음</a></li>
						<li class="page-item"><a class="page-link" href="/admin/emp?page=${emplist.number-1}">이전</a></li>	
					</c:otherwise>
				</c:choose>
				
				<c:forEach begin="${startBlockPage }" end="${endBlockPage }" var="pageNum" >
					<c:choose>
						<c:when test="${pageNum == emplist.pageable.pageNumber+1 }">
							<li class="page-item active"><a class="page-link" href="/admin/emp?page=${pageNum-1}&field=${param.field}&word=${param.word}">${pageNum}</a>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="/admin/emp?page=${pageNum-1}&field=${param.field}&word=${param.word}">${pageNum}</a>
						</c:otherwise>		
					</c:choose>
				</c:forEach>

				<c:choose>
					<c:when test="${emplist.last ne true }">
						<li class="page-item"><a class="page-link" href="/admin/emp?page=${emplist.number+1}">다음</a></li>
						<li class="page-item"><a class="page-link" href="/admin/emp?page=${emplist.totalPages-1}">마지막</a></li>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>

		<!-- 발령 처리 -->
		<div class="card text-center bg-dark text-white">
			<div class="card-header">발령처리</div>
			<div class="card-body">
				<h1 class="text-white">선택한 인원 발령처리</h1>
				<br>
				<br>
				<br>
				<form class="form-inline justify-content-right" action="/admin/emp"
					method="post">
					<label class="col-lg-3 col-form-label" for="changeDept">부서<span
						class="text-danger">*</span>
					</label>
					<div class="col-lg-3">
						<select class="form-control" id="changeDept" name="changeDept">
							<option value="">변경사항 없음</option>									
							<option value=1>인사팀</option>
							<option value=2>마케팅팀</option>
							<option value=3>경영팀</option>
							<option value=4>개발팀</option>
							<option value=5>기획팀</option>
							<option value=6>법무팀</option>
						</select>
					</div>
					<label class="col-lg-3 col-form-label" for="changePos">직급 <span
						class="text-danger">*</span>
					</label>
					<div class="col-lg-3">
						<select class="form-control" id="changePos" name="changePos">
							<option value="">변경사항 없음</option>
							<option value=1>사원</option>
							<option value=2>대리</option>
							<option value=3>과장</option>
							<option value=4>차장</option>
							<option value=5>부장</option>
							<option value=6>사장</option>
						</select>
					</div>
					<div class="col-lg-4"></div>
						<div class="col-lg-4">
						<input type="submit" class="btn btn-outline-info btn-xl" id="updateHumanResource" style="margin-top:30px;" value="확인">
					</div>	
					<div class="col-lg-4"></div>
				</form>
			</div>
		</div>

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
<c:import url="/WEB-INF/views/include/footer.jsp" />