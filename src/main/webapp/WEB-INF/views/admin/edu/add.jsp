<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/views/include/header.jsp" />
<c:import url="/WEB-INF/views/include/sidebar.jsp" />
<!DOCTYPE html>
<html>
<head>
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
							<h4 class="card-title">교육 과정 추가</h4>
						</div>
						<div class="card-body">
							<div class="form-validation">
								<form class="form-valide" action="/edu/add" method="post">
									<div class="row">
										<div class="col-xl-6">
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduTitle">교육명
													<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" id="eduTitle"
														name="eduTitle" placeholder="교육명">
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduDesc">교육
													상세 <span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<textarea class="form-control" id="eduDesc"
														name="eduDesc" rows="5"
														placeholder=""></textarea>
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduLoc">장소
													<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" id="eduLoc"
														name="eduLoc" placeholder="장소">
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduTime">총
													이수 시간 <span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" id="eduTime"
														name="eduTime" placeholder="총이수시간">
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduTeacher">담당
													강사<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" id="eduTeacher"
														name="eduTeacher" placeholder="교육 상세">
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
														name="eduPeople" placeholder="인원">
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduTarget">대상
													<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" id="eduTarget"
														name="eduTarget" placeholder="대상">
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduStart">시작일
													<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="date" class="form-control" id="eduStart"
															name="eduStart">												
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduEnd">종료일
													<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="date" class="form-control" id="eduEnd"
														name="eduEnd">
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduCost">비용
													<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" id="eduCost"
														name="eduCost" placeholder="비용">
												</div>
											</div>


											<div class="form-group row">
												<div class="col-lg-8 ml-auto">
													<button type="submit" class="btn btn-primary">등록</button>
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