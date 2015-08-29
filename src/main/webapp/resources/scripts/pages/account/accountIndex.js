var ZuesAccountIndex = function() {
	// Handle Select2 Dropdowns
//	var handleSelect2 = function() {
//		if (jQuery().select2) {
//			$('.select2me').select2({
//				placeholder : "Select",
//				allowClear : true
//			});
//		}
//	}

	var createOrUpdate = function() {
		urls = $("#setOptionUrl").val() + "createOrUpdate"
		$.ajax({
			url : urls,
			type : "POST",
			data : $("#IndexForm").serialize(),
			dataType : "json",
			success : function(result) {
				$.cookie.json = true;
				if (result.success) {
					$.cookie('action-message', {
						action : "success",
						message : result.detail
					});
				} else {
					$.cookie('action-message', {
						action : "error",
						message : result.detail
					});
				}
				window.location.href="/account/list";
			}
		});
	}

	return {
		init : function() {
			handleSelect2();

			$("#btnConfirm").click(createOrUpdate);
		}
	}
}();

$(function() {
	ZuesAccountIndex.init();
})