<!-- <!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Focus - Bootstrap Admin Dashboard </title>
    Favicon icon
    <link rel="icon" type="image/png" sizes="16x16" href="./images/favicon.png">
    Custom Stylesheet
    <link href="./css/style.css" rel="stylesheet">

</head>

<body>

    *******************
        Preloader start
    ********************
    <div id="preloader">
        <div class="sk-three-bounce">
            <div class="sk-child sk-bounce1"></div>
            <div class="sk-child sk-bounce2"></div>
            <div class="sk-child sk-bounce3"></div>
        </div>
    </div>
    *******************
        Preloader end
    ********************


    **********************************
        Main wrapper start
    ***********************************
    <div id="main-wrapper">

        **********************************
            Nav header start
        ***********************************
        <div class="nav-header">
            <a href="index.html" class="brand-logo">
                <img class="logo-abbr" src="./images/logo.png" alt="">
                <img class="logo-compact" src="./images/logo-text.png" alt="">
                <img class="brand-title" src="./images/logo-text.png" alt="">
            </a>

            <div class="nav-control">
                <div class="hamburger">
                    <span class="line"></span><span class="line"></span><span class="line"></span>
                </div>
            </div>
        </div>
        **********************************
            Nav header end
        ***********************************

        **********************************
            Header start
        ***********************************
        <div class="header">
            <div class="header-content">
                <nav class="navbar navbar-expand">
                    <div class="collapse navbar-collapse justify-content-between">
                        <div class="header-left">
                            <div class="search_bar dropdown">
                                <span class="search_icon p-3 c-pointer" data-toggle="dropdown">
                                    <i class="mdi mdi-magnify"></i>
                                </span>
                                <div class="dropdown-menu p-0 m-0">
                                    <form>
                                        <input class="form-control" type="search" placeholder="Search" aria-label="Search">
                                    </form>
                                </div>
                            </div>
                        </div>

                        <ul class="navbar-nav header-right">
                            <li class="nav-item dropdown notification_dropdown">
                                <a class="nav-link" href="#" role="button" data-toggle="dropdown">
                                    <i class="mdi mdi-bell"></i>
                                    <div class="pulse-css"></div>
                                </a>
                                <div class="dropdown-menu dropdown-menu-right">
                                    <ul class="list-unstyled">
                                        <li class="media dropdown-item">
                                            <span class="success"><i class="ti-user"></i></span>
                                            <div class="media-body">
                                                <a href="#">
                                                    <p><strong>Martin</strong> has added a <strong>customer</strong> Successfully
                                                    </p>
                                                </a>
                                            </div>
                                            <span class="notify-time">3:20 am</span>
                                        </li>
                                        <li class="media dropdown-item">
                                            <span class="primary"><i class="ti-shopping-cart"></i></span>
                                            <div class="media-body">
                                                <a href="#">
                                                    <p><strong>Jennifer</strong> purchased Light Dashboard 2.0.</p>
                                                </a>
                                            </div>
                                            <span class="notify-time">3:20 am</span>
                                        </li>
                                        <li class="media dropdown-item">
                                            <span class="danger"><i class="ti-bookmark"></i></span>
                                            <div class="media-body">
                                                <a href="#">
                                                    <p><strong>Robin</strong> marked a <strong>ticket</strong> as unsolved.
                                                    </p>
                                                </a>
                                            </div>
                                            <span class="notify-time">3:20 am</span>
                                        </li>
                                        <li class="media dropdown-item">
                                            <span class="primary"><i class="ti-heart"></i></span>
                                            <div class="media-body">
                                                <a href="#">
                                                    <p><strong>David</strong> purchased Light Dashboard 1.0.</p>
                                                </a>
                                            </div>
                                            <span class="notify-time">3:20 am</span>
                                        </li>
                                        <li class="media dropdown-item">
                                            <span class="success"><i class="ti-image"></i></span>
                                            <div class="media-body">
                                                <a href="#">
                                                    <p><strong> James.</strong> has added a<strong>customer</strong> Successfully
                                                    </p>
                                                </a>
                                            </div>
                                            <span class="notify-time">3:20 am</span>
                                        </li>
                                    </ul>
                                    <a class="all-notification" href="#">See all notifications <i
                                            class="ti-arrow-right"></i></a>
                                </div>
                            </li>
                            <li class="nav-item dropdown header-profile">
                                <a class="nav-link" href="#" role="button" data-toggle="dropdown">
                                    <i class="mdi mdi-account"></i>
                                </a>
                                <div class="dropdown-menu dropdown-menu-right">
                                    <a href="./app-profile.html" class="dropdown-item">
                                        <i class="icon-user"></i>
                                        <span class="ml-2">Profile </span>
                                    </a>
                                    <a href="./email-inbox.html" class="dropdown-item">
                                        <i class="icon-envelope-open"></i>
                                        <span class="ml-2">Inbox </span>
                                    </a>
                                    <a href="./page-login.html" class="dropdown-item">
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
        **********************************
            Header end ti-comment-alt
        ***********************************

        **********************************
            Sidebar start
        ***********************************
        <div class="quixnav">
            <div class="quixnav-scroll">
                <ul class="metismenu" id="menu">
                    <li class="nav-label first">Main Menu</li>
                    <li><a href="index.html"><i class="icon icon-single-04"></i><span class="nav-text">Dashboard</span></a>
                    </li>
                    <li><a class="has-arrow" href="javascript:void()" aria-expanded="false"><i
                                class="icon icon-single-04"></i><span class="nav-text">Dashboard</span></a>
                        <ul aria-expanded="false">
                            <li><a href="./index.html">Dashboard 1</a></li>
                            <li><a href="./index2.html">Dashboard 2</a></li></ul>
                    </li>
                    
                    <li class="nav-label">Apps</li>
                    <li><a class="has-arrow" href="javascript:void()" aria-expanded="false"><i
                                class="icon icon-app-store"></i><span class="nav-text">Apps</span></a>
                        <ul aria-expanded="false">
                            <li><a href="./app-profile.html">Profile</a></li>
                            <li><a class="has-arrow" href="javascript:void()" aria-expanded="false">Email</a>
                                <ul aria-expanded="false">
                                    <li><a href="./email-compose.html">Compose</a></li>
                                    <li><a href="./email-inbox.html">Inbox</a></li>
                                    <li><a href="./email-read.html">Read</a></li>
                                </ul>
                            </li>
                            <li><a href="./app-calender.html">Calendar</a></li>
                        </ul>
                    </li>
                    <li><a class="has-arrow" href="javascript:void()" aria-expanded="false"><i
                                class="icon icon-chart-bar-33"></i><span class="nav-text">Charts</span></a>
                        <ul aria-expanded="false">
                            <li><a href="./chart-flot.html">Flot</a></li>
                            <li><a href="./chart-morris.html">Morris</a></li>
                            <li><a href="./chart-chartjs.html">Chartjs</a></li>
                            <li><a href="./chart-chartist.html">Chartist</a></li>
                            <li><a href="./chart-sparkline.html">Sparkline</a></li>
                            <li><a href="./chart-peity.html">Peity</a></li>
                        </ul>
                    </li>
                    <li class="nav-label">Components</li>
                    <li><a class="has-arrow" href="javascript:void()" aria-expanded="false"><i
                                class="icon icon-world-2"></i><span class="nav-text">Bootstrap</span></a>
                        <ul aria-expanded="false">
                            <li><a href="./ui-accordion.html">Accordion</a></li>
                            <li><a href="./ui-alert.html">Alert</a></li>
                            <li><a href="./ui-badge.html">Badge</a></li>
                            <li><a href="./ui-button.html">Button</a></li>
                            <li><a href="./ui-modal.html">Modal</a></li>
                            <li><a href="./ui-button-group.html">Button Group</a></li>
                            <li><a href="./ui-list-group.html">List Group</a></li>
                            <li><a href="./ui-media-object.html">Media Object</a></li>
                            <li><a href="./ui-card.html">Cards</a></li>
                            <li><a href="./ui-carousel.html">Carousel</a></li>
                            <li><a href="./ui-dropdown.html">Dropdown</a></li>
                            <li><a href="./ui-popover.html">Popover</a></li>
                            <li><a href="./ui-progressbar.html">Progressbar</a></li>
                            <li><a href="./ui-tab.html">Tab</a></li>
                            <li><a href="./ui-typography.html">Typography</a></li>
                            <li><a href="./ui-pagination.html">Pagination</a></li>
                            <li><a href="./ui-grid.html">Grid</a></li>

                        </ul>
                    </li>

                    <li><a class="has-arrow" href="javascript:void()" aria-expanded="false"><i
                                class="icon icon-plug"></i><span class="nav-text">Plugins</span></a>
                        <ul aria-expanded="false">
                            <li><a href="./uc-select2.html">Select 2</a></li>
                            <li><a href="./uc-nestable.html">Nestedable</a></li>
                            <li><a href="./uc-noui-slider.html">Noui Slider</a></li>
                            <li><a href="./uc-sweetalert.html">Sweet Alert</a></li>
                            <li><a href="./uc-toastr.html">Toastr</a></li>
                            <li><a href="./map-jqvmap.html">Jqv Map</a></li>
                        </ul>
                    </li>
                    <li><a href="widget-basic.html" aria-expanded="false"><i class="icon icon-globe-2"></i><span
                                class="nav-text">Widget</span></a></li>
                    <li class="nav-label">Forms</li>
                    <li><a class="has-arrow" href="javascript:void()" aria-expanded="false"><i
                                class="icon icon-form"></i><span class="nav-text">Forms</span></a>
                        <ul aria-expanded="false">
                            <li><a href="./form-element.html">Form Elements</a></li>
                            <li><a href="./form-wizard.html">Wizard</a></li>
                            <li><a href="./form-editor-summernote.html">Summernote</a></li>
                            <li><a href="form-pickers.html">Pickers</a></li>
                            <li><a href="form-validation-jquery.html">Jquery Validate</a></li>
                        </ul>
                    </li>
                    <li class="nav-label">Table</li>
                    <li><a class="has-arrow" href="javascript:void()" aria-expanded="false"><i
                                class="icon icon-layout-25"></i><span class="nav-text">Table</span></a>
                        <ul aria-expanded="false">
                            <li><a href="table-bootstrap-basic.html">Bootstrap</a></li>
                            <li><a href="table-datatable-basic.html">Datatable</a></li>
                        </ul>
                    </li>

                    <li class="nav-label">Extra</li>
                    <li><a class="has-arrow" href="javascript:void()" aria-expanded="false"><i
                                class="icon icon-single-copy-06"></i><span class="nav-text">Pages</span></a>
                        <ul aria-expanded="false">
                            <li><a href="./page-register.html">Register</a></li>
                            <li><a href="./page-login.html">Login</a></li>
                            <li><a class="has-arrow" href="javascript:void()" aria-expanded="false">Error</a>
                                <ul aria-expanded="false">
                                    <li><a href="./page-error-400.html">Error 400</a></li>
                                    <li><a href="./page-error-403.html">Error 403</a></li>
                                    <li><a href="./page-error-404.html">Error 404</a></li>
                                    <li><a href="./page-error-500.html">Error 500</a></li>
                                    <li><a href="./page-error-503.html">Error 503</a></li>
                                </ul>
                            </li>
                            <li><a href="./page-lock-screen.html">Lock Screen</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
        **********************************
            Sidebar end
        *********************************** -->

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
                                    <form class="form-valide" action="/admin/emp/updateEmp" method="post" enctype="multipart/form-data">
                                    <input type="hidden" name="empNo" value="${emp.empNo }">
                                    <input type="hidden" name="empPhoto" value="${emp.empPhoto }">
                                    <input type="hidden" name="empRePhoto" value="${emp.empRePhoto }">
                                        <div class="row">
                                            <div class="col-xl-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-4 col-form-label" for="upfile">사원 이미지
                                                        <span class="text-danger">*</span>
                                                    </label>
                                                    <div class="col-lg-6">
                                                        ${emp.empPhoto } <input type="file" class="form-control" id="upfile" name="upfile"> 
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label class="col-lg-4 col-form-label" for="empName">이름 <span
                                                            class="text-danger">*</span>
                                                    </label>
                                                    <div class="col-lg-6">
                                                        <input type="text" class="form-control" id="empName" name="empName" value="${emp.empName }">
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group row">
                                                    <label class="col-lg-4 col-form-label"><a
                                                            href="javascript:void()">성별</a> <span
                                                            class="text-danger">*</span>
                                                    </label>
                                                    <c:if test="${ emp.empGender eq '여' }">
                                                    <div class="col-lg-8">
                                                            <input type="radio" name="empGender" value="여" checked> 여자 &nbsp;
                                                        	<input type="radio" name="empGender" value="남">남자
                                                    </div>
                                                    </c:if>
                                                    <c:if test="${ emp.empGender eq '남' }">
                                                    <div class="col-lg-8">
                                                            <input type="radio" name="empGender" value="여"> 여자 &nbsp;
                                                        	<input type="radio" name="empGender" value="남" checked>남자
                                                    </div>
                                                    </c:if>
                                                </div>
                                                
                                                <div class="form-group row">
                                                    <label class="col-lg-4 col-form-label" for="empPhone">전화번호
                                                        <span class="text-danger">*</span>
                                                    </label>
                                                    <div class="col-lg-6">
                                                        <input type="tel" class="form-control" id="empPhone" name="empPhone" value="${emp.empPhone }" placeholder="'-' 포함 입력해주세요.">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label class="col-lg-4 col-form-label" for="empEmail">이메일 <span
                                                            class="text-danger">*</span>
                                                    </label>
                                                    <div class="col-lg-6">
                                                        <input type="email" class="form-control" id="empEmail" name="empEmail" value="${emp.empEmail }">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label class="col-lg-4 col-form-label" for="empHiredate">입사일 <span
                                                            class="text-danger">*</span>
                                                    </label>
                                                    <div class="col-lg-6">
                                                        <input type="date" class="form-control" id="empHiredate" name="empHiredate" value="${emp.empHiredate }">
                                                    </div>
                                                </div>
                                                 </div>
                                                 <div class="col-xl-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-4 col-form-label" for="empRetdate">퇴사일 </label>
                                                    <div class="col-lg-6">
                                                        <input type="date" class="form-control" id="empRetdate" name="empRetdate" value="${emp.empRetdate }">
                                                    </div>
                                                </div>
                                            
                                           
                                            
                                           
                                            <div class="form-group row">
                                                    <label class="col-lg-4 col-form-label" for="empSsc">주민번호
                                                        <span class="text-danger">*</span>
                                                    </label>
                                                    <div class="col-lg-6">
                                                        <input type="text" class="form-control" id="empSsc" name="empSsc" value="${emp.empSsc }" placeholder="'-' 포함 입력해주세요.">
                                                    </div>
                                            </div>
                                            
                                                
                                            
                                                <div class="form-group row">
                                                    <label class="col-lg-4 col-form-label" for="empDegree">최종학력
                                                        <span class="text-danger">*</span>
                                                    </label>
                                                    <c:if test="${ emp.empDegree eq '고졸'}">
                                                    <div class="col-lg-6">
                                                        <select class="form-control" id="empDegree" name="empDegree">
                                                            <option value="">최종학력을 선택해주세요</option>
                                                            <option value="고졸" selected>고졸</option>
                                                            <option value="학사">학사</option>
                                                            <option value="석사">석사</option>
                                                            <option value="박사">박사</option>
                                                        </select>
                                                    </div>
                                                    </c:if>
                                                    <c:if test="${ emp.empDegree eq '학사'}">
                                                    <div class="col-lg-6">
                                                        <select class="form-control" id="empDegree" name="empDegree">
                                                            <option value="">최종학력을 선택해주세요</option>
                                                            <option value="고졸">고졸</option>
                                                            <option value="학사" selected>학사</option>
                                                            <option value="석사">석사</option>
                                                            <option value="박사">박사</option>
                                                        </select>
                                                    </div>
                                                    </c:if>
                                                    <c:if test="${ emp.empDegree eq '석사'}">
                                                    <div class="col-lg-6">
                                                        <select class="form-control" id="empDegree" name="empDegree">
                                                            <option value="">최종학력을 선택해주세요</option>
                                                            <option value="고졸">고졸</option>
                                                            <option value="학사">학사</option>
                                                            <option value="석사" selected>석사</option>
                                                            <option value="박사">박사</option>
                                                        </select>
                                                    </div>
                                                    </c:if>
                                                    <c:if test="${ emp.empDegree eq '박사'}">
                                                    <div class="col-lg-6">
                                                        <select class="form-control" id="empDegree" name="empDegree">
                                                            <option value="">최종학력을 선택해주세요</option>
                                                            <option value="고졸">고졸</option>
                                                            <option value="학사">학사</option>
                                                            <option value="석사">석사</option>
                                                            <option value="박사" selected>박사</option>
                                                        </select>
                                                    </div>
                                                    </c:if>
                                                </div>
                                                
                                                <div class="form-group row">
                                                    <label class="col-lg-4 col-form-label"><a
                                                            href="javascript:void()">신입/경력</a> <span
                                                            class="text-danger">*</span>
                                                    </label>
                                                    <c:if test="${ emp.empCareer eq '신입' }">
                                                    <div class="col-lg-8">
                                                            <input type="radio" name="empCareer" value="신입" checked> 신입 &nbsp;
                                                        	<input type="radio" name="empCareer" value="경력">경력
                                                    </div>
                                                    </c:if>
                                                    <c:if test="${ emp.empCareer eq '경력' }">
                                                    <div class="col-lg-8">
                                                            <input type="radio" name="empCareer" value="신입"> 신입 &nbsp;
                                                        	<input type="radio" name="empCareer" value="경력" checked>경력
                                                    </div>
                                                    </c:if>
                                                </div>
                                                
                                                
                                                
                                                <div class="form-group row">
                                                    <label class="col-lg-4 col-form-label" for="empSal">연봉
                                                        <span class="text-danger">*</span>
                                                    </label>
                                                    <div class="col-lg-4"> 
                                                        <input type="text" class="form-control" id="empSal" name="empSal" value="${emp.empSal }">
                                                        
                                                    </div>
                                                    <div>만 원</div>
                                                </div>
                                                
                                                <div class="form-group row">
                                                    <label class="col-lg-4 col-form-label" for="empMil">병역
                                                        <span class="text-danger">*</span>
                                                    </label>
                                                    <c:if test="${ emp.empMil eq '미필' }">
                                                    <div class="col-lg-6">
                                                        <select class="form-control" id="empMil" name="empMil">
                                                            <option value="">병역을 선택해주세요</option>
                                                            <option value="미필" selected>미필</option>
                                                            <option value="군필">군필</option>
                                                            <option value="면제">면제</option>
                                                            <option value="해당없음">해당없음</option>
                                                        </select>
                                                    </div>
                                                    </c:if>
                                                    <c:if test="${ emp.empMil eq '군필' }">
                                                    <div class="col-lg-6">
                                                        <select class="form-control" id="empMil" name="empMil">
                                                            <option value="">병역을 선택해주세요</option>
                                                            <option value="미필">미필</option>
                                                            <option value="군필" selected>군필</option>
                                                            <option value="면제">면제</option>
                                                            <option value="해당없음">해당없음</option>
                                                        </select>
                                                    </div>
                                                    </c:if>
                                                    <c:if test="${ emp.empMil eq '면제' }">
                                                    <div class="col-lg-6">
                                                        <select class="form-control" id="empMil" name="empMil">
                                                            <option value="">병역을 선택해주세요</option>
                                                            <option value="미필">미필</option>
                                                            <option value="군필">군필</option>
                                                            <option value="면제" selected>면제</option>
                                                            <option value="해당없음">해당없음</option>
                                                        </select>
                                                    </div>
                                                    </c:if>
                                                    <c:if test="${ emp.empMil eq '해당없음' }">
                                                    <div class="col-lg-6">
                                                        <select class="form-control" id="empMil" name="empMil">
                                                            <option value="">병역을 선택해주세요</option>
                                                            <option value="미필">미필</option>
                                                            <option value="군필">군필</option>
                                                            <option value="면제">면제</option>
                                                            <option value="해당없음" selected>해당없음</option>
                                                        </select>
                                                    </div>
                                                    </c:if>
                                                </div>
                                                
                                                
                                                <div class="form-group row">
                                                    <div class="col-lg-8 ml-auto">
                                                        <input type="submit" class="btn btn-primary">
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