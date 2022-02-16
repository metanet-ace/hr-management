<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:import url="/WEB-INF/views/include/header.jsp" />
	<c:import url="/WEB-INF/views/include/sidebar.jsp" />
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
	
        <!--**********************************
            Content body start
        ***********************************-->
        <div class="content-body">
            <div class="container-fluid">
                <!-- row -->
                <div class="row">
                    <div class="col-12">
                        <div class="card1">
                            <div class="card-body1">
                                <div class="table-responsive1">
                                    <table class="table table-striped" border=1>
                                        <thead>
                                            <tr>
                                                <th>교육명</th>
                                                <th>교육인원</th>
                                                <th>교육대상</th>
                                                <th>교육시작일</th>
                                                <th>교육종료일</th>
                                                <th>진행상태</th>
                                                <th>교육배정</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                             <c:forEach items="${eduList }" var="list">
                                              <tr>
                                                <td onClick="location.href='./detail?edu_no=${list.eduNo }'">${list.eduTitle }</td>
                                                <td>${list.eduPeople }</td>
                                                <td>${list.eduTarget }</td>
                                                <td>${list.eduStart }</td>
                                                <td>${list.eduEnd }</td>
                                                <td>
                                                	<c:choose>
														<c:when test="${list.eduProgress eq 'pre'}">진행예정</c:when>
														<c:when test="${list.eduProgress eq 'ing'}">진행중</c:when>
														<c:otherwise>진행종료</c:otherwise>
													</c:choose>
                                                </td>
                                                <td><button type="button" class="btn btn-primary" onclick="location.href='./allocation2/${list.eduNo }'">교육배정</button></td>
                                            </tr>
                                       		 </c:forEach> 
                                        </tbody>
                                    </table>
                                </div>
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