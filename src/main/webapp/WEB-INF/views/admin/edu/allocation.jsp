<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/views/include/header.jsp" />
<c:import url="/WEB-INF/views/include/sidebar.jsp" />
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
h1 {text-align: center;}

table {
width: 80%;
height: 100px;
text-align: center;
margin: auto;
}

table th {height: 10px;}

form {text-align: center;}

li{
float: left;
margin: 20px;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>교육 인원 할당</h1>
<hr>
<div class="content-body">
		<div class="container-fluid">

<form id="search_form" action="./allocation" method="post">
					<select name="keyword" size="1">
						<option value="deptName">부서</option>
						<option value="posName">직급</option>
					</select> <input type="text" id="kwd" name="searchContent" value=""> <input
						type="submit" value="찾기">
				</form>
				<br>
				<div style='width:80px;float: right;'>
					<input type='button' class='btn' name='btn' value='발령처리'>
					</div>
				<table border=1>
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
					<c:forEach items="${list }" var="list">
						<tr>
							<td><input type="checkbox" name="cox{" value="${list.empNo }"></td>
							<td>${list.empNo }</td>
							<td>${list.empName }</td>
                           	<td>${list.posName }</td>
                            <td>${list.deptName }</td>
						</tr>
					</c:forEach>
					<tbody>
				</table>
				<br>
				<div class="pager">
               <ul>
                  <c:if test="${paging.hasPrev }">
                     <li><a
                        href="./allocation?pageNum=${paging.startPage-1}&keyword=${pageInfo.keyword }&searchContent=${pageInfo.searchContent}">◀</a></li>
                  </c:if>

                  <c:forEach var="p" begin="${paging.startPage }"
                     end="${paging.endPage }" step="1">
                     <li><a
                        ${p == tempPageNum ? "style='color:red;' selected" : "" }
                        href="./allocation?pageNum=${p}&keyword=${pageInfo.keyword }&searchContent=${pageInfo.searchContent}">${p}</a>
                     </li>
                  </c:forEach>

                  <c:if test="${paging.hasNext }">
                     <li><a
                        href="./allocation?pageNum=${paging.endPage+1}
                     &keyword=${pageInfo.keyword }
                     &searchContent=${pageInfo.searchContent}">▶</a></li>
                  </c:if>
               </ul>
            </div>
				</div>
				</div>
<c:import url="/WEB-INF/views/include/footer.jsp" />
</body>
</html>