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
		<div class="row margin-top-30"></div>
		<!-- 左側顯示可編輯欄目 -->
		<!-- 此處欄位先寫死，應該是可以從資料庫抓取 -->
		<div class="col-sm-3">
			<ul class="nav nav-pills nav-stacked">
				<li class=""><a href="teacherManage"><i
						class="fa fa-home fa-fw"></i> 返回主界面</a></li>
				<li class="active"><a href="#career" data-toggle="tab"><i
						class="fa fa-briefcase fa-fw"></i> 現職相關資料</a></li>
				<li class=""><a href="#researchResult" data-toggle="tab"><i
						class="fa fa-pencil fa-fw"></i> 研究成果</a></li>
				<li class=""><a href="#teaching" data-toggle="tab"><i
						class="fa fa-comments fa-fw"></i> 教學</a></li>
				<li class=""><a href="#service" data-toggle="tab"><i
						class="fa fa-user fa-fw"></i> 服務</a></li>
			</ul>
			<div class="clearfix"></div>
		</div>
		<!-- 右側顯示詳情部分 -->
		<div class="col-md-9">
			<div class="tab-content">
				<!-- 現職相關資料 -->
				<div class="tab-pane fade active in" id="career">
					<div class="tabs">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#teaEdu" data-toggle="tab">學歷</a></li>
							<li class=""><a href="#teaExp" data-toggle="tab">經歷</a></li>
							<li class=""><a href="#teaSpe" data-toggle="tab">專長</a></li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane fade active in" id="teaEdu">
								<a class="btn btn-default new" href="#" data-toggle="modal"
									data-target="#newTeaEdu" data-teaCode="${teacher.teaCode}">新增</a>

								<div class="row margin-top-20"></div>

								<table class="table table-hover" width="100%">
									<tr>
										<th>學校</th>
										<th>系所</th>
										<th>學位</th>
										<th>編輯</th>
										<th>更改顯示位置</th>
									</tr>
									<c:forEach items="${teacherEduInfo}" var="teacherEduInfo">
										<tr>
											<td>${teacherEduInfo.teaSch}</td>
											<td>${teacherEduInfo.teaDep}</td>
											<td>${teacherEduInfo.teaDeg}</td>
											<td><a class="btn btn-sm btn-default" href="#"
												id="updateTeaEduInfo" data-toggle="modal"
												data-target="#updateTeaEdu"
												data-teaCode="${teacher.teaCode}"
												data-eduCode="${teacherEduInfo.teaEduCode}"
												data-sch="${teacherEduInfo.teaSch}"
												data-dep="${teacherEduInfo.teaDep}"
												data-deg="${teacherEduInfo.teaDeg}">修改</a> <a
												class="btn btn-sm btn-danger deleteBtn" href="#"
												id="deleteTeaEduInfo" data-toggle="modal"
												data-target="#deleteTeaEdu"
												data-teaCode="${teacher.teaCode}"
												data-eduCode="${teacherEduInfo.teaEduCode}">刪除</a></td>
											<td>暫未開發</td>
										</tr>
									</c:forEach>
								</table>
								<div class="clearfix"></div>
							</div>
							<div class="tab-pane fade" id="teaExp">

								<div class="clearfix"></div>
							</div>
							<div class="tab-pane fade" id="teaSpe">

								<div class="clearfix"></div>
							</div>
						</div>
					</div>
				</div>
				<!-- 研究成果 -->
				<div class="tab-pane fade" id="researchResult">
					<div class="tabs">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#scholarWriting"
								data-toggle="tab">學術著作</a></li>
							<li class=""><a href="#teaAwa" data-toggle="tab">研究獎勵</a></li>
							<li class=""><a href="#teaPlan" data-toggle="tab">研究計劃</a></li>
							<li class=""><a href="#practical" data-toggle="tab">實務研究</a></li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane fade active in" id="scholarWriting">
								<div class="row margin-top-20"></div>
								<div class="tabs alternative">
									<ul class="nav nav-tabs">
										<li class="active"><a href="#IssuePaper"
											data-toggle="tab">期刊論文</a></li>
										<li class=""><a href="#MeetingPaper" data-toggle="tab">研討會論文</a></li>
										<li class=""><a href="#Books" data-toggle="tab">書籍</a></li>
										<li class=""><a href="#TechReport" data-toggle="tab">技術報告</a></li>
										<li class=""><a href="#TecherPaper" data-toggle="tab">畢業論文</a></li>
										<li class=""><a href="#MagazinePaper" data-toggle="tab">在資訊相關雜誌上近幾年發表之文章</a></li>
										<li class=""><a href="#WaitingPaper" data-toggle="tab">期刊審查中論文</a></li>
										<li class=""><a href="#ScholarPaper" data-toggle="tab">學術著作</a></li>
									</ul>
									<div class="tab-content">
										<div class="tab-pane fade active in" id="IssuePaper">
											<div class="clearfix"></div>
										</div>
										<div class="tab-pane fade" id="MeetingPaper">
											<div class="clearfix"></div>
										</div>
										<div class="tab-pane fade" id="Books">
											<div class="clearfix"></div>
										</div>
										<div class="tab-pane fade" id="TechReport">
											<div class="clearfix"></div>
										</div>
										<div class="tab-pane fade active in" id="TecherPaper">
											<div class="clearfix"></div>
										</div>
										<div class="tab-pane fade" id="MagazinePaper">
											<div class="clearfix"></div>
										</div>
										<div class="tab-pane fade" id="WaitingPaper">
											<div class="clearfix"></div>
										</div>
										<div class="tab-pane fade" id="ScholarPaper">
											<div class="clearfix"></div>
										</div>
									</div>
								</div>
								<div class="clearfix"></div>
							</div>
							<div class="tab-pane fade" id="teaAwa">

								<div class="clearfix"></div>
							</div>
							<div class="tab-pane fade" id="teaPlan">

								<div class="clearfix"></div>
							</div>
							<div class="tab-pane fade" id="practical">

								<div class="clearfix"></div>
							</div>
						</div>
					</div>
				</div>
				<!-- 教學 -->
				<div class="tab-pane fade" id="teaching">
					<div class="tabs">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#teaStuTopic" data-toggle="tab">指導學生專題</a></li>
							<li class=""><a href="#teaStuPaper" data-toggle="tab">指導研究生專題</a></li>
							<li class=""><a href="#otherTeaExp" data-toggle="tab">其他教學經驗</a></li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane fade active in" id="teaStuTopic">

								<div class="clearfix"></div>
							</div>
							<div class="tab-pane fade" id="teaStuPaper">

								<div class="clearfix"></div>
							</div>
							<div class="tab-pane fade" id="otherTeaExp">

								<div class="clearfix"></div>
							</div>
						</div>
					</div>
				</div>
				<!-- 服務 -->
				<div class="tab-pane fade" id="service">
					<div class="tabs">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#inSchService" data-toggle="tab">校內服務</a></li>
							<li class=""><a href="#outSchService" data-toggle="tab">校外服務</a></li>
							<li class=""><a href="#asCommitMem" data-toggle="tab">擔任委員</a></li>
							<li class=""><a href="#scholarExp" data-toggle="tab">學術經驗</a></li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane fade active in" id="inSchService">

								<div class="clearfix"></div>
							</div>
							<div class="tab-pane fade" id="outSchService">

								<div class="clearfix"></div>
							</div>
							<div class="tab-pane fade" id="asCommitMem">

								<div class="clearfix"></div>
							</div>
							<div class="tab-pane fade" id="scholarExp">

								<div class="clearfix"></div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>

		<!-- 所有删除动作皆在此处 -->
		<div class="modal fade bs-example-modal-sm" id="deleteTeaEdu"
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
			aria-hidden="true">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">刪除學歷信息</h4>
					</div>
					<div class="modal-body">
						<p>該條學歷信息刪除後，將不可恢復</p>
					</div>
					<div class="modal-footer">
						<form id="deleteTeaEduForm" action="deleteTeaEdu" method="post">
							<input name="teaCode" id="teaCode"> <input
								name="teaEduCode" id="teaEduCode">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<button type="submit" class="btn btn-danger">確認</button>
						</form>
					</div>
				</div>
			</div>
		</div>

		<!-- 所有修改动作皆在此处 -->
		<div class="modal fade bs-example-modal-sm" id="updateTeaEdu"
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
			aria-hidden="true">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">修改學歷信息</h4>
					</div>
					<form id="updateTeaEduForm" action="updateTeaEdu" method="post">
						<div class="modal-body">
							<input name="teaCode" id="teaCode"> <input
								name="teaEduCode" id="teaEduCode">
							<div class="form-group">
								<label>學校</label> <input type="text" name="teaSch" id="teaSch"
									class="form-control margin-buttom-10" required>
							</div>
							<div class="form-group">
								<label>系所</label> <input type="text" name="teaDep" id="teaDep"
									class="form-control margin-buttom-10" required>
							</div>
							<div class="form-group">
								<label>學位</label> <input type="text" name="teaDeg" id="teaDeg"
									class="form-control margin-buttom-10" required>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<button type="submit" class="btn btn-primary">確認</button>
						</div>
					</form>
				</div>
			</div>
		</div>

		<!-- 所有新增动作皆在此处 -->
		<div class="modal fade bs-example-modal-sm" id="newTeaEdu"
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
			aria-hidden="true">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">修改學歷信息</h4>
					</div>
					<form id="newTeaEduForm" action="newTeaEdu" method="post">
						<div class="modal-body">
							<input name="teaCode" id="teaCode">
							<div class="form-group">
								<label>學校</label> <input type="text" name="teaSch" id="teaSch"
									class="form-control margin-buttom-10" required>
							</div>
							<div class="form-group">
								<label>系所</label> <input type="text" name="teaDep" id="teaDep"
									class="form-control margin-buttom-10" required>
							</div>
							<div class="form-group">
								<label>學位</label> <input type="text" name="teaDeg" id="teaDeg"
									class="form-control margin-buttom-10" required>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<button type="submit" class="btn btn-primary">確認</button>
						</div>
					</form>
				</div>
			</div>
		</div>

	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<!-- 所有刪除內容在此 -->
	<script>
		$(function() {
			//只能取到最頂端的值，不會變
			$("#deleteTeaEduInfo").click(function() {
				$("#teaCode").val($(this).attr("data-teaCode"));
				$("#teaEduCode").val($(this).attr("data-eduCode"));
			});
		});
	</script>

	<!-- 所有更新內容在此 -->
	<script>
		$(function() {
			//前兩個值不顯示
			$("#updateTeaEduInfo").click(function() {
				$("#teaCode").val($(this).attr("data-teaCode"));
				$("#teaEduCode").val($(this).attr("data-eduCode"));
				$("#teaSch").val($(this).attr("data-sch"));
				$("#teaDep").val($(this).attr("data-dep"));
				$("#teaDeg").val($(this).attr("data-deg"));
			});
		});
	</script>

	<!-- 所有新增內容在此 -->
	<script>
		$(function() {
			//前兩個值不顯示
			$(".new").click(function() {
				$("#teaCode").val($(this).attr("data-teaCode"));
			});
		});
	</script>
</body>

<!-- JS -->
<script type="text/javascript" src="js/jquery.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="js/bootstrap.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="js/scripts.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript" src="js/additional-methods.js"></script>
<script type="text/javascript" src="js/messages_zh_TW.js"></script>
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


