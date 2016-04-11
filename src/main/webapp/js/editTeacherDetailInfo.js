//教師學歷信息
$(function() {
	// 只能取到最頂端的值，不會變，而且要先按第一个才能有第二个出来
	$("#deleteTeaEduInfo").click(
			function() {
				$("#deleteEduTeaCode").val(
						$("#deleteTeaEduInfo").attr("data-deleteEduTeaCode"));
				$("#deleteEduCode").val(
						$("#deleteTeaEduInfo").attr("data-deleteEduCode"));
			});
});

$(function() {
	$("#newEduInfo").click(function() {
		$("#newEduTeaCode").val($(this).attr("data-newEduTeaCode"));
	});
});

$(function() {
	// 只能取到最頂端的值，不會變，而且要先按第一个才能有第二个出来
	$("#updateTeaEduInfo").click(function() {
		$("#updateEduTeaCode").val($(this).attr("data-updateEduTeaCode"));
		$("#updateEduCode").val($(this).attr("data-updateEduCode"));
		$("#updateEduTeaSch").val($(this).attr("data-eduSch"));
		$("#updateEduTeaDep").val($(this).attr("data-eduDep"));
		$("#updateEduTeaDeg").val($(this).attr("data-eduDeg"));
	});
});