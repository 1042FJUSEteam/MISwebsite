<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Meta -->
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="css/bootstrap.css" rel="stylesheet">
<!-- Template CSS -->
<link rel="stylesheet" href="css/animate.css" rel="stylesheet">
<link rel="stylesheet" href="css/font-awesome.css" rel="stylesheet">
<link rel="stylesheet" href="css/nexus.css" rel="stylesheet">
<link rel="stylesheet" href="css/responsive.css" rel="stylesheet">
<link rel="stylesheet" href="css/custom.css" rel="stylesheet">
<!-- Google Fonts -->
<link href="http://fonts.googleapis.com/css?family=Lato:400,300"
	rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300"
	rel="stylesheet" type="text/css">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>輔仁大學資訊管理學系</title>

</head>
<body>
	<%@include file="jspf/MISwebsiteNavbar.jspf"%>
	
	<div id="content" class="container">
		<div class="row margin-top-10"></div><!-- 用于控制与navbar间距 -->
		<div id="carousel-example" class="carousel slide" data-ride="carousel">
			<!-- Carousel Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#carousel-example" data-slide-to="0" class="active"></li>
				<li data-target="#carousel-example" data-slide-to="1"></li>
				<li data-target="#carousel-example" data-slide-to="2"></li>
			</ol>
			<!-- End Carousel Indicators -->
			<!-- Carousel Images -->
			<div class="carousel-inner">
				<div class="item active">
					<img src="1100.jpg">
				</div>
				<div class="item">
					<img src="img/1111.jpg">
				</div>
				<div class="item">
					<img src="img/1122.jpg">
				</div>
				<div class="item">
					<img src="img/1133.png">
				</div>
			</div>
			<!-- End Carousel Images -->
			<!-- Carousel Controls -->
			<a class="left carousel-control" href="#carousel-example"
				data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left"></span>
			</a> <a class="right carousel-control" href="#carousel-example"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right"></span>
			</a>
			<!-- End Carousel Controls -->
		</div>
		<!-- End Carousel Slideshow -->
		<div class="row margin-top-10"></div><!-- 用于控制与footer间距 -->
	</div>
	<%@include file="jspf/MISwebsiteFooter.jspf"%>
</body>

<!-- JS -->
<script type="text/javascript" src="js/jquery.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="js/bootstrap.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="js/scripts.js"></script>
<!-- Isotope - Portfolio Sorting -->
<script type="text/javascript" src="js/jquery.isotope.js"
	type="text/javascript"></script>
<!-- Mobile Menu - Slicknav -->
<script type="text/javascript" src="js/jquery.slicknav.js"
	type="text/javascript"></script>
<!-- Animate on Scroll-->
<script type="text/javascript" src="js/jquery.visible.js"
	charset="utf-8"></script>
<!-- Slimbox2-->
<script type="text/javascript" src="js/slimbox2.js" charset="utf-8"></script>
<!-- Modernizr -->
<script src="js/modernizr.custom.js" type="text/javascript"></script>
<!-- End JS -->

</html>


