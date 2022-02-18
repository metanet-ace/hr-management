<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/views/include/header.jsp" />
<c:import url="/WEB-INF/views/include/sidebar.jsp" />

<script type="text/javascript">
	function del(no, fileName){
		if(confirm("해당 공지사항을 삭제하시겠습니까?")){
			location.href="./deleteNotice?notice_no="+no+"&notice_refile="+fileName;
		}
	}
	
	function update(no){
		if(confirm("해당 공지사항 수정 창으로 이동하시겠습니까?")){
			location.href="./updateNotice?notice_no="+no;
		}
	}
</script>

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
							<h4 class="card-title">공지사항 등록</h4>
						</div>
						<div class="card-body">
							<div class="form-validation">
								<form class="form-valide" action="./noticeAdd" method="post"
									enctype="multipart/form-data">
									<input type="hidden" name="empNo" value="${sessionEmp.empNo }">
									<input type="hidden" name="empName" value="${sessionEmp.empName }">
									<div class="row">
										<div class="col-xl-6">
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="noticeTitle">제목
													<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" id="noticeTitle"
														name="noticeTitle" style="width: 1200px;"
														value="${detail.noticeTitle }" readonly>
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduTeacher">작성자<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" id="noticeWriter"
														name="noticeWriter" style="width: 1200px;" value="${detail.noticeWriter }" readonly>
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="eduTeacher">작성일<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" id="noticeDate"
														name="noticeDate" style="width: 1200px;" value="${detail.noticeDate }" readonly>
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="noticeContent">내용<span
													class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<textarea class="form-control" id="noticeContent" name="noticeContent"
														style="width: 1200px; height: 400px;" readonly>${detail.noticeContent }</textarea>
												</div>
											</div>

											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="uploadfile">첨부파일
													<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
                                                    	<c:choose>
                                                    		<c:when test="${detail.noticeFile eq null}">
                                                    			파일이 없습니다.
                                                    		</c:when>
                                                    		<c:otherwise>
                                                    		<input type="hidden" name="noticeRefile" value="${detail.noticeRefile }">
                                                    		<input type="text" class="form-control" id="uploadfile" name="uploadfile" value="${detail.noticeFile }" readonly>
                                                        <a href="./downloadNoticeFile?uuid=${uuid }&fileName=${detail.noticeFile}">[download]</a>
                                                    		</c:otherwise>
                                                    	</c:choose>
                                                    </div>
											</div>
											<c:choose>
												<c:when test="${sessionEmp.empNo eq detail.empNo}">
													<div class="form-group row">
													<div class="col-lg-8 ml-auto">
													<button type="button" onclick="update(${detail.noticeNo})" class="btn btn-primary">수정</button>
													<button type="button" onclick="del('${detail.noticeNo}', '${detail.noticeRefile}')" class="btn btn-primary">삭제</button>
													<button type="button" onclick="location.href='./notice';" class="btn btn-primary">리스트로</button>
												</div>
											</div>
												</c:when>
												<c:otherwise>
													<div class="form-group row">
													<div class="col-lg-8 ml-auto">
													<button type="button" onclick="location.href='./notice';"
														class="btn btn-primary">리스트로</button>
												</div>
											</div>
												</c:otherwise>
											</c:choose>
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