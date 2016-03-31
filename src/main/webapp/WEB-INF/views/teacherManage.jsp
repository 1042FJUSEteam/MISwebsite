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
<title>輔仁大學資訊管理學系管理后台</title>

</head>
<body>
	<%@include file="jspf/adminNavbar.jspf"%>

	<div id="content" class="container">

		<div class="row margin-vert-30">
			<div class="col-md-12">
				<h2>教師管理</h2>
				<div class="row margin-top-20">
					<div class="tabs">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#proTeacherManage"
								data-toggle="tab">專任教師管理</a></li>
							<li class=""><a href="#partTeacherManage" data-toggle="tab">兼任教師管理</a></li>
							<li class=""><a href="#newTeacher" data-toggle="tab">新增教師</a></li>
						</ul>
						<div class="tab-content">

							<div class="tab-pane fade active in" id="proTeacherManage">
								<div class="col-md-12">
									<table class="table table-hover" width="100%">
										<tr>
											<th>教師編號</th>
											<th>姓名</th>
											<th>姓名英文</th>
											<th>職稱</th>
											<th>聯絡電話</th>
											<th>郵箱</th>
											<th>辦公地址</th>
											<th>查看詳情</th>
											<th>編輯</th>
											<th></th>
										</tr>
										<c:forEach items="${proTeacherList}" var="proTeacherList">
											<tr>
												<td>${proTeacherList.teaCode}</td>
												<td>${proTeacherList.teaName}</td>
												<td>${proTeacherList.teaENName}</td>
												<td>${proTeacherList.teaPos}</td>
												<td>${proTeacherList.teaTel}</td>
												<td>${proTeacherList.teaEmail}</td>
												<td>${proTeacherList.teaLoc}</td>
												<td><a class="btn btn-default"
													href="updateTeacherDetail?teaCode=${proTeacherList.teaCode}">查看詳情</a></td>
												<td><a class="btn btn-default"
													href="updateTeacherInfo?teaCode=${proTeacherList.teaCode}">修改</a></td>
												<td><a class="btn btn-sm btn-danger deleteBtn" href="#"
													data-toggle="modal" data-target="#deleteModal"
													data-id="${proTeacherList.teaCode}">刪除</a></td>
											</tr>
										</c:forEach>
									</table>
								</div>
								<div class="clearfix"></div>
							</div>

							<div class="tab-pane fade" id="partTeacherManage"></div>

							<div class="tab-pane fade" id="newTeacher"></div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade bs-example-modal-sm" id="deleteModal"
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
			aria-hidden="true">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">刪除</h4>
					</div>
					<div class="modal-body">
						<p>確認刪除後，相關之訊息也將刪除</p>
					</div>
					<div class="modal-footer">
						<form id="deleteForm" action="deleteTeacherInfo" method="post">
							<input type="hidden" name="Code" id="code">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<button type="submit" class="btn btn-danger">確認刪除</button>
						</form>
					</div>
				</div>
			</div>
		</div>

	</div>
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


