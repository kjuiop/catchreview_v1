/**
 * Attachment JS
 * @author JAKE
 * @date 21.11.14
 */

function initialization($form) {
	const 	$file 	= $form.find('input[name=file]'),
			$files 	= $form.find('#files');

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

function documentUpload(options) {
	console.log("1")
	const $form = $("form[name='frmUpload']");

	$.ajaxSetup({
	    dataType: "text",
	    beforeSend: function () {
	        $("body").spin("modal");
	    },
	    complete: function () {
	        $("body").spin("modal");
	    },
	    error: function (jqxhr, textStatus, errorThrown) {
	    }
	});

	/**
	 * init
	 */
	initialization($form);
	let	$inputFile = $form.find('#file');
	if (options !== null && options.multiple !== undefined && options.multiple) $inputFile = $form.find('#files');
	if (options !== null && options.accept !== undefined) $inputFile.attr('accept', options.accept);

	const uploadSuccess = function(responseText, statusText) {
		console.log("2")
		$("body").spin("modal");
		if (statusText === 'success') {
			let data = responseText;
			if (typeof(options.callback) === 'function') options.callback(data);
			$inputFile.val('');
			$inputFile.attr("accept", "");
		}
	};

	$inputFile.one("change", function(e) {
		e.preventDefault();
		ajaxForm($form, uploadSuccess);

		// 바이트 기준 1048576(Byte) = 1024(KB) = 1(MB)
		const fileSize = document.getElementById("file").files[0].size;
		const fileType = document.getElementById("file").files[0].type.split('/');

		//3MB
		if (fileType[0] === 'image' && (fileType[1] === 'png' || fileType[1] === 'jpg' || fileType[1] === 'jpeg') && fileSize > 3145728) {
			oneBtnModal("이미지는 최대 3MB 용량을 넘기지 못합니다.");
			return false;
		}

		$form.submit();
	});

	/**
	 * Hidden input File Button Click.
	 */
	$inputFile.click();
}

function documentExcelUpload(options) {

	var $form = $("form[name='multipartExcelFileUpload']");

	$.ajaxSetup({
		dataType: "json",
		beforeSend: function () {
			$("body").spin("modal");
		},
		complete: function () {
			$("body").spin("modal");
		},
		error: function (jqxhr, textStatus, errorThrown) {
		}
	});

	/**
	 * init
	 */
	initialization($form);
	var	$inputFile = $form.find('input[name=file]');
	if (options !== null && options.multiple !== undefined && options.multiple) $inputFile = $form.find('#files');

	var uploadSuccess = function(responseText, statusText) {
		$("body").spin("modal");
		if (statusText === 'success') {
			var responseStatus = responseText.rs_st;
			var $file = $("input:file", $form);
			if (responseStatus === '1') return false;
			var data = responseText.responseJSON;
			if (typeof(options.callback) === 'function') options.callback(data, options);
			$file.val('');
		}
	};

	$inputFile.one("change", function(e) {
		e.preventDefault();
		ajaxForm($form, uploadSuccess);
		$form.submit();
	});

	/**
	 * Hidden input File Button Click.
	 */
	$inputFile.click();
}
