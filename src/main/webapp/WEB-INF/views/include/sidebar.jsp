<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
      <!--**********************************
            Sidebar start
        ***********************************-->
  <div class="quixnav">
            <div class="quixnav-scroll">
                <ul class="metismenu" id="menu">
                   <li class="nav-label first">Menu</li> 
                   	<li><a  href="/notice" aria-expanded="false"><i
                                class="icon icon-book-open-2"></i><span class="nav-text">공지 사항</span></a>
                    </li>
                    <li><a class="has-arrow" href="javascript:void()" aria-expanded="false"><i
                                class="icon icon-single-04"></i><span class="nav-text">정보 조회</span></a>
                        <ul aria-expanded="false">
                            <li><a href="/emp/detail?empNo=${sessionEmp.empNo }">내 정보 조회</a></li> 
                            <li><a href="/edu/history?empNo=${sessionEmp.empNo}">내 교육 정보</a></li>
                            <li><a href="/main">출·퇴근 관리</a></li>
                        </ul>
                    </li>
                 	
                 	<c:if test="${sessionEmp.dept.deptName eq '인사팀'}">
                    <li><a class="has-arrow" href="javascript:void()" aria-expanded="false"><i
                                class="icon icon-app-store"></i><span class="nav-text">조직 관리</span></a>
                        <ul aria-expanded="false">
                            <li><a href="/admin/emp/insertEmpPage">사원 등록</a></li>
                            <li><a href="/admin/emp/deptList">부서 관리</a></li>
                            <li><a href="/admin/emp/posList">직급 관리</a></li>
                   			<li><a href="/admin/emp">인사 이동</a></li>
                            <li><a href="/admin/emp/history">인사 이동 기록</a></li>
                            <li><a href="/admin/emp/retire">퇴사자 관리</a></li>
                        </ul>
                    </li>
                    <li><a class="has-arrow" href="javascript:void()" aria-expanded="false"><i
                                class="icon icon-chart-bar-33"></i><span class="nav-text">교육 관리</span></a>
                        <ul aria-expanded="false">
                            <li><a href="/edu/add">교육 과정 등록</a></li>
                            <li><a href="/edu/calendar">교육 과정 조회</a></li>
                            <li><a href="/edu/admin/history">교육 진행 관리</a></li>
                        </ul>
                    </li>
                    <li><a  href="/edu/log" aria-expanded="false"><i
                                class="icon icon-e-reader"></i><span class="nav-text">로그 기록</span></a>
                    </li>
                    </c:if>
                </ul>
            </div>
        </div>
        <!--**********************************
            Sidebar end
        ***********************************-->