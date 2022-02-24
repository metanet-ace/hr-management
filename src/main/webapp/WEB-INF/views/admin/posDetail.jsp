<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/views/include/header.jsp" />
<c:import url="/WEB-INF/views/include/sidebar.jsp" />
<script type="text/javascript">
function update(no){
	if(confirm("직급 수정 페이지로 이동하시겠습니까?")){
		location.href="/admin/emp/updatePosPage?posNo="+no;
	}
}
function del(no){
	if(confirm("해당 직급을 삭제하시겠습니까?")){
		location.href="/admin/emp/deletePos?posNo="+no;
	}
}

$(document).ready(function(){
	$("#keyField").val("${keyField}").attr("selected","selected");
})
</script>


<!--**********************************
            Content body start
        ***********************************-->
<div class="content-body">
	<div class="container-fluid">
	<div class="row page-titles mx-0">
         <div class="col-sm-6 p-md-0">
            <div class="welcome-text">
               <h4>${pos.posName }</h4>
            </div>
         </div>
      </div>
		<!-- row -->
		<div class="row">
			<div class="col-12">
				<div class="card1">
					<div class="card-body1">
						<div class="table-responsive1">
							<table class="table table-striped" border=1>

								<thead>
									<tr align="center">
										<th>직급</th>
										<th>최소연봉</th>
										<th>최대연봉</th>
									</tr>
								</thead>
								<tbody>
									<tr align="center">
										<td>${pos.posName }</td>
										<td>${pos.posMinsal }만 원</td>
										<td>${pos.posMaxsal }만 원</td>
									</tr>

								</tbody>

							</table>

							<br>
							<form id="search_form" action="/admin/emp/posDetail" method="post"
								class="form-inline d-flex justify-content-end">
								<input type="hidden" name="posNo" value="${pos.posNo }">
								<select name="keyField" id="keyField" size="1"
									class="form-control form-control-sm">
									<option value="">=====</option>
									<option value="empNo">사원번호</option>
									<option value="empName">이름</option>
								</select> <input type="text" id="kwd" name="keyword"
									value="${pageInfo.keyword}" class="form-control form-cotrol-sm"
									style="margin: 10px"> <input type="submit"
									class="btn btn-outline-info btn-sm" value="찾기">
							</form>
							<table class="table table-striped" border=1>
								<thead>
									<tr align="center">
										<th>사원</th>
										<th>사원번호</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${list }" var="list">
										<tr align="center">
											<c:if test="${list.empRetdate == null }">
												<td>${list.empName }</td>
												<td>${list.empNo }</td>
											</c:if>
										</tr>
									</c:forEach>

								</tbody>

							</table>
							<button type="button" onClick="update(${pos.posNo});"
								class="btn btn-primary">직급 수정</button>
							<button type="button" onClick="del(${pos.posNo});"
								class="btn btn-danger">직급 삭제</button>
						</div>
<!-- 페이지네이션  -->
			<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<c:if test="${paging.hasPrev }">
					<li class="page-item"><a class="page-link" href="/admin/emp/posDetail?posNo=${pos.posNo}&pageNum=${paging.startPage-1}&keyField=${pageInfo.keyField }&keyword=${pageInfo.keyword}">이전</a></li>
				</c:if>
				
				<c:forEach var="p" begin="${paging.startPage }" end="${paging.endPage }" step="1">
					<c:choose>
						<c:when test="${p == tempPageNum }" >
							<li class="page-item active"><a class="page-link" href="#">${p}</a>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${empty pageInfo.keyField}">
									<li class="page-item"><a class="page-link" href="/admin/emp/posDetail?posNo=${pos.posNo}&pageNum=${p}">${p}</a>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link" href="/admin/emp/posDetail?posNo=${pos.posNo}&pageNum=${p}&keyField=${pageInfo.keyField }&keyword=${pageInfo.keyword}">${p}</a>
								</c:otherwise>
							</c:choose>
							
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${paging.hasNext }">
					<li class="page-item"><a class="page-link" href="/admin/emp/posDetail?posNo=${pos.posNo}&pageNum=${paging.endPage+1}&keyField=${pageInfo.keyField }&keyword=${pageInfo.keyword}">다음</a></li>
				</c:if>
			</ul>
			</nav>
					</div>

				</div>

			</div>
		</div>
	</div>
</div>
<!--**********************************
            Content body end
        ***********************************-->

<c:import url="/WEB-INF/views/include/footer.jsp" />
