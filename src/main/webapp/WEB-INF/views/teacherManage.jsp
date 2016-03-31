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
<title>教師管理</title>

</head>
<body>
	<%@include file="jspf/MISwebsiteNavbar.jspf"%>
	<div class="container theme-showcase" role="main">

		<div class="jumbotron">
			<h1>管理教師信息</h1>
			<p class="lead">專任教師</p>
		</div>
	<div class="container">
		<div class="row">
			<br>
			<div class="col-md-12">
	  			<!-- <a class="btn btn-primary" href="insertTeacher">新增</a> -->
				<table class="table">
				  	<tr>
				  		<th>教師編號</th>
				  		<th>姓名</th>
				  		<th>姓名英文</th>
				  		<th>職稱</th>
				  		<th>聯絡電話</th>
				  		<th>郵箱</th>
				  		<th>辦公地址</th>
				  		<th>編輯</th>
				  	</tr>
				  	<c:forEach items="${teacherList}" var="teacherInfo">
					  	<tr>
					  		<td>${teacherInfo.teaCode}</td>
					  		<td>${teacherInfo.teaName}</td>
					  		<td>${teacherInfo.teaENName}</td>
					  		<td>${teacherInfo.teaPos}</td>
					  		<td>${teacherInfo.teaTel}</td>
					  		<td>${teacherInfo.teaEmail}</td>
					  		<td>${teacherInfo.teaLoc}</td>
					  		<td>
					  			<a class="btn btn-default" href="updateTeacherInfo?teaCode=${teacherInfo.teaCode}">修改</a>
					  			<a class="btn btn-sm btn-danger deleteBtn" href="#" data-toggle="modal" data-target="#deleteModal" data-id="${teacherInfo.teaCode}">刪除</a>
					  		</td>
					  	</tr>
				  	</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<div class="modal fade bs-example-modal-sm" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">刪除</h4>
                </div>
                <div class="modal-body">
                    <p>確認刪除後，相關之訊息也將刪除</p>
                </div>
                <div class="modal-footer">
	                <form id="deleteForm" action="deleteTeacherInfo" method="post">
	            		<input type="hidden" name="Code" id="code">
	                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	                    <button type="submit" class="btn btn-danger">確認刪除</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
	

    </div><!-- /.container -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
    $(function(){
    	$(".deleteBtn").click(function(){
    		//alert($(this).attr("data-id"));
    		$("#code").val($(this).attr("teaCode"));
    	});
    });
    </script>


</body>
</html>
