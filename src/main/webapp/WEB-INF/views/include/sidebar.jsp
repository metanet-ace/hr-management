<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
      <!--**********************************
            Sidebar start
        ***********************************-->
  <div class="quixnav">
            <div class="quixnav-scroll">
                <ul class="metismenu" id="menu">
                   <li class="nav-label first">Menu</li> 
                    <li><a class="has-arrow" href="javascript:void()" aria-expanded="false"><i
                                class="icon icon-single-04"></i><span class="nav-text">정보 조회</span></a>
                        <ul aria-expanded="false">
                            <li><a href="./index.html">내 정보 조회</a></li>
                            <li><a class="has-arrow" href="javascript:void()" aria-expanded="false">교육 과정</a>
                                <ul aria-expanded="false">
                                    <li><a href="./email-compose.html">진행 예정</a></li>
                                    <li><a href="./email-inbox.html">진행중</a></li>
                                </ul>
                            </li>  
                            <li><a href="./index2.html">교육 이수 내역</a></li>
                        </ul>
                    </li>
                    
                    <li><a class="has-arrow" href="javascript:void()" aria-expanded="false"><i
                                class="icon icon-app-store"></i><span class="nav-text">조직 관리</span></a>
                        <ul aria-expanded="false">
                            <li><a href="./app-profile.html">사원 등록</a></li>
                            <li><a href="./app-profile.html">사원 목록</a></li>
                   			<li><a href="./app-profile.html">인사 이동</a></li>
                        </ul>
                    </li>
                    <li><a class="has-arrow" href="javascript:void()" aria-expanded="false"><i
                                class="icon icon-chart-bar-33"></i><span class="nav-text">교육 관리</span></a>
                        <ul aria-expanded="false">
                            <li><a href="./chart-flot.html">교육 추가</a></li>
                            <li><a href="./chart-morris.html">교육 일정 조회</a></li>
                            <li><a href="./chart-chartjs.html">교육 결과</a></li>
                        </ul>
                    </li>
                    
                </ul>
            </div>
        </div>
        <!--**********************************
            Sidebar end
        ***********************************-->