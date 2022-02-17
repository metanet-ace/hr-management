<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<c:import url="/WEB-INF/views/include/header.jsp" />
	<c:import url="/WEB-INF/views/include/sidebar.jsp" />

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
                    <div class="col-sm-6 p-md-0 justify-content-sm-end mt-2 mt-sm-0 d-flex">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="javascript:void(0)">Form</a></li>
                            <li class="breadcrumb-item active"><a href="javascript:void(0)">Validation</a></li>
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
                                    <form class="form-valide" action="/admin/emp/insertEmp" method="post" enctype="multipart/form-data">
                                        <div class="row">
                                            <div class="col-xl-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-4 col-form-label" for="upfile">사원 이미지</label>
                                                    <div class="col-lg-6">
                                                        <input type="file" class="form-control" id="upfile" name="upfile">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label class="col-lg-4 col-form-label" for="empName">이름<span
                                                            class="text-danger">*</span></label>
                                                    <div class="col-lg-6">
                                                        <input type="text" class="form-control" id="empName" name="empName" value="${emp.empName }">
                                                    	<span style="color: #ff0000;">${valid_empName}</span>
                                                    </div>
                                                </div>
                                                  
                                                
                                                <div class="form-group row">
                                                    <label class="col-lg-4 col-form-label">성별<span
                                                            class="text-danger">*</span></label>
                                                    <div class="col-lg-8">
                                                           <c:choose>
                                                       <c:when test="${emp.empGender eq '여'}">
                                                          <input type="radio" name="empGender" value="여" checked> 여자 &nbsp;
                                                           <input type="radio" name="empGender" value="남">남자
                                                       </c:when>
                                                       <c:when test="${emp.empGender eq '남'}">
                                                            <input type="radio" name="empGender" value="여"> 여자 &nbsp;
                                                           <input type="radio" name="empGender" value="남" checked>남자
                                                        </c:when>
                                                        <c:otherwise>
                                                           <input type="radio" name="empGender" value="여"> 여자 &nbsp;
                                                           <input type="radio" name="empGender" value="남"> 남자
                                                        </c:otherwise>
                                                        </c:choose>
                                                        <br>

                                                        	<span style="color: #ff0000;">${valid_empGender}</span>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group row">
                                                    <label class="col-lg-4 col-form-label" for="empPhone">전화번호<span
                                                            class="text-danger">*</span></label>
                                                    <div class="col-lg-6">
                                                        <input type="text" class="form-control" id="empPhone" name="empPhone" value="${emp.empPhone }" placeholder="'-' 포함 입력">
                                                        <span style="color: #ff0000;">${valid_empPhone}</span>
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label class="col-lg-4 col-form-label" for="empEmail">이메일<span
                                                            class="text-danger">*</span></label>
                                                    <div class="col-lg-6">
                                                        <input type="text" class="form-control" id="empEmail" name="empEmail" value="${emp.empEmail }" placeholder="xxx@xxxxx.xxx">
                                                        <span style="color: #ff0000;">${valid_empEmail}</span>
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label class="col-lg-4 col-form-label" for="empHiredate">입사일<span
                                                            class="text-danger">*</span></label>
                                                    <div class="col-lg-6">
                                                        <input type="date" class="form-control" id="empHiredate" name="empHiredate" value="${emp.empHiredate }">
                                                        <span style="color: #ff0000;">${valid_empHiredate}</span>
                                                    </div>
                                                </div>
                                            
                                            
                                            <div class="form-group row">
                                                    <label class="col-lg-4 col-form-label" for="empSsc">주민번호 <span
                                                            class="text-danger">*</span></label>
                                                    <div class="col-lg-6">
                                                        <input type="text" class="form-control" id="empSsc" name="empSsc" placeholder="xxxxxx-xxxxxxx" value="${emp.empSsc }" placeholder="'-' 포함 입력">
                                                        <span style="color: #ff0000;">${valid_empSsc}</span>
                                                    </div>
                                            </div>
                                            </div>
                                                
                                            <div class="col-xl-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-4 col-form-label" for="empDegree">최종학력<span
                                                            class="text-danger">*</span></label>
                                                    <div class="col-lg-6">
                                                    <c:choose>
                                                      <c:when test="${ emp.empDegree eq '고졸'}">
                                                        <select class="form-control" id="empDegree" name="empDegree">
                                                            <option value="">최종학력을 선택해주세요</option>
                                                            <option value="고졸" selected>고졸</option>
                                                            <option value="학사">학사</option>
                                                            <option value="석사">석사</option>
                                                            <option value="박사">박사</option>
                                                        </select>
                                                      </c:when>
                                                      <c:when test="${ emp.empDegree eq '학사'}">
                                                        <select class="form-control" id="empDegree" name="empDegree">
                                                            <option value="">최종학력을 선택해주세요</option>
                                                            <option value="고졸">고졸</option>
                                                            <option value="학사" selected>학사</option>
                                                            <option value="석사">석사</option>
                                                            <option value="박사">박사</option>
                                                        </select>
                                                      </c:when>
                                                      <c:when test="${ emp.empDegree eq '석사'}">
                                                        <select class="form-control" id="empDegree" name="empDegree">
                                                            <option value="">최종학력을 선택해주세요</option>
                                                            <option value="고졸">고졸</option>
                                                            <option value="학사">학사</option>
                                                            <option value="석사" selected>석사</option>
                                                            <option value="박사">박사</option>
                                                        </select>
                                                      </c:when>
                                                      <c:when test="${ emp.empDegree eq '박사'}">
                                                        <select class="form-control" id="empDegree" name="empDegree">
                                                            <option value="">최종학력을 선택해주세요</option>
                                                            <option value="고졸">고졸</option>
                                                            <option value="학사">학사</option>
                                                            <option value="석사">석사</option>
                                                            <option value="박사" selected>박사</option>
                                                        </select>
                                                      </c:when>
                                                      <c:otherwise>
                                                        <select class="form-control" id="empDegree" name="empDegree">
                                                            <option value="">최종학력을 선택해주세요</option>
                                                            <option value="고졸">고졸</option>
                                                            <option value="학사">학사</option>
                                                            <option value="석사">석사</option>
                                                            <option value="박사">박사</option>
                                                        </select>
                                                      </c:otherwise>
                                                    </c:choose>
                                                        <span style="color: #ff0000;">${valid_empDegree}</span>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group row">
                                                    <label class="col-lg-4 col-form-label">신입/경력 <span
                                                            class="text-danger">*</span></label>
                                                    <div class="col-lg-8">
                                                    <c:choose>
                                                    	<c:when test="${emp.empCareer eq '신입' }">
                                                            <input type="radio" name="empCareer" value="신입" checked> 신입 &nbsp;
                                                        	<input type="radio" name="empCareer" value="경력">경력
                                                        </c:when>
                                                        <c:when test="${emp.empCareer eq '경력' }">
                                                            <input type="radio" name="empCareer" value="신입"> 신입 &nbsp;
                                                        	<input type="radio" name="empCareer" value="경력" checked>경력
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input type="radio" name="empCareer" value="신입"> 신입 &nbsp;
                                                        	<input type="radio" name="empCareer" value="경력">경력
                                                        </c:otherwise>
                                                    </c:choose>
                                                        	<br>
                                                        	<span style="color: #ff0000;">${valid_empCareer}</span>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group row">
                                                    <label class="col-lg-4 col-form-label" for="deptNo">부서<span
                                                            class="text-danger">*</span></label>
                                                    <div class="col-lg-6">
                                                    
                                                        <select class="form-control" id="deptNo" name="deptNo">
                                                        	
                                                            <option value="">부서를 선택해주세요</option>
                                                            <c:forEach items="${dept }" var="dept">
                                                            <option value="${dept.deptNo }" <c:if test="${dept.deptNo eq emp.deptNo}">selected</c:if>>${dept.deptName }</option>
                                                            </c:forEach>
                                                        </select>
                                                        <span style="color: #ff0000;">${valid_deptNo}</span>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group row">
                                                    <label class="col-lg-4 col-form-label" for="posNo">직급<span
                                                            class="text-danger">*</span></label>
                                                    <div class="col-lg-6">
                                                    <c:choose>
                                                      <c:when test="${emp.posNo eq 1}">
                                                        <select class="form-control" id="posNo" name="posNo" >
                                                            <option value="">직급을 선택해주세요</option>
                                                            <option value=1 selected>사원</option>
                                                            <option value=2>대리</option>
                                                            <option value=3>과장</option>
                                                            <option value=4>차장</option>
                                                            <option value=5>부장</option>
                                                            <option value=6>사장</option>
                                                        </select>
                                                      </c:when>
                                                      <c:when test="${emp.posNo eq 2}">
                                                        <select class="form-control" id="posNo" name="posNo" >
                                                            <option value="">직급을 선택해주세요</option>
                                                            <option value=1>사원</option>
                                                            <option value=2 selected>대리</option>
                                                            <option value=3>과장</option>
                                                            <option value=4>차장</option>
                                                            <option value=5>부장</option>
                                                            <option value=6>사장</option>
                                                        </select>
                                                      </c:when>
                                                      <c:when test="${emp.posNo eq 3}">
                                                        <select class="form-control" id="posNo" name="posNo" >
                                                            <option value="">직급을 선택해주세요</option>
                                                            <option value=1>사원</option>
                                                            <option value=2>대리</option>
                                                            <option value=3 selected>과장</option>
                                                            <option value=4>차장</option>
                                                            <option value=5>부장</option>
                                                            <option value=6>사장</option>
                                                        </select>
                                                      </c:when>
                                                      <c:when test="${emp.posNo eq 4}">
                                                        <select class="form-control" id="posNo" name="posNo" >
                                                            <option value="">직급을 선택해주세요</option>
                                                            <option value=1>사원</option>
                                                            <option value=2>대리</option>
                                                            <option value=3>과장</option>
                                                            <option value=4 selected>차장</option>
                                                            <option value=5>부장</option>
                                                            <option value=6>사장</option>
                                                        </select>
                                                      </c:when>
                                                      <c:when test="${emp.posNo eq 5}">
                                                        <select class="form-control" id="posNo" name="posNo" >
                                                            <option value="">직급을 선택해주세요</option>
                                                            <option value=1>사원</option>
                                                            <option value=2>대리</option>
                                                            <option value=3>과장</option>
                                                            <option value=4>차장</option>
                                                            <option value=5 selected>부장</option>
                                                            <option value=6>사장</option>
                                                        </select>
                                                      </c:when>
                                                      <c:when test="${emp.posNo eq 6}">
                                                        <select class="form-control" id="posNo" name="posNo" >
                                                            <option value="">직급을 선택해주세요</option>
                                                            <option value=1>사원</option>
                                                            <option value=2>대리</option>
                                                            <option value=3>과장</option>
                                                            <option value=4>차장</option>
                                                            <option value=5>부장</option>
                                                            <option value=6 selected>사장</option>
                                                        </select>
                                                      </c:when>
                                                      <c:otherwise>
                                                        <select class="form-control" id="posNo" name="posNo" >
                                                            <option value="">직급을 선택해주세요</option>
                                                            <option value=1>사원</option>
                                                            <option value=2>대리</option>
                                                            <option value=3>과장</option>
                                                            <option value=4>차장</option>
                                                            <option value=5>부장</option>
                                                            <option value=6>사장</option>
                                                        </select>
                                                      </c:otherwise>
                                                    </c:choose>
                                                        <span style="color: #ff0000;">${valid_posNo}</span>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group row">
                                                    <label class="col-lg-4 col-form-label" for="empSal">연봉<span
                                                            class="text-danger">*</span></label>
                                                    <div class="col-lg-4">
                                                        <input type="text" class="form-control" id="empSal" name="empSal" placeholder="숫자만 입력" value="${emp.empSal }">
                                                    	<span style="color: #ff0000;">${valid_empSal}</span>
                                                    </div>만 원
                                                    
                                                    
                                                </div>
                                               
                                                
                                                <div class="form-group row">
                                                    <label class="col-lg-4 col-form-label" for="empMil">병역<span
                                                            class="text-danger">*</span></label>
                                                    <div class="col-lg-6">
                                                    <c:choose>
                                                      <c:when test="${emp.empMil eq '미필' }">
                                                        <select class="form-control" id="empMil" name="empMil">
                                                            <option value="">병역을 선택해주세요</option>
                                                            <option value="미필" selected>미필</option>
                                                            <option value="군필">군필</option>
                                                            <option value="면제">면제</option>
                                                            <option value="해당없음">해당없음</option>
                                                        </select>
                                                      </c:when>
                                                      <c:when test="${emp.empMil eq '군필' }">
                                                        <select class="form-control" id="empMil" name="empMil">
                                                            <option value="">병역을 선택해주세요</option>
                                                            <option value="미필">미필</option>
                                                            <option value="군필" selected>군필</option>
                                                            <option value="면제">면제</option>
                                                            <option value="해당없음">해당없음</option>
                                                        </select>
                                                      </c:when>
                                                      <c:when test="${emp.empMil eq '면제' }">
                                                        <select class="form-control" id="empMil" name="empMil">
                                                            <option value="">병역을 선택해주세요</option>
                                                            <option value="미필">미필</option>
                                                            <option value="군필">군필</option>
                                                            <option value="면제" selected>면제</option>
                                                            <option value="해당없음">해당없음</option>
                                                        </select>
                                                      </c:when>
                                                      <c:when test="${emp.empMil eq '해당없음' }">
                                                        <select class="form-control" id="empMil" name="empMil">
                                                            <option value="">병역을 선택해주세요</option>
                                                            <option value="미필">미필</option>
                                                            <option value="군필">군필</option>
                                                            <option value="면제">면제</option>
                                                            <option value="해당없음" selected>해당없음</option>
                                                        </select>
                                                      </c:when>
                                                      <c:otherwise>
                                                        <select class="form-control" id="empMil" name="empMil">
                                                            <option value="">병역을 선택해주세요</option>
                                                            <option value="미필">미필</option>
                                                            <option value="군필">군필</option>
                                                            <option value="면제">면제</option>
                                                            <option value="해당없음">해당없음</option>
                                                        </select>
                                                      </c:otherwise>
                                                    </c:choose>
                                                        <span style="color: #ff0000;">${valid_empMil}</span>
                                                    </div>
                                                </div>
                                                
                                                
                                                <div class="form-group row">
                                                    <div class="col-lg-8 ml-auto">
                                                        <button type="submit" class="btn btn-primary">Submit</button>
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
