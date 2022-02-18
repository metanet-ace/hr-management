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
                            <li><a href="/emp/detail?empNo=${sessionEmp.empNo }">내 정보 조회</a></li> 
                            <li><a href="/edu/history?empNo=${sessionEmp.empNo}">내 교육 정보</a></li>
                        </ul>
                    </li>
                    
                    <li><a class="has-arrow" href="javascript:void()" aria-expanded="false"><i
                                class="icon icon-app-store"></i><span class="nav-text">조직 관리</span></a>
                        <ul aria-expanded="false">
                            <li><a href="/admin/emp/insertEmpPage">사원 등록</a></li>
                            <li><a href="/admin/emp/deptList">부서 관리</a></li>
                   			<li><a href="/admin/emp">인사 이동</a></li>
                            <li><a href="/admin/emp/history">인사 이동 히스토리</a></li>
                        </ul>
                    </li>
                    <li><a class="has-arrow" href="javascript:void()" aria-expanded="false"><i
                                class="icon icon-chart-bar-33"></i><span class="nav-text">교육 관리</span></a>
                        <ul aria-expanded="false">
                            <li><a href="/edu/add">교육 과정 등록</a></li>
                            <li><a href="/edu/list">교육 과정 조회</a></li>
                            <li><a href="/edu/admin/history">교육 진행 관리</a></li>
                        </ul>
                    </li>
                    
                </ul>
            </div>
        </div>
        <!--**********************************
            Sidebar end
        ***********************************-->