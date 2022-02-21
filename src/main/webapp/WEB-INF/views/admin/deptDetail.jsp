<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:import url="/WEB-INF/views/include/header.jsp" />
	<c:import url="/WEB-INF/views/include/sidebar.jsp" />
	

<script type="text/javascript">
function update(no){
	if(confirm("부서 수정 페이지로 이동하시겠습니까?")){
		location.href="/admin/emp/updateDeptPage?deptNo="+no;
	}
}

function del(no){
	if(confirm("해당 부서를 삭제하시겠습니까?")){
		location.href="/admin/emp/deleteDept?deptNo="+no;
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
                                
                                	<h2>${dept.deptName}</h2>
                                        <thead>
                                            <tr align="center">
                                                <th>부서장</th>
                                                <th>부서장 사원번호</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                              <tr align="center">
                                                <td>${dept.empName }</td>
                                          
                                                <td>${dept.deptHead }</td>
                                            </tr>
                                       		
                                        </tbody>
                                        
                                    </table>
                                    
                                    <br>
                                    <table class="table table-striped" border=1>
                                        <thead>
                                            <tr align="center">
                                                <th>부서원</th>
                                                <th>사원번호</th>
                                                <th>부서 생성일</th>
                                                <th>부서 수정일</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                             <c:forEach items="${deptDetail }" var="detail">
                                              <tr align="center">
                                              <c:if test="${detail.deptHead != detail.empNo }">
                                              <c:if test="${detail.empRetdate == null }">
                                                <td>${detail.empName }</td>
                                                <td>${detail.empNo }</td>
                                                <td>${detail.deptDate }</td>
                                                <td>
                                                <c:if test="${detail.deptModify != null}">
                                                ${detail.deptModify }
                                                </c:if>
                                                <c:if test="${detail.deptModify == null}">
                                                ---
                                                </c:if>
                                                </td>
                                                </c:if>
                                                </c:if>
                                            </tr>
                                       		 </c:forEach> 
                                       		
                                        </tbody>
                                        
                                    </table>
                                     <button type="button" onClick="update(${dept.deptNo});" class="btn btn-primary">부서 수정</button>
                                     <button type="button" onClick="del(${dept.deptNo});" class="btn btn-danger">부서 삭제</button>
                                     
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