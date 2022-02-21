<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:import url="/WEB-INF/views/include/header.jsp" />
	<c:import url="/WEB-INF/views/include/sidebar.jsp" />

<script type="text/javascript">
function posWriteForm(){
	location.href="${ pageContext.servletContext.contextPath }/admin/emp/insertPosPage"
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
                                        <thead>
                                            <tr align="center">
                                                <th>직급명</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                             <c:forEach items="${posList }" var="list">
                                              <tr align="center">
                                              <c:url value="/admin/emp/posDetail" var="detail">
                                              	<c:param name="posNo" value="${list.posNo }" />
                                              </c:url>
                                                <td><a href="${detail}">${list.posName }</a></td>
                                            </tr>
                                       		 </c:forEach> 
                                       		
                                        </tbody>
                                        
                                    </table>
                                    <button onclick="posWriteForm();"  class="btn btn-primary">직급 등록하기</button>
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