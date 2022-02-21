<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	<c:import url="/WEB-INF/views/include/header.jsp" />
	<c:import url="/WEB-INF/views/include/sidebar.jsp" />
 	
  	<script>
  		$(document).ready(function(){
  			// 페이지가 처음 로드될 때 캘린더 보여주기
  			calendarView();
  		});
  	</script>
        <!--**********************************
            Content body start
        ***********************************-->
        <div class="content-body">
            <!-- row -->
            <div class="container-fluid">
                <div class="row">
                	<div class="col-lg-9 col-sm-10">
	                	<div id="calendar">
	                		
                		<!-- 달력 출력 -->
                		</div>
                	</div>
                	
					<div class="col-lg-3  text-center">
						<div class="card text-center border-dark mb-3" style="height: 100%;">
							<div class="card-header text-center" style="display: block; font-size: 1.5em; color: black;">근태관리</div>
							<div class="card-body">
								<button type="button" class="btn btn-outline-danger" id="startTime" onclick='recordTime();'>출근하기</button>
								<button type="button" class="btn btn-outline-danger" id="endTime" onclick='recordEndTime();'>퇴근하기</button>
								<br><br>
								
								<h4>금일 출근시간</h4>
								<h5 id="start">${startTime}</h5>
								<h4>금일 퇴근시간</h4>
								<h5 id="end">${endTime}</h5>
								<h4>이번주 누적근무 시간</h4>
								<h5 id="total">${totalTime }</h5>
								<h5></h5>
							</div>
							<div class="card-footer"></div>
						</div>
					</div>
                </div>
            </div>
        </div>
        <!--**********************************
            Content body end
        ***********************************-->
 	<script>
	function calendarView(){
		
		var empNo = ${sessionEmp.empNo}; 
		var data = {empNo: empNo};
		
		$.ajax({
			url: "/emp/workingTimeList",
			type: "POST",
			data: JSON.stringify(data),
			contentType : 'application/json',
			dataType: 'JSON',
			success: function(result){
				console.log("자바 >>>> 캘린더 뷰로 주는 결과: ");
				console.log(result);
				
				// 보여줄 데이터 가공
				const keys = Object.keys(result);
				var list = [];
				for(var i = 0; i < keys.length; i++){
					if(result[i]["WORK_START"] == null) { // 퇴근 시간
						list.push({title: result[i]["WORK_TYPE"], start: result[i]["WORK_END"] });	
					} else{ // 출근 시간 
						list.push({title: result[i]["WORK_TYPE"], start: result[i]["WORK_START"]});
					}
				}
				
				console.log(list);
				
				var calendarEl = document.getElementById('calendar');
				var calendar = new FullCalendar.Calendar(calendarEl, {
					headerToolbar: {
						left: '',
						center: 'title',
						right: ''
					},
					locale : "ko",
					dateClick : function(info) {
						console.log(info);
					},
					//navLinks: true,
					editable : true,
					events : list
				});
				calendar.render();
			
			},
			error: function(err){
				alert(err);
			}
		});
		
	
	}
	</script>
 	<script>
 		// 출근 시간 등록 
		function recordTime() {
			
			var empNo = ${sessionEmp.empNo}; 
			var data = {empNo: empNo};
			
			$.ajax({
				url: "/emp/recordTime",
				data: JSON.stringify(data),
				type: "POST",
				contentType : 'application/json',
				success: function(result){
					// 당일 출근시간 찍힘
					$("#start").text(result['formattedDate']);
					calendarView();
				},
				error: function(err){
					alert('이미 출근 시간을 등록하였습니다.');
				}
			});
		}
 		
 		// 퇴근 시간 등록 
 		function recordEndTime(){
 			var empNo = ${sessionEmp.empNo}; 
 			var startTime = $("#start").text();
			var data = {empNo: empNo,
						startTime: startTime};
			
			console.log($("#start").text())
			$.ajax({
				url: "/emp/recordEndTime",
				data: JSON.stringify(data),
				type: "POST",
				contentType : 'application/json',
				success: function(result){
					// 당일 퇴근시간 찍힘
					$("#end").text(result['formattedDate']);
					$("#total").text(result['totalTime']);
					calendarView();
				},
				error: function(err){
					
					console.log(err);
					if($("#start").text() == '') {
						alert('출근 시간을 등록하지 않았습니다.');
					} else if($("#end").text() != '') {
						alert('이미 퇴근 시간을 등록하였습니다.');
					} 
					
					if(err.status == 423){
						alert("주 52시간을 초과하였습니다.")
					}
				}
			});
 		}
 		
 		
	</script>
	
        <c:import url="/WEB-INF/views/include/footer.jsp" />
        