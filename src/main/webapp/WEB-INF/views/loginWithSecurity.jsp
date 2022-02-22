<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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
</head>
	<style>
	.authincation {
		height: 100vh;
		background-image: url('/assets/images/metanet.jpg'); 
		height: 800px; 
		background-repeat : no-repeat; 
		background-size : cover;
	}
	</style>
<body>
    <div class="authincation" >
        <div class="container-fluid">
            <div class="row justify-content-center align-items-center">
                <div class="col-md-6">
                    <div class="authincation-content">
                        <div class="row no-gutters" style="margin-top: 200px;">
                            <div class="col-xl-12" >
                                <div class="auth-form" >
                                    <h4 class="text-center mb-4">메타넷 인사관리시스템 로그인</h4>
                                    <form method="post">
                                        <div class="form-group">
                                            <label><strong>아이디</strong></label>
                                            <input type="text" class="form-control" name="username">
                                        </div>
                                        <div class="form-group">
                                            <label><strong>패스워드</strong></label>
                                            <input type="password" class="form-control" name="password">
                                        </div>
                                      
                                        <div class="text-center">
                                            <button type="submit" class="btn btn-primary btn-block">로그인</button>
                                        </div>
                                    </form>
                                    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <!--**********************************
        Scripts
    ***********************************-->
    <!-- Required vendors -->
    <script src="./vendor/global/global.min.js"></script>
    <script src="./js/quixnav-init.js"></script>
    <script src="./js/custom.min.js"></script>


        <!--**********************************
            Footer start
        ***********************************-->
        <div class="footer" style="padding-left: 1em;">
            <div class="copyright">
                <p>Copyright Designed &amp; Developed by <a href="#" target="_blank">Quixkit</a> 2022</p>
                <p>Distributed by <a href="https://themewagon.com/" target="_blank">Themewagon</a></p> 
            </div>
        </div>
        <!--**********************************
            Footer end
        ***********************************-->

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
    <script src="/assets/vendor/global/global.min.js"></script>
    <script src="/assets/js/quixnav-init.js"></script>
    <script src="/assets/js/custom.min.js"></script>


    <!-- Vectormap -->
    <script src="/assets/vendor/raphael/raphael.min.js"></script>
    <script src="/assets/vendor/morris/morris.min.js"></script>


    <script src="/assets/vendor/circle-progress/circle-progress.min.js"></script>
    <script src="/assets/vendor/chart.js/Chart.bundle.min.js"></script>

    <script src="/assets/vendor/gaugeJS/dist/gauge.min.js"></script>

    <!--  flot-chart js -->
    <script src="/assets/vendor/flot/jquery.flot.js"></script>
    <script src="/assets/vendor/flot/jquery.flot.resize.js"></script>

    <!-- Owl Carousel -->
    <script src="/assets/vendor/owl-carousel/js/owl.carousel.min.js"></script>

    <!-- Counter Up -->
    <script src="/assets/vendor/jqvmap/js/jquery.vmap.min.js"></script>
    <script src="/assets/vendor/jqvmap/js/jquery.vmap.usa.js"></script>
    <script src="/assets/vendor/jquery.counterup/jquery.counterup.min.js"></script>

    <script src="/assets/js/dashboard/dashboard-1.js"></script>
    
    <!-- Datatable -->
    <script src="/assets/vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script src="/assets/js/plugins-init/datatables.init.js"></script>

</body>

</html>

