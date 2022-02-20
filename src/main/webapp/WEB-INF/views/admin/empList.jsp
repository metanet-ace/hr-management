<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/views/include/header.jsp" />
<c:import url="/WEB-INF/views/include/sidebar.jsp" />
<style>
textarea {
	width: 100%;
	height: 4.25em;
	border: none;
	resize: none;
}
</style>
<script>

	$(document).ready(function() {

		// 사원 인사이동 처리 버튼 클릭시
		$("#updateHumanResource").on("click", function(e) {
			e.preventDefault();

			if (confirm("정말로 변경하시겠습니까?") == true) {

				var array = [];

				$('input:checkbox[name="checkbox"]').each(function() {
					if ($(this).is(":checked")) {
						array.push(this.value);
					}
				});

				if (array.length === 0) {
					alert("선택된 인원이 없습니다.");
					return;
				}

				var deptData = $("#changeDept").val();
				var posData = $("#changePos").val();
				var reason = $("#reason").val();

				if (deptData === "" && posData === "") {
					alert("변경사항이 없습니다.");
					return;
				}

				var data = {
					targetEmps : array,
					deptData : deptData,
					posData : posData,
					reason : reason
				};

				$.ajax({
					url : '/admin/hr',
					data : JSON.stringify(data),
					contentType : 'application/json',
					type : 'POST',
					dataType : 'text',
					success : function(result) {
						console.log(result);
						alert("성공적으로 처리되었습니다.");
						window.location.reload();
					},
					error : function(error) {
						console.log(error);
					}
				});
			} else {

			}
		});
		
		// 사원 퇴사 처리
		$("#updateRetire").on("click", function(e){
			e.preventDefault();
			
			if (confirm("정말로 변경하시겠습니까?") == true) {

				var array = [];

				$('input:checkbox[name="checkbox"]').each(function() {
					if ($(this).is(":checked")) {
						array.push(this.value);
					}
				});

				if (array.length === 0) {
					alert("선택된 인원이 없습니다.");
					return;
				}

				var retireReason = $("#retireReason").val();
		
				if (retireReason === "") {
					alert("사유를 입력해주세요.");
					return;
				}

				var data = {
					targetEmps : array,
					retireReason : retireReason
				};
				console.log(data.retireReason);
				$.ajax({
					url : '/admin/retire',
					data : JSON.stringify(data),
					contentType : 'application/json',
					type : 'POST',
					dataType : 'text',
					success : function(result) {
						console.log(result);
						alert("성공적으로 처리되었습니다.");
						window.location.reload();
					},
					error : function(error) {
						console.log(error);
					}
				});
			} else {

			}
			
		})
		
		
		// 체크박스 한번에 선택
		$("#selectAll").on("click", function(e){
			 
			if($('input[name="checkbox"]').is(":checked")){
				$('input:checkbox[name="checkbox"]').each(function() {
					this.checked = false;
				});
			} else {
				$('input:checkbox[name="checkbox"]').each(function() {
					this.checked = true;
				});
			}
		})
		
		var field = '${field}';
		
		// 이전 검색키워드 받아오기
		if(field != ''){
			console.log(111);
			$("#field").val("${field}").attr("selected","selected");
		} else {
			$("#field").val(deptName).attr("selected","selected");
		}
		
	});
</script>

<!--**********************************
            Content body start
        ***********************************-->
<div class="content-body">
	<div class="container-fluid">
		<!-- 사원 리스트 출력 -->
		<div class="row" >
			<div class="col-lg-2">
				<button class="btn btn-primary" style="margin-top: 0.5em;" id="selectAll">전체선택</button>
			</div>
			<div class="col-lg-10">
				<form class="form-inline d-flex justify-content-end"
					action="/admin/emp" method="get">
					<select name="field" id="field"
						class="form-control form-control-sm">
						<option value="deptName">부서</option>
						<option value="posName">직급</option>
						<option value="empName">이름</option>
					</select> <input type="text" id="word" name="word" value=""
						class="form-control form-cotrol-sm" style="margin: 10px">
					<input type="submit" class="btn btn-outline-info btn-sm" value="검색">
				</form>
			</div>
		</div>
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
			<c:choose>
				<c:when test="${empty emplist.content}" >
					<td colspan='5' align='center'>검색 결과가 없습니다.</td>
				</c:when>	
				<c:otherwise>
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
				</c:otherwise>
			</c:choose>	
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
						<li class="page-item"><a class="page-link" href="/admin/emp?page=${emplist.totalPages-1}">끝</a></li>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>

		<!-- 발령 처리 -->
		<div class="row">
			<div class="card text-center bg-dark text-white col-lg-8">
				<div class="card-header">발령처리</div>
				<div class="card-body">
					<h1 class="text-white">선택한 인원 발령처리</h1>
					<br> <br> <br>
					<form class="form-inline justify-content-right" action="/admin/emp"
						method="post">
						<label class="col-lg-3 col-form-label" for="changeDept">부서<span
							class="text-danger">*</span>
						</label>
						<div class="col-lg-3">
							<select class="form-control" id="changeDept" name="changeDept">
								<option value="">변경사항 없음</option>
								<c:forEach items="${deptList }" var="list">
									<option value="${list.deptNo }">${list.deptName }</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-lg-3 col-form-label" for="changePos">직급
							<span class="text-danger">*</span>
						</label>
						<div class="col-lg-3">
							<select class="form-control" id="changePos" name="changePos">
								<option value="">변경사항 없음</option>
								<c:forEach items="${posList }" var="list">
									<option value="${list.posNo }">${list.posName }</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-lg-3 col-form-label" for="reason">사유<span
							class="text-danger">*</span></label>
						<div class="col-lg-9" style="margin-top: 2em;">
							<textarea rows="1" cols="100" id="reason" name="reason"></textarea>
						</div>
						<div class="col-lg-4"></div>
						<div class="col-lg-4">
							<input type="submit" class="btn btn-outline-info btn-xl"
								id="updateHumanResource" style="margin-top: 30px;" value="확인">
						</div>
						<div class="col-lg-4"></div>
					</form>
				</div>
			</div>

			<!--퇴사 처리 -->
			<div class="card text-center bg-danger text-white col-lg-4">
				<div class="card-header">퇴사자 등록</div>
				<div class="card-body">
					<h1 class="text-white">선택한 인원 퇴사처리</h1>
					<br> <br>
					<form class="form-inline justify-content-right" action="/admin/emp"
						method="post">
						<label class=" col-lg-2 col-form-label" for="retireReason">사유<span
							class="text-white">*</span></label>
						<div class="col-lg-10" >
							<textarea rows="3" cols="200" id="retireReason" name="retireReason"></textarea>
						</div>
						<div class="col-lg-4"></div>
						<div class="col-lg-4">
							<input type="submit" class="btn btn-outline-info btn-xl"
								id="updateRetire" style="margin-top: 5em;" value="확인">
						</div>
						<div class="col-lg-4"></div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<!--**********************************
            Content body end
        ***********************************-->
<c:import url="/WEB-INF/views/include/footer.jsp" />