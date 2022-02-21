<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/views/include/header.jsp" />
<c:import url="/WEB-INF/views/include/sidebar.jsp" />

<script>
$(document).ready(function(){
	$("#empNoCheck").on("click", function(){
		if($("#empNo").val() == ""){
			alert("부서장을 입력해주세요.")
		}
	})
})

function fn_empNoCheck(){
			$.ajax({
				url: "/empNoCheck", 
				type: "POST",
				dataType: "JSON",
				data: {"empNo" : $("#empNo").val()},
				success: function(data){
					if(data == 0){
						$("empNoCheck").attr("value", "N");
						alert("등록되어있지 않은 사원번호입니다.");
					}else if(data == 1){
						$("#empNoCheck").attr("value", "Y");
						alert("등록되어있는 사원번호입니다.");
						$("#empNo").attr("readonly", "readonly");
					}
					
					
				}
			})
		}
		
function fnSubmit(){
	if($("#deptName").val() == ""){
		alert("부서명을 입력해주세요.");
		$("#deptName").focus();
		
		return false;
	}
	
	if($("#empNoCheck").val() != 'Y'){
		alert("사원번호를 확인해주세요.");
		$("#empNoCheck").focus();
		
		return false;
	}
	
	if(confirm("부서를 수정하시겠습니까?")){
		$("#update").submit();
		return false;
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
			<div class="col-lg-12">
				<div class="card">
					<div class="card-header">
						<h4 class="card-title">Form Validation</h4>
					</div>
					<div class="card-body">
						<div class="form-validation">
							<form id="update" class="form-valide" action="/admin/emp/updateDept"
								method="post">
								<input type="hidden" name="deptNo" value="${dept.deptNo }">
								<div class="row">
									<div class="col-xl-6">

										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="deptName">부서명<span
												class="text-danger">*</span></label>
											<div class="col-lg-6">
												<input type="text" class="form-control" id="deptName"
													name="deptName" value="${dept.deptName }">
											</div>
										</div>
									</div>


									<div class="col-xl-6">

										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="empNo">부서장<span
												class="text-danger">*</span></label>
											<div class="col-lg-6">
												<input type="text" class="form-control" id="empNo"
													name="empNo" placeholder="사원번호로 입력해주세요." value="${dept.deptHead }">
													<button type="button" class="btn btn-primary" id="empNoCheck"
													name="empNoCheck" onclick="fn_empNoCheck(); return false;"
													value="N">사원번호 확인</button>
											</div>
										</div>

										<div class="form-group row">
											<div class="col-lg-8 ml-auto">
												<button type="button" class="btn btn-primary"
													onclick="fnSubmit(); return false;">Submit</button>
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
                <p>Copyright © Designed &amp; Developed by <a href="#" target="_blank">Quixkit</a> 2019</p>
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