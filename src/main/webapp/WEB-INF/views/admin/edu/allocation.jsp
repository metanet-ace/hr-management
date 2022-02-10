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

</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>교육 인원 할당</h1>
<hr>
<div class="content-body">
		<div class="container-fluid">

<form id="search_form" action="/mysite/board" method="post">
					<input type="hidden" name="a" value="list"> 
					<select name="keyField" size="1">
						<option value="deptName">부서</option>
						<option value="posName">직급</option>
					</select> <input type="text" id="kwd" name="keyWord" value=""> <input
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
					<c:forEach items="${empList }" var="list">
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
				</div>
				</div>
<c:import url="/WEB-INF/views/include/footer.jsp" />
</body>
</html>