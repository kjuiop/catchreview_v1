var commonModal = function(options) {
	var $modal = $("#commonModal"),
		$title = $modal.find("#commonModalTitle"),
		$contents = $modal.find("#commonModalContent"),
		$btnCancel = $modal.find(".commonModalClose"),
		$btnSubmit = $modal.find("#commonModalConfirm");

	if(typeof(options) !== 'object') {
		return false;
	}
	
	var option = {
		title: 'Message',
		submitText: '확인',
		cancelText: '취소',
		onlySubmit: false,
		autoClose: false
	};
	
	$.extend(option, options);
	
	this.modalInit = function() {
		$title.text(option.title);
		$contents.html(option.contents);
		$btnCancel.text(option.cancelText);
		$btnSubmit.text(option.submitText);
		$btnCancel.show();
		$btnSubmit.show();
		
		if(option.onlySubmit) {
			$btnCancel.hide();
		}
		
		if(option.autoClose) {
			$btnCancel.hide();
			$btnSubmit.hide();
		}
	};
	
	this.modalReset = function() {
		$title.text("");
		$contents.html("");
		$btnCancel.find("span").text("Confirm");
		$btnSubmit.find("span").text("Cancel");
	};
	
	var bPopupOption = {};
	bPopupOption.opacity = 0.80;
	bPopupOption.onOpen = function() {
		modalInit();
	};
	bPopupOption.modalClose = false;
	
	if(option.autoClose) {
		bPopupOption.autoClose = 1000;
		if(typeof(option.submit) === "function") {
			bPopupOption.onClose = function() {
				option.submit();
			}
		}
	}
	
	var bPopup = $modal.bPopup(bPopupOption);
	
	$btnSubmit.unbind("click").click(function(e) {
		e.preventDefault();
		if(typeof(option.submit) === 'function') {
			option.submit();
		}
		modalReset();
		bPopup.close();
	});

	$btnCancel.unbind("click").click(function(e) {
		e.preventDefault();
		if(typeof(options.cancel) === "function") {
			option.cancel();
		}
		modalReset();
		bPopup.close();
	});
};

var oneBtnModal = function(message, callback) {
    commonModal({
        contents: message ? message : "Error",
        submit: callback,
        onlySubmit: true
    });
};

var twoBtnModal = function(message, callback) {
    commonModal({
        contents: message ? message : "Error",
        submit: callback
    });
};

var commonErrorModal = function(message, callback, onlySubmit) {
	commonModal({
		contents: message ? message : "Error",
		submit: callback,
		cancel: callback,
		onlySubmit: onlySubmit ? onlySubmit : false
	});
};
