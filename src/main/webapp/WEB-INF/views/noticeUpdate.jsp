<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/views/include/header.jsp" />
<c:import url="/WEB-INF/views/include/sidebar.jsp" />

<script type="text/javascript">
	function previous(no){
			location.href="./noticeDetail?notice_no="+no;
	}
	
	function updateCheck(){
		if(confirm("해당 공지사항을 수정하시겠습니까?")){
			return true;
		}else{
			return false;
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
							<h4 class="card-title">공지사항 수정</h4>
						</div>
						<div class="card-body">
							<div class="form-validation">
								<form class="form-valide" action="./updateNotice" method="post" onsubmit="return updateCheck()"
									enctype="multipart/form-data">
									<input type="hidden" name="empNo" value="${noticeVO.empNo }">
									<input type="hidden" name="noticeNo" value="${noticeVO.noticeNo }" />
									<div class="row">
										<div class="col-xl-6">
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="noticeTitle">제목
													<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" id="noticeTitle"
														name="noticeTitle" style="width: 1200px;"
														value="${noticeVO.noticeTitle }" placeholder="제목을 입력해주세요.">
													<span style="color: #ff0000;">${valid_noticeTitle}</span>
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="noticeContent">내용<span
													class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<textarea class="form-control" id="noticeContent" name="noticeContent"
														style="width: 1200px; height: 500px;"
														placeholder="내용을 입력해주세요.">${noticeVO.noticeContent }</textarea>
													<span style="color: #ff0000;">${valid_noticeContent}</span>
												</div>
											</div>

											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="uploadfile">첨부파일
													<span class="text-danger">*</span>
												</label>
												<!-- <div class="col-lg-6">
													<input type="file" style="width: 1200px;"
														class="form-control" id="uploadfile" name="uploadfile">
												</div> -->
												<div class="col-lg-6">
													<c:choose>
														<c:when test="${noticeVO.noticeFile eq null}">
                                                    			파일이 없습니다.
                                                    			<input
																type="file" class="form-control" id="uploadfile"
																name="uploadfile">
														<input type="hidden" name="noticeFile" value="${noticeVO.noticeFile}" />
														</c:when>
														<c:otherwise>
                                                    			${noticeVO.noticeFile}
                                                    			<input
																type="file" class="form-control" id="uploadfile"
																name="uploadfile">
																<input type="hidden" name="noticeFile" value="${noticeVO.noticeFile}" />
														</c:otherwise>
													</c:choose>
												</div>
											</div>
											<div class="form-group row">
												<div class="col-lg-8 ml-auto">
													<button type="submit" class="btn btn-primary">수정</button>
													<button type="button" onclick="previous('${noticeVO.noticeNo}');" class="btn btn-primary">돌아가기</button>

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