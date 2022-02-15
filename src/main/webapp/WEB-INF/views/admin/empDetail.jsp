
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/views/include/header.jsp" />
<c:import url="/WEB-INF/views/include/sidebar.jsp" />

<<script type="text/javascript">
function upPwd(no){
	if(confirm("해당 직원의 비밀번호를 초기화하시겠습니까?")){
		location.href="/admin/emp/updatePwd?empNo="+no;
	}
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
					<h4>Hi, welcome back!</h4>
					<p class="mb-1">Validation</p>
				</div>
			</div>
			<div
				class="col-sm-6 p-md-0 justify-content-sm-end mt-2 mt-sm-0 d-flex">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="javascript:void(0)">Form</a></li>
					<li class="breadcrumb-item active"><a
						href="javascript:void(0)">Validation</a></li>
				</ol>
			</div>
		</div>
		<!-- row -->
		<div class="row">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-header">
						<h4 class="card-title">Form Validation</h4>
					</div>
					<div class="card-body">
						<div class="form-validation">
							<form class="form-valide" align="center">
							<img
											src="${ pageContext.servletContext.contextPath }/resources/employeeImages/${ empDetail.empRePhoto }"
											style="width: 250px; height: 300px">
											<br><br><br><br>
								<div class="row">
									
										
									
									<div class="col-xl-6">

										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="empNo">사원번호</label>
											<div class="col-lg-6">
											${ empDetail.empNo }
											</div>
										</div>
										
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="empName">이름</label>
											<div class="col-lg-6">
											${ empDetail.empName }
											</div>
										</div>
										
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="empGender">성별</label>
											<div class="col-lg-6">
											${ empDetail.empGender }
											</div>
										</div>
										
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="empSsc">주민번호</label>
											<div class="col-lg-6">
											${ empDetail.empSsc }
											</div>
										</div>
										
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="empPhone">전화번호</label>
											<div class="col-lg-6">
											${ empDetail.empPhone }
											</div>
										</div>
										
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="empEmail">이메일</label>
											<div class="col-lg-6">
											${ empDetail.empEmail }
											</div>
										</div>
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="empHiredate">입사일</label>
											<div class="col-lg-6">
											${ empDetail.empHiredate }
											</div>
										</div>
										</div>
										<div class="col-xl-6">
										
										
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="empRetdate">퇴사일</label>
											
											<div class="col-lg-6">
											<c:if test="${ !empty empDetail.empRetdate }">
											${ empDetail.empRetdate }
											</c:if>
											<c:if test="${empty empDetail.empRetdate }">
											---
											</c:if>
											</div>
										</div>
										
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="deptName">부서</label>
											<div class="col-lg-6">
											${ empDetail.deptName }
											</div>
										</div>
										
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="posName">직급</label>
											<div class="col-lg-6">
											${ empDetail.posName }
											</div>
										</div>
										
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="empDegree">학력</label>
											<div class="col-lg-6">
											${ empDetail.empDegree }
											</div>
										</div>
										
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="empCareer">신입/경력</label>
											<div class="col-lg-6">
											${ empDetail.empCareer }
											</div>
										</div>
										
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="empMil">병역</label>
											<div class="col-lg-6">
											${ empDetail.empMil }
											</div>
										</div>
										
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="empSal">연봉</label>
											<div class="col-lg-6">
											${ empDetail.empSal } 만 원 </div>
										</div>
										<div class="form-group row">
											<div class="col-lg-8 ml-auto">
											<c:url var="update" value="/admin/emp/updateEmpPage">
											<c:param name="empNo" value="${empDetail.empNo }" />
											</c:url>
												<button type="button" onClick="location.href='${update}';" class="btn btn-primary">수정하기</button>
												<button type="button" onClick="upPwd(${empDetail.empNo })" class="btn btn-primary">비밀번호 초기화</button>
											</div>
											
											
											
										</div>
									</div>
								</div>
							</form>
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


<!-- <!--**********************************
            Footer start
        ***********************************
        <div class="footer">
            <div class="copyright">
                <p>Copyright ÃÂ© Designed &amp; Developed by <a href="#" target="_blank">Quixkit</a> 2019</p>
            </div>
        </div>
        **********************************
            Footer end
        *********************************** -->
<c:import url="/WEB-INF/views/include/footer.jsp" />

<!--**********************************
           Support ticket button start
        ***********************************-->

<!--**********************************
           Support ticket button end
        ***********************************-->


</div>
<!--**********************************
        Main wrapper end
    ***********************************-->

<!--**********************************
        Scripts
    ***********************************-->
<!-- Required vendors -->
<script src="./vendor/global/global.min.js"></script>
<script src="./js/quixnav-init.js"></script>
<script src="./js/custom.min.js"></script>




<!-- Jquery Validation -->
<script src="./vendor/jquery-validation/jquery.validate.min.js"></script>
<!-- Form validate init -->
<script src="./js/plugins-init/jquery.validate-init.js"></script>
