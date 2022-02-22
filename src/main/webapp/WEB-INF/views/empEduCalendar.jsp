<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	<c:import url="/WEB-INF/views/include/header.jsp" />
	<c:import url="/WEB-INF/views/include/sidebar.jsp" />
 	
  	<script>
  		$(document).ready(function(){
  			var calList = [];
  			
  			<c:forEach items="${calList }" var="list">
  				calList.push({title: "${list.eduTitle}", start:"${list.eduStart}", end: "${list.eduEnd}", url:"/edu/eduEmpDetail?edu_no=${list.eduNo}", color:ColorCode() });
  			</c:forEach >

  			var calendarEl = document.getElementById('calendar');
			var calendar = new FullCalendar.Calendar(calendarEl, {
				customButtons: {
					listButton: {
						text:'목록으로',
						click: function(){
							location.href='/edu/history?empNo='+${sessionEmp.empNo};
						}
					}
				},
				headerToolbar: {
					left: 'title',
					right: 'today prev,next listButton'
				},
				locale : "ko",
				editable : true,
				events : calList,
				locale : "ko",
			}); 
			calendar.render();
  			
  		});
  		
  		function ColorCode() {
  	      var makingColorCode = '0123456789ABCDEF';
  	      var finalCode = '#';
  	      for (var counter = 0; counter < 6; counter++) {
  	         finalCode =finalCode+ makingColorCode[Math.floor(Math.random() * 16)];
  	      }
  	      return finalCode;
  	   }
  	</script>
        <!--**********************************
            Content body start
        ***********************************-->
		<div class="content-body">
			<!-- row -->
			<div class="container-fluid">
				<div class="row page-titles mx-0">
         <div class="col-sm-6 p-md-0">
            <div class="welcome-text">
               <h4>교육 과정 달력</h4>
            </div>
         </div>
      </div>
				<div id="calendar" style="padding-right:200px; padding-left:200px"></div>
			</div>
		</div>
<!--**********************************
            Content body end
        ***********************************-->

        <c:import url="/WEB-INF/views/include/footer.jsp" />
        