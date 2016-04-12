//學歷
$(function() {
	$("#newEduInfo").click(function() {
		$("#newEduTeaCode").val($(this).attr("data-newEduTeaCode"));
	});
});

$(function() {
	$(".updateTeaEduInfo").click(function() {
		$("#updateEduTeaCode").val($(this).attr("data-updateEduTeaCode"));
		$("#updateEduCode").val($(this).attr("data-updateEduCode"));
		$("#updateEduTeaSch").val($(this).attr("data-eduSch"));
		$("#updateEduTeaDep").val($(this).attr("data-eduDep"));
		$("#updateEduTeaDeg").val($(this).attr("data-eduDeg"));
	});
});

$(function() {
	$(".deleteTeaEduInfo").click(function() {
		$("#deleteEduTeaCode").val($(this).attr("data-deleteEduTeaCode"));
		$("#deleteEduCode").val($(this).attr("data-deleteEduCode"));
	});
});

// 經歷
$(function() {
	$("#newTeaExpInfo").click(function() {
		$("#newExpTeaCode").val($(this).attr("data-newExpTeaCode"));
	});
});

$(function() {
	$(".updateTeaExpInfo").click(function() {
		$("#updateExpTeaCode").val($(this).attr("data-updateExpTeaCode"));
		$("#updateTeaExpCode").val($(this).attr("data-UpdateTeaExpCode"));
		$("#updateTeaExpPer").val($(this).attr("data-per"));
		$("#updateTeaExpUnit").val($(this).attr("data-unit"));
		$("#updateTeaExpDep").val($(this).attr("data-dep"));
		$("#updateTeaExpTitle").val($(this).attr("data-title"));
	});
});

$(function() {
	$(".deleteTeaExpInfo").click(function() {
		$("#deleteExpTeaCode").val($(this).attr("data-deleteExpTeaCode"));
		$("#deleteTeaExpCode").val($(this).attr("data-deleteTeaExpCode"));
	});
});

// 專長
$(function() {
	$("#newSpeInfo").click(function() {
		$("#newSpeTeaCode").val($(this).attr("data-newSpeTeaCode"));
	});
});

$(function() {
	$(".updateTeaSpeInfo").click(function() {
		$("#updateSpeTeaCode").val($(this).attr("data-updateSpeTeaCode"));
		$("#updateTeaSpeCode").val($(this).attr("data-updateTeaSpeCode"));
		$("#updateTeaSpe").val($(this).attr("data-updateTeaSpe"));
	});
});

$(function() {
	$(".deleteTeaSpeInfo").click(function() {
		$("#deleteExpTeaCode").val($(this).attr("data-deleteExpTeaCode"));
		$("#deleteTeaExpCode").val($(this).attr("data-deleteTeaExpCode"));
	});
});
