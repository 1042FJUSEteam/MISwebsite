//交換順序機制有問題，暫時不用

//學歷
$(function() {
	$(".eduUp").click(function() {
		var $tr = $(this).parents("tr");
		if ($tr.index() != 1) {
			$tr.fadeOut().fadeIn();
			$tr.prev().before($tr).fadeOut().fadeIn();
			var i = $tr.find("#teaEduCode").val();
			var j = $tr.next().find("#teaEduCode").val();
			var teaCode = $tr.find("#teaCode").val();
			// 前端交換順序
			$tr.find("#teaEduCode").val(j);
			$tr.next().find("#teaEduCode").val(i);
		}
	})

	$(".eduDown").click(function() {
		var $tr = $(this).parents("tr");
		if ($tr.index() != $("#down").length - 1) {
			$tr.fadeOut().fadeIn();
			$tr.next().after($tr).fadeOut().fadeIn();
			var i = $tr.find("#teaSort").val();
			var j = $tr.prev().find("#teaSort").val();
			//前端交換順序
			$tr.find("#teaSort").val(j);
			$tr.prev().find("#teaSort").val(i);
		}
	})

})
