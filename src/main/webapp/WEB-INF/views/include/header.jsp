<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>${title}</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="/assets/images/favicon.png">
    <link rel="stylesheet" href="/assets/vendor/owl-carousel/css/owl.carousel.min.css">
    <link rel="stylesheet" href="/assets/vendor/owl-carousel/css/owl.theme.default.min.css">
    <link href="/assets/vendor/jqvmap/css/jqvmap.min.css" rel="stylesheet">
    <link href="/assets/vendor/datatables/css/jquery.dataTables.min.css" rel="stylesheet">
    <link href="/assets/css/style.css" rel="stylesheet">
    <!-- 풀캘린더 -->
    <link href='/assets/lib/main.css' rel='stylesheet' />
	<script src='/assets/lib/main.js'></script>
	<!-- 제이쿼리 -->
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	
	<style>
	th, td, td > a{
		color: #2a2c32 !important
	}
	
	</style>
</head>

<body>

    <!--*******************
        Preloader start
    ********************-->
    <div id="preloader">
        <div class="sk-three-bounce">
            <div class="sk-child sk-bounce1"></div>
            <div class="sk-child sk-bounce2"></div>
            <div class="sk-child sk-bounce3"></div>
        </div>
    </div>
    <!--*******************
        Preloader end
    ********************-->


    <!--**********************************
        Main wrapper start
    ***********************************-->
    <div id="main-wrapper">

        <!--**********************************
            Nav header start
        ***********************************-->
        <div class="nav-header" style="display:absolute;">
            <a href="/main" class="brand-logo">
             	<img class="logo-abbr" src="" style="width: 300px;" alt="">
                <img class="brand-title" src="/assets/icons/metanet-logo-removebg.png" style="max-width: 400px; width: 300px; margin-left: -30px"alt="">
            </a>

            <div class="nav-control">
                <div class="hamburger">
                     <span class="line"></span><span class="line"></span><span class="line"></span> 
                </div>
            </div>
        </div>
        <!--**********************************
            Nav header end
        ***********************************-->

        <!--**********************************
            Header start
        ***********************************-->
        <div class="header">
            <div class="header-content">
                <nav class="navbar navbar-expand">
                    <div class="collapse navbar-collapse justify-content-between">
						
						<div class="header-left">
							<div class="search_bar dropdown">
							<!-- 
								<a style="font-size: 30px; color: black;" href="./">
									정보 조회
								</a>
								<a style="font-size: 30px; color: black;" href="./">
									<div style="font-size: 30px; color: black; padding-left: 30px">조직 관리</div>
								</a>
								<a style="font-size: 30px; color: black;" href="./">
									<div style="font-size: 30px; color: black; padding-left: 30px">교육 관리</div>
								</a>
								-->
							</div>
						</div> 
						

						<ul class="navbar-nav header-right">
                            <li class="nav-item dropdown header-profile">
                                <a class="nav-link" href="#" role="button" data-toggle="dropdown">
                                    <i class="mdi mdi-account"></i>
                                </a>
                                <div class="dropdown-menu dropdown-menu-right">
                                    <a href="/emp/detail?empNo=${sessionEmp.empNo }" class="dropdown-item">
                                        <i class="icon-user"></i>
                                        <span class="ml-2">
                                        <sec:authorize access="isAuthenticated()">
                                        	<sec:authentication property="principal.username" />
                                        </sec:authorize>
                                       ${sessionEmp.empName}님
                                        </span>
                                    </a>
                                    <a href="/selfLogout" class="dropdown-item"> 
                                        <i class="icon-key"></i>
                                        <span class="ml-2">Logout </span>
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
        <!--**********************************
            Header end ti-comment-alt
        ***********************************-->