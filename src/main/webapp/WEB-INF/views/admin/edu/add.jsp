<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/views/include/header.jsp" />
<c:import url="/WEB-INF/views/include/sidebar.jsp" />

<!DOCTYPE html>
<html>
<script type="text/javascript">
let now = new Date();
let year =  now.getFullYear();
let month = now.getMonth()+1;
let date = now.getDate();
var today = new Date(year+'-'+ month +'-'+ date);

function checkStart(){
	start = new Date($('#eduStart').val());
	if(start.valueOf() < today.valueOf()){
		alert("교육 시작일은 현재 날짜 이후만 가능합니다.");
		$("#eduStart").val('');
	}else {
		$("#eduEnd").attr("min",$('#eduStart').val());
		$("#eduEnd").val('');
		$("#eduEnd").attr("readonly",false);
	}
}
</script>

<body>
	<!--**********************************
            Content body start
        ***********************************-->
	<div class="content-body">
		<div class="container-fluid">
		<div class="row page-titles mx-0">
			<div class="col-sm-6 p-md-0">
				<div class="welcome-text">
					<h4>교육 과정 등록</h4>
				</div>
			</div>
		</div>
			<!-- row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<div class="form-validation">
								<form class="form-valide" action="./add" method="post" enctype="multipart/form-data">
									<input type="hidden" name="empNo" value="${sessionEmp.empNo }">
									<input type="hidden" name="empName" value="${sessionEmp.empName }">
									<div class="row">
										<div class="col-xl-6">
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduTitle">교육명
													<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" id="eduTitle"
														name="eduTitle" value="${eduVO.eduTitle }" placeholder="교육명을 입력해주세요.">
														<span style="color: #ff0000;">${valid_eduTitle}</span>
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduDesc">교육
													상세 <span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<textarea class="form-control" id="eduDesc" name="eduDesc" rows="5" placeholder="교육 상세를 입력해주세요.">${eduVO.eduDesc }</textarea>
														<span style="color: #ff0000;">${valid_eduDesc}</span>
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduLoc">장소
													<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" id="eduLoc"
														name="eduLoc" value="${eduVO.eduLoc }" placeholder="장소를 입력해주세요.">
														<span style="color: #ff0000;">${valid_eduLoc}</span>
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduTime">총
													이수 시간 <span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" id="eduTime"
														name="eduTime" value="${eduVO.eduTime }" placeholder="총 이수 시간을 입력해주세요.">
														<span style="color: #ff0000;">${valid_eduTime}</span>
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduTeacher">담당
													강사<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" id="eduTeacher"
														name="eduTeacher" value="${eduVO.eduTeacher }" placeholder="담당 강사를 입력해주세요.">
														<span style="color: #ff0000;">${valid_eduTeacher}</span>
												</div>
											</div>
										</div>
										<div class="col-xl-6">
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduPeople">인원
													<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" id="eduPeople"
														name="eduPeople" value="${eduVO.eduPeople }" placeholder="인원을 입력해주세요.">
														<span style="color: #ff0000;">${valid_eduPeople}</span>
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduTarget">대상
													<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" id="eduTarget"
														name="eduTarget" value="${eduVO.eduTarget }"placeholder="대상을 입력해주세요.">
														<span style="color: #ff0000;">${valid_eduTarget}</span>
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduStart">시작일
													<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="date" class="form-control" id="eduStart" value="${eduVO.eduStart }"
															name="eduStart" onchange="checkStart();">
													<span style="color: #ff0000;">${valid_eduStart}</span>												
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduEnd">종료일
													<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="date" class="form-control" id="eduEnd" value="${eduVO.eduEnd }"
														name="eduEnd" readonly="readonly">
													<span style="color: #ff0000;">${valid_eduEnd}</span>
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduCost">비용
													<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" id="eduCost" value="${eduVO.eduCost }"
														name="eduCost" placeholder="비용을 입력해주세요.">
													<span style="color: #ff0000;">${valid_eduCost}</span>
												</div>
											</div>
											<div class="form-group row">
                                                    <label class="col-lg-4 col-form-label" for="uploadfile">첨부파일
                                                        <span class="text-danger">*</span>
                                                    </label>
                                                    <div class="col-lg-6">
                                                        <input type="file" class="form-control" id="uploadfile" name="uploadfile">
                                                    </div>
                                                </div>


											<div class="form-group row">
												<div class="col-lg-8 ml-auto">
													<button type="submit" class="btn btn-primary">등록</button>
													<button type="button" onclick="location.href='./list';" class="btn btn-primary">취소</button>
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

	<c:import url="/WEB-INF/views/include/footer.jsp" />
</body>
</html>