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
	console.log("1234567892108765432")

	if(confirm("해당 직급을 삭제하시겠습니까?")){
		location.href="/admin/emp/deletePos?posNo="+no;
	}
}

</script>	


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
                                
                                	<h2>${pos.posName}</h2>
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
                                          		<td>${pos.posMinsal } 만 원</td>
                                                <td>${pos.posMaxsal } 만 원</td>
                                            </tr>
                                       		
                                        </tbody>
                                        
                                    </table>
                                    
                                    <br>
                                    <table class="table table-striped" border=1>
                                        <thead>
                                            <tr align="center">
                                                <th>사원</th>
                                                <th>사원번호</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                             <c:forEach items="${posDetail }" var="detail">
                                              <tr align="center">
                                              <c:if test="${detail.empRetdate == null }">
                                                <td>${detail.empName }</td>
                                                <td>${detail.empNo }</td>
                                                </c:if>
                                            </tr>
                                       		 </c:forEach> 
                                       		
                                        </tbody>
                                        
                                    </table>
                                     <button type="button" onClick="update(${pos.posNo});" class="btn btn-primary">직급 수정</button>
                                     <button type="button" onClick="del(${pos.posNo});" class="btn btn-danger">직급 삭제</button>
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
