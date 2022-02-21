<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/views/include/header.jsp" />
<c:import url="/WEB-INF/views/include/sidebar.jsp" />

<script>
$(document).ready(function(){
	$("#sub").on("click", function(){
		var min = $("#posMinsal").val();
		var max = $("#posMaxsal").val();
		var pattern = /^[0-9]+$/;
		
		if($("#posName").val()==""){
			alert("직급명을 입력해주세요.");
			$("#posName").focus;
			
			return false;
		}
		
		else if($("#posMinsal").val()==""){
			alert("최소연봉을 입력해주세요.");
			$("#posMinsal").focus;
			
			return false
		}
		
		else if($("#posMaxsal").val()==""){
			alert("최대연봉을 입력해주세요.");
			$("#posMaxsal").focus;
			
			return false
		}
		
		
		
		else if(!pattern.test(min)){
			alert("최소연봉은 숫자만 입력가능합니다.")
			$("#posMinsal").focus();
			return;
		}else if(!pattern.test(max)){
			alert("최대연봉은 숫자만 입력가능합니다.")
			$("#posMaxsal").focus();
			return;
		}
		else if(confirm("직급을 등록하시겠습니까?")){
			$("#update").submit();
			return false;
		}
	})
})

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
							<form id="update" class="form-valide"
								action="/admin/emp/updatePos" method="post">
								<input type="hidden" name="posNo" value="${pos.posNo }">
								<div class="row">
									<div class="col-xl-6">

										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="posName">직급명<span
												class="text-danger">*</span></label>
											<div class="col-lg-6">
												<input type="text" class="form-control" id="posName"
													name="posName" value="${pos.posName }">
											</div>
										</div>



									</div>
									<div class="col-xl-6">
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="posMinsal">최소연봉<span
												class="text-danger">*</span></label>
											<div class="col-lg-6">
												<input type="text" class="form-control" id="posMinsal"
													name="posMinsal" value="${pos.posMinsal }">

											</div>만 원
										</div>
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="posMaxsal">최대연봉<span
												class="text-danger">*</span></label>
											<div class="col-lg-6">
												<input type="text" class="form-control" id="posMaxsal"
													name="posMaxsal" value="${pos.posMaxsal }">
											</div>만 원
										</div>

										<div class="form-group row">
											<div class="col-lg-8 ml-auto">
												<button type="button" class="btn btn-primary" id="sub" name="sub">Submit</button>
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
