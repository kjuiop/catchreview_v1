/**
 * @param options
 * @returns {boolean}
 * @author park-pro
 *
 * @example
 * <input type="text" class="form-control eventDate" name="eventDate">
 * <input type="hidden" class="form-control" name="startTarget">
 *
 * $(".eventDate").multiDateRangePickerInit({
 *      target: $(".eventTarget"),
 *      startTarget: $(".eventTarget").find($("input[name='startTarget']"))
 * });
 *
 */
function singleDateRangePickerWithTimeInit(options) {
    if (typeof (options) != "object") {
        return false;
    }

    var target;
    if (typeof (options.targetId) === "string") {
        target = $("#" + options.targetId);
    } else if (typeof (options.target) === "object") {
        target = options.target;
    } else {
        return false;
    }
    var st_dt;
    if (typeof (options.startName) === "string") {
        st_dt = $("input[name='" + options.startName + "']");
    } else if (typeof (options.startTarget) === "object") {
        st_dt = options.startTarget;
    } else {
        return false;
    }
    var timeIncrement = 1;
    var format = 'YYYY-MM-DD HH:mm';
    var formatT= 'YYYY-MM-DDTHH:mm';
    if (typeof (options.daterangepicker) == "object") {
        if (options.daterangepicker.timePickerIncrement !== undefined) {
            timeIncrement = options.daterangepicker.timePickerIncrement;
        }
        if (options.daterangepicker.format !== undefined) {
            format = options.daterangepicker.format;
        }
        if (options.daterangepicker.formatT !== undefined) {
            formatT = options.daterangepicker.formatT;
        }
    }
    target.daterangepicker({
        startDate: moment(),
        endDate: moment(),
        timePicker: true,
        timePicker24Hour: true,
        singleDatePicker: true,
        timePickerIncrement: timeIncrement,
        format: 'YYYY-MM-DD HH:mm',
        showDropdowns: true,
        showWeekNumbers: false,
        opens: 'right',
        locale: {
            applyLabel: 'Apply',
            cancelLabel: 'Clear',
            fromLabel: 'From',
            toLabel: 'To',
            daysOfWeek: ['일', '월', '화', '수', '목', '금', '토'],
            monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
            firstDay: 1
        },
        autoUpdateInput: true
    });

    var setShowDateRange = function () {
        var targetStartDate = target.data('daterangepicker').startDate.format(format);
        var startDate = target.data('daterangepicker').startDate.format(formatT);
        target.val(targetStartDate);
        st_dt.val(startDate);
    };

    //init search date start
    if (st_dt.val().length > 0) {
        target.data("daterangepicker").setStartDate(moment(st_dt.val(), formatT));
        setShowDateRange();
    } else {
        target.val('');
    }

    //date range picker apply event
    target.on('apply.daterangepicker', function (e, picker) {
        setShowDateRange();
    });

    //date range picker cancel event
    target.on('cancel.daterangepicker', function (e, picker) {
        $(this).val('');
        st_dt.val('');
    });
}


const checkNullOrEmptyValue = function (parameter) {
    if (parameter === null || parameter === '' || parameter === undefined || parameter === 'null') return false;
    return true;
};

const convertNullOrEmptyValue = function (parameter) {
    if (parameter === null || parameter === '' || parameter === undefined || parameter === 'null') return '';
    return parameter;
};

const convertNullOrZero = function (parameter) {
    if (parameter === null || parameter === '' || parameter === undefined || parameter === 'null') return 0;
    return parameter;
};

const isModify = function($form, id) {
    if ($form.find('input[name=' + id + ']').val() === null || $form.find('input[name=' + id + ']').val() === '') return false;
    return true;
};

const pagination = function(page) {
    const $frmSearch = $("form[name='frmSearch']");
    $frmSearch.find("input[name='page']").val(page);
    $frmSearch.submit();
};

var searchCheck = function(searchCodeObj, searchDto) {
    if (searchDto === null || searchDto === "") return;

    var resetYn = searchDto.reset;

    if(resetYn === 'Y') { return false; }

    for(var i=0; i<searchCodeObj.length; i++){
        if (searchCodeObj[i].searchCode != null) {

            var $chkCode = $(":checkbox[name=" + searchCodeObj[i].codeName + "]");
            if (searchCodeObj[i].allCheckId !== '') {
                $(searchCodeObj[i].allCheckId).iCheck('uncheck');
                if ($chkCode.length === searchCodeObj[i].searchCode.length) {
                    $(searchCodeObj[i].allCheckId).iCheck('check');
                }
            }


            $chkCode.each(function (idx, chk) {
                var $checkbox = $(chk);
                $(searchCodeObj[i].searchCode).each(function(idx, contentType) {
                    if ($checkbox.val() === contentType) {
                        $checkbox.iCheck('check');
                    }
                });
            });
        }
    }
};

