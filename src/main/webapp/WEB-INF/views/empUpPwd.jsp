<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

   <c:import url="/WEB-INF/views/include/header.jsp" />
   <c:import url="/WEB-INF/views/include/sidebar.jsp" />

<script>
$(document).ready(function(){
	$("#pwUpdate").on("click", function(){
		if($("#empPwd").val()==""){
			alert("현재 비밀번호를 입력해주세요.");
			$("#empPwd").focus();
			return false 
		}
		else if($("#pw1").val()==""){
			alert("변경할 비밀번호를 입력해주세요.");
			$("#pw1").focus();
			return false
		}
		else if($("#pw2").val()==""){
			alert("변경할 비밀번호를 한번 더 입력해주세요.");
			$("#pw2").focus();
			return false
		}
		else if($("#pw1").val() != $("#pw2").val()){
			alert("변경비밀번호가 일치하지 않습니다.");
			$("#pw2").focus();
		}
		
		else{
			$.ajax({
				url: "/emp/pwCheck", 
				type: "POST",
				data: $("#pwUpdateForm").serializeArray(),
				success: function(data){
					if(data == 0){
						alert("패스워드가 틀렸습니다.");
						return;
					}else{
						if(confirm("변경하시겠습니까?")){
							$("#pwUpdateForm").submit();
						}
					}
				}
			})
		}
		
	});
})

function cancle(no){
	if(confirm("비밀번호 수정을 취소하시겠습니까?")){
		location.href="/emp/detail?empNo="+no;
	}
}
</script>

    <div class="authincation h-100">
        <div class="container-fluid h-100">
            <div class="row justify-content-center h-100 align-items-center">
                <div class="col-md-6">
                    <div class="authincation-content">
                        <div class="row no-gutters">
                            <div class="col-xl-12">
                            
                                <div class="auth-form">
                                    <h4 class="text-center mb-4">Sign in your account</h4>
                                    <form action="/emp/pwUpdate" method="post" id="pwUpdateForm" name="pwUpdateForm">
                                    sessuionEmp : ${sessionEmp.empNo}
                                    <input type="hidden" id="empNo" name="empNo" value="${ sessionEmp.empNo }">
                                        <div class="form-group">
                                            <label><strong>현재 비밀번호</strong></label>
                                            <input type="password" class="form-control" name="empPwd" id="empPwd" value="${ pass.empPwd }">
                                        </div>
                                        <div class="form-group">
                                            <label><strong>새로운 비밀번호</strong></label>
                                            <input type="password" class="form-control" name="pw1" id="pw1" value="${ pass.pw1 }">
                                            <span style="color: #ff0000;">${ valid_pw1 }</span>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label><strong>비밀번호 확인</strong></label>
                                            <input type="password" class="form-control" name="pw2" id="pw2" value="${ pass.pw2 }">
                                        </div>
                                      
                                        <div class="text-center">
                                            <button type="button" id="pwUpdate" name="pwUpdate" class="btn btn-primary btn-block">비밀번호 변경하기</button>
                                        </div>
                                        <br>
                                        <div class="text-center">
                                        	<button type="button" onClick="cancle(${sessionEmp.empNo})" class="btn btn-outline-primary btn-block">비밀번호 수정취소</button>
                                        </div>
                                    </form>
                                    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <!--**********************************
        Scripts
    ***********************************-->
    <!-- Required vendors -->
    <script src="./vendor/global/global.min.js"></script>
    <script src="./js/quixnav-init.js"></script>
    <script src="./js/custom.min.js"></script>


<c:import url="/WEB-INF/views/include/footer.jsp" />
