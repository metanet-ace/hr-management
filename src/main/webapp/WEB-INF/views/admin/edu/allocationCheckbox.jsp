<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/views/include/header.jsp" />
<c:import url="/WEB-INF/views/include/sidebar.jsp" />
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
h1 {
	text-align: center;
}

table {
	width: 80%;
	height: 100px;
	text-align: center;
	margin: auto;
}

table th {
	height: 10px;
}

form {
	text-align: center;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function ajaxExample(){
    
    // name이 같은 체크박스의 값들을 배열에 담는다.
    var checkboxValues = [];
    $("input[name='empNo']:checked").each(function(i) {
        checkboxValues.push($(this).val());
    });
     
    // 사용자 ID(문자열)와 체크박스 값들(배열)을 name/value 형태로 담는다.
    var allData = { "userId": userId, "checkArray": checkboxValues };
     
    $.ajax({
        url:"edu/allocation2",
        type:'POST',
        data: checkBoxValues,


      //데이터 전송이 완료되면 출력되는 메시지

        success:function(data){
            alert("완료!");
            window.opener.location.reload();
            self.close();
        },

       //에러가 발생되면 출력되는 메시지

        error:function(jqXHR, textStatus, errorThrown){
            alert("에러 발생~~ \n" + textStatus + " : " + errorThrown);
            self.close();
        }
    });
}
</script>
</head>
<body>

	<h1>교육 인원 할당</h1>
	<hr>
	<div class="content-body">
		<div class="container-fluid">

			<form id="search_form" action="/mysite/board" method="post">
				<input type="hidden" name="a" value="list"> <select
					name="keyField" size="1">
					<option value="deptName">부서</option>
					<option value="posName">직급</option>
				</select> <input type="text" id="kwd" name="keyWord" value=""> <input
					type="submit" value="찾기">
			</form>
			<br>
			<form action="/edu/allocation2" method="post">
				<div style='width: 80px; float: right;'>
					<input type='submit' class='btn' name='btn' value='교육배정'>
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
								<td><input type="checkbox" name="cox{"
									value="${list.empNo }"></td>
								<td>${list.empNo }</td>
								<td>${list.empName }</td>
								<td>${list.posName }</td>
								<td>${list.deptName }</td>
							</tr>
						</c:forEach>
					<tbody>
				</table>
			</form>
		</div>
	</div>
	<c:import url="/WEB-INF/views/include/footer.jsp" />
</body>
</html>