var selectedChkAll = function(e) {
    e.preventDefault();

    var $this = $(this);
    var childenClass = $this.data('children');
    var checked = $this.prop('checked');
    if (checked) {
        $(':checkbox[name=' + childenClass + ']').iCheck('check');
    } else {
        $(':checkbox[name=' + childenClass + ']').iCheck('uncheck');
    }

};

var selectedChkBox = function(e) {
    e.preventDefault();

    if($(this).attr('class') === 'chkAll') return;

    var $chkAll;
    var childrenName = $(this).attr('name');

    $(".chkAll").each(function() {
        if(childrenName === $(this).data("children")) $chkAll = $(this);
    });

    if ($(':checkbox[name=' + childrenName + ']').length === $(':checkbox[name=' + childrenName + ']:checked').length) {
        $chkAll.prop('checked', 'checked');
    } else {
        $chkAll.prop('checked', false);
    }
    $chkAll.iCheck('update');
};

var closeModal = function(e) {
    e.preventDefault();

    $('.close').trigger('click');
};

var onlyNumberKeyEvent = function (options) {
    if (typeof (options) !== 'object') return false;

    var option = {};
    option.className = "only-number";
    option.formId = "";

    $.extend(options, option);

    var target = "";
    if (option.formId === "") {
        target = $("." + option.className);
    } else {
        target = $("." + option.className, $("#" + option.formId));
    }

    target.each(function () {
        $(this).unbind("keydown").keydown(function (e) {
            // Allow: backspace, delete, tab, escape, enter, and, -, .
            if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 189, 190]) !== -1 ||
                // Allow: Ctrl+A
                (e.keyCode == 65 && e.ctrlKey === true) ||
                // Allow: home, end, left, right
                (e.keyCode >= 35 && e.keyCode <= 39)) {
                return;
            }
            // Ensure that it is a number and stop the keypress
            if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
                e.preventDefault();
            }
        });
    });

    target.keyup(function (e) {
        var inputValue = $(this).val();
        if (e.keyCode == 8) return;
        $(this).val(onlyNumber(inputValue));
    });
};

var checkEmailValidCheck = function (email) {
    var reg = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    return reg.test(email)
};

var checkMobile = function (p) {
    p = p.split('-').join('');
    var regPhone = /^((01[0|1|6|7|8|9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})$/;
    return regPhone.test(p);
};

//비밀번호 유효성 검사
var passwordValidCheck = function (password) {
    var reg = /^(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[!"#$%&()*+,./:;<=>?@[\]\'\-\^_`{|}~-]).{8,12}$/;
    var reg2 = /([a-zA-Z0-9])\1{3,}/g;
    var reg3 = /([!"#$%&()*+,./:;<=>?@[\]\'\-\^_`{|}~-])\1{3,}/;
    return reg.test(password) && !reg2.test(password) && !reg3.test(password)
};

var ajaxErrorFieldByText = function (response) {
    const errorFields = response.responseJSON;

    var $field, error;
    $.each(errorFields, function(idx, error){
        $field = $('#'+ error['field']);

        if ($field && $field.length > 0){
            $field.siblings('.error-message').remove();
            drawErrorMessage($field, error.defaultMessage);
        }
    });
};

var ajaxErrorFieldByModal = function (response) {
    const errorFields = response.responseJSON;

    var $field, error;
    $.each(errorFields, function(idx, error){
        $field = $('#'+ error['field']);

        if ($field && $field.length > 0){
            $field.siblings('.error-message').remove();
            oneBtnModal(error.defaultMessage);
        }
    });
};

var jQueryErrorField = function (errorList) {
    console.log("errorList", errorList);
    var $field;
    $.each(errorList, function(idx, error){
        $field = $(error.element);

        if ($field && $field.length > 0){
            $field.siblings('.error-message').remove();
            drawErrorMessage($field, error.message);
        }
    });
};

var drawErrorMessage = function($field, errorMsg) {
    $field.after('<small class="error-message text-small text-danger">' + errorMsg + '</small>');
}

var drawSuccessMessage = function($field, errorMsg) {
    $field.after('<small class="error-message text-small text-blue">' + errorMsg + '</small>');
}
