<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Update TeacherInfo</title>
</head>
<body>
	<%@include file="jspf/MISwebsiteNavbar.jspf"%>
	<div class="container theme-showcase" role="main">

		<div class="jumbotron">
			<h1>管理教師信息</h1>
			<p class="lead">更新教師信息</p>
		</div>
		<div class="container">
			<div class="row">
				<br>
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<form method="post" action="updateTeacherInfo" id="updateForm">
						<input type="hidden" name="teaCode" value="${updateInfo.teaCode}">
						<input type="hidden" name="teaLDAP" value="${updateInfo.teaLDAP}">

						<div class="form-group">
							<label>姓名:</label> <input type="text" name="teaName"
								value="${updateInfo.teaName}" required>
							<p class="help-block">教師姓名</p>
						</div>

						<div class="form-group">
							<label>姓名英文:</label> <input type="text" name="teaENName"
								value="${updateInfo.teaENName}" required>
							<p class="help-block">教師姓名英文</p>
						</div>

						<div class="form-group">
							<label for="category">職稱:</label> <select class="form-control"
								name="teaPos">
								<option value="DA" ${updateInfo.teaPos=="教授兼系主任"?'selected':''}>教授兼系主任</option>
								<option value="DD" ${updateInfo.teaPos=="副教授兼系主任"?'selected':''}>副教授兼系主任</option>
								<option value="DQ" ${updateInfo.teaPos=="副教授"?'selected':''}>副教授</option>
								<option value="DR" ${updateInfo.teaPos=="助理教授"?'selected':''}>助理教授</option>
								<option value="DS" ${updateInfo.teaPos=="講師"?'selected':''}>講師</option>
								<option value="DT" ${updateInfo.teaPos=="教授"?'selected':''}>教授</option>
								<option value="DZ" ${updateInfo.teaPos=="離校"?'selected':''}>離校</option>
							</select>
						</div>

						<div class="form-group">
							<label>電話:</label> <input type="text" name="teaTel" 
								value="${updateInfo.teaTel}" required>
							<p class="help-block">教師聯絡電話</p>
						</div>
						
						<div class="form-group">
							<label>E-MAIL:</label> <input type="text" name="teaEmail"
								 value="${updateInfo.teaEmail}" required>
							<p class="help-block">教師聯絡郵箱</p>
						</div>
						
						<div class="form-group">
							<label>辦公地址:</label> <input type="text" name="teaLoc"
								 value="${updateInfo.teaLoc}" required>
							<p class="help-block">教師辦公地址</p>
						</div>
						

						<button type="submit" class="btn btn-primary">修改</button>
					</form>
				</div>
				<div class="col-md-3"></div>
			</div>
		</div>
	</div>
	<!-- /.container -->
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.validate.js"></script>
	<script src="js/additional-methods.js"></script>
	<script src="js/messages_zh_TW.js"></script>
	<script>
		$("#updateForm").validate();
	</script>
</body>
</html>