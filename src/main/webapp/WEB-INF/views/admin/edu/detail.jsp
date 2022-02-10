<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/views/include/header.jsp" />
<c:import url="/WEB-INF/views/include/sidebar.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<!-- Favicon icon -->
<link rel="icon" type="image/png" sizes="16x16"
	href="/assets/images/favicon.png">
<!-- Daterange picker -->
<link
	href="/assets/vendor/bootstrap-daterangepicker/daterangepicker.css"
	rel="stylesheet">
<!-- Clockpicker -->
<link
	href="/assets/vendor/clockpicker/css/bootstrap-clockpicker.min.css"
	rel="stylesheet">
<!-- asColorpicker -->
<link
	href="/assets/vendor/jquery-asColorPicker/css/asColorPicker.min.css"
	rel="stylesheet">
<!-- Material color picker -->
<link
	href="/assets/vendor/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css"
	rel="stylesheet">
<!-- Pick date -->
<link rel="stylesheet"
	href="/assets/vendor/pickadate/themes/default.css">
<link rel="stylesheet"
	href="/assets/vendor/pickadate/themes/default.date.css">
<!-- Custom Stylesheet -->
<link href="/assets/css/style.css" rel="stylesheet">
<script type="text/javascript">
	function del(no){
		if(confirm("해당 교육과정을 삭제하시겠습니까?")){
			location.href="./delete?edu_no="+no;
		}
	}
	
	function update(no){
		if(confirm("해당 교육과정 수정 창으로 이동하시겠습니까?")){
			location.href="./update?edu_no="+no;
		}
	}
</script>
</head>
<body>
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
							<h4 class="card-title">교육 과정 조회</h4>
						</div>
						<div class="card-body">
							<div class="form-validation">
								<div class="form-valide">
									<div class="row">
										<div class="col-xl-6">
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduTitle">교육명
													<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" id="eduTitle"
														name="eduTitle" value="${detail.eduTitle }" readonly>
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduDesc">교육
													상세 <span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<textarea class="form-control" id="eduDesc"
														name="eduDesc" rows="5" readonly>${detail.eduDesc }</textarea>
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduLoc">장소
													<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" id="eduLoc"
														name="eduLoc" value="${detail.eduLoc }" readonly>
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduTime">총
													이수 시간 <span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" id="eduTime"
														name="eduTime" value="${detail.eduTime }" readonly>
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduTeacher">담당
													강사<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" id="eduTeacher"
														name="eduTeacher" value="${detail.eduTeacher }" readonly>
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
														name="eduPeople" value="${detail.eduPeople }" readonly>
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduTarget">대상
													<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" id="eduTarget"
														name="eduTarget" value="${detail.eduTarget }" readonly>
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduStart">시작일
													<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" id="eduStart"
														name="eduStart" value="${detail.eduStart }" readonly>
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduEnd">종료일
													<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" id="eduEnd"
														name="eduEnd" value="${detail.eduEnd }" readonly>
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduCost">비용
													<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" id="eduCost"
														name="eduCost" value="${detail.eduCost }" readonly>
												</div>
											</div>


											<div class="form-group row">
												<div class="col-lg-8 ml-auto">
													<button onclick="update(${detail.eduNo})" class="btn btn-primary">수정</button>
													<button onclick="del(${detail.eduNo})" class="btn btn-primary">삭제</button>
												</div>
											</div>
										</div>
									</div>
								</div>
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