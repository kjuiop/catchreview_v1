/**
 * Attachment JS
 * @author Jake
 * @date 20/04/04
 */

function initialization($form) {
	var $file = $form.find('#multipartFile'),
		$files = $form.find('#multipartFiles');
	
	$file.val('');
	$files.val('');
}

function ajaxForm($form, uploadSuccess) {
	$form.ajaxForm({
		beforeSend: function () {
			$("body").spin("modal");
		},
		uploadProgress: function (event, position, total, percentComplete) {},
		success: function () {},
		complete: uploadSuccess,
		error: function () {}
	});
}

// function documentUpload(options) {
//
// 	var $form = $("form[name='multipartFileUpload']");
//
// 	$.ajaxSetup({
// 		dataType: "json",
// 		beforeSend: function () {
// 			$("body").spin("modal");
// 		},
// 		complete: function () {
// 			$("body").spin("modal");
// 		},
// 		error: function (jqxhr, textStatus, errorThrown) {
// 		}
// 	});
//
// 	/**
// 	 * init
// 	 */
// 	initialization($form);
// 	var	$inputFile = $form.find('#multipartFile');
// 	if (options !== null && options.multiple !== undefined && options.multiple) $inputFile = $form.find('#multipartFiles');
// 	if (options !== null && options.accept !== undefined) $inputFile.attr('accept', options.accept);
// 	if (options !== null && options.identity !== undefined) $form.find('input[name=identity]').val(options.identity);
// 	if (options !== null && options.position !== undefined) $form.find('input[name=position]').val(options.position);
// 	if (options !== null && options.floor !== undefined) $form.find('input[name=floor]').val(options.floor);
//
// 	var uploadSuccess = function(responseText, statusText) {
// 		$("body").spin("modal");
// 		if (statusText === 'success') {
// 			var responseStatus = responseText.rs_st;
// 			var $file = $("input:file", $form);
// 			if (responseStatus === '1') return false;
// 			var data = responseText.responseJSON;
// 			if (typeof(options.callback) === 'function') options.callback(data, options);
// 			$file.val('');
// 			$file.attr("accept", "");
// 			$form.find('input[name=identity]').val('');
// 			$form.find('input[name=position]').val('');
// 			$form.find('input[name=floor]').val('');
// 		}
// 	};
//
// 	ajaxForm($form, uploadSuccess);
//
// 	$inputFile.one("change", function(e) {
// 		e.preventDefault();
// 		// 바이트 기준 1048576(Byte) = 1024(KB) = 1(MB)
//
// 		/** Migration 작업으로 인하여 파일용량 체크 주석
// 		var fileSize = document.getElementById("multipartFile").files[0].size;
//
// 		// 3MB
// 		if (fileSize > 3145728) {
// 			oneBtnModal(i18nProperty('alert.file.upload'));
// 			return false;
// 		}
// 		 **/
//
// 		$form.submit();
// 	});
//
// 	/**
// 	 * Hidden input File Button Click.
// 	 */
// 	$inputFile.click();
// }
