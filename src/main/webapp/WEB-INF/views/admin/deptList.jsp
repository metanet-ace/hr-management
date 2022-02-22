<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:import url="/WEB-INF/views/include/header.jsp" />
	<c:import url="/WEB-INF/views/include/sidebar.jsp" />

<script type="text/javascript">
function deptWriteForm(){
	location.href="${ pageContext.servletContext.contextPath }/admin/emp/insertDeptPage"
}
</script>
	
        <!--**********************************
            Content body start
        ***********************************-->
        <div class="content-body">
            <div class="container-fluid">
            <div class="row page-titles mx-0">
         <div class="col-sm-6 p-md-0">
            <div class="welcome-text">
               <h4>부서 목록</h4>
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
                                                <th>부서명</th>
                                                <th>부서장</th>
                                                <th>사원번호</th>
                                                <th>부서 생성일</th>
                                                <th>부서 수정일</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                             <c:forEach items="${deptList }" var="list">
                                              <tr align="center">
                                              <c:url value="/admin/emp/deptDetail" var="detail">
                                              	<c:param name="deptNo" value="${list.deptNo }" />
                                              </c:url>
                                                <td><a href="${detail}">${list.deptName }</a></td>
                                                <td>${list.empName }</td>
                                                <td>${list.deptHead }</td>
                                                <td>${list.deptDate }</td>
                                                <td>
                                                <c:if test="${list.deptModify != null}">
                                                ${list.deptModify }
                                                </c:if>
                                                <c:if test="${list.deptModify == null}">
                                                ---
                                                </c:if>
                                                </td>
                                            </tr>
                                       		 </c:forEach> 
                                       		
                                        </tbody>
                                        
                                    </table>
                                     <button onclick="deptWriteForm();"  class="btn btn-primary">부서 등록하기</button>
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