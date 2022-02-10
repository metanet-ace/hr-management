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
		<!-- 사원 리스트 출력 -->
		<form id="search_form" action="/admin/emp" method="post">
			<input type="hidden" name="a" value="list"> <select
				name="keyField" size="1">
				<option value="deptName">부서</option>
				<option value="posName">직급</option>
			</select> <input type="text" id="kwd" name="keyWord" value=""> <input
				type="submit" value="찾기">
		</form>
		<br>
		<div>
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
				<c:forEach items="${emplist.content }" var="list">
					<tr>
					<c:url value="/admin/emp/detail" var="detail" >
						<c:param name="empNo" value="${list.empNo }" />
					</c:url>
						<td><input type="checkbox" name="checkbox" value="${list.empNo }"></td>
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
							<li class="page-item active"><a class="page-link" href="/admin/emp?page=${pageNum-1}">${pageNum}</a>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="/admin/emp?page=${pageNum-1}">${pageNum}</a>
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